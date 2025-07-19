<%-- 
    Document   : AddNewBook
    Created on : Jun 25, 2025, 12:42:13 PM
    Author     : ChanRoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Dashboard</title>
        <link rel="stylesheet" type="text/css" href="Style/AdminStyle.css">
    </head>
    <body>
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
                <form action="AdminTransactionServlet" method="post">
                    <input type="hidden" name="function" value="ReturnBack">
                    
                </form>
                <a href="AdminTransactionServlet">Return</a>
        </div>
    </body>
</html>
