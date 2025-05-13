package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;

import entity.Account;
import entity.Email;
import entity.EmailUtils;

/**
 * Servlet implementation class ForgotPasswordControl
 */
@WebServlet(name = "ForgotPasswordControl", urlPatterns = {"/forgotPassword"})
public class ForgotPasswordControl extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try {
            String emailAddress = request.getParameter("email");
            String username = request.getParameter("username");

            DAO dao = new DAO();
            Account account = dao.checkAccountExistByUsernameAndEmail(username, emailAddress);
            if (account == null) {
                request.setAttribute("error", "Email hoặc username sai!");
            }
            if (account != null) {
                Email email = new Email();
                email.setFrom("dinnoshopweb@gmail.com");
                email.setFromPassword("xpez ruov apxa voje");
                email.setTo(emailAddress);
                email.setSubject("Forgot Password Function");
                StringBuilder sb = new StringBuilder();
                sb.append("DINNO shop xin chào: ").append(username).append("<br>");
                sb.append("Bạn đã sử dụng tính năng quên mật khẩu.<br>");
                sb.append("Mật khẩu của bạn là: <b>").append(account.getPass()).append(" </b> <br>");
                sb.append("Đừng chia sẻ với anh nhé!<br>");
                sb.append("Trân trọng!");

                email.setContent(sb.toString());
                EmailUtils.send(email);
                request.setAttribute("mess", "Mật khẩu đã được gửi đến email của bạn!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
    }

}
