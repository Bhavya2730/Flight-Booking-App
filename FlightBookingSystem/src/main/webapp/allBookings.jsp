<%@ page import="java.sql.*, javax.servlet.*, javax.servlet.http.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>My Bookings</title>
</head>
<body>
    <h2>My Bookings</h2>
    <table border="1">
        <tr><th>Flight Name</th><th>Source</th><th>Destination</th><th>Price</th></tr>
        <%
            
            if (session == null || session.getAttribute("user") == null) {
                response.sendRedirect("login.html");
                return;
            }

            String userEmail = (String) session.getAttribute("user");

            try {
                Connection con = com.mycompany.flightbookingsystem.db.DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(
                    "SELECT f.flight_name, f.source, f.destination, f.price FROM bookings b " +
                    "JOIN flights f ON b.flight_id = f.id WHERE b.user_email = ?"
                );
                ps.setString(1, userEmail);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
        %>
                    <tr>
                        <td><%= rs.getString("flight_name") %></td>
                        <td><%= rs.getString("source") %></td>
                        <td><%= rs.getString("destination") %></td>
                        <td><%= rs.getString("price") %></td>
                    </tr>
        <%
                }
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<p>Error fetching bookings.</p>");
            }
        %>
    </table>
    <br>
    <a href="searchFlights.jsp">Search More Flights</a>
</body>
</html>
