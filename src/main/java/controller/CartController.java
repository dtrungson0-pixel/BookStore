package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataBase.SanPhamDAO;
import model.GioHang;
import model.SanPham;

@WebServlet("/cart")
public class CartController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String maSanPham = request.getParameter("id");

        HttpSession session = request.getSession();
        
        // Lấy giỏ hàng từ Session, nếu chưa có thì tạo mới
        GioHang cart = (GioHang) session.getAttribute("cart");
        if (cart == null) {
            cart = new GioHang();
            session.setAttribute("cart", cart);
        }

        if ("add".equals(action) && maSanPham != null) {
            // Lấy thông tin sản phẩm từ DB
            SanPhamDAO spDAO = new SanPhamDAO();
            SanPham sp = new SanPham();
            sp.setMaSanPham(maSanPham);
            sp = spDAO.selectByID(sp); // Bạn cần đảm bảo SanPhamDAO có hàm tìm theo ID
            
            if (sp != null) {
                cart.addItem(sp);
            }
            // Thêm xong quay lại trang trước đó hoặc trang chủ
            response.sendRedirect(request.getHeader("Referer"));
            return;
        } 
        else if ("remove".equals(action) && maSanPham != null) {
            cart.removeItem(maSanPham);
            // Xóa xong tải lại trang giỏ hàng
            response.sendRedirect(request.getContextPath() + "/cart.jsp");
            return;
        }

        // Mặc định: Chuyển đến trang xem giỏ hàng
        response.sendRedirect("cart.jsp");
    }
}