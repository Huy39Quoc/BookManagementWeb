<%-- 
    Document   : BookData
    Created on : Jun 5, 2025, 8:36:30 PM
    Author     : ChanRoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Core.Entities.Book"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Data</title>
    <link rel="stylesheet" type="text/css" href="Style/AdminStyle.css">
    </head>
    <body>
        <div class="header">
            <h1>Transaction</h1>
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
            
        <form action="AdminTransactionServlet" class="searchBook">
            <input type="hidden" name="function" value="Search">
            <input type="text" name="SearchBook">
            <input type="submit" value="Search">
        </form>
        
       <%
         ArrayList<Book> getSearchBook = (ArrayList<Book>) request.getAttribute("BookList");
         String searchTitle = (String) request.getAttribute("SearchTitle");

          if (getSearchBook != null && searchTitle != null) {
       %>
            <h1><%= searchTitle %></h1>

       <%
           if (!getSearchBook.isEmpty()) {
       %>
        <table class="ListBook">
          <tr>
            <th>ID</th>
            <th>Title</th> 
            <th>Author</th>
            <th>ISBN</th>
            <th>Category</th>
            <th>Published Year</th>
            <th>Total Copies</th>
            <th>Available Copies</th>
            <th>Status</th>
            <th>Edit</th>
            <th>Delete</th>
          </tr>
        <%
            for (Book book : getSearchBook) {
        %>
          <tr>
            <td><%= book.getId() %></td>
            <td><%= book.getTitle() %></td>
            <td><%= book.getAuthor() %></td>
            <td><%= book.getISBN() %></td>
            <td><%= book.getCategory() %></td>
            <td><%= book.getPublishedYear() %></td>
            <td><%= book.getTotalCopies() %></td>
            <td><%= book.getAvailableCopies() %></td>
            <td><%= book.getStatus() %></td>
            <td><button>Edit</button></td>
            <td><button>Delete</button></td>
          </tr>
        <%
            }
        %>
        </table>
        <%
            }
          }
        %>

    </body>
</html>
