package ru.icl.test.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.icl.test.dao.Factory;
import ru.icl.test.dao.MessageDao;

@WebServlet(name = "DeleteMessage", urlPatterns = {"/delete"})
public class DeleteMessage extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            
            Factory factory = Factory.getInstance();
            MessageDao messageDao = factory.getMessageDao();  
            messageDao.deleteMessage(messageDao.getMessage(id));
        } catch (SQLException ex) {
            Logger.getLogger(DeleteMessage.class.getName()).log(Level.SEVERE, null, ex);
        } 
        response.sendRedirect(request.getContextPath()+"/allmessage");
    }
    
}