package employeeDetails;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/employeedetails")
public class EmployeeTableServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // Retrieve session attributes
        String personalEmail = (String) session.getAttribute("personalEmail");
        String officeEmail = (String) session.getAttribute("officeEmail");
        String qualification = (String) session.getAttribute("qualification");
        String employmentType = (String) session.getAttribute("employementType");
        String monthlyIncome = (String) session.getAttribute("monthlyIncome");
        String salaryType = (String) session.getAttribute("salaryType");
        String officeAddress1 = (String) session.getAttribute("officeAddress1");
        String officeAddress2 = (String) session.getAttribute("officeAddress2");
        String officePincode = (String) session.getAttribute("officePincode");
        String officeCity = (String) session.getAttribute("officeCity");
        String officeState = (String) session.getAttribute("officeState");
        String companyName = (String) session.getAttribute("companyName");
        String companyType = (String) session.getAttribute("companyType");
        String designation = (String) session.getAttribute("designation");
        String totalWorkExperience = (String) session.getAttribute("totalWorkExperience");

        // Debugging: Print values to verify they're received correctly
        System.out.println("Personal Email: " + personalEmail);
        System.out.println("Office Email: " + officeEmail);
        System.out.println("Qualification: " + qualification);
        System.out.println("Employment Type: " + employmentType);
        System.out.println("Monthly Income: " + monthlyIncome);
        System.out.println("Salary Type: " + salaryType);
        System.out.println("Office Address 1: " + officeAddress1);
        System.out.println("Office Address 2: " + officeAddress2);
        System.out.println("Office Pincode: " + officePincode);
        System.out.println("Office City: " + officeCity);
        System.out.println("Office State: " + officeState);
        System.out.println("Company Name: " + companyName);
        System.out.println("Company Type: " + companyType);
        System.out.println("Designation: " + designation);
        System.out.println("Total Work Experience: " + totalWorkExperience);

        // Create SQL Table Query
        String createTableQuery = "CREATE TABLE IF NOT EXISTS employeedetails (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "personal_email VARCHAR(255), " +
                "office_email VARCHAR(255), " +
                "qualification VARCHAR(255), " +
                "employment_type VARCHAR(255), " +
                "monthly_income VARCHAR(255), " +
                "salary_type VARCHAR(255), " +
                "office_address1 VARCHAR(255), " +
                "office_address2 VARCHAR(255), " +
                "office_pincode VARCHAR(255), " +
                "office_city VARCHAR(255), " +
                "office_state VARCHAR(255), " +
                "company_name VARCHAR(255), " +
                "company_type VARCHAR(255), " +
                "designation VARCHAR(255), " +
                "total_work_experience VARCHAR(255))";

        // Insert Query
        String insertQuery = "INSERT INTO employeedetails (personal_email, office_email, qualification, employment_type, monthly_income, salary_type, office_address1, office_address2, office_pincode, office_city, office_state, company_name, company_type, designation, total_work_experience) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            // Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loandashboard", "root", "root");

            // Create table if not exists
            Statement stmt = con.createStatement();
            stmt.execute(createTableQuery);

            // Prepare statement for inserting data
            PreparedStatement pstmt = con.prepareStatement(insertQuery);
            pstmt.setString(1, personalEmail);
            pstmt.setString(2, officeEmail);
            pstmt.setString(3, qualification);
            pstmt.setString(4, employmentType);
            pstmt.setString(5, monthlyIncome);
            pstmt.setString(6, salaryType);
            pstmt.setString(7, officeAddress1);
            pstmt.setString(8, officeAddress2);
            pstmt.setString(9, officePincode);
            pstmt.setString(10, officeCity);
            pstmt.setString(11, officeState);
            pstmt.setString(12, companyName);
            pstmt.setString(13, companyType);
            pstmt.setString(14, designation);
            pstmt.setString(15, totalWorkExperience);

            // Execute Insert
            int i = pstmt.executeUpdate();
            PrintWriter pw = resp.getWriter();
            resp.setContentType("text/html");

            if (i > 0) {
            	RequestDispatcher dispatcher = req.getRequestDispatcher("bankaccountdetails.html");
                dispatcher.forward(req, resp);
            } else {
                pw.println("<html><body>");
                pw.println("<h1 style='color: red'>Failed to Save Employee Details</h1>");
                pw.println("</body></html>");
            }

            // Close resources
            pstmt.close();
            stmt.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            PrintWriter pw = resp.getWriter();
            resp.setContentType("text/html");
            pw.println("<html><body>");
            pw.println("<h1 style='color: red'>Error: " + e.getMessage() + "</h1>");
            pw.println("</body></html>");
        }
    }
}
