package loanDetails;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SelfieServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String panNumber = (String) session.getAttribute("pan"); // Retrieve PAN from session

        if (panNumber == null || panNumber.isEmpty()) {
            resp.getWriter().write("Error: PAN number is missing.");
            return;
        }

        String imageData = req.getParameter("imageData");

        if (imageData == null || imageData.isEmpty()) {
            System.out.println("Error: No image data received.");
            resp.getWriter().write("Error: Image data is empty.");
            return;
        }

        byte[] imageBytes = java.util.Base64.getDecoder().decode(imageData);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
        BufferedImage image = ImageIO.read(bis);

        if (image == null) {
            System.out.println("Error: Failed to decode image.");
            resp.getWriter().write("Error: Failed to decode image.");
            return;
        }

        // Define upload path inside PAN directory
        String uploadPath = "C:/Users/gutla/OneDrive/Desktop/LoanDashBoard/uploads/" + panNumber;
        File uploadDir = new File(uploadPath);

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        File imageFile = new File(uploadPath + File.separator + "selfie.png");
        ImageIO.write(image, "png", imageFile);

        // Store file reference in session
        session.setAttribute("selfie", panNumber + "/selfie.png");

        System.out.println("Selfie stored: " + imageFile.getAbsolutePath());
        resp.getWriter().write("Selfie uploaded successfully!");

        RequestDispatcher rd = req.getRequestDispatcher("addressVarification.html");
        rd.forward(req, resp);
    }
}
