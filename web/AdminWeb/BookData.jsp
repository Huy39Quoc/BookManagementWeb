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
        <title>Transaction</title>
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
        
        <form action="AdminTransactionServlet" method="post" class="search">
            <input type="hidden" name="function" value="SearchBook">
            <input type="text" name="SearchBook">
            <input type="submit" value="Search">
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
                <div class="Action">
            <form action="AdminTransactionServlet" method="post">
                <input type="hidden" name="function" value="Add">
                Title Book: <input type="text" name="title" required><span style="color: red">*</span><br>
                Author: <input type="text" name="author" required><span style="color: red">*</span><br>
                ISBN: <input type="text" name="isbn" required><span style="color: red">*</span><br>
                Category: <input type="text" name="category"><br>
                Published Year: <input type="number" name="publishedYear" value="1000"><br>
                Total Copies: <input type="number" name="totalCopies" value="1" min="1"><br>
                Available Copies: <input type="number" name="availableCopies" value="1" min="1"><br>
                Status:
                <select name="status" required>
                    <option value="active" selected>Active</option>
                    <option value="inactive">Inactive</option>
                </select><br>
                <input type="submit" value="Add">
            </form>
        </div>
    </body>
</html>
