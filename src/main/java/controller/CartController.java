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
        String maSach = request.getParameter("id"); // Lấy ID (Mã sách)

        HttpSession session = request.getSession();
        
        GioHang cart = (GioHang) session.getAttribute("cart");
        if (cart == null) {
            cart = new GioHang();
            session.setAttribute("cart", cart);
        }

        if ("add".equals(action) && maSach != null) {
            SanPhamDAO spDAO = new SanPhamDAO();
            SanPham sp = new SanPham();
            // SỬA: Dùng setMaSach()
            sp.setMaSach(maSach);
            sp = spDAO.selectByID(sp); 
            
            if (sp != null) {
                cart.addItem(sp);
            }
            String referer = request.getHeader("Referer");
            response.sendRedirect(referer != null ? referer : "index.jsp");
            return;
        } 
        else if ("remove".equals(action) && maSach != null) {
            cart.removeItem(maSach);
            response.sendRedirect(request.getContextPath() + "/cart.jsp");
            return;
        }

        response.sendRedirect("cart.jsp");
    }
}