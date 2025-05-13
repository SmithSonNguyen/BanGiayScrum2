/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;

import entity.Account;
import entity.Category;
import entity.Customer;
import entity.Invoice;
import entity.Product;
import entity.Voucher;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


@WebServlet(name = "XuatExcelVoucherControl", urlPatterns = {"/xuatExcelVoucherControl"})
public class XuatExcelVoucher extends HttpServlet {


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
        response.setContentType("text/html;charset=UTF-8");

        // Lấy danh sách voucher từ DAO
        DAO dao = new DAO();
        List<Voucher> list = dao.getAllVouchers();

        // Tạo file Excel và sheet
        int maximum = 2147483647;
        int minimum = 1;
        Random rn = new Random();
        int range = maximum - minimum + 1;
        int randomNum = rn.nextInt(range) + minimum;

        // Đường dẫn để lưu file Excel
        FileOutputStream file = new FileOutputStream("C:\\ExcelWebBanGiay\\" + "voucher" + Integer.toString(randomNum) + ".xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet workSheet = workbook.createSheet("Voucher List");

        // Tạo hàng đầu tiên với các tiêu đề cột
        XSSFRow row = workSheet.createRow(0);
        XSSFCell cell0 = row.createCell(0);
        cell0.setCellValue("Voucher ID");
        XSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("Code");
        XSSFCell cell2 = row.createCell(2);
        cell2.setCellValue("Discount Amount");
        XSSFCell cell3 = row.createCell(3);
        cell3.setCellValue("Minimum Spend");
        XSSFCell cell4 = row.createCell(4);
        cell4.setCellValue("Start Date");
        XSSFCell cell5 = row.createCell(5);
        cell5.setCellValue("End Date");
        XSSFCell cell6 = row.createCell(6);
        cell6.setCellValue("Created At");
        XSSFCell cell7 = row.createCell(7);
        cell7.setCellValue("Updated At");

        // Điền dữ liệu vào các hàng
        int i = 1;
        for (Voucher voucher : list) {
            row = workSheet.createRow(i++);
            cell0 = row.createCell(0);
            cell0.setCellValue(voucher.getVoucherId());
            cell1 = row.createCell(1);
            cell1.setCellValue(voucher.getCode());
            cell2 = row.createCell(2);
            cell2.setCellValue(voucher.getDiscountAmount());
            cell3 = row.createCell(3);
            cell3.setCellValue(voucher.getMinSpend());
            cell4 = row.createCell(4);
            cell4.setCellValue(voucher.getStartDate().toString());
            cell5 = row.createCell(5);
            cell5.setCellValue(voucher.getEndDate().toString());
            cell6 = row.createCell(6);
            cell6.setCellValue(voucher.getCreatedAt().toString());
            cell7 = row.createCell(7);
            cell7.setCellValue(voucher.getUpdatedAt().toString());
        }

        // Ghi dữ liệu vào file
        workbook.write(file);
        workbook.close();
        file.close();

        // Thông báo thành công và chuyển hướng
        request.setAttribute("mess", "Đã xuất file Excel thành công!");
        request.getRequestDispatcher("quanlyvoucher").forward(request, response);
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
