package com.mycompany.flightbookingsystem.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import com.mycompany.flightbookingsystem.db.DBConnection;
import java.util.logging.Logger;

@WebServlet("/SearchFlightsServlet")
public class SearchFlightsServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(SearchFlightsServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String source = request.getParameter("source").trim();
        String destination = request.getParameter("destination").trim();
        session.setAttribute("source", source);
        session.setAttribute("destination", destination);


        // Input validation
        if (source.isEmpty() || destination.isEmpty()) {
            response.sendRedirect("searchFlights.jsp?error=Please+provide+source+and+destination.");
            return;
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Start HTML output with Bootstrap for better styling
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Available Flights</title>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("<style>");
        out.println(".flight-card { border: 1px solid #ddd; border-radius: 10px; padding: 20px; margin-bottom: 15px; }");
        out.println(".flight-card:hover { box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body class='bg-light'>");
        out.println("<div class='container mt-5'>");
        out.println("<h2 class='text-center mb-4'>Available Flights from " + source + " to " + destination + "</h2>");

        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM flights WHERE source=? AND destination=?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, source);
                ps.setString(2, destination);
                try (ResultSet rs = ps.executeQuery()) {
                    boolean flightsAvailable = false;
                    while (rs.next()) {
                        flightsAvailable = true;
                        String flightName = rs.getString("flight_name");
                        double price = rs.getDouble("price");

                        // Display flight details in a card
                        out.println("<div class='flight-card'>");
                        out.println("<h4>" + flightName + "</h4>");
                        out.println("<p class='text-muted'>Price: ₹" + price + "</p>");
                        out.println("<form action='BookFlightServlet' method='post'>");
                        out.println("<input type='hidden' name='flight_name' value='" + flightName + "'>");
                        out.println("<input type='hidden' name='price' value='" + price + "'>");
                        out.println("<button type='submit' class='btn btn-primary'>Book Now</button>");
                        out.println("</form>");
                        out.println("</div>");
                    }

                    if (!flightsAvailable) {
                        out.println("<div class='alert alert-warning text-center'>No flights found for this route.</div>");
                    }
                }
            }
        } catch (Exception e) {
            logger.severe("Error fetching flights: " + e.getMessage()); // Log the error
            out.println("<div class='alert alert-danger text-center'>An error occurred while fetching flights. Please try again later.</div>");
        }

        out.println("<div class='text-center mt-4'>");
        out.println("<a href='searchFlights.jsp' class='btn btn-secondary'>Back to Search</a>");
        out.println("</div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}