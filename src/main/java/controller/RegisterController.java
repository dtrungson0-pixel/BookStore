package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataBase.KhachHangDAO;
import model.KhachHang;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Chuyển hướng người dùng về trang đăng ký nếu họ truy cập bằng phương thức GET
		response.sendRedirect("register.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Đặt tiếng Việt (UTF-8) để đọc dữ liệu từ form không bị lỗi font
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// 1. Lấy dữ liệu từ form đăng ký
		String tenDangNhap = request.getParameter("tenDangNhap");
		String matKhau = request.getParameter("matKhau");
		String hoVaTen = request.getParameter("hoVaTen");
		String gioiTinh = request.getParameter("gioiTinh");
		String ngaySinhStr = request.getParameter("ngaySinh");
		String soDienThoai = request.getParameter("soDienThoai");
		String email = request.getParameter("email");
		String diaChi = request.getParameter("diaChi");
		String diaChiNhanHang = request.getParameter("diaChiNhanHang");
		String diaChiMuaHang = request.getParameter("diaChiMuaHang");
		
		// Xử lý checkbox nhận bản tin (Nếu được check thì sẽ trả về giá trị "on")
		String nhanBanTinStr = request.getParameter("dangKyNhanBanTin");
		boolean dangKyNhanBanTin = (nhanBanTinStr != null && nhanBanTinStr.equals("on"));

		// 2. Tiền xử lý dữ liệu
		// Xử lý Ngày Sinh (Chuyển từ chuỗi yyyy-MM-dd sang java.sql.Date)
		Date ngaySinh = null;
		if (ngaySinhStr != null && !ngaySinhStr.trim().isEmpty()) {
			try {
				ngaySinh = Date.valueOf(ngaySinhStr);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}

		// Tạo mã khách hàng ngẫu nhiên (Ví dụ: KH169123456789)
		String maKhachHang = "KH" + System.currentTimeMillis();

		// Cảnh báo/Thông báo lỗi
		String baoLoi = "";

		// Kiểm tra các trường bắt buộc (Backend validation)
		if (tenDangNhap == null || tenDangNhap.trim().isEmpty() || 
			matKhau == null || matKhau.trim().isEmpty() || 
			hoVaTen == null || hoVaTen.trim().isEmpty()) {
			baoLoi = "Vui lòng nhập đầy đủ các trường bắt buộc (*).";
		}

		if (baoLoi.length() > 0) {
			// Nếu có lỗi, trả về trang đăng ký và báo lỗi
			request.setAttribute("baoLoi", baoLoi);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.jsp");
			rd.forward(request, response);
		} else {
			// 3. Nếu không có lỗi, tiến hành tạo đối tượng và lưu xuống DB
			// Lưu ý: Phương thức trong KhachHang.java của bạn là getNgaySing()/setNgaySing() nên truyền vào constructor khớp biến
			KhachHang kh = new KhachHang(maKhachHang, tenDangNhap, matKhau, gioiTinh, hoVaTen, 
					diaChi, diaChiNhanHang, diaChiMuaHang, ngaySinh, soDienThoai, email, dangKyNhanBanTin);
			
			KhachHangDAO khDAO = new KhachHangDAO();
			int ketQua = khDAO.insert(kh);

			if (ketQua > 0) {
				// Đăng ký thành công
				request.setAttribute("thongBaoThanhCong", "Đăng ký tài khoản thành công! Bạn có thể đăng nhập ngay bây giờ.");
			} else {
				// Đăng ký thất bại (Ví dụ: Trùng tên đăng nhập DB)
				request.setAttribute("baoLoi", "Đăng ký thất bại. Tên đăng nhập có thể đã tồn tại hoặc có lỗi hệ thống.");
			}

			// Forward lại trang register để hiển thị thông báo
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.jsp");
			rd.forward(request, response);
		}
	}
}