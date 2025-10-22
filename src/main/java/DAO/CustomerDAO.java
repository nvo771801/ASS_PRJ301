package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Customer;
import utils.DBContext;

public class CustomerDAO extends DBContext {

    /**
     * Kiểm tra thông tin đăng nhập của khách hàng.
     *
     * @param email Email người dùng nhập.
     * @param password Mật khẩu người dùng nhập.
     * @return Trả về đối tượng Customer nếu thành công, ngược lại trả về null.
     */
    public Customer getCustomer(String email, String password) {
        // Tên cột trong DB là 'password', trong model là 'pass'
        String sql = "SELECT * FROM Customer WHERE email = ? AND password = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("cus_id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("password"), // Lấy từ cột 'password'
                        rs.getString("phone"),
                        rs.getString("address")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Kiểm tra xem một email đã tồn tại trong hệ thống chưa.
     *
     * @param email Email cần kiểm tra.
     * @return Trả về đối tượng Customer nếu email đã tồn tại, ngược lại null.
     */
    public Customer checkEmailExists(String email) {
        String sql = "SELECT * FROM Customer WHERE email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("cus_id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("address")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Tạo một tài khoản khách hàng mới (đăng ký).
     *
     * @param customer Đối tượng Customer chứa thông tin đăng ký.
     */
    public void createCustomer(Customer customer) {
        String sql = "INSERT INTO Customer (full_name, email, password, phone, address) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, customer.getFullname());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getPass()); // Dùng getPass() từ model
            ps.setString(4, customer.getPhone());
            ps.setString(5, customer.getAddress());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lấy thông tin khách hàng bằng ID. (Hàm này sẽ cần thiết cho các DAO sau
     * như OrderDAO, CartDAO)
     *
     * @param cus_id ID của khách hàng.
     * @return Đối tượng Customer hoặc null.
     */
    public Customer getCustomerById(int cus_id) {
        String sql = "SELECT * FROM Customer WHERE cus_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cus_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("cus_id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("address")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // Thêm hàm này vào file DAO/CustomerDAO.java đã có

    /**
     * [Admin] Xóa một khách hàng khỏi cơ sở dữ liệu.
     *
     * @param cus_id ID của khách hàng cần xóa.
     */
    public void deleteCustomer(int cus_id) {
        // Lưu ý: Cần xử lý xóa các đơn hàng và giỏ hàng của khách này trước
        // để tránh lỗi khóa ngoại.
        String sql = "DELETE FROM Customer WHERE cus_id = ?";
        try {
            // Ví dụ: Xóa các mục liên quan trong giỏ hàng trước
            // PreparedStatement psCart = conn.prepareStatement("DELETE FROM Cart WHERE cus_id = ?");
            // psCart.setInt(1, cus_id);
            // psCart.executeUpdate();

            // (Tương tự cần xóa trong Orders và OrderDetail)
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cus_id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
