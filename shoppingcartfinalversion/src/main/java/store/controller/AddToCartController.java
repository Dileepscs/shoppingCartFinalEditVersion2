package store.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import store.modal.CartItems;

@WebServlet("/AddToCartController")
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int prodId = Integer.parseInt(request.getParameter("pid"));

		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<CartItems> cartItems = (List<CartItems>) session.getAttribute("cartitems");
		if (cartItems == null) {
			cartItems = new ArrayList<>();
		}

		boolean productExists = false;
		for (CartItems item : cartItems) {
			if (item.getId() == prodId) {
				item.setQuantity(item.getQuantity() + 1);
				productExists = true;
				break;
			}
		}

		if (!productExists) {
			CartItems item = new CartItems(prodId, 1);
			cartItems.add(item);
		}

		session.setAttribute("cartitems", cartItems);

		request.getRequestDispatcher("cart.jsp").forward(request, response);
	}
}
