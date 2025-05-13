package control;

import dao.DAO;
import entity.Account;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ChangePasswordControl", urlPatterns = {"/changePassword"})
public class ChangePasswordControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set encoding để xử lý tiếng Việt
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // Lấy session và kiểm tra người dùng đã đăng nhập hay chưa
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");

        if (a == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int id = a.getId(); // Lấy ID người dùng từ session
        DAO dao = new DAO();

        // Lấy dữ liệu từ form
        String oldPassword = request.getParameter("passcu");
        String newPassword = request.getParameter("passmoi");
        String confirmPassword = request.getParameter("passxacnhan");

        try {
            if (oldPassword != null && newPassword != null && confirmPassword != null) {
                if (newPassword.equals(confirmPassword)) { // Kiểm tra xác nhận mật khẩu
                    dao.updatePassword(oldPassword, newPassword, id); // Cập nhật mật khẩu
                    a.setPass(newPassword); // Cập nhật session với mật khẩu mới
                    request.setAttribute("mess", "Đổi mật khẩu thành công!");
                } else {
                    request.setAttribute("mess", "Mật khẩu mới và xác nhận không khớp!");
                }
            }
        } catch (RuntimeException e) {
            request.setAttribute("mess", e.getMessage()); // Thông báo lỗi từ DAO
        } catch (Exception e) {
            request.setAttribute("mess", "Đã xảy ra lỗi không mong muốn!");
        }

        // Forward lại trang EditProfile.jsp với thông báo
        request.getRequestDispatcher("EditProfile.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet xử lý việc đổi mật khẩu người dùng";
    }
}