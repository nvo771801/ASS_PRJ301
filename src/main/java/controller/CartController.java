/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class CartController extends DBContext {

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
            out.println("<title>Servlet CartController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartController at " + request.getContextPath() + "</h1>");
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
    String action = request.getParameter("action");
        if (action == null) action = "view";

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        ProductDAO productDAO = new ProductDAO();

        switch (action) {
            case "add":
                try {
                    int productId = Integer.parseInt(request.getParameter("productId"));
                    Product p = productDAO.getProductById(productId);
                    if (p != null) {
                        cart.addItem(p);
                        session.setAttribute("cart", cart);
                    }
                } catch (NumberFormatException ignored) {}
                response.sendRedirect(request.getContextPath() + "/cart?action=view");
                break;

            case "update":
                try {
                    int productId = Integer.parseInt(request.getParameter("productId"));
                    int qty = Integer.parseInt(request.getParameter("quantity"));
                    cart.updateItem(productId, qty);
                    session.setAttribute("cart", cart);
                } catch (NumberFormatException ignored) {}
                response.sendRedirect(request.getContextPath() + "/cart?action=view");
                break;

            case "remove":
                try {
                    int productId = Integer.parseInt(request.getParameter("productId"));
                    cart.removeItem(productId);
                    session.setAttribute("cart", cart);
                } catch (NumberFormatException ignored) {}
                response.sendRedirect(request.getContextPath() + "/cart?action=view");
                break;

            case "view":
            default:
                // Forward to JSP to view cart
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
                break;
        }
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
