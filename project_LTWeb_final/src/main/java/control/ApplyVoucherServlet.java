/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.DAO;
import entity.Account;
import entity.Cart;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ApplyVoucherServlet", urlPatterns = {"/applyVoucher"})
public class ApplyVoucherServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String voucherCode = request.getParameter("voucherCode");
        HttpSession session = request.getSession();

        Account a = (Account) session.getAttribute("acc");
        int accountID = a.getId();

        DAO dao = new DAO();

        // Lấy danh sách giỏ hàng và sản phẩm
        List<Cart> listCart = dao.getCartByAccountID(accountID);
        List<Product> listProduct = dao.getAllProduct();

        // Tính tổng số lượng sản phẩm trong giỏ
        int totalAmount = 0;
        for (Cart cart : listCart) {
            totalAmount += cart.getAmount();
        }

        Map<Integer, Double> discountedPrices = new HashMap<>();
            double discountedPrice=0;
            double totalMoney = 0;
            for (Cart o : listCart) {
                for (Product p : listProduct) {
                    if (o.getProductID() == p.getId()) {
                        
                        double productPrice = p.getPrice();
                        double discountRate = dao.getDiscountRate(o.getProductID());
                        // Tính giá đã giảm
                        discountedPrice = p.getPrice();
                        if (discountRate > 0) {
                            discountedPrice = productPrice * (1 - discountRate );  // Giảm giá theo tỷ lệ
                        }
                           // Lưu giá đã giảm vào Map
                        discountedPrices.put(o.getProductID(), discountedPrice);                   
                        totalMoney += discountedPrice * o.getAmount();
                        request.setAttribute("discountedPrices", discountedPrices);                    
                    }
                }
            }
        double totalPayment = totalMoney ;
            if (totalPayment > 0) {
                totalPayment += 25000; // Phí vận chuyển
            } else {
                totalPayment = 0; 
            }
            

        int totalAmountCart = 0;
        if (a != null) {
            List<Cart> list = dao.getCartByAccountID(accountID);
            totalAmountCart = list.size();
        }
        String voucherMessage = "";
        double discountAmount = 0;
        if (voucherCode != null && !voucherCode.isEmpty()) {
            if (dao.isVoucherValid(voucherCode,  totalMoney)&&!dao.isVoucherLinkedToAccount(voucherCode, accountID)) {
                discountAmount = dao.getDiscountAmount(voucherCode);
                voucherMessage = "Áp mã thành công";
            } 
            else{
                voucherMessage = "Mã giảm giá không sử dụng được!.";
            }
        }
        
        totalPayment -= discountAmount;
        
        session.setAttribute("cartQuantity", totalAmountCart);
        session.setAttribute("totalPayment", totalPayment);
        session.setAttribute("voucherCode", voucherCode);
        
        request.setAttribute("listCart", listCart);
        request.setAttribute("listProduct", listProduct);
        request.setAttribute("totalAmount", totalAmount);
        request.setAttribute("totalMoney", totalMoney);
        request.setAttribute("discountPrice", discountAmount);
        request.setAttribute("discountPrice", discountAmount);
        request.setAttribute("voucherMessage", voucherMessage);

        // Chuyển tiếp đến JSP
        request.getRequestDispatcher("Cart.jsp").forward(request, response);
    }
}