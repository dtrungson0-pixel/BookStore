<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.GioHang" %>
<%@ page import="model.CartItem" %>
<%@ page import="model.KhachHang" %>
<%
    GioHang cart = (GioHang) session.getAttribute("cart");
    KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Giỏ hàng của bạn - bookstore</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://unpkg.com/lucide@latest"></script>
</head>
<body class="bg-[#fff8f5] text-[#1e1b18] min-h-screen flex flex-col">

    <header class="bg-white/95 backdrop-blur-md border-b border-gray-200 sticky top-0 z-50">
        <div class="max-w-7xl mx-auto px-6 h-20 flex items-center justify-between">
            <a href="index.jsp" class="text-3xl font-bold tracking-tighter text-[#000613]">bookstore</a>
            <a href="index.jsp" class="text-sm font-medium hover:underline flex items-center gap-2"><i data-lucide="arrow-left" class="w-4 h-4"></i> Tiếp tục mua sắm</a>
        </div>
    </header>

    <main class="flex-grow max-w-5xl mx-auto px-6 py-12 w-full">
        <h1 class="text-3xl font-bold mb-8">Giỏ hàng của bạn</h1>

        <% if (cart == null || cart.getItems().isEmpty()) { %>
            <div class="text-center py-20 bg-white rounded-xl border border-gray-200">
                <i data-lucide="shopping-cart" class="w-16 h-16 mx-auto text-gray-300 mb-4"></i>
                <h2 class="text-xl font-bold text-gray-600">Giỏ hàng trống</h2>
                <p class="text-gray-500 mt-2 mb-6">Bạn chưa có sản phẩm nào trong giỏ hàng.</p>
                <a href="index.jsp" class="bg-[#000613] text-white px-6 py-3 rounded-md font-bold">Đi mua sắm ngay</a>
            </div>
        <% } else { %>
            <div class="flex flex-col lg:flex-row gap-8">
                <div class="flex-grow bg-white rounded-xl border border-gray-200 p-6">
                    <table class="w-full text-left">
                        <thead>
                            <tr class="border-b border-gray-200 text-sm text-gray-500 uppercase tracking-wider">
                                <th class="pb-4">Sản phẩm</th>
                                <th class="pb-4 text-center">Số lượng</th>
                                <th class="pb-4 text-right">Đơn giá</th>
                                <th class="pb-4 text-center">Xóa</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (CartItem item : cart.getItems()) { %>
                            <tr class="border-b border-gray-100">
                                <td class="py-4 flex items-center gap-4">
                                    <div class="w-16 h-24 bg-gray-100 rounded overflow-hidden">
                                        <img src="https://images.unsplash.com/photo-1544947950-fa07a98d237f?w=100" class="w-full h-full object-cover">
                                    </div>
                                    <div>
                                        <p class="font-bold text-gray-900"><%= item.getSanPham().getTenSanPham() %></p>
                                        <p class="text-xs text-gray-500">Mã: <%= item.getSanPham().getMaSanPham() %></p>
                                    </div>
                                </td>
                                <td class="py-4 text-center font-medium"><%= item.getSoLuong() %></td>
                                <td class="py-4 text-right font-medium">$<%= item.getSanPham().getGia() %></td>
                                <td class="py-4 text-center">
                                    <a href="${pageContext.request.contextPath}/cart?action=remove&id=<%= item.getSanPham().getMaSanPham() %>" 
                                       class="text-red-500 hover:text-red-700 flex justify-center">
                                        <i data-lucide="trash-2" class="w-5 h-5"></i>
                                    </a>
                                </td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>

                <div class="w-full lg:w-80 flex-shrink-0">
                    <div class="bg-white rounded-xl border border-gray-200 p-6 sticky top-28">
                        <h2 class="text-lg font-bold mb-4">Tóm tắt đơn hàng</h2>
                        <div class="flex justify-between mb-2 text-sm">
                            <span class="text-gray-600">Tổng phụ</span>
                            <span class="font-bold">$<%= cart.getTotalAmount() %></span>
                        </div>
                        <div class="flex justify-between mb-6 text-sm">
                            <span class="text-gray-600">Phí giao hàng</span>
                            <span class="font-bold text-green-600">Miễn phí</span>
                        </div>
                        <div class="border-t border-gray-200 pt-4 mb-6 flex justify-between items-center">
                            <span class="font-bold text-gray-900">Tổng cộng</span>
                            <span class="text-2xl font-black text-[#000613]">$<%= cart.getTotalAmount() %></span>
                        </div>
                        
                        <form action="${pageContext.request.contextPath}/checkout" method="POST">
                            <% if (khachHang == null) { %>
                                <a href="login.jsp" class="block text-center w-full bg-[#000613] text-white py-3 rounded-md font-bold hover:bg-gray-800 transition">Đăng nhập để Thanh toán</a>
                            <% } else { %>
                                <button type="submit" class="w-full bg-[#000613] text-white py-3 rounded-md font-bold hover:bg-gray-800 transition flex justify-center items-center gap-2">
                                    Tiến hành Thanh toán <i data-lucide="arrow-right" class="w-4 h-4"></i>
                                </button>
                            <% } %>
                        </form>
                    </div>
                </div>
            </div>
        <% } %>
    </main>

    <script>lucide.createIcons();</script>
</body>
</html>