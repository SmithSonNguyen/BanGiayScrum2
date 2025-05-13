/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import entity.Account;
import entity.Cart;
import entity.Review;
import entity.History;
import entity.Category;
import entity.Customer;
import entity.Invoice;
import entity.Product;
import entity.Promotion;
import entity.Reply;
import entity.Voucher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "select * from product";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public List<History> getTop10SanPhamBanChay() {
        List<History> list = new ArrayList<>();
        String query = "SELECT productID, SUM(amount) AS totalSold FROM history GROUP BY productID ORDER BY totalSold DESC LIMIT 10";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new History(
                        0, // historyID is not relevant here
                        0, // accountID is not relevant here
                        rs.getInt("productID"),
                        null, // purchaseDate is not relevant here
                        rs.getInt("totalSold"), // Aggregate amount (total sold quantity)
                        null));// size is not relevant here

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public List<Invoice> getAllInvoice() {
        List<Invoice> list = new ArrayList<>();
        String query = "select * from invoice";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Invoice(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getDate(4),
                        rs.getString(5)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public int countAllProductBySellID(int sell_ID) {
        String query = "select count(*) from product where sell_ID=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, sell_ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return 0;
    }

    public int getSellIDByProductID(int productID) {
        String query = "select sell_ID\r\n"
                + "from product\r\n"
                + "where id=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, productID);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return 0;
    }

    public double totalMoneyDay(int day) {
        String query = "SELECT SUM(tongGia) AS totalPrice FROM invoice WHERE DAYOFWEEK(ngayXuat) = ? GROUP BY ngayXuat";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, day);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return 0;
    }

    public double totalMoneyMonth(int month) {
        String query = "select SUM(tongGia) from invoice\r\n"
                + "where MONTH(ngayXuat)=?\r\n"
                + "Group by MONTH(ngayXuat)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, month);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return 0;
    }

    public int countAllProduct() {
        String query = "select count(*) from product";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return 0;
    }

    public double sumAllInvoice() {
        String query = "select SUM(tongGia) from invoice";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return 0;
    }

    public int countAllReview() {
        String query = "select count(*) from review";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return 0;
    }

    public int getCateIDByProductID(String id) {
        String query = "select cateID from product\r\n"
                + "where id =?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return 0;
    }

    public List<Account> getAllAccount() {
        List<Account> list = new ArrayList<>();
        String query = "select * from account";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(
                        rs.getInt("uID"),
                        rs.getString("user"),
                        rs.getString("pass"),
                        rs.getInt("isAdmin"),
                        rs.getString("email"),
                        rs.getString("avatar"),
                        rs.getString("fullName"),
                        rs.getString("dob"),
                        rs.getString("address"),
                        rs.getString("phoneNumber")
                ));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public List<Customer> getTop5KhachHang() {
        List<Customer> list = new ArrayList<>();
        String query = "SELECT accountID, SUM(tongGia) AS totalSpent FROM invoice GROUP BY accountID ORDER BY totalSpent DESC LIMIT 5";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Customer(rs.getInt(1),
                        rs.getDouble(2)
                ));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public List<Product> getTop3() {
        List<Product> list = new ArrayList<>();
        String query = "select top 3 * from product";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public List<Product> getNext3Product(int amount) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM product ORDER BY id LIMIT 3 OFFSET ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, amount);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public List<Product> getNext4GVProduct(int amount) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM product WHERE cateID = 1 ORDER BY id DESC LIMIT 4 OFFSET ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, amount);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public List<Product> getNext4GNProduct(int amount) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM product WHERE cateID = 2 ORDER BY id DESC LIMIT 4 OFFSET ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, amount);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public List<Product> getNext4HogotProduct(int amount) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM product WHERE cateID = 3 ORDER BY id DESC LIMIT 4 OFFSET ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, amount);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public List<Product> getProductByCID(String cid) {
        List<Product> list = new ArrayList<>();
        String query = "select * from product\n"
                + "where cateID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public List<Product> getProductBySellIDAndIndex(int id, int indexPage) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM product WHERE sell_ID = ? ORDER BY id LIMIT 5 OFFSET ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, (indexPage - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public List<Product> getProductByIndex(int indexPage) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM product ORDER BY id LIMIT 9 OFFSET ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, (indexPage - 1) * 9);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public List<Product> searchByName(String txtSearch) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM product WHERE name LIKE ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            String modifiedSearch = txtSearch.replace(" ", "%");
            ps.setString(1, "%" + modifiedSearch + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public List<Invoice> searchByNgayXuat(String ngayXuat) {
        List<Invoice> list = new ArrayList<>();
        String query = "select * from invoice\r\n"
                + "where ngayXuat ='" + ngayXuat + "'";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
//            ps.setString(1,ngayXuat);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Invoice(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getDate(4),
                        rs.getString(5)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public List<Product> searchPriceUnder400() {
        List<Product> list = new ArrayList<>();
        String query = "select * from product\r\n"
                + "where price < 400000";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public List<Product> searchPrice400To500() {
        List<Product> list = new ArrayList<>();
        String query = "select * from product\r\n"
                + "where price >= 400000 and price<=500000";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public List<Product> searchColorWhite() {
        List<Product> list = new ArrayList<>();
        String query = "select * from product\r\n"
                + "where color = 'White'";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public List<Product> searchColorGray() {
        List<Product> list = new ArrayList<>();
        String query = "select * from product\r\n"
                + "where color = 'Gray'";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public List<Product> searchColorBlack() {
        List<Product> list = new ArrayList<>();
        String query = "select * from product\r\n"
                + "where color = 'Black'";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public List<Product> searchColorYellow() {
        List<Product> list = new ArrayList<>();
        String query = "select * from product\r\n"
                + "where color = 'Yellow'";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public List<Product> searchByPriceMinToMax(String priceMin, String priceMax) {
        List<Product> list = new ArrayList<>();
        String query = "select * from product\r\n"
                + "where price >= ? and price<=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, priceMin);
            ps.setString(2, priceMax);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public List<Product> searchPriceAbove500() {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product\r\n"
                + "where price > 500000";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public List<Product> getRelatedProduct(int cateIDProductDetail) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM product WHERE cateID = ? ORDER BY id DESC LIMIT 4";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, cateIDProductDetail);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public List<Review> getAllReviewByProductID(String productId) {
        List<Review> list = new ArrayList<>();
        String query = "SELECT maReview, accountID, productID, contentReview, dateReview, rating FROM review WHERE productID = ?";
        try {
            conn = new DBContext().getConnection(); // Mở kết nối SQL
            ps = conn.prepareStatement(query);
            ps.setString(1, productId); // Gán tham số
            rs = ps.executeQuery();

            // Lấy kết quả và thêm vào danh sách
            while (rs.next()) {
                int maReview = rs.getInt("maReview"); // Lấy theo tên cột
                int accountID = rs.getInt("accountID");
                int productIDValue = rs.getInt("productID");
                String contentReview = rs.getString("contentReview");
                Date dateReview = rs.getDate("dateReview");
                int rating = rs.getInt("rating"); // Lấy cột rating

                list.add(new Review(maReview, accountID, productIDValue, contentReview, dateReview, rating)); // Thêm rating
            }
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi để dễ dàng debug
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace(); // Đảm bảo đóng tài nguyên
            }
        }
        return list;
    }

    public List<Reply> getReplyAll() {
        String query = "SELECT * FROM reply ORDER BY replyID DESC";
        List<Reply> replyList = new ArrayList<>();

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            try (ResultSet rs = ps.executeQuery()) {
                // Duyệt qua kết quả và thêm các phản hồi vào danh sách
                while (rs.next()) {
                    Reply reply = new Reply(
                            rs.getInt("replyID"), // replyID
                            rs.getInt("reviewID"), // reviewID
                            rs.getInt("accountID"), // accountID
                            rs.getString("contentReply"), // Nội dung phản hồi
                            rs.getDate("dateReply") // Ngày phản hồi
                    );
                    replyList.add(reply);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();  // In ra lỗi nếu có
        } finally {
            new DBContext().closeConnection(conn);
        }

        return replyList;  // Trả về danh sách phản hồi
    }

    public Product getProductByID(String id) {
        String query = "select * from product\n"
                + "where id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return null;
    }

    public List<Cart> getCartByAccountID(int accountID) {
        List<Cart> list = new ArrayList<>();
        String query = "select * from cart where accountID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountID);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Cart(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public String getProductNameByID(int productID) {
        String query = "SELECT name FROM product WHERE id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, productID);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("name"); // Trả về tên sản phẩm
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            new DBContext().closeConnection(conn);
        }
        return null;
    }

    public Cart checkCartExist(int accountID, int productID, String size) {
        String query = "SELECT * FROM cart WHERE accountID = ? AND productID = ? AND size = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountID);
            ps.setInt(2, productID);
            ps.setString(3, size);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Cart(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi nếu có
        } finally {
            new DBContext().closeConnection(conn);
        }
        return null;
    }

    public List<History> getHistoryByAccountID(int accountID) {
        List<History> list = new ArrayList<>();
        String query = "select * from history where accountID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountID);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new History(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public int checkAccountAdmin(int userID) {

        String query = "select isAdmin from account where uID=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, userID);

            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {

        } finally {
            new DBContext().closeConnection(conn);
        }
        return 0;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "select * from category";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

//
    public Product getLast() {
        String query = "SELECT * FROM product ORDER BY id DESC LIMIT 1";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return null;
    }

    public List<Product> get8Last() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM product ORDER BY id DESC LIMIT 8";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public List<Product> get4GVLast() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM product WHERE cateID = 1 ORDER BY id DESC LIMIT 4";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public List<Product> get4GNLast() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM product WHERE cateID = 2 ORDER BY id DESC LIMIT 4";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public List<Product> get4hogotLast() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM product WHERE cateID = 3 ORDER BY id DESC LIMIT 4";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return list;
    }

    public Account login(String user, String pass) {
        String query = "SELECT * FROM account WHERE `user` = ? AND pass = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(
                        rs.getInt("uID"),
                        rs.getString("user"),
                        rs.getString("pass"),
                        rs.getInt("isAdmin"),
                        rs.getString("email"),
                        rs.getString("avatar"),
                        rs.getString("fullName"),
                        rs.getString("dob"),
                        rs.getString("address"),
                        rs.getString("phoneNumber"));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return null;
    }

    public Account checkAccountExist(String user) {
        String query = "SELECT * FROM account WHERE user = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(
                        rs.getInt("uID"),
                        rs.getString("user"),
                        rs.getString("pass"),
                        rs.getInt("isAdmin"),
                        rs.getString("email"),
                        rs.getString("avatar"),
                        rs.getString("fullName"),
                        rs.getString("dob"),
                        rs.getString("address"),
                        rs.getString("phoneNumber"));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return null;
    }

    public Account checkAccountExistByUsernameAndEmail(String username, String email) {
        String query = "SELECT * FROM account WHERE user = ? AND email = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(
                        rs.getInt("uID"),
                        rs.getString("user"),
                        rs.getString("pass"),
                        rs.getInt("isAdmin"),
                        rs.getString("email"),
                        rs.getString("avatar"),
                        rs.getString("fullName"),
                        rs.getString("dob"),
                        rs.getString("address"),
                        rs.getString("phoneNumber"));
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return null;
    }

    public Review getNewReview(int accountID, int productID) {
        String query = "SELECT * FROM review WHERE accountID = ? AND productID = ? ORDER BY maReview DESC LIMIT 1";
        return null;
    }

    public void singup(String user, String pass, String email) {
        String query = "insert into account(pass,isAdmin,email,user,avatar,fullName,dob,address,phoneNumber)\n"
                + "values(?,0,?,?,'https://res.cloudinary.com/ds1rgnuvr/image/upload/v1733239477/928429_account_customer_profile_user_icon_akxdo2.png','',0,'','')";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, pass);
            ps.setString(2, email);
            ps.setString(3, user);
            ps.executeUpdate();
            System.out.println("Tài khoản đã được thêm thành công.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            new DBContext().closeConnection(conn);
        }
    }

    public void deleteInvoiceByAccountId(String id) {
        String query = "delete from invoice\n"
                + "where accountID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
    }

    public void deleteProduct(String pid) {
        String query = "delete from product\n"
                + "where id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, pid);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
    }

    public void deleteProductBySellID(String id) {
        String query = "delete from product\n"
                + "where sell_ID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
    }

    public void deleteCartByAccountID(int accountID) {
        String query = "delete from cart \r\n"
                + "where accountID=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountID);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
    }

    public void deleteCartByProductID(String productID) {
        String query = "delete from cart where productID=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, productID);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
    }

    public void deleteReviewByProductID(String productID) {
        String query = "delete from review where productID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, productID);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
    }

    public void deleteReviewByAccountID(String id) {
        String query = "delete from review where accountID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
    }

    public void deleteAccount(String id) {
        String query = "delete from account where uID= ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
    }

    public void deleteCart(int accountID, int productID, String size) {
        String query = "DELETE FROM cart WHERE accountID = ? AND productID = ? AND size = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountID);
            ps.setInt(2, productID);
            ps.setString(3, size);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            new DBContext().closeConnection(conn);
        }
    }

    public void deleteHistory(int historyID) {
        String query = "delete from history where historyID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, historyID);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
    }

    public void insertProduct(String name, String image, String price, String title, String description,
            String category, int sid, String color, String image2, String image3, String image4) {
        String query = "insert product(name,image,price,title,\r\n"
                + "description,cateID,sell_ID,\r\n"
                + "color,image2,image3,image4)\r\n"
                + "values(N'" + name + "','" + image + "','" + price + "',N'" + title + "',N'" + description + "','" + category + "','" + sid + "',N'" + color + "',N'" + image2 + "','" + image3 + "','" + image4 + "')";
        try {

            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            /*
			 * ps.setString(1, name); ps.setString(2, image); ps.setString(3, price);
			 * ps.setString(4, title); ps.setString(5, description); ps.setString(6,
			 * category); ps.setInt(7, sid); ps.setString(8, model); ps.setString(9, color);
			 * ps.setString(10, delivery); ps.setString(11, image2); ps.setString(12,
			 * image3); ps.setString(13, image4);
             */
            ps.executeUpdate();

        } catch (Exception e) {

        } finally {
            new DBContext().closeConnection(conn);
        }
    }

    public void insertReply(int accountID, int reviewID, String replyContent) {
        String query = "insert reply(accountID, reviewID, contentReply, dateReply)\r\n"
                + "values(?,?,?,?)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountID);
            ps.setInt(2, reviewID);
            ps.setString(3, replyContent);
            ps.setDate(4, getCurrentDate());
            ps.executeUpdate();

        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
    }

    public Reply getReply(int accountID, int reviewID) {
        String query = "SELECT * FROM reply WHERE accountID = ? AND reviewID = ? ORDER BY replyID DESC";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, accountID);
            ps.setInt(2, reviewID);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Reply(
                            rs.getInt("replyID"),
                            rs.getInt("reviewID"),
                            rs.getInt("accountID"),
                            rs.getString("contentReply"),
                            rs.getDate("dateReply")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();  // In lỗi nếu có
        } finally {
            new DBContext().closeConnection(conn);
        }
        return null;  // Trả về null nếu không tìm thấy phản hồi
    }

    private static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }

    public void insertReview(int accountID, int productID, String contentReview, int rating) {
        String query = "INSERT INTO review(accountID, productID, contentReview, rating, dateReview) "
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection(); // Mở kết nối với SQL
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountID);          // Truyền accountID
            ps.setInt(2, productID);          // Truyền productID
            ps.setString(3, contentReview);   // Truyền nội dung đánh giá
            ps.setInt(4, rating);             // Truyền giá trị rating
            ps.setDate(5, getCurrentDate());  // Truyền ngày hiện tại
            ps.executeUpdate();               // Thực thi câu lệnh
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi ra console nếu xảy ra
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                new DBContext().closeConnection(conn);
            }
        }
    }

    public void insertInvoice(int accountID, double tongGia, String phuongThuc) {
        String query = "insert invoice(accountID,tongGia,ngayXuat,phuongThuc)\r\n"
                + "values(?,?,?,?)";

        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountID);
            ps.setDouble(2, tongGia);
            ps.setDate(3, getCurrentDate());
            ps.setString(4, phuongThuc);
            ps.executeUpdate();

        } catch (Exception e) {

        } finally {
            new DBContext().closeConnection(conn);
        }
    }

    public void insertCart(int accountID, int productID, int amount, String size) {
        String query = "insert cart(accountID, productID, amount,size)\r\n"
                + "values(?,?,?,?)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountID);
            ps.setInt(2, productID);
            ps.setInt(3, amount);
            ps.setString(4, size);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
    }

    public void insertHistory(int accountID, int productID, int amount, String size) {
        String query = "insert history(accountID, productID, amount,size)\r\n"
                + "values(?,?,?,?)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountID);
            ps.setInt(2, productID);
            ps.setInt(3, amount);
            ps.setString(4, size);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
    }

    public void editPhuongThuc(int maHD) {
        String query = "UPDATE invoice SET phuongThuc = ? WHERE maHD = ?";
        String phuongThuc = "Đã xác nhận chuyển khoản";
        try {
            conn = new DBContext().getConnection();  // Mở kết nối với DB
            ps = conn.prepareStatement(query);
            ps.setString(1, phuongThuc);
            ps.setInt(2, maHD);
            ps.executeUpdate();  // Thực thi câu lệnh
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            new DBContext().closeConnection(conn);
        }

    }

    public void editProduct(String pname, String pimage, String pprice, String ptitle, String pdescription, String pcategory,
            String pcolor, String pimage2, String pimage3, String pimage4, String pid) {
        String query = "UPDATE product\n"
                + "SET `name` = ?, \n"
                + "    `image` = ?, \n"
                + "    price = ?, \n"
                + "    title = ?, \n"
                + "    `description` = ?, \n"
                + "    cateID = ?, \n"
                + "    color = ?, \n"
                + "    image2 = ?, \n"
                + "    image3 = ?, \n"
                + "    image4 = ? \n"
                + "WHERE `id` = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, pname);
            ps.setString(2, pimage);
            ps.setString(3, pprice);
            ps.setString(4, ptitle);
            ps.setString(5, pdescription);
            ps.setString(6, pcategory);
            ps.setString(7, pcolor);
            ps.setString(8, pimage2);
            ps.setString(9, pimage3);
            ps.setString(10, pimage4);
            ps.setString(11, pid);
            ps.executeUpdate();

        } catch (Exception e) {

        } finally {
            new DBContext().closeConnection(conn);
        }
    }

    public void editProfile(String username, String password, String email, String avatarUrl,
            String fullName, String dob, String address, String phoneNumber, int id) {
        String query = "UPDATE account SET user = ?, pass = ?, email = ?, avatar = ?, "
                + "fullName = ?, dob = ?, address = ?, phoneNumber = ? WHERE uID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);  // Mật khẩu sẽ không thay đổi nếu không cập nhật
            ps.setString(3, email);
            ps.setString(4, avatarUrl);
            ps.setString(5, fullName);
            ps.setString(6, dob);
            ps.setString(7, address);
            ps.setString(8, phoneNumber);
            ps.setInt(9, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void updatePassword(String oldPassword, String newPassword, int id) {
        String checkPasswordQuery = "SELECT pass FROM account WHERE uID = ?";
        String updatePasswordQuery = "UPDATE account SET pass = ? WHERE uID = ?";

        try {
            conn = new DBContext().getConnection();

            // Kiểm tra mật khẩu cũ
            ps = conn.prepareStatement(checkPasswordQuery);
            ps.setInt(1, id); // Lấy thông tin người dùng theo id
            rs = ps.executeQuery();

            if (rs.next()) {
                String currentPassword = rs.getString("pass");

                // So sánh mật khẩu cũ
                if (currentPassword.trim().equals(oldPassword.trim())) {
                    // Nếu mật khẩu cũ đúng, cập nhật mật khẩu mới
                    ps = conn.prepareStatement(updatePasswordQuery);
                    ps.setString(1, newPassword); // Cập nhật mật khẩu mới
                    ps.setInt(2, id); // Dùng id người dùng để thay đổi mật khẩu của tài khoản đó
                    ps.executeUpdate();
                    System.out.println("Mật khẩu đã được cập nhật thành công.");
                } else {
                    throw new RuntimeException("Mật khẩu cũ không chính xác.");
                }
            } else {
                throw new RuntimeException("Không tìm thấy tài khoản với ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Đã xảy ra lỗi khi cập nhật mật khẩu: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void editTongBanHang(int sell_ID, double tongTienBanHangThem) {
        String query = "exec dbo.proc_CapNhatTongBanHang ?,?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, sell_ID);
            ps.setDouble(2, tongTienBanHangThem);

            ps.executeUpdate();

        } catch (Exception e) {

        } finally {
            new DBContext().closeConnection(conn);
        }
    }

    public void editAmountAndSizeCart(int accountID, int productID, int amount, String size) {
        String query = "UPDATE cart SET amount = ? WHERE accountID = ? AND productID = ? AND size = ?";
        try {
            conn = new DBContext().getConnection();  // Mở kết nối với SQL
            ps = conn.prepareStatement(query);
            ps.setInt(1, amount);                   // Cập nhật số lượng
            ps.setInt(2, accountID);                // ID tài khoản
            ps.setInt(3, productID);                // ID sản phẩm
            ps.setString(4, size);                  // Kích thước
            ps.executeUpdate();                     // Thực thi cập nhật
        } catch (Exception e) {
            e.printStackTrace();                    // In ra lỗi nếu có
        } finally {
            new DBContext().closeConnection(conn);
        }
    }

    public void editAmountCart(int accountID, int productID, int amount, String size) {
        // Nếu số lượng còn lại bằng 0, xóa sản phẩm khỏi giỏ hàng
        // Nếu số lượng > 0, chỉ cần cập nhật số lượng
        String query = "UPDATE cart SET amount = ? WHERE accountID = ? AND productID = ? AND size = ?";
        try {
            conn = new DBContext().getConnection();  // Mở kết nối với DB
            ps = conn.prepareStatement(query);
            ps.setInt(1, amount);
            ps.setInt(2, accountID);
            ps.setInt(3, productID);
            ps.setString(4, size);
            ps.executeUpdate();  // Thực thi câu lệnh
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            new DBContext().closeConnection(conn);
        }

    }

    public boolean isVoucherUsable(String code, int accountId, double totalMoney) {
        String query = "SELECT COUNT(*) \n"
                + "FROM voucher v\n"
                + "LEFT JOIN voucher_acc va ON v.voucher_id = va.voucher_id\n"
                + "WHERE v.code = ?\n"
                + "AND (v.start_date <= NOW() AND v.end_date >= NOW() AND v.min_spend <= ?)\n"
                + "AND va.account_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, code); // Mã giảm giá
            ps.setDouble(2, totalMoney); // Tổng tiền cần thanh toán
            ps.setInt(3, accountId); // ID tài khoản người dùng
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) <= 0; // Trả về true nếu mã có thể sử dụng
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return false;
    }

    public void insertVoucherAcc(int accountId, String code) {
        String query = "INSERT INTO voucher_acc (account_id, voucher_id, used_at) \n"
                + "VALUES (?, (SELECT voucher_id FROM voucher WHERE code = ?), NOW())";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountId); // Gán giá trị cho account_id
            ps.setString(2, code);   // Gán giá trị cho mã code
            ps.executeUpdate();      // Thực thi lệnh
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi ra màn hình console (nên xử lý tốt hơn trong thực tế)
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public double getDiscountAmount(String code) {
        String query = "SELECT discount_amount FROM voucher WHERE code = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, code);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("discount_amount");
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return 0;
    }
    
    public List<Voucher> getAllVouchers() {
        List<Voucher> vouchers = new ArrayList<>();
        String query = "SELECT * FROM voucher";

        try {
            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                int voucherId = rs.getInt("voucher_id");
                String code = rs.getString("code");
                double discountAmount = rs.getDouble("discount_amount");
                double minSpend = rs.getDouble("min_spend");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");

                Voucher voucher = new Voucher(voucherId, code, discountAmount, minSpend, startDate, endDate, createdAt, updatedAt);
                vouchers.add(voucher);
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }

        return vouchers;
    }

    public List<Voucher> getActiveVouchers() {
        List<Voucher> vouchers = new ArrayList<>();
        String query = "SELECT * FROM voucher WHERE end_date >= NOW() AND start_date <= NOW()";
        try {
            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                int voucherId = rs.getInt("voucher_id");
                String code = rs.getString("code");
                double discountAmount = rs.getDouble("discount_amount");
                double minSpend = rs.getDouble("min_spend");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");

                Voucher voucher = new Voucher(voucherId, code, discountAmount, minSpend, startDate, endDate, createdAt, updatedAt);
                vouchers.add(voucher);
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return vouchers;
    }


    // Lấy voucher chưa tới hạn sử dụng
    public List<Voucher> getNotStartedVouchers() {
        List<Voucher> vouchers = new ArrayList<>();
        String query = "SELECT * FROM voucher WHERE start_date > NOW()";
        try {
            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                int voucherId = rs.getInt("voucher_id");
                String code = rs.getString("code");
                double discountAmount = rs.getDouble("discount_amount");
                double minSpend = rs.getDouble("min_spend");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");

                Voucher voucher = new Voucher(voucherId, code, discountAmount, minSpend, startDate, endDate, createdAt, updatedAt);
                vouchers.add(voucher);
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }

        return vouchers;
    }

    // Lấy voucher đã hết hạn sử dụng
    public List<Voucher> getExpiredVouchers() {
        List<Voucher> vouchers = new ArrayList<>();
        String query = "SELECT * FROM voucher WHERE end_date < NOW()";
        try {
            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                int voucherId = rs.getInt("voucher_id");
                String code = rs.getString("code");
                double discountAmount = rs.getDouble("discount_amount");
                double minSpend = rs.getDouble("min_spend");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");

                Voucher voucher = new Voucher(voucherId, code, discountAmount, minSpend, startDate, endDate, createdAt, updatedAt);
                vouchers.add(voucher);
            }
        } catch (Exception e) {
        } finally {
            new DBContext().closeConnection(conn);
        }
        return vouchers;
    }

    public boolean deleteVoucherById(int id) {
        String query = "DELETE FROM voucher WHERE voucher_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            new DBContext().closeConnection(conn);
        }
        return false;
    }

    public Voucher getVoucherById(int id) {
        String query = "SELECT * FROM voucher WHERE voucher_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Voucher(
                        rs.getInt("voucher_id"),
                        rs.getString("code"),
                        rs.getDouble("discount_amount"),
                        rs.getDouble("min_spend"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            new DBContext().closeConnection(conn);
        }
        return null;
    }

    public boolean updateVoucher(Voucher voucher) {
        String query = "UPDATE voucher SET code = ?, discount_amount = ?, min_spend = ?, \n"
                + "                  start_date = ?, end_date = ?, updated_at = NOW() WHERE voucher_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, voucher.getCode());
            ps.setDouble(2, voucher.getDiscountAmount());
            ps.setDouble(3, voucher.getMinSpend());
            ps.setDate(4, new java.sql.Date(voucher.getStartDate().getTime()));
            ps.setDate(5, new java.sql.Date(voucher.getEndDate().getTime()));
            ps.setInt(6, voucher.getVoucherId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            new DBContext().closeConnection(conn);
        }
        return false;
    }

    public boolean addVoucher(Voucher voucher) {
        String query = "INSERT INTO voucher (code, discount_amount, min_spend, start_date, end_date, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, voucher.getCode());
            ps.setDouble(2, voucher.getDiscountAmount());
            ps.setDouble(3, voucher.getMinSpend());
            ps.setDate(4, new java.sql.Date(voucher.getStartDate().getTime()));
            ps.setDate(5, new java.sql.Date(voucher.getEndDate().getTime()));
            ps.setDate(6, new java.sql.Date(voucher.getCreatedAt().getTime()));
            ps.setDate(7, new java.sql.Date(voucher.getUpdatedAt().getTime()));

            int result = ps.executeUpdate();
            return result > 0; // Trả về true nếu thêm thành công
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            new DBContext().closeConnection(conn);
        }
        return false;
    }

    public List<List<String>> getUsersByVoucher(int voucherId) {
        String query = "SELECT account.user, voucher_acc.used_at "
                + "FROM voucher_acc "
                + "JOIN account ON voucher_acc.account_id = account.uID "
                + "WHERE voucher_acc.voucher_id = ?";
        List<List<String>> usages = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, voucherId);
            rs = ps.executeQuery();
            while (rs.next()) {
                List<String> usage = new ArrayList<>();
                usage.add(rs.getString("user"));       // Thêm `user` vào danh sách con
                usage.add(rs.getString("used_at"));   // Thêm `used_at` vào danh sách con
                usages.add(usage);                    // Thêm danh sách con vào danh sách lớn
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usages;
    }

    public List<Product> getPromotionProducts() {
        List<Product> promotionProducts = new ArrayList<>();
        String sql = "SELECT * FROM promotion p JOIN product pr ON p.productID = pr.id WHERE CURDATE() BETWEEN p.startDate AND p.endDate;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("productID"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setImage(rs.getString("image"));
                promotionProducts.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return promotionProducts;
    }

    public boolean isProductOnPromotion(int productId) {
        String sql = "SELECT * FROM promotion WHERE productID = ?;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            return rs.next();  // Returns true if product is part of a promotion
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public double getDiscountRate(int productID) {
        String sql = "SELECT discountRate FROM promotion WHERE productID = ? AND CURDATE() BETWEEN startDate AND endDate;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, productID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("discountRate"); // Trả về tỷ lệ giảm giá
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0; // Không có khuyến mãi
    }

    public List<Promotion> getAllPromotions() {
        List<Promotion> promotions = new ArrayList<>();
        String sql = "SELECT * FROM promotion;";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Promotion promo = new Promotion();
                promo.setId(rs.getInt("id"));
                promo.setProductID(rs.getInt("productID"));
                promo.setDiscountRate(rs.getDouble("discountRate"));
                promo.setStartDate(rs.getDate("startDate"));
                promo.setEndDate(rs.getDate("endDate"));
                promotions.add(promo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return promotions;
    }

    public boolean addPromotion(Promotion promotion) {
        String sql = "INSERT INTO promotion (productID, discountRate, startDate, endDate) VALUES (?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, promotion.getProductID());
            ps.setDouble(2, promotion.getDiscountRate());
            ps.setDate(3, (java.sql.Date) promotion.getStartDate());
            ps.setDate(4, (java.sql.Date) promotion.getEndDate());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Promotion getPromotionById(int id) {
        String query = "SELECT * FROM promotion WHERE id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Promotion(rs.getInt("id"), rs.getInt("productID"),
                        rs.getDouble("discountRate"), rs.getDate("startDate"), rs.getDate("endDate"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePromotion(Promotion promotion) {
        String sql = "UPDATE promotion SET productID = ?, discountRate = ?, startDate = ?, endDate = ? WHERE id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, promotion.getProductID());
            ps.setDouble(2, promotion.getDiscountRate());
            ps.setDate(3, (java.sql.Date) promotion.getStartDate());
            ps.setDate(4, (java.sql.Date) promotion.getEndDate());
            ps.setInt(5, promotion.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletePromotionById(int id) {
        String query = "DELETE FROM promotion WHERE id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            new DBContext().closeConnection(conn);
        }
        return false;
    }
    public boolean isVoucherValid(String code, double totalMoney) {
        String query = "SELECT COUNT(*) "
                + "FROM voucher "
                + "WHERE code = ? "
                + "AND start_date <= NOW() "
                + "AND end_date >= NOW() "
                + "AND min_spend <= ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, code); // Mã giảm giá
            ps.setDouble(2, totalMoney); // Tổng tiền cần thanh toán
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu có bản ghi phù hợp
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false; // Trả về false nếu có lỗi hoặc không có bản ghi
    }

    public boolean isVoucherLinkedToAccount(String code, int accountId) {
        String query = "SELECT COUNT(*) " +
                       "FROM voucher v " +
                       "INNER JOIN voucher_acc va ON v.voucher_id = va.voucher_id " +
                       "WHERE v.code = ? " +
                       "AND va.account_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, code); // Mã giảm giá
            ps.setInt(2, accountId); // ID tài khoản người dùng
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu có bản ghi phù hợp
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false; // Trả về false nếu có lỗi hoặc không có bản ghi
    }
    
    public static void main(String[] args) {
        DAO dao = new DAO();

    }

}
