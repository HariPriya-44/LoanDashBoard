package bankdetails;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BankAccountDetailsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accountNumber = req.getParameter("AccountNumber");
        String confirmAccountNumber = req.getParameter("ConfirmAccountNumber");
        String ifsc = req.getParameter("IFSC");
        String bankAccountType = req.getParameter("BankAccountType");

        HttpSession session = req.getSession();
        session.setAttribute("accountNumber", accountNumber);
        session.setAttribute("confirmAccountNumber", confirmAccountNumber);
        session.setAttribute("ifsc", ifsc);
        session.setAttribute("bankAccountType", bankAccountType);

        RequestDispatcher rd = req.getRequestDispatcher("bankincomeverification.html");
        rd.forward(req, resp);
    }
}
