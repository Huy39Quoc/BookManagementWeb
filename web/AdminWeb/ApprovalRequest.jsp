<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Core.Entities.BookRequest"%>
<%@page import="Core.Interfaces.IRequest"%>
<%@page import="dao.RequestDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Approve Request</title>
        <link rel="stylesheet" type="text/css" href="Style/AdminStyle.css">
    </head>
    <body>
        <div class="header">
            <h1>Approve Request</h1>
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
        ArrayList<BookRequest> getRequest = (ArrayList<BookRequest>) request.getAttribute("requestSearch");
        if (getRequest == null) {
            IRequest reQuest = new RequestDAO();
            getRequest = reQuest.RequestBook();
        }

        if (getRequest != null && !getRequest.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    %>

    <div class="RequestList">
        <table class="Table_UserList">
            <tr>
                <th>User Name</th>
                <th>User Status</th>
                <th>Book Title</th>
                <th>Request Date</th>
                <th>Request Status</th>
                <th>Approve</th>
                <th>Reject</th>
            </tr>

            <%
                for (BookRequest book : getRequest) {
            %>
            <tr>
                <td><%= book.getUserName() %></td>
                <td><%= book.getUserStatus() %></td>
                <td><%= book.getBookTitle() %></td>
                <td><%= sdf.format(book.getRequestDate()) %></td>
                <td><%= book.getStatus() %></td>

                <%
                    String userStatus = book.getStatus();
                    if ("blocked".equalsIgnoreCase(userStatus)) {
                %>
                    <td colspan="2" style="color: red; text-align: center;">User is banned</td>
                <%
                    } else if ("pending".equalsIgnoreCase(book.getStatus())) {
                %>
                    <td>
                        <form action="AdminTransactionServlet" method="post">
                            <input type="hidden" name="function" value="Approved" />
                            <input type="hidden" name="id" value="<%= book.getId() %>" />
                            <input type="submit" value="Approve" />
                        </form>
                    </td>
                    <td>
                        <form action="AdminTransactionServlet" method="post">
                            <input type="hidden" name="function" value="Rejected" />
                            <input type="hidden" name="id" value="<%= book.getId() %>" />
                            <input type="submit" value="Reject" />
                        </form>
                    </td>
                <%
                    } else {
                %>
                    <td colspan="2" style="text-align: center;">Already <%= book.getStatus() %></td>
                <%
                    }
                %>
            </tr>
            <%
                }
            %>
        </table>
    </div>

    <%
        } else {
    %>
        <p style="margin: 20px;">No pending requests found.</p>
    <%
        }
    %>
</body>
</html>
