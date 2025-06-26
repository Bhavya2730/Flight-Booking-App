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

@WebServlet("/AllBookingsServlet")
public class AllBookingsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String userId = (String) session.getAttribute("user_id");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>My Bookings</title></head><body>");
        out.println("<h2>Your Bookings</h2>");

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM bookings WHERE user_id = ?");
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();

            boolean hasBookings = false;
            while (rs.next()) {
                hasBookings = true;
                out.println("<p>Flight: " + rs.getString("flight_name") + 
                            " | Route: " + rs.getString("source") + " → " + rs.getString("destination") +
                            " | Price: ₹" + rs.getDouble("price") +
                            " | Booked on: " + rs.getString("booking_date") + "</p>");
            }

            if (!hasBookings) {
                out.println("<p>No bookings found.</p>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error retrieving bookings.");
        }

        out.println("<br><a href='searchFlights.jsp'>Search More Flights</a>");
        out.println("</body></html>");
    }
}
