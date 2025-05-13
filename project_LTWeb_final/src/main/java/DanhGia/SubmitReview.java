package DanhGia;

import dao.DAO;
import entity.Account;
import entity.History;
import entity.Product;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "SubmitReview", urlPatterns = {"/SubmitReview"})
public class SubmitReview extends HttpServlet {

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

        // Lấy dữ liệu từ form
        String contentReview = request.getParameter("contentReview");
        int productID = Integer.parseInt(request.getParameter("productID"));
        int rating = Integer.parseInt(request.getParameter("rating")); // Lấy rating từ form

        // Xử lý chèn đánh giá vào cơ sở dữ liệu
        DAO dao = new DAO();
        dao.insertReview(accountID, productID, contentReview, rating); // Truyền rating
        request.setAttribute("mess", "Đánh giá thành công");
        request.getRequestDispatcher("managerHistory").forward(request, response);
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



