package com.mycompany.flightbookingsystem.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.mycompany.flightbookingsystem.db.DBConnection;

@WebServlet("/ConfirmationServlet")
public class ConfirmationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String flightId = (String) session.getAttribute("flight_id");  // Retrieve from session
        String userId = (String) session.getAttribute("user_id");  // Assuming user_id is stored in session

        if (flightId == null || userId == null) {
            response.getWriter().println("Error retrieving flight details.");
            return;
        }

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO bookings (user_id, flight_id, booking_date) VALUES (?, ?, NOW())");
            ps.setString(1, userId);
            ps.setString(2, flightId);
            ps.executeUpdate();

            // Remove flight_id from session after booking
            session.removeAttribute("flight_id");

            // Redirect to Bookings page with a success message
            response.sendRedirect("AllBookingsServlet?success=1");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error booking flight.");
        }
    }
}
