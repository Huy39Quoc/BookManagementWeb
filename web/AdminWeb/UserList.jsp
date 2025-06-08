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
        <title>User List</title>
        <link rel="stylesheet" type="text/css" href="Style/AdminStyle.css">
    </head>
    <body>
        <div class="header">
            <h1>User List</h1>
            <p><a href="AdminDashboard?file=Logout">Logout</a></p>
        </div>
        
        <div class="Menu">
            <a href="AdminDashboard?file=Manage">Manage Book</a>
            <a href="AdminDashboard?file=Transaction">Transaction</a>
            <a href="AdminDashboard?file=Access">User Access</a>
            <a href="AdminDashboard?file=Overdue">Overdue Book</a>
            <a href="AdminDashboard?file=Inventory">Inventory</a>
            <a href="AdminDashboard?file=Statistic">Statistic</a>
            <a href="AdminDashboard?file=System">System Configuration</a>
        </div>
        
        <form action="AdminTransactionServlet" class="search">
            <input type="hidden" name="function" value="SearchUser">
            <input type="text" name="SearchUser">
            <input type="submit" value="Search">
        </form
        
        <%
            ArrayList<Account> getUserList = (ArrayList<Account>) request.getAttribute("getUser");
            String SearchTitle = (String) request.getAttribute("SearchTitle");
            if(getUserList == null){
               IUser users = new UserDAO();
               getUserList = users.getUserList();
            }
            if(SearchTitle != null){
        %>
            <h1><%= SearchTitle%></h1>
        <%
            }
            if (getUserList != null && !getUserList.isEmpty()){
        %>
        
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
                    for (Account user : getUserList){
                %>
                <tr>
                    <td><%= user.getId() %></td>
                    <td><%= user.getName() %></td>
                    <td><%= user.getEmail() %></td>
                    <td><%= user.getRole() %></td>
                    <td><%= user.getStatus() %></td>
                    <td>
                        <form action="AdminTransactionServlet" method="post">
                            <input type="hidden" name="function" value="<%= user.getStatus().equals("active") ? "Ban" : "Unban" %>" />
                            <input type="hidden" name="userId" value="<%= user.getId() %>" />
                            <input type="submit" value="<%= user.getStatus().equals("active") ? "Ban" : "Unban" %>" />
                        </form>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
            </table>
        </div>
    </body>
</html>
