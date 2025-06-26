<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Booking Confirmation</title>
</head>
<body>
    <h2>Booking Confirmation</h2>

    <% 
        String flightName = (String) request.getAttribute("flight_name");
        String source = (String) request.getAttribute("source");
        String destination = (String) request.getAttribute("destination");
        Double price = (Double) request.getAttribute("price"); // Fetch price

        if (flightName != null) { 
    %>
        <p><strong>Flight Name:</strong> <%= flightName %></p>
        <p><strong>From:</strong> <%= source %></p>
        <p><strong>To:</strong> <%= destination %></p>
        <p><strong>Price:</strong> $<%= price %></p>
    <% 
        } else { 
    %>
        <p>Booking details not available. Please try again.</p>
    <% 
        } 
    %>

    <a href="searchFlights.jsp">Book Another Flight</a>
</body>
</html>
