package loanDashBoard;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PersonalDetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve input parameters
        String fullName = req.getParameter("fullName");
        String dob = req.getParameter("dob");
        String pan = req.getParameter("pan");

        // Create or get the existing session
        HttpSession session = req.getSession();
        session.setAttribute("fullName", fullName);
        session.setAttribute("dob", dob);
        session.setAttribute("pan", pan);

        // Debugging
        System.out.println("Session ID: " + session.getId());
        System.out.println("PAN stored in session: " + pan);

        // Forward to gender selection page
        RequestDispatcher rd = req.getRequestDispatcher("gender.html");
        rd.forward(req, resp);
    }
}
