/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.DAO;
import entity.Voucher;
import java.io.IOException;
import java.io.PrintWriter;
import entity.Voucher;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "AddVoucherServlet", urlPatterns = {"/addVoucherServlet"})
public class AddVoucherServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao = new DAO();
        String code = request.getParameter("code");
        double discountAmount = Double.parseDouble(request.getParameter("discountAmount"));
        double minSpend = Double.parseDouble(request.getParameter("minSpend"));
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

        Date startDate = Date.valueOf(startDateStr);
        Date endDate = Date.valueOf(endDateStr);
        Date currentDate = new Date(System.currentTimeMillis());

        Voucher voucher = new Voucher(1, code, discountAmount, minSpend, 
                                              startDate, endDate, currentDate, currentDate);
        
        boolean isAdded = dao.addVoucher(voucher);

        if (isAdded) {
            // Nếu thêm thành công, chuyển hướng về trang quản lý voucher
            response.sendRedirect("quanlyvoucher");
        } else {
            // Nếu có lỗi, hiển thị thông báo lỗi
            request.setAttribute("error", "Failed to add the voucher.");
            request.getRequestDispatcher("/addVoucher.jsp").forward(request, response);
        }
    }
}
