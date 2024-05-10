package store.BLL;

import store.DAL.Common;
import store.DAO.DataBaseBridge;
import store.modal.CheckOutModal;
import store.modal.Product;
import store.modal.ProductList;

public class CalculateBLL implements Bll {
	CheckOutModal c;

	public CalculateBLL() {
		c = new CheckOutModal();
		// TODO Auto-generated constructor stub
	}

	@Override
	public CheckOutModal getCheckOutDetails(ProductList pl) {
		// TODO Auto-generated method stub
		c.setSubTotal(pl.sum());
		DataBaseBridge dao = new DataBaseBridge();
		Common commonDALObject = dao.getCommonDAL();
		double baseShip = commonDALObject.getBaseShippingAmount(c.getSubTotal());
		c.setShipping_tax(getShippingTax(pl, baseShip, c.getSubTotal()));
		c.setShipping_charges(baseShip);
		double total = c.getSubTotal() + c.getShipping_tax() + c.getShipping_charges();
		c.setTotal(total);
		return c;
	}

	private double getShippingTax(ProductList pl, double baseShip, double subTotal) {
		// TODO Auto-generated method stub
		double cummulativeTax = 0;
		for (Product p : pl) {
			double shippingChargeForTheProduct = ((p.getPrice() * p.getQty()) / subTotal) * 100 * baseShip;
			cummulativeTax += shippingChargeForTheProduct * p.getGST();
		}
		return cummulativeTax;
	}

}
