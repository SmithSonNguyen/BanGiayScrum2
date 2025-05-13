/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import entity.Account;
import entity.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "DeleteCartControl", urlPatterns = {"/deleteCart"})
public class DeleteCartControl extends HttpServlet {

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

        // Lấy tham số từ request
        int productID = Integer.parseInt(request.getParameter("productID"));
        String size = request.getParameter("size");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");

        if (account == null) {
            response.sendRedirect("login");
            return;
        }

        int accountID = account.getId();
        DAO dao = new DAO();

        // Kiểm tra sản phẩm trong giỏ
        Cart cartExisted = dao.checkCartExist(accountID, productID, size);
        if (cartExisted != null) {
            dao.deleteCart(accountID, productID, size);  // Xóa sản phẩm theo productID và size
            request.setAttribute("mess", "Đã xóa sản phẩm khỏi giỏ hàng!");
        } else {
            request.setAttribute("error", "Sản phẩm không tồn tại trong giỏ hàng!");
        }

        // Cập nhật số lượng sản phẩm trong giỏ hàng
        int totalAmountCart = 0;
        List<Cart> list = dao.getCartByAccountID(accountID);
        totalAmountCart = list.size();
        session.setAttribute("cartQuantity", totalAmountCart);

        // Quay lại trang giỏ hàng
        request.getRequestDispatcher("managerCart").forward(request, response);
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
