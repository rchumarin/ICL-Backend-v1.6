package ru.icl.test.filters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestParam;
import ru.icl.test.dao.Factory;
import ru.icl.test.dao.MessageDao;
import ru.icl.test.dao.UserDao;
import ru.icl.test.db.JDBC;
import ru.icl.test.entity.Message;
import ru.icl.test.entity.User;

public class MyFilter implements Filter {

    private FilterConfig filterConfig = null;
    
    private boolean active = false;
    
    public MyFilter() {
    }    
        
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain)
            throws IOException, ServletException {
    
        /* 
        Структура нашего JSON объекта:
        { id_key : {msg_key : [message], 
                       name_Key: name} 
        }
        
        Cтруктура GSON:
        { id_key : [message] }
        
        где: 
            id_key - сессия клиента           
            name - имя клиента
            message - сообщения клиента (массив)                    
        */
                                        
        try {            
            HttpServletRequest request = (HttpServletRequest) req;             
            HttpSession session = request.getSession(true);                    
            String id = session.getId();
            String name = (String) session.getAttribute("name");                                    
            String msg = request.getParameter("msg");            
            
            session.setAttribute("name", name);            

            request.getServletContext().setAttribute("active", active);                        
            if(active) { //active - параметр фильтра со значением true
                //Обертка в JSON                      
                JSONObject jsonOb = (JSONObject) request.getServletContext().getAttribute("sessionMap");
                if (jsonOb==null) {
                    jsonOb = new JSONObject();
                }                  
                JSONArray jsonArr;            
                if (session.isNew()) {                
                    jsonArr = new JSONArray();
                } else { 
                    jsonArr = (JSONArray)session.getAttribute("jmessage");                
                }
                jsonArr.add(msg);  
                session.setAttribute("jmessage", jsonArr);                            
                JSONObject jsonName = new JSONObject();
                jsonName.put("name", name);
                jsonName.put("msg", jsonArr);
                jsonOb.put(id, jsonName);    
                request.getServletContext().setAttribute("sessionMap", jsonOb);                                                                
            } else { //Обертка в GSON                        
                Map<String, List> sessionMap = (HashMap<String,List>)request.getServletContext().getAttribute("sessionMap");        
                if (sessionMap==null) {
                    sessionMap = new HashMap<String, List>();
                }       
                ArrayList<String> listMsg;    

                if (session.isNew()) {
                    listMsg = new ArrayList<>();            
                } else { 
                    listMsg = (ArrayList<String>) session.getAttribute("message");            
                }                
                listMsg.add(msg);            
                session.setAttribute("message", listMsg);
                sessionMap.put(id, listMsg);

                request.getServletContext().setAttribute("sessionMap", sessionMap);
                
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String jstring = gson.toJson(sessionMap);
                request.getServletContext().setAttribute("jstring", jstring); 
            }             
            //JDBC jdbc = new JDBC(session, id, name, msg);
            chain.doFilter(req, res);
            
            //Hibernate : begin           
            Factory factory = Factory.getInstance();
            UserDao userDao = factory.getUserDao();
            MessageDao messageDao = factory.getMessageDao();        
            User user = new User(id);
            user.setFname(name);
            if(session.isNew()) userDao.addUser(user);
            Message message = new Message();        
            message.setMessage(msg);
            message.setUser(user);
            messageDao.addMessage(message);           
            //Hibernate : end
        
        } catch (IOException io) {
            System.out.println ("IOException raised");
        } catch (ServletException se) {
            System.out.println ("ServletException raised");
        } catch (SQLException ex) {
            Logger.getLogger(MyFilter.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }                               
    
    public void destroy() {        
    }

    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;  
        String act = filterConfig.getInitParameter("active"); 
        if (act != null) 
          active = (act.toUpperCase().equals("TRUE")); 
    } 
}

