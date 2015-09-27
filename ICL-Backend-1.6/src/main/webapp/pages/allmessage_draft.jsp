<%@page import="ru.icl.test.entity.Message"%>
<%@page import="ru.icl.test.entity.MessageList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style_main.css" rel="stylesheet" type="text/css">
         <title>Онлайн ЧАТ</title>
    </head>
    
    <body>
        <div class="container">        
            <div class="logo">
                <a href="main.jsp"><img src="../images/chat_.png" width="86" height="87" alt="" name="logo" /></a>
            </div>
            <div class="descr">
                <h3>Онлайн ЧАТ</h3>
            </div>
            <div class="welcome">
                <h5>Добро пожаловать, <%=session.getAttribute("login")%></h5>
                <h5 class="sessia">Ваш id: <%=session.getId()%></h5>
                <h6><a href="main.jsp">< Назад&emsp;</a>
                    <a href="index.jsp">Выход</a>
                </h6>
            </div>
            <jsp:useBean id="messageList" class="ru.icl.test.entity.MessageList" scope="application"/>                   
            <div class="big_column">                           
            <div>
                <table>
                    <tr>
                        <th>id</th> 
                        <th>Users</th> 
                        <th>Messages</th>
                        <th></th>
                    </tr>                                                                       
                    <%                        
                        for (Message message : messageList.getMessageList()) {                                                         
                    %>
                    <tr>
                        <td><%=message.getId()%></td>
                        <td><%=message.getUser().getClientid()%></td>
                        <td><%=message.getMessage()%></td>
                        <td>
                            <a href="<%=request.getContextPath()%>/delete?id=<%=message.getId()%>">
                                <p style="text-align: center;"><img src="../images/x.gif" width="26" height="26" alt="Delete" name="logo" /></p>
                            </a>
                        </td>  
                    </tr>                    
                    <%}%>                    
                    
                    <%--                                        
                    Factory factory = Factory.getInstance();
                    MessageDao messageDao = factory.getMessageDao();  
                    try {
                        List<Message> mList = messageDao.getMessages();                                    
                        for(Message message : mList) {                                                                                     
                    %>
                    <tr>
                        <td><%=message.getId()%></td>
                        <td><%=message.getUser().getClientid()%></td>
                        <td><%=message.getMessage()%></td>
                    </tr>                                                            
                    <%
                        }
                    } catch (SQLException ex) {
                        System.out.println("Ошибка: " + ex);
                    }      
                    --%>                    
                </table>
            </div>                                                                   
        </div>                     
    </div>                                        
    <div class="footer">© 2015 ICL. Test project</div>
    </body>
</html>
