<%-- 
    Document   : BookData
    Created on : Jun 5, 2025, 8:36:30 PM
    Author     : ChanRoi
--%>

<%@page import="dao.BookDAO"%>
<%@page import="Core.Interfaces.IBook"%>
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
            <h1>Book Data</h1>
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
        
        <form action="AdminTransactionServlet" method="post" class="search">
            <input type="hidden" name="function" value="SearchBook">
            <input type="text" name="SearchBook">
            <input type="submit" value="Search">
        </form>
        
        <form action="AdminTransactionServlet" method="post" class="search">
            <input type="hidden" name="function" value="AddBook">
            <input type="submit" value="Add Book">
        </form>
        
        <%
           ArrayList<Book> getSearchBook = (ArrayList<Book>) request.getAttribute("BookList");
           String searchTitle = (String) request.getAttribute("SearchTitle");
           if(getSearchBook == null){
               IBook book = new BookDAO();
               getSearchBook = book.getBookList();
            }
           if (getSearchBook != null){
           if(searchTitle != null){
        %>
        <h1><%= searchTitle %></h1>
        <%
            }
           if (!getSearchBook.isEmpty()) {
        %>
        
        <table class="ListBook">
            <tr>
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
                <td><%= book.getTitle() %></td>
                <td><%= book.getAuthor() %></td>
                <td><%= book.getISBN() %></td>
                <td><%= book.getCategory() %></td>
                <td><%= book.getPublishedYear() %></td>
                <td><%= book.getTotalCopies() %></td>
                <td><%= book.getAvailableCopies() %></td>
                <td><%= book.getStatus() %></td>
                <td>
                    <form action="AdminTransactionServlet" method="post">
                        <input type="hidden" name="function" value="Edit">
                        <input type="hidden" name="id" value="<%= book.getId() %>">
                        <input type="submit" value="Edit">
                    </form>
                </td>
                <td>
                    <form action="AdminTransactionServlet" method="post" onsubmit="return confirm('Are you sure you want to delete this book?');">
                        <input type="hidden" name="function" value="Delete">
                        <input type="hidden" name="id" value="<%= book.getId() %>">
                        <input type="submit" value="Delete">
                    </form>
                </td>
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
