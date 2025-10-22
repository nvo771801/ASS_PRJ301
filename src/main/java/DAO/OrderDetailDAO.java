package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.OrderDetail;
import model.Orders;
import model.ProductVariant;
import utils.DBContext;

public class OrderDetailDAO extends DBContext {

    /**
     * Lưu một mục chi tiết đơn hàng vào CSDL.
     * @param detail Đối tượng OrderDetail chứa (order_id, variant_id, quantity, price).
     */
    public void createOrderDetail(OrderDetail detail) {
        String sql = "INSERT INTO OrderDetail (order_id, variant_id, quantity, price) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, detail.getOrder_id().getOrder_id());
            ps.setInt(2, detail.getVariant_id().getVariant_id());
            ps.setInt(3, detail.getQuantity());
            ps.setDouble(4, detail.getPrice());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lấy tất cả các chi tiết (sản phẩm) của một đơn hàng cụ thể.
     * @param orderId ID của đơn hàng cần xem chi tiết.
     * @return Danh sách các sản phẩm trong đơn hàng đó.
     */
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT * FROM OrderDetail WHERE order_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            
            // Dùng các DAO khác để lấy đối tượng hoàn chỉnh
            ProductVariantDAO variantDAO = new ProductVariantDAO();
            
            // Giả sử bạn có hàm getOrderById trong OrderDAO
            // OrderDAO orderDAO = new OrderDAO();
            // Orders order = orderDAO.getOrderById(orderId);

            while (rs.next()) {
                ProductVariant variant = variantDAO.getVariantById(rs.getInt("variant_id"));
                
                // Vì việc lấy Order trong vòng lặp có thể gây truy vấn lặp lại,
                // ta có thể tạo một đối tượng Order tạm chỉ với ID
                Orders tempOrder = new Orders();
                tempOrder.setOrder_id(orderId);
                
                OrderDetail detail = new OrderDetail(
                        rs.getInt("detail_id"),
                        tempOrder, // Gán Order tạm
                        variant,
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                );
                list.add(detail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}