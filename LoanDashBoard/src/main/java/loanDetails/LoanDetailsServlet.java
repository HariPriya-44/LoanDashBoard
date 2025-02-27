package loanDetails;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoanDetailsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // Retrieve all session attributes
        String loanType = (String) session.getAttribute("loanType");
        String loanAmount = (String) session.getAttribute("loanAmount");
        String aadhaarNumber = (String) session.getAttribute("aadhaarNumber");
        String panCard = (String) session.getAttribute("panCard");
        String addressProof = (String) session.getAttribute("addressProof");
        String selfie = (String) session.getAttribute("selfie");
        String payslip1 = (String) session.getAttribute("payslip1");
        String payslip2 = (String) session.getAttribute("payslip2");
        String payslip3 = (String) session.getAttribute("payslip3");

        // Retrieve user details from session
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

        // Retrieve employee details from session
        String personalEmail = (String) session.getAttribute("personalEmail");
        String officeEmail = (String) session.getAttribute("officeEmail");
        String qualification = (String) session.getAttribute("qualification");
        String employmentType = (String) session.getAttribute("employmentType");
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

        // Retrieve bank details from session
        String accountNumber = (String) session.getAttribute("accountNumber");
        String confirmAccountNumber = (String) session.getAttribute("confirmAccountNumber");
        String ifsc = (String) session.getAttribute("ifsc");
        String bankAccountType = (String) session.getAttribute("bankAccountType");
        String fileName = (String) session.getAttribute("bankStatementFileName");
        String filePath = (String) session.getAttribute("bankStatementFilePath");

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loandashboard", "root", "root");

            // Start transaction
            con.setAutoCommit(false);

            // Create tables if they don't exist
            createTables(con);

            // Insert into userdetails table
            String userDetailsQuery = "INSERT INTO userdetails (mobileno, full_name, dob, pan, address1, address2, pincode, city, state, permanent_address1, permanent_address2, permanent_pincode, permanent_city, permanent_state, gender, marital_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement userDetailsStmt = con.prepareStatement(userDetailsQuery);
            userDetailsStmt.setString(1, mobileno);
            userDetailsStmt.setString(2, fullName);
            userDetailsStmt.setString(3, dob);
            userDetailsStmt.setString(4, pan);
            userDetailsStmt.setString(5, address1);
            userDetailsStmt.setString(6, address2);
            userDetailsStmt.setString(7, pincode);
            userDetailsStmt.setString(8, city);
            userDetailsStmt.setString(9, state);
            userDetailsStmt.setString(10, permanentAddress1);
            userDetailsStmt.setString(11, permanentAddress2);
            userDetailsStmt.setString(12, permanentPincode);
            userDetailsStmt.setString(13, permanentCity);
            userDetailsStmt.setString(14, permanentState);
            userDetailsStmt.setString(15, gender);
            userDetailsStmt.setString(16, maritalStatus);
            userDetailsStmt.executeUpdate();

            // Insert into employeedetails table
            String employeeDetailsQuery = "INSERT INTO employeedetails (personal_email, office_email, qualification, employment_type, monthly_income, salary_type, office_address1, office_address2, office_pincode, office_city, office_state, company_name, company_type, designation, total_work_experience) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement employeeDetailsStmt = con.prepareStatement(employeeDetailsQuery);
            employeeDetailsStmt.setString(1, personalEmail);
            employeeDetailsStmt.setString(2, officeEmail);
            employeeDetailsStmt.setString(3, qualification);
            employeeDetailsStmt.setString(4, employmentType);
            employeeDetailsStmt.setString(5, monthlyIncome);
            employeeDetailsStmt.setString(6, salaryType);
            employeeDetailsStmt.setString(7, officeAddress1);
            employeeDetailsStmt.setString(8, officeAddress2);
            employeeDetailsStmt.setString(9, officePincode);
            employeeDetailsStmt.setString(10, officeCity);
            employeeDetailsStmt.setString(11, officeState);
            employeeDetailsStmt.setString(12, companyName);
            employeeDetailsStmt.setString(13, companyType);
            employeeDetailsStmt.setString(14, designation);
            employeeDetailsStmt.setString(15, totalWorkExperience);
            employeeDetailsStmt.executeUpdate();

            // Insert into bankdetails table
            String bankDetailsQuery = "INSERT INTO bankdetails (account_number, confirm_account_number, ifsc, bank_account_type, statement_file_name, statement_file_path) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement bankDetailsStmt = con.prepareStatement(bankDetailsQuery);
            bankDetailsStmt.setString(1, accountNumber);
            bankDetailsStmt.setString(2, confirmAccountNumber);
            bankDetailsStmt.setString(3, ifsc);
            bankDetailsStmt.setString(4, bankAccountType);
            bankDetailsStmt.setString(5, fileName);
            bankDetailsStmt.setString(6, filePath);

            try {
                bankDetailsStmt.executeUpdate();
                System.out.println("Bank details inserted successfully.");
            } catch (SQLException e) {
                System.err.println("Error inserting bank details: " + e.getMessage());
                e.printStackTrace();
            }
            // Insert into loanapproval table
            String loanApprovalQuery = "INSERT INTO loanapproval (loan_type, loan_amount, aadhaar_number, pan_card, payslip1, payslip2, payslip3, address_proof, selfie) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement loanApprovalStmt = con.prepareStatement(loanApprovalQuery);
            loanApprovalStmt.setString(1, loanType);
            loanApprovalStmt.setString(2, loanAmount);
            loanApprovalStmt.setString(3, aadhaarNumber);
            loanApprovalStmt.setString(4, panCard);
            loanApprovalStmt.setString(5, payslip1);
            loanApprovalStmt.setString(6, payslip2);
            loanApprovalStmt.setString(7, payslip3);
            loanApprovalStmt.setString(8, addressProof);
            loanApprovalStmt.setString(9, selfie);
            loanApprovalStmt.executeUpdate();

            // Commit transaction
            con.commit();

            // Forward to success page
            RequestDispatcher dispatcher = req.getRequestDispatcher("/combine");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            // Rollback transaction in case of error
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            resp.getWriter().println("<h1 style='color: red'>Error: " + e.getMessage() + "</h1>");
        } finally {
            // Close connection
            if (con != null) {
                try {
                    con.setAutoCommit(true); // Reset auto-commit
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void createTables(Connection con) throws SQLException {
        Statement stmt = con.createStatement();

        // Create userdetails table
        String userDetailsTable = "CREATE TABLE IF NOT EXISTS userdetails (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "mobileno VARCHAR(255), " +
                "full_name VARCHAR(255), " +
                "dob VARCHAR(255), " +
                "pan VARCHAR(255) UNIQUE, " +
                "address1 VARCHAR(255), " +
                "address2 VARCHAR(255), " +
                "pincode VARCHAR(255), " +
                "city VARCHAR(255), " +
                "state VARCHAR(255), " +
                "permanent_address1 VARCHAR(255), " +
                "permanent_address2 VARCHAR(255), " +
                "permanent_pincode VARCHAR(255), " +
                "permanent_city VARCHAR(255), " +
                "permanent_state VARCHAR(255), " +
                "gender VARCHAR(255), " +
                "marital_status VARCHAR(255))";
        stmt.execute(userDetailsTable);

        // Create employeedetails table
        String employeeDetailsTable = "CREATE TABLE IF NOT EXISTS employeedetails (" +
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
        stmt.execute(employeeDetailsTable);

        // Create bankdetails table
        String bankDetailsTable = "CREATE TABLE IF NOT EXISTS bankdetails (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "account_number VARCHAR(255), " +
                "confirm_account_number VARCHAR(255), " +
                "ifsc VARCHAR(11), " +
                "bank_account_type VARCHAR(50), " +
                "statement_file_name VARCHAR(255), " +
                "statement_file_path VARCHAR(255))";
        stmt.execute(bankDetailsTable);

        // Create loanapproval table
        String loanApprovalTable = "CREATE TABLE IF NOT EXISTS loanapproval (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "loan_type VARCHAR(255), " +
                "loan_amount VARCHAR(255), " +
                "aadhaar_number VARCHAR(255), " +
                "pan_card VARCHAR(255), " +
                "payslip1 VARCHAR(255), " +
                "payslip2 VARCHAR(255), " +
                "payslip3 VARCHAR(255), " +
                "address_proof VARCHAR(255), " +
                "selfie VARCHAR(255))";
        stmt.execute(loanApprovalTable);

        stmt.close();
    }
}


