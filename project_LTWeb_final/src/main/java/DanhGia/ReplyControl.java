package DanhGia;

import dao.DAO;
import entity.Account;
import entity.History;
import entity.Product;
import entity.Review;
import entity.Reply;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ReplyServlet", urlPatterns = {"/reply"})
public class ReplyControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // Lấy thông tin người dùng từ session
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        if (account == null) {
            response.sendRedirect("login");
            return;
        }
        int accountID = account.getId();
        String replyContent = request.getParameter("replyContent");
        int reviewID = Integer.parseInt(request.getParameter("reviewID"));
        DAO dao = new DAO();
        dao.insertReply(account.getId(), reviewID, replyContent);
        Reply newReply = new Reply();
        newReply = dao.getReply(accountID, reviewID);
        // In reply mới nhất
        PrintWriter out = response.getWriter();
        out.println("<hr>");
        out.println("<div class=\"media mt-2 ml-4\">");
        out.println("<img class=\"d-flex mr-3 z-depth-1\" src=\"images/logo3.png\" width=\"55\" alt=\"Reply placeholder\" style=\"border-radius: 100%;\">");
        out.println("  <div class=\"media-body\">");
        out.println("    <p class=\"mt-0 mb-1\">");
        out.println("      <strong>" + account.getUser() + "</strong>");
        out.println("      <span>– </span><span>" + newReply.getDateReply() + "</span>");
        out.println("    </p>");
        out.println("    <p>" + newReply.getContentReply() + "</p>");
        out.println("  </div>");
        out.println("</div>");
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
        return "Short description";
    }
}
