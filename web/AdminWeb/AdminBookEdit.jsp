<%-- 
    Document   : AdminBookEdit
    Created on : Jun 7, 2025, 11:49:45 PM
    Author     : ChanRoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Core.Entities.Book"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit</title>
        <link rel="stylesheet" type="text/css" href="Style/AdminStyle.css">
    </head>
    <body>
        <div class="header">
            <h1>Edit</h1>
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
           Book data = (Book) request.getAttribute("BookData");
        %>
        <div class="Action">
            <form action="AdminBook" method="post">
                <input type="hidden" name="Action" value="Edit">
                <input type="hidden" name="id" value="<%= data.getId() %>">
                Title Book: <input type="text" name="title" value="<%= data.getTitle()%>"><br>
                Author: <input type="text" name="author" value="<%= data.getAuthor()%>"><br>
                ISBN: <input type="text" name="isbn" value="<%= data.getISBN()%>"><br>
                Category: <input type="text" name="category" value="<%= data.getCategory()%>"><br>
                Published Year: <input type="number" name="publishedYear" value="<%= data.getPublishedYear()%>" min="1000"><br>
                Total Copies: <input type="number" name="totalCopies" value="<%= data.getTotalCopies()%>" min="0"><br>
                Available Copies: <input type="number" name="availableCopies" value="<%= data.getAvailableCopies()%>" min="0"><br>
                Status: <select name="status" required>
                            <option value="active" selected>Active</option>
                            <option value="inactive">Inactive</option>
                        </select><br>
                <input type="submit" value="Edit">
            </form>
        </div>
    </body>
</html>
