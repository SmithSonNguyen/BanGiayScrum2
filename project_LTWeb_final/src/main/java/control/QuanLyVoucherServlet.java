package control;

import dao.DAO;
import entity.Account;
import entity.Voucher;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "QuanLyVoucherServlet", urlPatterns = {"/quanlyvoucher"})
public class QuanLyVoucherServlet extends HttpServlet {

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

        Account a = (Account) session.getAttribute("acc");
        List<Voucher> listAllVoucher;
        String filter = request.getParameter("filter");

        // Lọc voucher nếu có
        if (filter != null && !filter.isEmpty()) {
            switch (filter) {
                case "active":
                    listAllVoucher = dao.getActiveVouchers();
                    break;
                case "not_started":
                    listAllVoucher = dao.getNotStartedVouchers();
                    break;
                case "expired":
                    listAllVoucher = dao.getExpiredVouchers();
                    break;
                default:
                    listAllVoucher = dao.getAllVouchers();
            }
        } else {
            listAllVoucher = dao.getAllVouchers();  // Nếu không có lọc, lấy tất cả
        }

        // Đưa dữ liệu vào request và chuyển tiếp đến trang quản lý voucher
        request.setAttribute("listAllVoucher", listAllVoucher);
        request.getRequestDispatcher("quanlyVoucher.jsp").forward(request, response);
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
