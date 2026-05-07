package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataBase.TheLoaiDAO;
import model.TheLoai;

@WebServlet("/admin/the-loai")
public class TheLoaiController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        TheLoaiDAO theLoaiDAO = new TheLoaiDAO();

        // Xử lý XÓA thể loại
        if ("delete".equals(action)) {
            String maTheLoai = request.getParameter("id");
            TheLoai tl = new TheLoai();
            tl.setMaTheLoai(maTheLoai);
            theLoaiDAO.delete(tl);
            // Xóa xong tải lại trang danh sách
            response.sendRedirect(request.getContextPath() + "/admin/the-loai");
        } 
        // Hiển thị DANH SÁCH mặc định
        else {
            ArrayList<TheLoai> listTheLoai = theLoaiDAO.selectAll();
            request.setAttribute("listTheLoai", listTheLoai);
            RequestDispatcher rd = request.getRequestDispatcher("/admin-theloai.jsp");
            rd.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        TheLoaiDAO theLoaiDAO = new TheLoaiDAO();

        String maTheLoai = request.getParameter("maTheLoai");
        String tenTheLoai = request.getParameter("tenTheLoai");

        TheLoai tl = new TheLoai(maTheLoai, tenTheLoai);

        // Xử lý THÊM MỚI
        if ("add".equals(action)) {
            theLoaiDAO.insert(tl);
        } 
        // Xử lý CẬP NHẬT
        else if ("edit".equals(action)) {
            theLoaiDAO.update(tl);
        }

        // Hoàn tất thì quay về trang danh sách
        response.sendRedirect(request.getContextPath() + "/admin/the-loai");
    }
}