package store.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import store.DAL.User;
import store.DAO.DataBaseBridge;
import store.modal.Customer;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/Login")
public class LoginController extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		DataBaseBridge dao = new DataBaseBridge();
		User u = dao.getUserDAL();
		RequestDispatcher rd;
		if (u.isValidUser(request.getParameter("cemail"), request.getParameter("cpassword"))) {
			Customer c = u.getCustomerDetails(request.getParameter("cemail"));
			request.setAttribute("CustomerDetails", c);
			rd = request.getRequestDispatcher("/home");
		} else {
			rd = request.getRequestDispatcher("customer-login.jsp");
		}
		rd.forward(request, response);
	}
}
