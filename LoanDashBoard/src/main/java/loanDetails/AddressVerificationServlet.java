package loanDetails;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
public class AddressVerificationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve document type
        String documentType = req.getParameter("AddressVerification");
        HttpSession session = req.getSession();
        String panNumber = (String) session.getAttribute("pan"); // Retrieve PAN from session

        if (panNumber == null || panNumber.isEmpty()) {
            resp.getWriter().write("Error: PAN number is missing.");
            return;
        }

        // Define the base directory for uploads inside the user's PAN directory
        String uploadPath = "C:/Users/gutla/OneDrive/Desktop/LoanDashBoard/uploads/" + panNumber;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Process Aadhaar card
        if ("Aadhaar".equalsIgnoreCase(documentType)) {
            Part aadhaarFront = req.getPart("aadhaarFront");
            Part aadhaarBack = req.getPart("aadhaarBack");

            if (aadhaarFront != null) saveFile(aadhaarFront, uploadPath, "aadhaarFront.pdf");
            if (aadhaarBack != null) saveFile(aadhaarBack, uploadPath, "aadhaarBack.pdf");

            session.setAttribute("documentType", documentType);
            session.setAttribute("aadhaarFront", "aadhaarFront.pdf");
            session.setAttribute("aadhaarBack", "aadhaarBack.pdf");

        } else if ("Rent Agreement".equalsIgnoreCase(documentType) ||
                   "Water Bill".equalsIgnoreCase(documentType) ||
                   "Electricity Bill".equalsIgnoreCase(documentType)) {
            
            Part documentUpload = req.getPart("documentUpload");
            if (documentUpload != null) saveFile(documentUpload, uploadPath, "addressProof.pdf");

            session.setAttribute("documentType", documentType);
            session.setAttribute("addressProof", "addressProof.pdf");
        }

        System.out.println("Address verification documents uploaded successfully.");

        // Forward to the next page
        RequestDispatcher rd = req.getRequestDispatcher("panCardVerification.html");
        rd.forward(req, resp);
    }

    // Method to save uploaded file
    private void saveFile(Part filePart, String uploadPath, String fileName) throws IOException {
        File file = new File(uploadPath + File.separator + fileName);
        filePart.write(file.getAbsolutePath());
        System.out.println("File saved: " + file.getAbsolutePath());
    }
}
