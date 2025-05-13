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

@WebServlet(name = "EditProfileControl", urlPatterns = {"/editProfile"})
public class EditProfileControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("EditProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");

        if (a == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int id = a.getId();
        DAO dao = new DAO();

        // Lấy dữ liệu từ form
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String avatarUrl = request.getParameter("avatarUrl");
        String fullName = request.getParameter("fullName");
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");

        // Cập nhật thông tin
        dao.editProfile(username, a.getPass(), email, avatarUrl, fullName, dob, address, phoneNumber, id);

        // Cập nhật session
        a.setUser(username);
        a.setEmail(email);
        a.setAvatar(avatarUrl);
        a.setFullName(fullName);
        a.setDob(dob);
        a.setAddress(address);
        a.setPhoneNumber(phoneNumber);

        session.setAttribute("acc", a);

        // Thông báo thành công
        request.setAttribute("mess", "Cập nhật thông tin thành công!");
        request.getRequestDispatcher("EditProfile.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet xử lý việc chỉnh sửa thông tin tài khoản người dùng";
    }
}
