package store.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import store.modal.CartItems;

/**
 * Servlet implementation class DecreaseQuantityController
 */
@WebServlet("/DecreaseQuantityController")
public class DecreaseQuantityController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int prodId = Integer.parseInt(request.getParameter("pid"));

		HttpSession session = request.getSession();
		List<CartItems> cartItems = (List<CartItems>) session.getAttribute("cartitems");
		if (cartItems != null) {
			for (CartItems item : cartItems) {
				if (item.getId() == prodId) {
					int newQuantity = item.getQuantity() - 1;
					if (newQuantity >= 0) {
						item.setQuantity(newQuantity);
						break;
					}
				}
			}
		}
	}
}
