<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*, com.mycompany.flightbookingsystem.db.DBConnection" %>
<!DOCTYPE html>
<html>
<head>
    <title>Book a Flight</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            color: #333;
            background: url('https://media.istockphoto.com/id/1392494733/photo/woman-with-pink-suitcase-and-amerian-passport-standing-on-passengers-ladder-of-airplane.jpg?s=612x612&w=0&k=20&c=pQ9hqLIP1ara_xwzDTeud2jvrLqrJiL4LZJ0hewRhYM=') no-repeat center center/cover;
            position: relative;
            overflow: hidden;
        }

        /* Overlay to improve readability */
        .overlay {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.5); /* Semi-transparent black overlay */
            z-index: 1;
        }

        /* Card Styles */
        .card {
            background: rgba(255, 255, 255, 0.9); /* Semi-transparent white background */
            border-radius: 15px;
            padding: 20px;
            width: 100%;
            max-width: 600px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
            z-index: 2;
            text-align: center;
        }

        .card h2 {
            font-size: 2rem;
            font-weight: 600;
            margin-bottom: 20px;
            color: #007bff;
        }

        .flight-card {
            background: rgba(255, 255, 255, 0.95);
            border-radius: 10px;
            padding: 15px;
            margin-bottom: 15px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        .flight-card p {
            font-size: 1.1rem;
            margin: 10px 0;
        }

        .btn-primary {
            background: linear-gradient(135deg, #007bff, #0056b3);
            border: none;
            padding: 10px 20px;
            font-size: 1rem;
            border-radius: 10px;
            color: white;
            cursor: pointer;
            transition: background 0.3s ease;
        }

        .btn-primary:hover {
            background: linear-gradient(135deg, #0056b3, #007bff);
        }

        .form-select {
            border-radius: 10px;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
        }

        .form-select:focus {
            border-color: #007bff;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
        }

        .no-flights {
            font-size: 1.2rem;
            color: #555;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <!-- Overlay -->
    <div class="overlay"></div>

    <!-- Main Content -->
    <div class="card">
        <h2>Available Flights</h2>
        <%
            String source = request.getParameter("source");
            String destination = request.getParameter("destination");

            try {
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement("SELECT * FROM flights WHERE source=? AND destination=?");
                ps.setString(1, source);
                ps.setString(2, destination);
                ResultSet rs = ps.executeQuery();

                boolean flightsAvailable = false;

                while (rs.next()) {
                    flightsAvailable = true;
        %>
                    <div class="flight-card">
                        <p><strong>Flight:</strong> <%= rs.getString("flight_name") %></p>
                        <p><strong>Price:</strong> ₹<%= rs.getString("price") %></p>
                        <form action="BookFlightServlet" method="post">
                            <input type="hidden" name="flight_name" value="<%= request.getParameter("flight_name") %>">
                            <input type="hidden" name="source" value="<%= request.getParameter("source") %>">
                            <input type="hidden" name="destination" value="<%= request.getParameter("destination") %>">
                            <input type="hidden" name="price" value="<%= request.getParameter("price") %>">
                            <label for="seat_class">Select Class:</label>
                            <select name="seat_class" id="seat_class" class="form-control">
                                <option value="Economy">Economy</option>
                                <option value="Premium Economy">Premium Economy</option>
                                <option value="Business">Business</option>
                                <option value="First Class">First Class</option>
                            </select>
                            <button type="submit" class="btn btn-success mt-3">Confirm Booking</button>
                        </form>


                    </div>
        <%
                }

                if (!flightsAvailable) {
        %>
                    <p class="no-flights">No flights found for this route.</p>
        <%
                }
            } catch (Exception e) {
                e.printStackTrace();
        %>
                <p class="no-flights">An error occurred while fetching flights. Please try again later.</p>
        <%
            }
        %>
    </div>
</body>
</html>