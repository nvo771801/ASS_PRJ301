/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductDAO;
import DAO.ProductVariantDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Product;
import model.ProductVariant;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ProductDetailController", urlPatterns = {"/productdetail"})
public class ProductDetailController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductDetailController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String proId_raw = request.getParameter("pro_id");

        ProductDAO productDAO = new ProductDAO();
        ProductVariantDAO variantDAO = new ProductVariantDAO();

        try {
            //Lấy pro_id và parse
            int pro_id = Integer.parseInt(proId_raw);

            //Lấy sản phẩm chính
            Product product = productDAO.getProductById(pro_id);

            if (product != null) {
                //Nếu sản phẩm tồn tại, lấy các biến thể (variants)
                List<ProductVariant> variants = variantDAO.getVariantsByProductId(pro_id);

                //Lấy các sản phẩm liên quan (cùng category)
                //Giả định .getCat_id() không bao giờ null nếu product tồn tại
                int cat_id = product.getCat_id().getCat_id();
                List<Product> relatedProducts = productDAO.getProductsByCategoryId(cat_id);

                //Gửi dữ liệu sang JSP
                request.setAttribute("product", product);
                request.setAttribute("variants", variants);
                request.setAttribute("related", relatedProducts);

                request.getRequestDispatcher("product-detail.jsp").forward(request, response);
            } else {
                //Nếu không tìm thấy sản phẩm (pro_id có trong DB nhưng không khớp)
                request.setAttribute("error", "Không tìm thấy sản phẩm.");
                //Chuyển về trang sản phẩm và hiển thị lỗi
                request.getRequestDispatcher("product").forward(request, response);
            }

        } catch (NumberFormatException e) {
            //Nếu pro_id không phải là số (hoặc null)
            response.sendRedirect("product"); // Chuyển về trang sản phẩm chung
        }
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
