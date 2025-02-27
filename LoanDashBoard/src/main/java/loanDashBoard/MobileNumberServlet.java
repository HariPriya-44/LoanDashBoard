package loanDashBoard;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class MobileNumberServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mobileno = req.getParameter("mobileno");

        // Check if the mobile number exists in the database
        if (mobileExists(mobileno)) {
            // Mobile number exists, return 'exists' to the frontend
            resp.getWriter().write("exists");
        } else {
            // Mobile number doesn't exist, store it in the session and proceed
            HttpSession session = req.getSession();
            session.setAttribute("mobileno", mobileno);

            // Send success response
            resp.getWriter().write("success");
        }
    }

    // Method to check if mobile number exists in the database
    private boolean mobileExists(String mobileno) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loandashboard", "root", "root");
            String query = "SELECT COUNT(*) FROM userdetails WHERE mobileno = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, mobileno);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true; // Mobile number exists
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false; // Mobile number does not exist
    }
}

    