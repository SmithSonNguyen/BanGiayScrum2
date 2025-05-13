/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.DAO;
import entity.Promotion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author trant
 */
@WebServlet(name = "AddPromotionControl", urlPatterns = {"/addPromotion"})
public class AddPromotionControl extends HttpServlet {

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
        DAO dao = new DAO();

        try {
            // Lấy thông tin từ form
            int productID = Integer.parseInt(request.getParameter("productID"));
            double discountRate = Double.parseDouble(request.getParameter("discountRate"));
            String startDateStr = request.getParameter("startDate");
            String endDateStr = request.getParameter("endDate");

            // Chuyển đổi dữ liệu
            Date startDate = Date.valueOf(startDateStr);
            Date endDate = Date.valueOf(endDateStr);
            Date currentDate = new Date(System.currentTimeMillis());

            // Tạo đối tượng Promotion
            Promotion promotion = new Promotion(1, productID, discountRate, startDate, endDate);

            // Gọi DAO để thêm Promotion
            boolean isAdded = dao.addPromotion(promotion);

            // Kiểm tra kết quả
            if (isAdded) {
                response.sendRedirect("promotion"); // Chuyển hướng đến trang quản lý promotion
            } else {
                request.setAttribute("error", "Failed to add the promotion.");
                request.getRequestDispatcher("/AddPromotion.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error occurred: " + e.getMessage());
            request.getRequestDispatcher("/AddPromotion.jsp").forward(request, response);
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
