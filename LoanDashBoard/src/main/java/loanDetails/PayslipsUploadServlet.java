package loanDetails;

import java.io.File;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class PayslipsUploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String panNumber = (String) session.getAttribute("pan"); // Retrieve PAN from session

        if (panNumber == null || panNumber.isEmpty()) {
            resp.getWriter().write("Error: PAN number is missing.");
            return;
        }

        Part payslip1 = req.getPart("payslip1");
        Part payslip2 = req.getPart("payslip2");
        Part payslip3 = req.getPart("payslip3");

        String uploadPath = "C:/Users/gutla/OneDrive/Desktop/LoanDashBoard/uploads/" + panNumber;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        if (payslip1 != null && payslip1.getSize() > 0) {
            saveFile(payslip1, "Payslip1.pdf", uploadPath);
            session.setAttribute("payslip1", "Payslip1.pdf");
        }
        if (payslip2 != null && payslip2.getSize() > 0) {
            saveFile(payslip2, "Payslip2.pdf", uploadPath);
            session.setAttribute("payslip2", "Payslip2.pdf");
        }
        if (payslip3 != null && payslip3.getSize() > 0) {
            saveFile(payslip3, "Payslip3.pdf", uploadPath);
            session.setAttribute("payslip3", "Payslip3.pdf");
        }

        System.out.println("Payslips uploaded successfully to: " + uploadPath);
        RequestDispatcher rd = req.getRequestDispatcher("/loanDetails");
        rd.forward(req, resp);
    }

    private void saveFile(Part filePart, String fileName, String uploadPath) throws IOException {
        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);
        System.out.println("File saved: " + filePath);
    }
}
