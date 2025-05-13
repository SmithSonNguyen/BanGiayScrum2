package control;

import dao.DAO;
import entity.Voucher;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "VoucherManagementServlet", urlPatterns = {"/voucher-management"})
public class VoucherManagementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String idParam = request.getParameter("id");
        DAO dao = new DAO();
        if (action == null || idParam == null) {
            response.sendRedirect("quanlyvoucher");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("quanlyvoucher");
            return;
        }

        switch (action) {
            case "edit":
                // Load voucher details for editing
                Voucher voucher = dao.getVoucherById(id);
                if (voucher != null) {
                    request.setAttribute("voucher", voucher);
                    request.getRequestDispatcher("editVoucher.jsp").forward(request, response);
                } else {
                    response.sendRedirect("quanlyvoucher");
                }
                break;

            case "delete":
                // Delete voucher by ID
                boolean isDeleted = dao.deleteVoucherById(id);
                if (isDeleted) {
                    request.setAttribute("message", "Voucher deleted successfully!");
                } else {
                    request.setAttribute("message", "Failed to delete voucher.");
                }
                response.sendRedirect("quanlyvoucher");
                break;
            case "view_usage":
                int voucherId = Integer.parseInt(request.getParameter("id"));
                List<List<String>> usages = dao.getUsersByVoucher(voucherId); // Lấy danh sách sử dụng voucher
                StringBuilder responseHtml = new StringBuilder();
                responseHtml.append("<ul>");
                for (List<String> usage : usages) {
                    String user = usage.get(0);       // Lấy thông tin `user`
                    String usedAt = usage.get(1);     // Lấy thông tin `used_at`
                    responseHtml.append("<li>")
                                .append("User: ")
                                .append(user)
                                .append(" - Used at: ")
                                .append(usedAt)
                                .append("</li>");
                }
                if (usages.isEmpty()) {
                    responseHtml.append("<p>No usage details available.</p>");
                }
                responseHtml.append("</ul>");
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write(responseHtml.toString());
                break;

            default:
                response.sendRedirect("quanlyvoucher");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        DAO dao = new DAO();

        if ("update".equals(action)) {
            try {
                int voucherId = Integer.parseInt(request.getParameter("voucherId"));
                String code = request.getParameter("code");
                double discountAmount = Double.parseDouble(request.getParameter("discountAmount"));
                double minSpend = Double.parseDouble(request.getParameter("minSpend"));
                String startDateStr = request.getParameter("startDate");
                String endDateStr = request.getParameter("endDate");

                Date startDate = Date.valueOf(startDateStr);
                Date endDate = Date.valueOf(endDateStr);
                Date currentDate = new Date(System.currentTimeMillis());

                Voucher voucher = new Voucher(voucherId, code, discountAmount, minSpend, 
                                              startDate, endDate, currentDate, currentDate);

                boolean isUpdated = dao.updateVoucher(voucher);

                if (isUpdated) {
                    response.sendRedirect("quanlyvoucher");
                } else {
                    // Xử lý lỗi, gửi thông báo lỗi
                    request.setAttribute("error", "Failed to update voucher.");
                    request.getRequestDispatcher("/editVoucher.jsp").forward(request, response);
                }
            } catch (Exception e) {
                // Xử lý lỗi nếu có vấn đề với việc lấy thông tin
                request.setAttribute("error", "Invalid input data. Please try again.");
                request.getRequestDispatcher("/editVoucher.jsp").forward(request, response);
            }
        }

    }
}