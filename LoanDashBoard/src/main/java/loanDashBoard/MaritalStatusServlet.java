
package loanDashBoard;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MaritalStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String maritalStatus = req.getParameter("maritalStatus");
        if (maritalStatus != null) {
            session.setAttribute("maritalStatus", maritalStatus);
            RequestDispatcher rd = req.getRequestDispatcher("address.jsp");
            rd.forward(req, resp);
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("maritalstatus.html");
            rd.forward(req, resp);
        }
    }
}


