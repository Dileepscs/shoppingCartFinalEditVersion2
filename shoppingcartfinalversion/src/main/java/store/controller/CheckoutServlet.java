package store.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import store.BLL.Bll;
import store.DAO.BLLBridge;
import store.modal.CheckOutModal;
import store.modal.ProductList;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/Checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		ProductList pl = (ProductList) request.getAttribute("Products");
		Bll bussinessLogic = BLLBridge.getBLLObject();
		CheckOutModal c = bussinessLogic.getCheckOutDetails(pl);
		request.setAttribute("checkoutDetails", c);
		RequestDispatcher rd = request.getRequestDispatcher("checkout.jsp");
		rd.forward(request, response);
	}

}
