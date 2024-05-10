package store.modal;

public class Product {

	private int id;
	private String title;
	private String category;
	private double gst;
	private String image;
	private double price;
	private int qty;

	public Product(int pid, String ptitle, String pcat, String pimag, Double pprice, double GST) {
		// TODO Auto-generated constructor stub
		this.id = pid;
		this.title = ptitle;
		this.category = pcat;
		this.image = pimag;
		this.price = pprice;
		gst = GST;
		System.out.println("productss....");
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getGST() {
		return gst;
	}

	public void setGST(double gst) {
		this.gst = gst;
	}
}
