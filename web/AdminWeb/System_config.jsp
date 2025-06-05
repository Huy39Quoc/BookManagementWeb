<%-- 
    Document   : System_config
    Created on : Jun 4, 2025, 8:10:57 AM
    Author     : ChanRoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Core.Interfaces.IConfig" %>
<%@ page import="Core.Entities.Config" %>
<%@ page import="dao.ConfigDAO" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>System Config</title>
        <link rel="stylesheet" type="text/css" href="Style/AdminStyle.css">
    </head>
    <body>
        <div class="header">
            <h1>System Configuration</h1>
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
        <div class="Config">
            <table class="Config_Table">
                <tr>
                    <th>Key</th>
                    <th>Value</th>
                    <th>Description</th>
                </tr>
                <%
                IConfig configDAO = new ConfigDAO();
                ArrayList<Config> getConfig = configDAO.configList();
                for(Config data: getConfig){
                %>
                <tr>
                    <td><%=data.getKey()%></td>
                    <td><%=data.getValue()%></td>
                    <td><%=data.getDescription()%></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
    </body>
</html>
