package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.UUID;
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
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        HttpSession session = request.getSession();
        KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");
        GioHang cart = (GioHang) session.getAttribute("cart");

        if (khachHang == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        if (cart == null || cart.getItems().isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }

        try {
            // 1. Dữ liệu chuẩn bị cho Đơn Hàng
            String maDonHang = "DH" + System.currentTimeMillis();
            Date ngayDat = new Date(System.currentTimeMillis());
            double tongTien = cart.getTotalAmount();
            
            // SỬA: Gọi đúng thứ tự 11 tham số trong model.DonHang
            // DonHang(maDonHang, diaChiNguoiMua, diaChiNhanHang, khachHang, trangThai, phuongThucThanhToan, trangThaiThanhToan, soTienDaThanhToan, soTienConThieu, ngayDatHang, ngayGiaoHang)
            DonHang donHang = new DonHang(
                maDonHang, 
                khachHang.getDiaChi(), // Địa chỉ người mua
                khachHang.getDiaChiNhanHang(), // Địa chỉ nhận hàng
                khachHang, 
                "Đang xử lý", // Trạng thái đơn hàng
                "Thanh toán khi nhận hàng", // Phương thức
                "Chưa thanh toán", // Trạng thái thanh toán
                "0", // Số tiền đã thanh toán (Kiểu String theo model của bạn)
                String.valueOf(tongTien), // Số tiền còn thiếu
                ngayDat, 
                null // Ngày giao hàng (chưa có)
            );
            
            DonHangDAO donHangDAO = new DonHangDAO();
            donHangDAO.insert(donHang); 

            // 2. Lưu Chi Tiết Đơn Hàng
            ChiTietDonHangDAO ctDAO = new ChiTietDonHangDAO();
            for (CartItem item : cart.getItems()) {
                String maChiTiet = "CT" + UUID.randomUUID().toString().substring(0, 8);
                
                // SỬA: Gọi đúng thứ tự 9 tham số trong model.ChiTietDonHang
                // ChiTietDonHang(maChiTietDonHang, donHang, sanPham, soLuong, giaGoc, giamGia, giaBan, thueVAT, tongThanhTien)
                ChiTietDonHang ct = new ChiTietDonHang(
                    maChiTiet, 
                    donHang, 
                    item.getSanPham(), 
                    item.getSoLuong(), 
                    item.getSanPham().getGiaGoc(), // Giá gốc
                    0.0, // Giảm giá
                    item.getSanPham().getGiaBan(), // Giá bán
                    0.0, // Thuế VAT
                    item.getSanPham().getGiaBan() * item.getSoLuong() // Tổng thành tiền
                );
                ctDAO.insert(ct);
            }

            // 3. Xóa giỏ hàng
            session.removeAttribute("cart");

            // 4. Thông báo
            response.getWriter().println("<script>alert('Thanh toán thành công! Mã đơn hàng: " + maDonHang + "'); window.location.href='index.jsp';</script>");
            
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<script>alert('Đã xảy ra lỗi hệ thống khi thanh toán!'); window.location.href='cart.jsp';</script>");
        }
    }
}