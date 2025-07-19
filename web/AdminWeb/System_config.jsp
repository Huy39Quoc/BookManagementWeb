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
        <title>System Configuration</title>
        <link rel="stylesheet" type="text/css" href="Style/AdminStyle.css">
    </head>
    <body>
        <div class="header">
            <h1>System Configuration</h1>
            <p></p>
        </div>
        
       <div class="Menu">
           <p>Menu</p>
            <a href="AdminDashboard?file=Manage">Manage Book</a>
            <a href="AdminDashboard?file=Transaction">Transaction</a>
            <a href="AdminDashboard?file=Access">User Access</a>
            <a href="AdminDashboard?file=Request">Request</a>
            <a href="AdminDashboard?file=Overdue">Overdue Book</a>
            <a href="AdminDashboard?file=Inventory">Inventory</a>
            <a href="AdminDashboard?file=Statistic">Statistic</a>
            <a href="AdminDashboard?file=System">System Configuration</a>
            <a href="AdminDashboard?file=Logout">Logout</a>
       </div>
        
        <%
              IConfig configDAO = new ConfigDAO();
              ArrayList<Config> getConfig = configDAO.configList();
              String show = (String) request.getAttribute("ShowEdit");
              if(show == null){
                 show = "false";
            }
            if(show.equals("false")){
              if(getConfig != null && !getConfig.isEmpty()){
        %>
        <div class="Config">
            <table class="Config_Table">
                <tr>
                    <th>Key</th>
                    <th>Value</th>
                    <th>Description</th>
                    <th>Edit</th>
                </tr>
                <%
                for(Config data: getConfig){
                %>
                <tr>
                    <td><%=data.getKey()%></td>
                    <td><%=data.getValue()%></td>
                    <td><%=data.getDescription()%></td>
                    
                    <td><form action="AdminBook" method="post">
                    <input type="hidden" name="Action" value="Data">
                    <input type="hidden" name="id" value="<%= data.getId()%>">
                    <input type="submit" value="Edit">
                     </form></td>
                </tr>
                <%
                      }
                %>
            </table>
        </div>
            <%
                  }
                 }else{
            %>
            <div class="Config">
            <table class="Config_Table">
                <tr>
                    <th>Key</th>
                    <th>Value</th>
                    <th>Description</th>
                    <th>Save</th>
                </tr>
                <%
                for(Config data: getConfig){
                if(data.getId() == Integer.parseInt(request.getParameter("id"))){
                %>
                <tr>
                <td><%= data.getKey()%></td>
                <td><form action="AdminBook">
                    <input type="hidden" name="Action" value="Save">
                    <input type="hidden" name="id" value="<%= data.getId()%>">
                    <input type="text" name="Value" value="<%= data.getValue()%>">
                </td>
                <td><%= data.getDescription()%></td>
                <td>
                    <input type="submit" value="Save">
                    </form></td>
                </tr>
                <%
                    }else{
                      %>
                    <tr>
                    <td><%=data.getKey()%></td>
                    <td><%=data.getValue()%></td>
                    <td><%=data.getDescription()%></td>
                    
                    <td><form action="AdminBook" method="post">
                    <input type="hidden" name="Action" value="Data">
                    <input type="hidden" name="id" value="<%= data.getId()%>">
                    <input type="submit" value="Edit">
                     </form></td>
                </tr>
                
                      <%
                     }
                      }
                %>
            </table>
        </div>
            <%
                }
            %>
    </body>
</html>
