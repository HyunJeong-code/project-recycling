package recycling.dto.buyer;

public class CartOrder {
	
	private String cCode;
	private String prdCode;
	private int cCnt;
	private String prdName;
	private int price;
	private int prdFee;
	private String storedName;
	
	public CartOrder() {}

	public CartOrder(String cCode, String prdCode, int cCnt, String prdName, int price, int prdFee, String storedName) {
		super();
		this.cCode = cCode;
		this.prdCode = prdCode;
		this.cCnt = cCnt;
		this.prdName = prdName;
		this.price = price;
		this.prdFee = prdFee;
		this.storedName = storedName;
	}

	@Override
	public String toString() {
		return "CartOrder [cCode=" + cCode + ", prdCode=" + prdCode + ", cCnt=" + cCnt + ", prdName=" + prdName
				+ ", price=" + price + ", prdFee=" + prdFee + ", storedName=" + storedName + "]";
	}

	public String getcCode() {
		return cCode;
	}

	public void setcCode(String cCode) {
		this.cCode = cCode;
	}

	public String getPrdCode() {
		return prdCode;
	}

	public void setPrdCode(String prdCode) {
		this.prdCode = prdCode;
	}

	public int getcCnt() {
		return cCnt;
	}

	public void setcCnt(int cCnt) {
		this.cCnt = cCnt;
	}

	public String getPrdName() {
		return prdName;
	}

	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPrdFee() {
		return prdFee;
	}

	public void setPrdFee(int prdFee) {
		this.prdFee = prdFee;
	}

	public String getStoredName() {
		return storedName;
	}

	public void setStoredName(String storedName) {
		this.storedName = storedName;
	}

}
