# Flight-Booking-App


# ✈️ Flight Booking Management System

A full-stack Java web application for seamless flight search and booking, built using **JSP**, **Servlets**, and **MySQL**.
Users can browse flights, book tickets, and manage their bookings through a user-friendly interface.

---

## 📖 Overview

This web-based flight booking system allows users to:

* 👤 Register and log in securely
* 🔍 Search for available flights
* 📅 Book selected flights
* 📄 View booking history
* 🔓 Log out when done

The system is built using **Java Servlets**, stores data in **MySQL**, and runs on **Apache Tomcat**.

---

## 🖥️ Pages and Descriptions

### 🏠 Home Page (`index.html`)

➡️ Clean welcome screen with a “Get Started” button that leads to the login or registration page.

### 🔐 Login Page (`login.html`)

➡️ Secure login form. Redirects new users to the sign-up page if they don’t have an account.

### 📝 Sign-Up Page (`signup.html`)

➡️ New users can create an account by providing their name, email, and password. Stored in the `users` table.

### ✈️ Flight Search Page (`search.jsp`)

➡️ Users can enter source, destination, and date to search available flights fetched from the `flights` table.

### 📦 Booking Page (`book.jsp`)

➡️ Displays flight details with a "Book Now" button. Upon booking, the data is saved to the `bookings` table.

### 📄 My Bookings Page (`mybookings.jsp`)

➡️ Shows the list of flights booked by the user with details like flight number, date, time, and status.

### ✅ Booking Confirmation Page (`confirmation.jsp`)

➡️ Displays a success message with flight and passenger details after a successful booking.

---

## ⚙️ Tech Stack

| Layer        | Technology              |
| ------------ | ----------------------- |
| 🎨 Frontend  | HTML, CSS, JSP          |
| 🧠 Backend   | Java Servlets (Jakarta) |
| 🗃️ Database | MySQL                   |
| 🔥 Server    | Apache Tomcat           |
| 💻 IDE       | NetBeans                |

---

## 🗂️ Project Structure

```
FlightBookingApp/
├── nbproject/           # NetBeans config files
├── src/                 # Java Servlets
│   ├── SignupServlet.java
│   ├── LoginServlet.java
│   ├── SearchFlightServlet.java
│   ├── BookFlightServlet.java
│   └── MyBookingsServlet.java
├── web/                 # Frontend files (HTML, JSP, images)
│   ├── images/
│   ├── index.html
│   ├── login.html
│   ├── signup.html
│   ├── search.jsp
│   ├── book.jsp
│   ├── mybookings.jsp
│   └── confirmation.jsp
├── screenshots/         # UI screenshots (optional)
├── .gitignore
└── README.md
```

---

## 🚀 How to Run the Project

### 📦 Prerequisites

* Java JDK 11 or higher
* Apache Tomcat 9+
* MySQL Server
* NetBeans IDE with Java EE support
* Git (optional)

### 🛠️ Setup Instructions

1. **Clone the Repository**

```bash
git clone https://github.com/YourUsername/FlightBookingApp.git
cd FlightBookingApp
```

2. **Open the Project in NetBeans**

3. **Configure the MySQL Database**

* Create the database:

```sql
CREATE DATABASE flight_booking;
```

* Create tables: `users`, `flights`, `bookings`
* Update your database connection in the servlets:

```java
Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/flight_booking", "root", "your-password"
);
```

4. **Build and Deploy**

* Right-click project → Clean and Build
* Right-click again → Run (deploys to Tomcat)

5. **Visit the Application**

```
http://localhost:8080/FlightBookingApp
```

---

## 🧪 User Journey

1. 🏠 Visit welcome page
2. 🔐 Login or 📝 Register
3. 🔍 Search flights
4. 📅 Book a flight
5. 📄 View your bookings
6. ✅ Get confirmation
7. 🔓 Logout securely

---

## 🙋 Author(s)

👩‍💻 Bhavya Chowdary Bellamkonda

---

## ✨ Feedback & Contributions

If you like this project or want to improve it:

* ⭐ Star the repository
* 🍴 Fork and enhance features
* 🛠️ Submit a pull request

Let’s make flight booking smarter together! 🛫💻

---
