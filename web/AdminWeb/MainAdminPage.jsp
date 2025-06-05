<%-- 
    Document   : MainAdminPage
    Created on : Jun 5, 2025, 12:27:11 AM
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
       <div class="Menu">
            <a href="AdminDashboard?file=Manage">Manage Book</a>
            <a href="AdminDashboard?file=Transaction">Transaction</a>
            <a href="AdminDashboard?file=Access">User Access</a>
            <a href="AdminDashboard?file=Overdue">Overdue Book</a>
            <a href="AdminDashboard?file=Inventory">Inventory</a>
            <a href="AdminDashboard?file=Statistic">Statistic</a>
            <a href="AdminDashboard?file=System">System Configuration</a>
       </div>

    </body>
</html>
