<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="ru.icl.test.auth.Username" %>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Онлайн ЧАТ</title>
        <link href="../css/style_main.css" rel="stylesheet" type="text/css">
    </head>    
    <body>    
    <%
        request.setCharacterEncoding("UTF-8");                    
        if (request.getParameter("name") != null) {                
            session.setAttribute("name", request.getParameter("name"));
        }             
    %>           
    <div class="container">        
            <div class="logo">
                <a href="main.jsp"><img src="../images/chat_.png" width="86" height="87" alt="" name="logo" /></a>
            </div>
            <div class="descr">
                <h3>Онлайн ЧАТ</h3>
            </div>
            <div class="welcome">
                <h5>Добро пожаловать, <%=session.getAttribute("name")%>!</h5>
                <h5 class="sessia">Ваш id: <%=session.getId()%></h5>
                <h6><a href="../pages/allmsg.jsp">Все сообщения&emsp;</a>
                    <a href="../index.jsp">Выход</a>
                </h6>
            </div>
            <div class="search_form">
                <form name="search_form" method="GET" action="<%=request.getContextPath()%>/simple">
                    <input type="text" name="msg" size="135"/>
                    <input class="search_button" type="submit" value="Отправить"/>                                   
                </form>
            </div> 
            <div class="big_column">                           
            <div>
                <table>
                    <tr>
                        <th>id</th> 
                        <th>Users</th> 
                        <th>Messages</th>
                    </tr>                                                                                   
                </table>
            </div>                                                                   
        </div>                     
    </div>                                        
    <div class="footer">© 2015 ICL. Test project</div>
    </body>
</html>

