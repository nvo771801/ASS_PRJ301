package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Admin;
import utils.DBContext;

public class AdminDAO extends DBContext {

    /**
     * Kiểm tra thông tin đăng nhập của quản trị viên.
     * @param username Tên đăng nhập của admin.
     * @param password Mật khẩu của admin.
     * @return Trả về đối tượng Admin nếu đăng nhập thành công, ngược lại trả về null.
     */
    public Admin getAdmin(String username, String password) {
        String sql = "SELECT * FROM Admin WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Admin(
                        rs.getInt("admin_id"),
                        rs.getString("admin_name"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}