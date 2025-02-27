package loanDashBoard;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String mobileno = (String) session.getAttribute("mobileno");
        String fullName = (String) session.getAttribute("fullName");
        String dob = (String) session.getAttribute("dob");
        String pan = (String) session.getAttribute("pan");
        String gender = (String) session.getAttribute("gender");
        String maritalStatus = (String) session.getAttribute("maritalStatus");
        String address1 = (String) session.getAttribute("address1");
        String address2 = (String) session.getAttribute("address2");
        String pincode = (String) session.getAttribute("pincode");
        String city = (String) session.getAttribute("city");
        String state = (String) session.getAttribute("state");
        String permanentAddress1 = (String) session.getAttribute("permanent_address1");
        String permanentAddress2 = (String) session.getAttribute("permanent_address2");
        String permanentPincode = (String) session.getAttribute("permanent_pincode");
        String permanentCity = (String) session.getAttribute("permanent_city");
        String permanentState = (String) session.getAttribute("permanent_state");

        String qry = "CREATE TABLE IF NOT EXISTS loandashboard.userdetails ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "mobileno VARCHAR(255), "
                + "full_name VARCHAR(255), "
                + "dob VARCHAR(255), "
                + "pan VARCHAR(255) UNIQUE, "  // Ensuring PAN is unique
                + "address1 VARCHAR(255), "
                + "address2 VARCHAR(255), "
                + "pincode VARCHAR(255), "
                + "city VARCHAR(255), "
                + "state VARCHAR(255), "
                + "permanent_address1 VARCHAR(255), "
                + "permanent_address2 VARCHAR(255), "
                + "permanent_pincode VARCHAR(255), "
                + "permanent_city VARCHAR(255), "
                + "permanent_state VARCHAR(255), "
                + "gender VARCHAR(255), "
                + "marital_status VARCHAR(255))";

        String qry1 = "INSERT INTO userdetails (mobileno, full_name, dob, pan, address1, address2, pincode, city, state, "
                + "permanent_address1, permanent_address2, permanent_pincode, permanent_city, permanent_state, gender, marital_status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loandashboard", "root", "root");

            // Create table if not exists
            Statement stmt = con.createStatement();
            stmt.execute(qry);

            // Check if PAN already exists
            String checkPanQuery = "SELECT COUNT(*) FROM userdetails WHERE pan = ?";
            PreparedStatement checkPanStmt = con.prepareStatement(checkPanQuery);
            checkPanStmt.setString(1, pan);
            ResultSet rs = checkPanStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count > 0) {
                // PAN already exists, show error message
                PrintWriter pw = resp.getWriter();
                pw.println("<html>");
                pw.println("<body>");
                pw.println("<h1 style='color: red'>PAN number already exists. Please use a different one.</h1>");
                pw.println("</body>");
                pw.println("</html>");
            } else {
                // PAN is unique, proceed with registration
                PreparedStatement pstmt = con.prepareStatement(qry1);
                pstmt.setString(1, mobileno);
                pstmt.setString(2, fullName);
                pstmt.setString(3, dob);
                pstmt.setString(4, pan);
                pstmt.setString(5, address1);
                pstmt.setString(6, address2);
                pstmt.setString(7, pincode);
                pstmt.setString(8, city);
                pstmt.setString(9, state);
                pstmt.setString(10, permanentAddress1);
                pstmt.setString(11, permanentAddress2);
                pstmt.setString(12, permanentPincode);
                pstmt.setString(13, permanentCity);
                pstmt.setString(14, permanentState);
                pstmt.setString(15, gender);
                pstmt.setString(16, maritalStatus);
               
                int i = pstmt.executeUpdate();

                if (i > 0) {
                    // Registration successful, forward to email.html
                    RequestDispatcher dispatcher = req.getRequestDispatcher("email.html");
                    dispatcher.forward(req, resp);
                } else {
                    PrintWriter pw = resp.getWriter();
                    pw.println("<html>");
                    pw.println("<body>");
                    pw.println("<h1 style='color: red'>Failed to Register</h1>");
                    pw.println("</body>");
                    pw.println("</html>");
                }

               
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
