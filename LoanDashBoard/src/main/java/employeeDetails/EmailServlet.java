package employeeDetails;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EmailServlet extends HttpServlet {
	 @Override
	  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String  email1=req.getParameter("personalEmail");
		 String  email2=req.getParameter("officeEmail");
		 HttpSession session = req.getSession();
		 session.setAttribute("personalEmail", email1);
		 session.setAttribute("officeEmail", email2);
		 
		 RequestDispatcher rd = req.getRequestDispatcher("educationQualification.html");
	     rd.forward(req, resp);
		 
		 
	 }
	 
	   


}
