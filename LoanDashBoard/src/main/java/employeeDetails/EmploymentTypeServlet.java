package employeeDetails;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EmploymentTypeServlet extends HttpServlet {
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employementType = req.getParameter("employementtype");
        HttpSession session = req.getSession();
        session.setAttribute("employementType", employementType);
        RequestDispatcher rd = req.getRequestDispatcher("monthlyIncome.html");
        rd.forward(req, resp);
    }


}
