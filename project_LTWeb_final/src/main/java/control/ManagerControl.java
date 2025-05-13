package control;

import dao.DAO;
import entity.Account;
import entity.Category;
import entity.Product;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ManagerControl", urlPatterns = {"/manager"})
public class ManagerControl extends HttpServlet {

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

        // Tiếp tục xử lý phần còn lại của mã
        Account a = (Account) session.getAttribute("acc");
        int id = a.getId();
        String index = request.getParameter("index");
        if (index == null) {
            index = "1";
        }
        int indexPage = Integer.parseInt(index);

        List<Product> list = dao.getProductBySellIDAndIndex(id, indexPage);
        List<Category> listC = dao.getAllCategory();
        int allProductBySellID = dao.countAllProductBySellID(id);
        int endPage = allProductBySellID / 5;
        if (allProductBySellID % 5 != 0) {
            endPage++;
        }

        // Đưa dữ liệu vào request và chuyển tiếp đến trang quản lý sản phẩm
        request.setAttribute("tag", indexPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("listCC", listC);
        request.setAttribute("listP", list);

        request.getRequestDispatcher("QuanLySanPham.jsp").forward(request, response);
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
