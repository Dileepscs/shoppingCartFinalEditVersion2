package store.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommonDAL implements Common {
	Connection con;

	public CommonDAL() {
		con = Db.connect();
	}

	public double getBaseShippingAmount(double amt) {

		double ship = 50;
		try {
			PreparedStatement ps = con.prepareStatement(
					"SELECT orvl_shippingamount FROM ordervaluewiseshippingcharges_1 WHERE orvl_id = (SELECT orvl_id FROM ordervaluewiseshippingcharges_1 WHERE orvl_from <= ? ORDER BY orvl_from DESC LIMIT 1 )");
			ps.setDouble(1, amt);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ship = Double.parseDouble(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ship;
	}

}
