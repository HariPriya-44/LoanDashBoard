

package combineTable;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CombinedDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to MySQL Database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loandashboard", "root", "root");

            // Create table if not exists
            String createTableSQL = getCreateTableSQL();
            Statement stmt = con.createStatement();
            stmt.execute(createTableSQL);

            // Combine data from four tables
            String qry = getCombineQuery();
            stmt.execute(qry);

            out.println("<html><body>");
            out.println("<h1 style='color: green'>Combined Table Created Successfully</h1>");
            out.println("</body></html>");
        } catch (ClassNotFoundException | SQLException e) {
            out.println("<html><body>");
            out.println("<h1 style='color: red'>Error Creating Combined Table</h1>");
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("</body></html>");
        }
    }

    private String getCreateTableSQL() {
        return "CREATE TABLE IF NOT EXISTS combinedtable (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "mobileno VARCHAR(255), " +
                "full_name VARCHAR(255), " +
                "dob VARCHAR(255), " +
                "pan VARCHAR(255), " +
                "pan_card VARCHAR(255), " +
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
                "marital_status VARCHAR(255), " +
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
                "total_work_experience VARCHAR(255), " +
                "account_number VARCHAR(255), " +
                "confirm_account_number VARCHAR(255), " +
                "ifsc VARCHAR(11), " +
                "bank_account_type VARCHAR(50), " +
                "statement_file_name VARCHAR(255), " +
                "statement_file_path VARCHAR(255), " +
                "loan_type VARCHAR(255), " +
                "loan_amount VARCHAR(255), " +
                "aadhaar_number VARCHAR(255), " +
                "payslip1 VARCHAR(255), " +
                "payslip2 VARCHAR(255), " +
                "payslip3 VARCHAR(255), " +
                "address_proof VARCHAR(255), " +
                "selfie VARCHAR(255)" +
                ")";
    }

    private String getCombineQuery() {
        return "INSERT INTO combinedtable (" +
                "loan_type, loan_amount, aadhaar_number, pan, pan_card, payslip1, payslip2, payslip3, address_proof, selfie, " +
               	"personal_email, office_email, qualification, employment_type, monthly_income, salary_type, office_address1, office_address2, office_pincode, office_city, office_state, company_name, company_type, designation, total_work_experience, " +
                	"account_number, confirm_account_number, ifsc, bank_account_type, statement_file_name, statement_file_path, " +
                	"mobileno, full_name, dob, address1, address2, pincode, city, state, " +
                	"permanent_address1, permanent_address2, permanent_pincode, permanent_city, permanent_state, gender, marital_status" +
                	") SELECT " +
                	"l.loan_type, l.loan_amount, l.aadhaar_number, u.pan, l.pan_card, l.payslip1, l.payslip2, l.payslip3, l.address_proof, l.selfie, " +
                	"e.personal_email, e.office_email, e.qualification, e.employment_type, e.monthly_income, e.salary_type, e.office_address1, e.office_address2, e.office_pincode, e.office_city, e.office_state, e.company_name, e.company_type, e.designation, e.total_work_experience, " +
                	"b.account_number, b.confirm_account_number, b.ifsc, b.bank_account_type, b.statement_file_name, b.statement_file_path, " +
                	"u.mobileno, u.full_name, u.dob, u.address1, u.address2, u.pincode, u.city, u.state, u.permanent_address1, u.permanent_address2, u.permanent_pincode, u.permanent_city, u.permanent_state, u.gender, u.marital_status " +
                	"FROM loanapproval l " +
                	"INNER JOIN employeedetails e ON l.id = e.id " +
                	"INNER JOIN bankdetails b ON e.id = b.id " +
                	"INNER JOIN userdetails u ON b.id = u.id";
    }
}