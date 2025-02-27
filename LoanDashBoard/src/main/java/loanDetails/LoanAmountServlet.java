package loanDetails;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoanAmountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loanAmount = req.getParameter("LoanAmount");

        HttpSession session = req.getSession();
        session.setAttribute("loanAmount", loanAmount);

        RequestDispatcher rd = req.getRequestDispatcher("selfiee.html");
        rd.forward(req, resp);
    }
}
