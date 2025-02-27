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

@MultipartConfig
public class PanCardVerificationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String panNumber = (String) session.getAttribute("pan"); // Retrieve PAN from session

        if (panNumber == null || panNumber.isEmpty()) {
            resp.getWriter().write("Error: PAN number is missing.");
            return;
        }

        Part panCardFile = req.getPart("PanFront");

        if (panCardFile != null && panCardFile.getSize() > 0) {
            saveFile(panCardFile, "PanCard.pdf", panNumber);
            session.setAttribute("panCard", panCardFile.getSubmittedFileName());
        } else {
            resp.getWriter().write("Error: No file uploaded.");
            return;
        }

        RequestDispatcher rd = req.getRequestDispatcher("aadhaarcard.html");
        rd.forward(req, resp);
    }

    private void saveFile(Part filePart, String fileName, String panNumber) throws IOException {
        // Define the directory for the specific PAN number
        String uploadPath = "C:/Users/gutla/OneDrive/Desktop/LoanDashBoard/uploads/" + panNumber;
        File uploadDir = new File(uploadPath);

        // Create the directory if it doesn't exist
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Save the file inside the PAN directory
        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);

        System.out.println("File saved: " + filePath);
    }
}
