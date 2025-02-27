package employeeDetails;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MonthlyIncomeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String monthlyIncome = req.getParameter("monthlyIncome");
        String salaryType = req.getParameter("salaryType");
        HttpSession session = req.getSession();
        session.setAttribute("monthlyIncome", monthlyIncome);
        session.setAttribute("salaryType", salaryType);
        RequestDispatcher rd = req.getRequestDispatcher("officeaddress.html");
        rd.forward(req, resp);
    }
}

