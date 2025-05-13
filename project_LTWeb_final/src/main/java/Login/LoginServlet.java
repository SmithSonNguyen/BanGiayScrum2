/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Login;

import dao.DAO;
import entity.GoogleAccount;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login1"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String code = request.getParameter("code");
        String error = request.getParameter("error");

        // Nếu người dùng hủy ủy quyền
        if (error != null) {
            request.getRequestDispatcher("login").forward(request, response);
            return;
        }

        try {
            GoogleLogin gg = new GoogleLogin();
            String accessToken = gg.getToken(code); // Lấy access token
            System.out.println("Access Token: " + accessToken); // Kiểm tra access token
            GoogleAccount googleAcc = gg.getUserInfo(accessToken); // Lấy thông tin người dùng từ Google
            System.out.println("Google Account: " + googleAcc); // Kiểm tra thông tin tài khoản

            DAO dao = new DAO(); // Tạo DAO để xử lý database
            Account existingAccount = dao.checkAccountExist(googleAcc.getEmail()); // Kiểm tra tài khoản tồn tại
            if (existingAccount == null) {
                // Nếu tài khoản chưa tồn tại, thêm tài khoản mới
                dao.singup(googleAcc.getEmail(), "1", googleAcc.getEmail()); // Insert new account
                existingAccount = dao.checkAccountExist(googleAcc.getEmail()); // Retrieve the newly added account
            }

            // Đăng nhập luôn sau khi đăng ký
            HttpSession session = request.getSession();
            session.setAttribute("acc", existingAccount);
            session.setMaxInactiveInterval(60 * 60 * 24);  // Session hết hạn sau 1 ngày

            // Lưu tài khoản vào session và redirect về trang chủ
            request.getSession().setAttribute("user", existingAccount);
            response.sendRedirect("home"); // Chuyển hướng về trang chủ

        } catch (Exception e) {
            e.printStackTrace(); // Log lỗi nếu có
            response.sendRedirect("login.jsp"); // Trả về trang đăng nhập nếu có lỗi
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Google Login Servlet";
    }
}

