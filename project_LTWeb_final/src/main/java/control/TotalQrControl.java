/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.DAO;
import entity.Account;
import entity.Cart;
import entity.Category;
import entity.Invoice;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "totalQr", urlPatterns = {"/totalQr"})
public class TotalQrControl extends HttpServlet {

     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        if (a == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int accountID = a.getId();
        DAO dao = new DAO();
        List<Cart> listCart = dao.getCartByAccountID(accountID);
        List<Product> listProduct = dao.getAllProduct();
        List<Invoice> listInvoice = dao.getAllInvoice();

        double discountedPrice=0;
            double totalMoney = 0;
            for (Cart o : listCart) {
                for (Product p : listProduct) {
                    if (o.getProductID() == p.getId()) {
                        
                        double productPrice = p.getPrice();
                        double discountRate = dao.getDiscountRate(o.getProductID());
                        // Tính giá đã giảm
                        discountedPrice = p.getPrice();
                        if (discountRate > 0) {
                            discountedPrice = productPrice * (1 - discountRate ); 
                        }
                        totalMoney += discountedPrice * o.getAmount();
                    }
                }
            }
          
            double totalPayment = totalMoney ;
            if (totalPayment > 0) {
                totalPayment += 25000; // Phí vận chuyển
            } else {
                totalPayment = 0; 
            }
            
        String voucherCode = (String) session.getAttribute("voucherCode");
        Double discountAmount = 0.0;
            if (voucherCode != "" || voucherCode != null && !voucherCode.isEmpty()) {
                if (dao.isVoucherValid(voucherCode,  totalMoney)&&!dao.isVoucherLinkedToAccount(voucherCode, accountID)){
                discountAmount = dao.getDiscountAmount(voucherCode);
                totalPayment -= discountAmount;
                }
            }
        int hd = 0;
        for (Invoice i : listInvoice) {
            if (hd < i.getMaHD()) {
                hd = i.getMaHD();
            }
        }
        hd += 1;
        String qrCodeUrl = "https://qr.sepay.vn/img?acc=1023751080&bank=VCB&amount=" + totalPayment + "&des=" + hd;

        PrintWriter out = response.getWriter();
        out.println("<div class=\"flex justify-center mb-6\">\n"
                + "    <img alt=\"Centered Image\"  src=\"" + qrCodeUrl + "\"  align=\"middle\"/>\n"
                + "<hr>"
                + "   </div>\n"
                + "<table class=\"table\">\n"
                + "            <tbody>\n"
                + "                <tr>\n"
                + "                    <td>Chủ tài khoản: </td>\n"
                + "                    <td><b>Trịnh Ngọc Hiếu</b></td>\n"
                + "                </tr>\n"
                +"                    <tr>\n"
                + "                    <td>Ngân hàng: </td>\n"
                + "                    <td><b>Vietcombank</b></td>\n"
                + "                </tr>\n"
                + "                 <tr>\n"
                + "                    <td>Số TK: </td>\n"
                + "                    <td><b>1023751080</b></td>\n"
                + "                </tr>\n"
                + "                 <tr>\n"
                + "                    <td>Số tiền: </td>\n"
                + "                    <td><b> " + totalPayment + "</b></td>\n"
                + "                </tr>\n"
                + "                 <tr>\n"
                + "                    <td>Nội dung CK: </td>\n"
                + "                    <td><b>" + hd + "</b></td>\n"
                + "                </tr>\n"
                + "            </tbody>\n"
                + "        </table>");

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
