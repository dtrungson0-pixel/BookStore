package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataBase.KhachHangDAO;
import model.KhachHang;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Chuyển tiếp người dùng đến giao diện đăng nhập khi truy cập đường dẫn /login
        response.sendRedirect("login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 1. Lấy thông tin từ form đăng nhập
        String tenDangNhap = request.getParameter("tenDangNhap");
        String matKhau = request.getParameter("matKhau");

        // 2. Kiểm tra thông tin qua lớp DAO
        KhachHangDAO khDAO = new KhachHangDAO();
        KhachHang kh = khDAO.selectByTenDangNhapVaMatKhau(tenDangNhap, matKhau);

        if (kh != null) {
            // Đăng nhập thành công: Lưu thông tin người dùng vào Session
            HttpSession session = request.getSession();
            session.setAttribute("khachHang", kh);
            
            // Chuyển về trang chủ sau khi thành công
            response.sendRedirect("index.jsp");
        } else {
            // Đăng nhập thất bại: Gửi thông báo lỗi quay lại trang login
            request.setAttribute("baoLoi", "Tên đăng nhập hoặc mật khẩu không chính xác!");
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        }
    }
}