package controller;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataBase.ChiTietDonHangDAO;
import dataBase.DonHangDAO;
import model.CartItem;
import model.ChiTietDonHang;
import model.DonHang;
import model.GioHang;
import model.KhachHang;

@WebServlet("/checkout")
public class CheckoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");
        GioHang cart = (GioHang) session.getAttribute("cart");

        // Bảo mật: Chưa đăng nhập hoặc giỏ hàng trống thì không cho thanh toán
        if (khachHang == null || cart == null || cart.getItems().isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }

        try {
            // 1. Tạo Đơn Hàng mới
            String maDonHang = "DH" + System.currentTimeMillis();
            Date ngayDat = new Date(System.currentTimeMillis());
            String hinhThucThanhToan = "Thanh toán khi nhận hàng"; // Mặc định
            String trangThaiThanhToan = "Chưa thanh toán";
            double tongTien = cart.getTotalAmount();
            
            // Giả định constructor của DonHang (Bạn hãy chỉnh sửa lại tham số cho đúng với model DonHang.java của bạn)
            DonHang donHang = new DonHang(maDonHang, khachHang, khachHang.getDiaChiNhanHang(), 
                                         hinhThucThanhToan, trangThaiThanhToan, tongTien, ngayDat, null);
            
            DonHangDAO donHangDAO = new DonHangDAO();
            donHangDAO.insert(donHang); // Lưu đơn hàng vào DB

            // 2. Lưu Chi Tiết Đơn Hàng
            ChiTietDonHangDAO ctDAO = new ChiTietDonHangDAO();
            for (CartItem item : cart.getItems()) {
                String maChiTiet = "CT" + System.nanoTime(); // Mã chi tiết ngẫu nhiên
                
                // Giả định constructor của ChiTietDonHang (Chỉnh sửa cho khớp model của bạn)
                ChiTietDonHang ct = new ChiTietDonHang(maChiTiet, donHang, item.getSanPham(), 
                                                       item.getSoLuong(), item.getSanPham().getGia(), 
                                                       0, item.getSanPham().getGia() * item.getSoLuong());
                ctDAO.insert(ct);
            }

            // 3. Xóa giỏ hàng sau khi thanh toán thành công
            session.removeAttribute("cart");

            // 4. Chuyển hướng đến trang báo thành công
            response.getWriter().println("<script>alert('Thanh toán thành công! Mã đơn hàng của bạn là: " + maDonHang + "'); window.location.href='index.jsp';</script>");
            
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<script>alert('Lỗi hệ thống khi thanh toán!'); window.location.href='cart.jsp';</script>");
        }
    }
}