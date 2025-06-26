<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*, com.mycompany.flightbookingsystem.db.DBConnection" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Flights</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            margin: 0;
            padding: 0;
            background: url('https://img.freepik.com/premium-photo/world-landmarks-famous-monuments-collage-with-cloud-background_880100-2480.jpg?w=1380') no-repeat center center/cover;
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            color: #333;
        }
        .overlay {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.5);
            z-index: 1;
        }
        .card {
            background: rgba(255, 255, 255, 0.9);
            border-radius: 15px;
            padding: 20px;
            width: 500px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            z-index: 2;
        }
        .card h2 {
            font-size: 2rem;
            font-weight: 600;
            margin-bottom: 20px;
            text-align: center;
            color: #007bff;
        }
        .flight-icon {
            font-size: 60px;
            color: #007bff;
            margin-bottom: 10px;
        }
        .form-control {
            border-radius: 10px;
            padding: 10px;
            margin-bottom: 15px;
        }
        .btn-primary {
            background: linear-gradient(135deg, #007bff, #0056b3);
            border-radius: 10px;
            width: 100%;
            color: white;
            font-size: 1rem;
        }
        .btn-primary:hover {
            background: linear-gradient(135deg, #0056b3, #007bff);
        }
        footer {
            background: rgba(255, 255, 255, 0.9);
            text-align: center;
            padding: 10px;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>
    <div class="overlay"></div>

    <div class="card text-center">
        <i class="fas fa-plane flight-icon"></i>
        <h2 class="text-primary mb-4">Search Flights</h2>
        
        <form action="searchFlights.jsp" method="post">
            <div class="mb-3">
                <label class="form-label">From:</label>
                <input type="text" name="source" class="form-control" placeholder="Enter departure city" required>
            </div>
            <div class="mb-3">
                <label class="form-label">To:</label>
                <input type="text" name="destination" class="form-control" placeholder="Enter destination" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Departure Date:</label>
                <input type="date" name="departureDate" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary">Search Flights</button>
        </form>
    </div>

    <!-- Display Available Flights -->
    <div class="card text-center mt-4">
        <h3>Available Flights</h3>
        <%
            String source = request.getParameter("source");
            String destination = request.getParameter("destination");

            if (source != null && destination != null) {
                try {
                    Connection con = DBConnection.getConnection();
                    PreparedStatement ps = con.prepareStatement("SELECT * FROM flights WHERE source=? AND destination=?");
                    ps.setString(1, source);
                    ps.setString(2, destination);
                    ResultSet rs = ps.executeQuery();
                    
                    while (rs.next()) {
        %>
                        <p>Flight: <%= rs.getString("flight_name") %> | Price: <%= rs.getString("price") %></p>
                        <form action="bookFlight.jsp" method="get">
                            <input type="hidden" name="flight_name" value="<%= rs.getString("flight_name") %>">
                            <input type="hidden" name="source" value="<%= rs.getString("source") %>">
                            <input type="hidden" name="destination" value="<%= rs.getString("destination") %>">
                            <input type="hidden" name="price" value="<%= rs.getString("price") %>">
                            <button type="submit" class="btn btn-primary">Proceed to Booking</button>
                        </form>


        <%
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        %>
    </div>
    

    <footer>
        <p class="mb-0">&copy; 2025 Flight Booking. <a href="#">Privacy Policy</a> | <a href="#">Terms of Service</a></p>
    </footer>

</body>
</html>
