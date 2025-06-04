<%-- 
    Document   : StatisticWeb
    Created on : Jun 4, 2025, 10:52:05 AM
    Author     : ChanRoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Core.Interfaces.IUser"%>
<%@page import="dao.UserDAO"%>
<%@page import="Core.Entities.Account"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Statistic</title>
        <link rel="stylesheet" type="text/css" href="Style/AdminStyle.css">
    </head>
    <body>
        <script src="Javascript/script.js"></script>
        <div class="UserList">
            <table class="Table_UserList">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Status</th>
                    <th>Action Button</th>
                </tr>
                <%
                IUser getUser = new UserDAO();
                ArrayList<Account> getUserList = getUser.getUserList();
                for(Account user: getUserList){
                %>
                <tr>
                    <td><%= user.getId()%></td>
                    <td><%= user.getName()%></td>
                    <td><%= user.getEmail()%></td>
                    <td><%= user.getRole()%></td>
                    <td><%= user.getStatus()%></td>
                    <td><button onclick="BanUser('<%= user.getId() %>', '<%= user.getStatus() %>', this)"><%= user.getStatus().equals("active") ? "Ban" : "Unban" %></button></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
    </body>
</html>
