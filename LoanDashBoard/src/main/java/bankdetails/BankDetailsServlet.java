package bankdetails;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BankDetailsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // Retrieve account details from session
        String accountNumber = (String) session.getAttribute("accountNumber");
        String confirmAccountNumber = (String) session.getAttribute("confirmAccountNumber");
        String ifsc = (String) session.getAttribute("ifsc");
        String bankAccountType = (String) session.getAttribute("bankAccountType");

        // Retrieve file details from session
        String fileName = (String) session.getAttribute("fileName");
        String filePath = (String) session.getAttribute("filePath");

        if (accountNumber == null || confirmAccountNumber == null || ifsc == null || bankAccountType == null) {
            resp.getWriter().println("Session attributes are missing.");
            return;
        }
        if (fileName == null || filePath == null) {
            resp.getWriter().println("File attributes are missing.");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loandashboard", "root", "root");

            String createTableSQL = "CREATE TABLE IF NOT EXISTS bankdetails (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "account_number VARCHAR(255) NOT NULL, " +
                    "confirm_account_number VARCHAR(255) NOT NULL, " +
                    "ifsc VARCHAR(11) NOT NULL, " +
                    "bank_account_type VARCHAR(50) NOT NULL, " +
                    "statement_file_name VARCHAR(255) NOT NULL, " +
                    "statement_file_path VARCHAR(255) NOT NULL" +
                    ")";

            PreparedStatement pstmt = con.prepareStatement(createTableSQL);
            pstmt.executeUpdate();

            String insertSQL = "INSERT INTO bankdetails (account_number, confirm_account_number, ifsc, bank_account_type, statement_file_name, statement_file_path) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(insertSQL);
            pstmt.setString(1, accountNumber);
            pstmt.setString(2, confirmAccountNumber);
            pstmt.setString(3, ifsc);
            pstmt.setString(4, bankAccountType);
            pstmt.setString(5, fileName);
            pstmt.setString(6, filePath);

            int i = pstmt.executeUpdate();
            if (i > 0) {
                RequestDispatcher dispatcher = req.getRequestDispatcher("purposeOfLoan.html");
                dispatcher.forward(req, resp);
            } else {
                PrintWriter pw = resp.getWriter();
                pw.println("<html><body><h1 style='color: red'>Failed to Save Bank Details</h1></body></html>");
            }
        } catch (ClassNotFoundException | SQLException e) {
            PrintWriter pw = resp.getWriter();
            pw.println("<html><body><h1 style='color: red'>Error Saving Bank Details</h1><p>" + e.getMessage() + "</p></body></html>");
        }
    }
}
