package store.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import store.modal.Product;
import store.modal.ProductList;

public class ProductsDAL implements Products {

	ProductList pl = null;
	Connection con;

	public ProductsDAL() {
		pl = new ProductList();
		con = Db.connect();
	}

	@Override
	public ProductList getProducts(String cat, String sort) {
		String query = "";
		PreparedStatement ps = null;
		query = "select p.id, title, c.category, hsnc_gstc_percentage , image,  prod_mrp from product_1 p, HSNCodes_1 h, categories c, ProductStocks_1 stock where stock.prod_id=p.id and h.hsnc_id=p.hsnid and c.category_id=p.category_id";
		if ((cat == null && sort == null) || (cat.equals("*") && sort.equals("*"))) {
			try {
				System.out.println("in 1" + cat + "   -  " + sort);
				ps = con.prepareStatement(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (!cat.equals("*") && sort.equals("*")) {
			System.out.println("in 2" + cat + "   -  " + sort);
			query = query + " and c.category_id = ?";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, Integer.parseInt(cat));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (cat.equals("*") && !sort.equals("*")) {
			query = query + " orderby ?";
			System.out.println("in 1" + cat + "   -  " + sort);
			try {
				ps = con.prepareStatement(query);
				// ps.setInt(1, Integer.parseInt(cat));
				ps.setString(1, sort);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			query = query + " and category_id = ? orderby ? ";
			try {
				System.out.println("in 4" + cat + "   -  " + sort);
				ps = con.prepareStatement(query);
				ps.setInt(1, Integer.parseInt(cat));
				ps.setString(2, sort);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return getProducts(ps);

	}

	@Override
	public ProductList getProducts(PreparedStatement ps) {
		// TODO Auto-generated method stub
		try {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setTitle(rs.getString("title"));
				p.setCategory(rs.getString("category"));
				p.setGST(Double.parseDouble(rs.getString("hsnc_gstc_percentage")));
				p.setImage(rs.getString("image"));
				p.setPrice(rs.getDouble("prod_mrp"));
				pl.add(p);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pl;
	}

	@Override
	public Product getProductById(int id) {
		String query = "select * from product_1 where id = ?";
		PreparedStatement pst;
		Product p = null;
		try {
			pst = con.prepareStatement(query);
			System.out.println("connect");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int pid = rs.getInt("id");
				System.out.println("Id: " + pid);
				String pcat = rs.getString("category");
				System.out.println("category:" + pcat);
				String ptitle = rs.getString("title");
				Double pprice = rs.getDouble("price");
				String pimag = rs.getString("image");
				double gst = getGST(rs.getInt("hsnid"));
				p = new Product(pid, ptitle, pcat, pimag, pprice, gst);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return p;
	}

	public double getGST(int hsnid) throws SQLException {
		String query = "select hsnc_gstc_percentage from HSNCodes_1  where hsnc_hsncode = ?";
		PreparedStatement pst;
		pst = con.prepareStatement(query);
		pst.setInt(1, hsnid);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			return rs.getDouble(1);
		}
		return 0;
	}

}