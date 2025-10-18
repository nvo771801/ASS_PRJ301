package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.Customer;
import model.ProductVariant;
import utils.DBContext;

public class CartDAO extends DBContext {

    /**
     * Lấy tất cả các sản phẩm trong giỏ hàng của một khách hàng.
     * @param cus_id ID của khách hàng.
     * @return Danh sách các mục trong giỏ hàng.
     */
    public List<Cart> getCartItemsByCustomerId(int cus_id) {
        List<Cart> list = new ArrayList<>();
        // JOIN để lấy tất cả thông tin liên quan trong một lần truy vấn
        String sql = "SELECT * FROM Cart WHERE cus_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cus_id);
            ResultSet rs = ps.executeQuery();
            
            // Dùng các DAO khác để tạo đối tượng hoàn chỉnh
            ProductVariantDAO variantDAO = new ProductVariantDAO();
            CustomerDAO customerDAO = new CustomerDAO();
            
            while (rs.next()) {
                Customer customer = customerDAO.getCustomerById(rs.getInt("cus_id"));
                ProductVariant variant = variantDAO.getVariantById(rs.getInt("variant_id"));
                
                Cart cartItem = new Cart(
                        rs.getInt("cart_id"),
                        customer,
                        variant,
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                );
                list.add(cartItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Thêm một sản phẩm vào giỏ hàng.
     * Nếu sản phẩm đã tồn tại, cập nhật số lượng.
     * @param cartItem Đối tượng Cart chứa cus_id, variant_id, quantity, price.
     */
    public void addToCart(Cart cartItem) {
        // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
        String checkSql = "SELECT * FROM Cart WHERE cus_id = ? AND variant_id = ?";
        try {
            PreparedStatement checkPs = conn.prepareStatement(checkSql);
            checkPs.setInt(1, cartItem.getCus_id().getCus_id());
            checkPs.setInt(2, cartItem.getVariant_id().getVariant_id());
            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {
                // Nếu đã tồn tại, cập nhật số lượng
                int newQuantity = rs.getInt("quantity") + cartItem.getQuantity();
                double newPrice = cartItem.getVariant_id().getPrice() * newQuantity;
                
                String updateSql = "UPDATE Cart SET quantity = ?, price = ? WHERE cart_id = ?";
                PreparedStatement updatePs = conn.prepareStatement(updateSql);
                updatePs.setInt(1, newQuantity);
                updatePs.setDouble(2, newPrice);
                updatePs.setInt(3, rs.getInt("cart_id"));
                updatePs.executeUpdate();
            } else {
                // Nếu chưa tồn tại, thêm mới
                String insertSql = "INSERT INTO Cart (cus_id, variant_id, quantity, price) VALUES (?, ?, ?, ?)";
                PreparedStatement insertPs = conn.prepareStatement(insertSql);
                insertPs.setInt(1, cartItem.getCus_id().getCus_id());
                insertPs.setInt(2, cartItem.getVariant_id().getVariant_id());
                insertPs.setInt(3, cartItem.getQuantity());
                insertPs.setDouble(4, cartItem.getPrice());
                insertPs.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Cập nhật số lượng của một sản phẩm trong giỏ hàng.
     * @param cart_id ID của mục trong giỏ hàng.
     * @param quantity Số lượng mới.
     */
    public void updateQuantity(int cart_id, int quantity) {
        // Lấy giá của một sản phẩm để tính lại tổng tiền
        String getPriceSql = "SELECT v.price FROM Cart c JOIN ProductVariant v ON c.variant_id = v.variant_id WHERE c.cart_id = ?";
        try {
            PreparedStatement getPricePs = conn.prepareStatement(getPriceSql);
            getPricePs.setInt(1, cart_id);
            ResultSet rs = getPricePs.executeQuery();
            
            if(rs.next()){
                double unitPrice = rs.getDouble("price");
                double newTotalPrice = unitPrice * quantity;
                
                String updateSql = "UPDATE Cart SET quantity = ?, price = ? WHERE cart_id = ?";
                PreparedStatement updatePs = conn.prepareStatement(updateSql);
                updatePs.setInt(1, quantity);
                updatePs.setDouble(2, newTotalPrice);
                updatePs.setInt(3, cart_id);
                updatePs.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Xóa một sản phẩm khỏi giỏ hàng.
     * @param cart_id ID của mục trong bảng Cart cần xóa.
     */
    public void removeFromCart(int cart_id) {
        String sql = "DELETE FROM Cart WHERE cart_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cart_id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Xóa toàn bộ giỏ hàng của một khách hàng sau khi thanh toán thành công.
     * @param cus_id ID của khách hàng.
     */
    public void clearCart(int cus_id) {
        String sql = "DELETE FROM Cart WHERE cus_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cus_id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}