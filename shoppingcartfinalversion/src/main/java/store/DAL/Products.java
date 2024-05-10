/**
 * 
 */
package store.DAL;

import java.sql.PreparedStatement;

import store.modal.Product;
import store.modal.ProductList;

/**
 * @author DileepKumarK
 *
 */
public interface Products {

	ProductList getProducts(PreparedStatement ps);

	ProductList getProducts(String cat, String sort);

	Product getProductById(int id);

	// ProductList getProducts(String cat);
}
