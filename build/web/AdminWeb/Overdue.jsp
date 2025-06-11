<%-- 
    Document   : Overdue
    Created on : Jun 8, 2025, 6:20:15 PM
    Author     : ChanRoi
--%>

<%@page import="Core.Entities.BorrowRecord"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Core.Entities.Book"%>
<%@page import="dao.BookDAO"%>
<%@page import="Core.Interfaces.IBook"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Overdue</title>
        <link rel="stylesheet" type="text/css" href="Style/AdminStyle.css">
    </head>
    <body>
        <div class="header">
            <h1>Overdue</h1>
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
        
        <%
        IBook book = new BookDAO();
        ArrayList<BorrowRecord> getOverdue = book.Overdue();
        if(!getOverdue.isEmpty()){
        %>
        <div>
            <table class="Overdue">
                <tr>
                    <th>User</th>
                    <th>Book Title</th>
                    <th>Borrow Date</th>
                    <th>Due Date</th>
                    <th>Return Date</th>
                    <th>Status</th>
                </tr>
        <%
        for(BorrowRecord overdue: getOverdue){
        %>
        <tr>
        <td><%= overdue.getUserName()%></td>
        <td><%= overdue.getBookTitle()%></td>
        <td><%= overdue.getBorrowDate()%></td>
        <td><%= overdue.getDueDate()%></td>
        <td><%= overdue.getReturnDate() == null ? "Not return yet" : overdue.getReturnDate()%></td>
        <td><%= overdue.getStatus()%></td>
        </tr>
        <%
              }
        %>
        </table>
      </div>
        <%
            }
        %>
    </body>
</html>
