package loanDashBoard;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GenderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String gender = req.getParameter("gender");
        String maritalStatus = req.getParameter("maritalStatus");

        HttpSession session = req.getSession();
        System.out.println("hi");
        session.setAttribute("gender", gender);
        session.setAttribute("maritalStatus", maritalStatus);
        RequestDispatcher rd = req.getRequestDispatcher("maritalstatus.html");
        rd.forward(req, resp);
    }
}