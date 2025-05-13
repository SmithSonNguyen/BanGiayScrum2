package entity;

public class Account {
    private int id;
    private String user;
    private String pass;
    private int isAdmin;
    private String email;
    private String avatar;
    private String fullName;        
    private String dob;                
    private String address;         
    private String phoneNumber;    

    // Getter và Setter cho avatar
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    // Getter và Setter cho email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }    

    // Getter và Setter cho id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter và Setter cho user
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    // Getter và Setter cho pass
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    // Getter và Setter cho isAdmin
    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    // Getter và Setter cho fullName
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // Getter và Setter cho dob
    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    // Getter và Setter cho address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Getter và Setter cho phoneNumber
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Constructor đầy đủ
    public Account(int id, String user, String pass, int isAdmin, String email, String avatar, String fullName, String dob, String address, String phoneNumber) {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.isAdmin = isAdmin;
        this.email = email;
        this.avatar = avatar;
        this.fullName = fullName;
        this.dob = dob;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    // Constructor mặc định
    public Account() {
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "Account [id=" + id + ", user=" + user + ", pass=" + pass + ", isAdmin=" + isAdmin
                + ", email=" + email + ", avatar=" + avatar + ", fullName=" + fullName + ", dob=" + dob+ ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
    }
}