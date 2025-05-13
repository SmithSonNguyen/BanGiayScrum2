package control;

import dao.DAO;
import entity.Account;
import entity.History;
import entity.Product;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Top10SanPhamControl", urlPatterns = {"/top10"})
public class Top10SanPhamControl extends HttpServlet {

    // Kiểm tra xem người dùng có phải là admin không
    private boolean isAdmin(HttpSession session, DAO dao) {
        Account a = (Account) session.getAttribute("acc");
        if (a == null) {
            return false;
        }
        int checkIsAdmin = dao.checkAccountAdmin(a.getId());
        return checkIsAdmin == 1; // Trả về true nếu là admin
    }

    // Xử lý yêu cầu GET và POST
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        DAO dao = new DAO();

        // Kiểm tra quyền admin trước khi tiếp tục xử lý
        if (!isAdmin(session, dao)) {
            response.sendRedirect("login");  // Chuyển hướng về trang đăng nhập nếu không phải admin
            return;
        }

        List<Product> listAllProduct = dao.getAllProduct();
        List<History> listTop10Product = dao.getTop10SanPhamBanChay();

        request.setAttribute("listAllProduct", listAllProduct);
        request.setAttribute("listTop10Product", listTop10Product);

        request.getRequestDispatcher("Top10SanPhamBanChay.jsp").forward(request, response);
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
