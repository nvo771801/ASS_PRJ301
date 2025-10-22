/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CategoryDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ProductController", urlPatterns = {"/product"})
public class ProductController extends HttpServlet {

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
            out.println("<title>Servlet ProductController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductController at " + request.getContextPath() + "</h1>");
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
        ProductDAO productDAO = new ProductDAO();
        CategoryDAO categoryDAO = new CategoryDAO();

        //Luôn lấy danh sách danh mục để hiển thị sidebar
        List<Category> listCategories = categoryDAO.getAllCategories();
        request.setAttribute("categories", listCategories);

        //Lấy hành động (action) từ người dùng
        String action = request.getParameter("action");
        List<Product> listProducts;

        if (action != null) {
            switch (action) {
                case "filter":
                    //Xử lý lọc theo category
                    try {
                        int cat_id = Integer.parseInt(request.getParameter("cat_id"));
                        listProducts = productDAO.getProductsByCategoryId(cat_id);
                        // Đánh dấu category đang active để tô màu trên view
                        request.setAttribute("active_cat_id", cat_id); 
                    } catch (NumberFormatException e) {
                        // Nếu cat_id không hợp lệ, tải tất cả sản phẩm
                        listProducts = productDAO.getAllProducts();
                    }
                    break;
                case "search":
                    //Xử lý tìm kiếm
                    String keyword = request.getParameter("keyword");
                    listProducts = productDAO.searchProductsByName(keyword);
                    //Gửi lại keyword về view để hiển thị trong ô search
                    request.setAttribute("keyword", keyword); 
                    break;
                default:
                    //Mặc định (nếu action không hợp lệ)
                    listProducts = productDAO.getAllProducts();
                    break;
            }
        } else {
            //Mặc định (nếu không có action)
            listProducts = productDAO.getAllProducts();
        }

        //Gửi danh sách sản phẩm (đã lọc hoặc tìm kiếm) sang view
        request.setAttribute("products", listProducts);

        //Chuyển tiếp đến trang JSP
        request.getRequestDispatcher("product.jsp").forward(request, response);
    
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
