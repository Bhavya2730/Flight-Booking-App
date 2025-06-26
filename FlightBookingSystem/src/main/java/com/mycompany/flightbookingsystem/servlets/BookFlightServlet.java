package com.mycompany.flightbookingsystem.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@WebServlet("/BookFlightServlet")
public class BookFlightServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // ✅ Redirect if user is not logged in
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        // ✅ Retrieve flight details from request
        String flightName = request.getParameter("flight_name");
        String source = request.getParameter("source");
        String destination = request.getParameter("destination");
        String price = request.getParameter("price");
        String seatClass = request.getParameter("seat_class");

        // ✅ Handle null values
        if (flightName == null || flightName.isEmpty()) flightName = "Unknown";
        if (source == null || source.isEmpty()) source = "Unknown";
        if (destination == null || destination.isEmpty()) destination = "Unknown";
        if (price == null || price.isEmpty()) price = "Not Available";
        if (seatClass == null || seatClass.isEmpty()) seatClass = "Economy"; // Default to Economy class

        // ✅ Set response type to HTML
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // ✅ Booking Confirmation Page with Bootstrap
        out.println("<html><head>");
        out.println("<title>Booking Confirmation</title>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("</head><body class='bg-light'>");

        out.println("<div class='container mt-5 text-center'>");
        out.println("<div class='card shadow-lg p-4' style='max-width: 400px; margin: auto;'>");
        out.println("<h2 class='text-success'>🎉 Booking Confirmed!</h2>");
        out.println("<p class='lead'><b>Flight:</b> " + flightName + "</p>");
        out.println("<p><b>Route:</b> " + source + " → " + destination + "</p>");
        out.println("<p><b>Class:</b> " + seatClass + "</p>");
        out.println("<p><b>Price:</b> ₹" + price + "</p>");
        out.println("<a href='index.html' class='btn btn-primary mt-3'>🏠 Back to Home</a>");
        out.println("</div></div>");

        out.println("</body></html>");
    }
}