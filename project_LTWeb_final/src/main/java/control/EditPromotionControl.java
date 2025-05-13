package control;

import dao.DAO;
import entity.Promotion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EditPromotionControl", urlPatterns = {"/promotion-management"})
public class EditPromotionControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String idParam = request.getParameter("id");
        DAO dao = new DAO();
        if (action == null || idParam == null) {
            response.sendRedirect("promotion");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("promotion");
            return;
        }

        switch (action) {
            case "edit":
                // Load promotion details for editing
                Promotion promotion = dao.getPromotionById(id);
                if (promotion != null) {
                    request.setAttribute("promotion", promotion);
                    request.getRequestDispatcher("EditPromotion.jsp").forward(request, response);
                } else {
                    response.sendRedirect("promotion");
                }
                break;

            case "delete":
                // Delete promotion by ID
                boolean isDeleted = dao.deletePromotionById(id);
                if (isDeleted) {
                    request.setAttribute("message", "Promotion deleted successfully!");
                } else {
                    request.setAttribute("message", "Failed to delete promotion.");
                }
                response.sendRedirect("promotion");
                break;
                
            default:
                response.sendRedirect("promotion");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        DAO dao = new DAO();

        if ("update".equals(action)) {
            try {
                int promotionId = Integer.parseInt(request.getParameter("id"));
                int productId = Integer.parseInt(request.getParameter("productID"));
                double discountRate = Double.parseDouble(request.getParameter("discountRate"));
                String startDateStr = request.getParameter("startDate");
                String endDateStr = request.getParameter("endDate");

                Date startDate = Date.valueOf(startDateStr);
                Date endDate = Date.valueOf(endDateStr);

                Promotion promotion = new Promotion(promotionId, productId, discountRate, startDate, endDate);

                boolean isUpdated = dao.updatePromotion(promotion);

                if (isUpdated) {
                    response.sendRedirect("promotion");
                } else {
                    // Handle failure and show error
                    request.setAttribute("error", "Failed to update promotion.");
                    request.getRequestDispatcher("/EditPromotion.jsp").forward(request, response);
                }
            } catch (Exception e) {
                // Handle invalid input
                request.setAttribute("error", "Invalid input data. Please try again.");
                request.getRequestDispatcher("/EditPromotion.jsp").forward(request, response);
            }
        }
    }
}
