package loanDetails;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AadhaarValidationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String aadhaarNumber = req.getParameter("aadhaarNumber");

        HttpSession session = req.getSession();
        if (aadhaarNumber != null && !aadhaarNumber.trim().isEmpty()) {
            session.setAttribute("aadhaarNumber", aadhaarNumber);
            System.out.println("Aadhaar Number Stored: " + aadhaarNumber);
        } else {
            System.out.println("Aadhaar Number is NULL or EMPTY");
        }

        RequestDispatcher rd = req.getRequestDispatcher("paySlips.html");
        rd.forward(req, resp);
    }
}
