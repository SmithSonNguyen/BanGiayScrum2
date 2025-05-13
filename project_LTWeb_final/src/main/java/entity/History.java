package entity;

public class History {
         private int historyID;
	 private int accountID;
	 private int productID;
         private String purchaseDate;
	 private int amount;
	 private String size;
	public History(int historyID, int accountID, int productID,String purchaseDate, int amount, String size) {
                this.historyID = historyID;
		this.accountID = accountID;
		this.productID = productID;
                this.purchaseDate = purchaseDate;
		this.amount = amount;
		this.size = size;
	}
	public History() {
		
	}
        public int gethistoryID() {
		return historyID;
	}
	public void sethistoryID(int historyID) {
		this.historyID = historyID;
	}
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
        public String getpurchaseDate() {
		return purchaseDate;
	}
	public void setpurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "Cart [historyID=" + historyID + ", accountID=" + accountID + ", productID=" + productID + ", purchaseDate=" + purchaseDate + ", amount=" + amount
				+ ", size=" + size + "]";
	} 
	 
	
}
