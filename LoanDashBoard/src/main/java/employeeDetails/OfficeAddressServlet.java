package employeeDetails;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OfficeAddressServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String address1 = req.getParameter("address1");
        String address2 = req.getParameter("address2");
        String pincode = req.getParameter("pincode");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        
        HttpSession session = req.getSession();
        session.setAttribute("officeAddress1", address1);
        session.setAttribute("officeAddress2", address2);
        session.setAttribute("officePincode", pincode);
        session.setAttribute("officeCity", city);
        session.setAttribute("officeState", state);
        
        RequestDispatcher rd = req.getRequestDispatcher("companydetails.html");
        rd.forward(req, resp);
    }
}
