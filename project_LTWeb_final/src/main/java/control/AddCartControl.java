/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import entity.Account;
import entity.Cart;
import entity.Product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "AddCartControl", urlPatterns = {"/addCart"})
public class AddCartControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8"); 
        int productID = Integer.parseInt(request.getParameter("pid"));
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        
        if(a == null) {
            response.sendRedirect("login");
            return;
        }
        
        int accountID = a.getId();
        int amount = Integer.parseInt(request.getParameter("quantity"));
        String size = request.getParameter("size");
        DAO dao = new DAO();
        String productName = dao.getProductNameByID(productID);
        // Kiểm tra nếu sản phẩm có size tương ứng đã có trong giỏ hàng
        Cart cartExisted = dao.checkCartExist(accountID, productID, size);
        if (cartExisted != null) {
            // Nếu sản phẩm có size tương ứng đã có trong giỏ, tăng số lượng
            int amountExisted = cartExisted.getAmount();
            dao.editAmountAndSizeCart(accountID, productID, (amountExisted + amount), size);
            request.setAttribute("mess", "Sản phẩm " + productName + " size " + size + " đã có trong giỏ hàng, đã tăng số lượng!");
        } else {
            // Nếu sản phẩm chưa có trong giỏ với size này, thêm mới vào giỏ
            dao.insertCart(accountID, productID, amount, size);
            request.setAttribute("mess", "Sản phẩm " + productName + " size " + size + " đã được thêm vào giỏ hàng!");
        }
        if (dao.isProductOnPromotion(productID)) {
            List<Product> promotionProducts = dao.getPromotionProducts();
            request.setAttribute("promotionProducts", promotionProducts);
         }
        
        
        
        // Cập nhật tổng số lượng giỏ hàng
        int totalAmountCart = 0;
        List<Cart> list = dao.getCartByAccountID(accountID);
        totalAmountCart = list.size();
        
        // Lưu tổng số lượng sản phẩm vào session
        session.setAttribute("cartQuantity", totalAmountCart);
        
        String isBuyNow = request.getParameter("BuyNow");
        
        if(isBuyNow != null){
            request.getRequestDispatcher("order").forward(request, response);
        }else{
            request.getRequestDispatcher("managerCart").forward(request, response);
        }

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
