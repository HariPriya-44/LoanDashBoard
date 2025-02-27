
package loanDashBoard;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddressServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String address1 = req.getParameter("address1");
        String address2 = req.getParameter("address2");
        String pincode = req.getParameter("pincode");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String permanentAddress1 = req.getParameter("permanent_address1");
        String permanentAddress2 = req.getParameter("permanent_address2");
        String permanentPincode = req.getParameter("permanent_pincode");
        String permanentCity = req.getParameter("permanent_city");
        String permanentState = req.getParameter("permanent_state");

        HttpSession session = req.getSession();
        session.setAttribute("address1", address1);
        session.setAttribute("address2", address2);
        session.setAttribute("pincode", pincode);
        session.setAttribute("city", city);
        session.setAttribute("state", state);

        // Check if permanent address is same as communication address
        String checkboxValue = req.getParameter("permanent");
        if (checkboxValue != null && checkboxValue.equals("on")) {
            session.setAttribute("permanent_address1", address1);
            session.setAttribute("permanent_address2", address2);
            session.setAttribute("permanent_pincode", pincode);
            session.setAttribute("permanent_city", city);
            session.setAttribute("permanent_state", state);
        } else {
            session.setAttribute("permanent_address1", permanentAddress1);
            session.setAttribute("permanent_address2", permanentAddress2);
            session.setAttribute("permanent_pincode", permanentPincode);
            session.setAttribute("permanent_city", permanentCity);
            session.setAttribute("permanent_state", permanentState);
        }

        RequestDispatcher rd = req.getRequestDispatcher("email.html");
        rd.forward(req, resp);
    }
}

