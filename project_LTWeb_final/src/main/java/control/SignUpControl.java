/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import entity.Account;
import entity.Email;
import entity.EmailUtils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SignUpControl", urlPatterns = {"/signup"})
public class SignUpControl extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String re_pass = request.getParameter("repass");
        String email = request.getParameter("email");
        if (!pass.equals(re_pass)) {
            response.sendRedirect("Login.jsp");
        } else {
            DAO dao = new DAO();
            Account a = dao.checkAccountExist(user);
            if (a == null) {
                dao.singup(user, pass, email); 

                String voucherCode = "FREESHIP"; 

                // Gửi email mã voucher
                try {
                    Email emailObj = new Email();
                    emailObj.setFrom("dinnoShopWeb@gmail.com");
                    emailObj.setFromPassword("xpez ruov apxa voje");
                    emailObj.setTo(email);
                    emailObj.setSubject("Chào mừng bạn đến với Dinno Shop!");

                    // Nội dung email
                    StringBuilder sb = new StringBuilder();
                    sb.append("Chào ").append(user).append(",<br>");
                    sb.append("Cảm ơn bạn đã đăng ký tài khoản tại Dinno Shop!<br>");
                    sb.append("Để tri ân, chúng tôi gửi tặng bạn một mã voucher miễn phí ship: <b>").append(voucherCode).append("</b><br>");
                    sb.append("Hãy sử dụng mã này trong lần mua hàng tiếp theo của bạn!<br>");
                    sb.append("Chúc bạn có trải nghiệm mua sắm tuyệt vời!<br>");
                    sb.append("<br><img src='https://res.cloudinary.com/ds1rgnuvr/image/upload/v1733243576/t%E1%BA%A3i_xu%E1%BB%91ng_xopa1j.jpg' alt='Thank You' style='width:500px; height:auto;'/><br>");

                    sb.append("Trân trọng,<br>Dinno Shop");

                    emailObj.setContent(sb.toString());
                    EmailUtils.send(emailObj);

                    request.setAttribute("mess", "Đăng ký thành công! Mã voucher đã được gửi qua email của bạn.");
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("error", "Đăng ký thành công nhưng không gửi được email. Vui lòng kiểm tra lại.");
                }

                // Chuyển hướng về trang Login.jsp
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Tài khoản đã tồn tại. Vui lòng thử tên khác.");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }

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
