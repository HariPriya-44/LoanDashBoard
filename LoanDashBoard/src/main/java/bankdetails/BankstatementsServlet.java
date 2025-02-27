package bankdetails;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
public class BankstatementsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get existing session, do not create a new one if it doesn't exist
        HttpSession session = req.getSession(false);

        if (session == null) {
            System.out.println("Error: No active session. Please enter personal details again.");
            resp.getWriter().write("Error: No active session. Please enter personal details again.");
            return;
        }

        // Retrieve PAN from session
        String panNumber = (String) session.getAttribute("pan");
        if (panNumber == null || panNumber.isEmpty()) {
            System.out.println("Error: PAN number is missing in session.");
            resp.getWriter().write("Error: PAN number is missing in session.");
            return;
        }

        // Debugging
        System.out.println("Session ID: " + session.getId());
        System.out.println("PAN retrieved from session: " + panNumber);

        // Retrieve bank account details from session
        String accountNumber = (String) session.getAttribute("accountNumber");
        String confirmAccountNumber = (String) session.getAttribute("confirmAccountNumber");
        String ifsc = (String) session.getAttribute("ifsc");
        String bankAccountType = (String) session.getAttribute("bankAccountType");

        // Debugging
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Confirm Account Number: " + confirmAccountNumber);
        System.out.println("IFSC: " + ifsc);
        System.out.println("Bank Account Type: " + bankAccountType);

        // Get the uploaded file part (bank statement)
        Part filePart = req.getPart("fileUpload");
        if (filePart == null || filePart.getSubmittedFileName().isEmpty()) {
            System.out.println("Error: No file uploaded.");
            resp.getWriter().write("Error: No file uploaded.");
            return;
        }

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        // Define the upload directory based on PAN number
        String uploadPath = "C:/Users/gutla/OneDrive/Desktop/LoanDashBoard/uploads/" + panNumber;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // Create the directory if it doesn't exist
        }

        // Save the uploaded file as bankStatement.pdf
        String filePath = uploadPath + File.separator + "bankStatement.pdf";
        filePart.write(filePath);

        // Store file details in session
        session.setAttribute("bankStatementFileName", "bankStatement.pdf");
        session.setAttribute("bankStatementFilePath", filePath);

        System.out.println("Bank statement uploaded successfully: " + filePath);

        // Forward to purpose of loan page
        RequestDispatcher rd = req.getRequestDispatcher("purposeOfLoan.html");
        rd.forward(req, resp);
    }
}
