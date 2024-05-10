package store.DAO;

import store.BLL.Bll;
import store.BLL.CalculateBLL;

public class BLLBridge {
	public static Bll getBLLObject() {
		return new CalculateBLL();
	}
}
