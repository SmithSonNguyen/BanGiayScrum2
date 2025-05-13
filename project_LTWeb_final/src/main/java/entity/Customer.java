package entity;

public class Customer {
	 private int userID;
	 private double tongChiTieu;
	public Customer(int userID, double tongChiTieu) {
		
		this.userID = userID;
		this.tongChiTieu = tongChiTieu;
	}
	public Customer() {
		
	}
	@Override
	public String toString() {
		return "Customer [userID=" + userID + ", tongChiTieu=" + tongChiTieu + "]";
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public double getTongChiTieu() {
		return tongChiTieu;
	}
	public void setTongChiTieu(double tongChiTieu) {
		this.tongChiTieu = tongChiTieu;
	}
		
}
