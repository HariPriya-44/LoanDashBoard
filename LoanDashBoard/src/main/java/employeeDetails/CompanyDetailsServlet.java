
package employeeDetails;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CompanyDetailsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // Retrieve parameters from request (ensure names match the form's input fields)
        String companyName = req.getParameter("companyName");  
        String companyType = req.getParameter("companyType");  
        String designation = req.getParameter("designation");  
        String totalWorkExperience = req.getParameter("totalWorkExperience");  

        // Debugging: Print values to verify they're received correctly
        System.out.println("Company Name: " + companyName);
        System.out.println("Company Type: " + companyType);
        System.out.println("Designation: " + designation);
        System.out.println("Total Work Experience: " + totalWorkExperience);

        // Validate that required fields are not empty (optional but recommended)
        if (companyName == null || companyName.trim().isEmpty() ||
            companyType == null || companyType.trim().isEmpty() ||
            designation == null || designation.trim().isEmpty() ||
            totalWorkExperience == null || totalWorkExperience.trim().isEmpty()) {
            
            System.out.println("ERROR: Missing company details!");
            
            // Redirect to an error page or display a message
            resp.getWriter().println("<html><body><h1 style='color: red;'>Error: Missing Company Details</h1></body></html>");
            return;
        }

        // Store values in session to be used in EmployeeTableServlet
        HttpSession session = req.getSession();
        session.setAttribute("companyName", companyName);
        session.setAttribute("companyType", companyType);
        session.setAttribute("designation", designation);
        session.setAttribute("totalWorkExperience", totalWorkExperience);

        // Forward request to EmployeeTableServlet
        RequestDispatcher rd = req.getRequestDispatcher("bankaccountdetails.html");  
        rd.forward(req, resp);
    }
}
