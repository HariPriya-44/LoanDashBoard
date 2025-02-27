package loanDetails;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PurposeOfLoanServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loanType = req.getParameter("loan");

        HttpSession session = req.getSession();
        session.setAttribute("loanType", loanType);

        RequestDispatcher rd = req.getRequestDispatcher("loanAmount.html");
        rd.forward(req, resp);
    }
}

