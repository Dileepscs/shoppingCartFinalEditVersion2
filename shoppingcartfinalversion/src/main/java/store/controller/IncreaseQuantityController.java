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

@WebServlet("/IncreaseQuantityController")
public class IncreaseQuantityController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int prodId = Integer.parseInt(request.getParameter("pid"));
		System.out.println("Increase");
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<CartItems> cartItems = (List<CartItems>) session.getAttribute("cartitems");
		if (cartItems != null) {
			for (CartItems item : cartItems) {
				if (item.getId() == prodId) {
					item.setQuantity(item.getQuantity() + 1);
					break;
				}
			}
		}

		session.setAttribute("cartitems", cartItems);

		// Send response to update the UI
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("success");
	}
}
