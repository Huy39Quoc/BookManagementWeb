<%-- 
    Document   : AdminStatistic
    Created on : Jun 7, 2025, 7:46:50 AM
    Author     : ChanRoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Core.Interfaces.IStatistic"%>
<%@page import="dao.CheckStatistic"%>
<%@page import="Core.Entities.Statistic"%>"
<%@page import="Core.Entities.Book"%>"
<%@page import="Core.Entities.Most5"%>"
<%@page import="Core.Entities.MonthBorrow"%>"
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Statistic</title>
        <link rel="stylesheet" type="text/css" href="Style/AdminStyle.css">
    </head>
    <body>
        <div class="header">
            <h1>Statistic</h1>
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
                IStatistic statistic = new CheckStatistic();
                Statistic getStatistic = statistic.getStatistic();
            %>
            
        <div>
            <table class="StatisticTable">
                <tr>
                    <th>Total Books</th>
                    <th>Total Users</th>
                    <th>Number of Currently Borrowed Books</th>
                    <th>Average Borrow Duration</th>
                </tr>
                <tr>
                    <td><%= getStatistic.getBook()%> Books</td>
                    <td><%= getStatistic.getUsers()%> Users</td>
                    <td><%= getStatistic.getBorrowedBook()%> Books</td>
                    <td><%= getStatistic.getDuration()%> Days</td>
                </tr>
            </table>
        </div>
                
                <%
                ArrayList<Most5> Most = (ArrayList<Most5>) getStatistic.getMostBorrowed();
                ArrayList<MonthBorrow> Month = (ArrayList<MonthBorrow>) getStatistic.getBorrowingStat();
                %>
                <div>
                    <h3>Top 5 books borrowed the most: </h3>
                    <table class="Most5">
                        <tr>
                          <th>No. </th>
                          <th>Book Title</th>
                          <th>Number of Borrow</th>
                        </tr>
                        <%
                        for(int i = 0; i < 5; i++){
                        %>
                        <tr>
                        <td><%= i + 1%></td>
                        <td><%= Most.get(i).getTitle()%></td>
                        <td><%= Most.get(i).getCount()%></td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
                </div>
                    
                
    </body>
</html>
