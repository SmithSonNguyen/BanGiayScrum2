/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import entity.Account;
import entity.Cart;
import entity.Category;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "TotalMoneyCartControl", urlPatterns = {"/totalMoneyCart"})
public class TotalMoneyCartControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("acc");
            if (a == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            int accountID = a.getId();
            DAO dao = new DAO();
            List<Cart> listCart = dao.getCartByAccountID(accountID);
            List<Product> listProduct = dao.getAllProduct();
            
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
            
            String voucherCode = request.getParameter("voucherCode");
            double discountAmount = 0;

            if (voucherCode != null && !voucherCode.isEmpty()) {
                if (dao.isVoucherUsable(voucherCode, accountID, totalMoney)) {
                    discountAmount = dao.getDiscountAmount(voucherCode);
                } 
            }
            
            double totalPayment = totalMoney - discountAmount;
            if (totalPayment > 0) {
                totalPayment += 25000; // Phí vận chuyển
            } else {
                totalPayment = 0; 
            }
            
            // Đưa dữ liệu vào request để chuyển qua JSP
            request.setAttribute("listCart", listCart);
            request.setAttribute("listProduct", listProduct);
            request.setAttribute("totalMoney", totalMoney);
            request.setAttribute("totalPayment", totalPayment);
            request.setAttribute("discountPrice", discountAmount);
            request.getRequestDispatcher("Cart.jsp").forward(request, response);
        }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
