<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.KhachHang" %>
<%
    // Lấy thông tin khách hàng từ Session
    KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>bookstore - Cửa hàng sách hiện đại</title>
    
    <!-- Load Fonts & Icons -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <script src="https://unpkg.com/lucide@latest"></script>

    <!-- Tailwind CSS (CDN) -->
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        tailwind.config = {
            theme: {
                extend: {
                    fontFamily: { sans: ['Inter', 'sans-serif'] },
                    colors: {
                        surface: 'var(--surface)',
                        'surface-container-low': 'var(--surface-container-low)',
                        'surface-container': 'var(--surface-container)',
                        'surface-container-highest': 'var(--surface-container-highest)',
                        'on-surface': 'var(--on-surface)',
                        'on-surface-variant': 'var(--on-surface-variant)',
                        'outline-variant': 'var(--outline-variant)',
                        'primary-brand': 'var(--primary-brand)',
                        'tertiary-brand': 'var(--tertiary-brand)',
                    }
                }
            }
        }
    </script>

    <!-- Custom CSS Variables -->
    <style>
        :root {
            --surface: #fff8f5;
            --surface-container-lowest: #ffffff;
            --surface-container-low: #fbf2ed;
            --surface-container: #f5ece7;
            --surface-container-highest: #e9e1dc;
            --on-surface: #1e1b18;
            --on-surface-variant: #43474e;
            --outline-variant: #c4c6cf;
            --primary-brand: #000613;
            --tertiary-brand: #150100;
        }
        body {
            background-color: var(--surface);
            color: var(--on-surface);
        }
        /* Custom scrollbar ẩn */
        ::-webkit-scrollbar { width: 0px; background: transparent; }
    </style>
</head>
<body class="min-h-screen flex flex-col">

    <!-- Header / Nav -->
    <header class="bg-surface/95 backdrop-blur-md fixed top-0 w-full z-50 border-b border-outline-variant/20 shadow-sm">
        <div class="flex items-center justify-between px-6 md:px-12 h-20 max-w-7xl mx-auto">
            <div class="flex items-center gap-8">
                <a href="${pageContext.request.contextPath}/index.jsp" class="text-3xl font-bold tracking-tighter text-primary-brand">bookstore</a>
                <nav class="hidden md:flex gap-8">
                    <a href="#" class="text-sm font-medium text-on-surface-variant hover:text-primary-brand transition-colors">Hàng mới về</a>
                    <a href="#" class="text-sm font-medium text-on-surface-variant hover:text-primary-brand transition-colors">Lựa chọn của chúng tôi</a>
                    <a href="#" class="text-sm font-medium text-primary-brand border-b-2 border-primary-brand transition-colors">Thể loại</a>
                    <a href="#" class="text-sm font-medium text-on-surface-variant hover:text-primary-brand transition-colors">Sách hiếm</a>
                </nav>
            </div>

            <div class="flex items-center gap-4">
                <div class="relative hidden lg:block">
                    <i data-lucide="search" class="absolute left-3 top-1/2 -translate-y-1/2 w-4 h-4 text-on-surface-variant"></i>
                    <input type="text" placeholder="Tìm kiếm tiêu đề, tác giả..." class="pl-10 pr-4 py-2 bg-surface-container-low border-b border-outline-variant focus:border-primary-brand outline-none text-sm w-64 transition-colors bg-transparent"/>
                </div>
                <button class="p-2 hover:bg-surface-container transition-colors rounded-full">
                    <i data-lucide="shopping-cart" class="w-5 h-5 text-on-surface"></i>
                </button>
                
                <!-- Menu User Thông Minh (Có logic kiểm tra đăng nhập) -->
                <div class="relative">
                    <button id="userMenuBtn" class="p-2 hover:bg-surface-container transition-colors rounded-full focus:outline-none flex items-center gap-2">
                        <% if (khachHang != null) { %>
                            <!-- ĐÃ ĐĂNG NHẬP: Hiện chữ cái đầu tiên của tên và Tên -->
                            <div class="w-8 h-8 rounded-full bg-primary-brand text-white flex items-center justify-center text-sm font-bold uppercase">
                                <%= khachHang.getHoVaTen().substring(0, 1) %>
                            </div>
                            <span class="hidden md:block text-sm font-medium text-on-surface"><%= khachHang.getHoVaTen() %></span>
                            <i data-lucide="chevron-down" class="w-4 h-4 text-on-surface-variant hidden md:block"></i>
                        <% } else { %>
                            <!-- CHƯA ĐĂNG NHẬP: Hiện icon user mặc định -->
                            <i data-lucide="user" class="w-5 h-5 text-on-surface"></i>
                        <% } %>
                    </button>
                    
                    <!-- Dropdown Content -->
                    <div id="userDropdown" class="hidden absolute right-0 mt-2 w-48 bg-white border border-outline-variant shadow-lg rounded-lg py-2 z-50 origin-top-right">
                        <% if (khachHang != null) { %>
                            <!-- Menu dành cho người dùng đã đăng nhập -->
                            <div class="px-4 py-2 border-b border-outline-variant/30 mb-1">
                                <p class="text-xs text-on-surface-variant">Đăng nhập với tên</p>
                                <p class="text-sm font-bold text-primary-brand truncate"><%= khachHang.getTenDangNhap() %></p>
                            </div>
                            <a href="#" class="block px-4 py-2 text-sm text-on-surface hover:bg-surface-container transition-colors">Hồ sơ cá nhân</a>
                            <a href="#" class="block px-4 py-2 text-sm text-on-surface hover:bg-surface-container transition-colors">Đơn hàng của tôi</a>
                            <a href="${pageContext.request.contextPath}/logout" class="block px-4 py-2 text-sm text-red-600 hover:bg-red-50 transition-colors font-medium">Đăng xuất</a>
                        <% } else { %>
                            <!-- Menu dành cho người dùng chưa đăng nhập (Khách) -->
                            <a href="${pageContext.request.contextPath}/login.jsp" class="block px-4 py-2 text-sm text-on-surface hover:bg-surface-container transition-colors">Đăng nhập</a>
                            <a href="${pageContext.request.contextPath}/register.jsp" class="block px-4 py-2 text-sm text-on-surface hover:bg-surface-container transition-colors">Đăng ký</a>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- Main Content -->
    <main class="flex-grow pt-32 pb-20 max-w-7xl mx-auto px-6 md:px-12 w-full">
        <!-- Page Header -->
        <section class="flex flex-col md:flex-row justify-between items-end mb-12 border-b border-outline-variant/30 pb-6">
            <div>
                <h1 class="text-4xl font-bold text-primary-brand mb-2">Tiểu thuyết Đương đại</h1>
                <p class="text-on-surface-variant">Khám phá những câu chuyện hiện đại phản ánh thế giới ngày nay.</p>
            </div>
            <div class="flex items-center gap-4 mt-6 md:mt-0">
                <span class="text-xs font-bold uppercase tracking-widest text-on-surface-variant">Sắp xếp theo:</span>
                <select class="bg-transparent border-b border-outline-variant focus:border-primary-brand outline-none text-sm font-medium py-1 pr-8 cursor-pointer appearance-none transition-colors">
                    <option>Mới nhất</option>
                    <option>Giá: Thấp đến Cao</option>
                    <option>Giá: Cao đến Thấp</option>
                </select>
            </div>
        </section>

        <div class="flex gap-12">
            <!-- Sidebar Filters -->
            <aside class="w-64 flex-shrink-0 hidden lg:block">
                <div class="sticky top-28 space-y-10">
                    <div>
                        <h3 class="text-xs font-bold text-primary-brand uppercase tracking-[0.15em] mb-6">Định dạng</h3>
                        <div class="space-y-4">
                            <label class="flex items-center gap-3 cursor-pointer group">
                                <input type="checkbox" checked class="w-4 h-4 accent-primary-brand border-outline-variant cursor-pointer rounded-sm" />
                                <span class="text-sm text-on-surface font-medium">Bìa cứng</span>
                            </label>
                            <label class="flex items-center gap-3 cursor-pointer group">
                                <input type="checkbox" class="w-4 h-4 accent-primary-brand border-outline-variant cursor-pointer rounded-sm" />
                                <span class="text-sm text-on-surface-variant">Bìa mềm</span>
                            </label>
                            <label class="flex items-center gap-3 cursor-pointer group">
                                <input type="checkbox" class="w-4 h-4 accent-primary-brand border-outline-variant cursor-pointer rounded-sm" />
                                <span class="text-sm text-on-surface-variant">Sách điện tử</span>
                            </label>
                            <label class="flex items-center gap-3 cursor-pointer group">
                                <input type="checkbox" class="w-4 h-4 accent-primary-brand border-outline-variant cursor-pointer rounded-sm" />
                                <span class="text-sm text-on-surface-variant">Bản đặc biệt</span>
                            </label>
                        </div>
                    </div>

                    <div>
                        <h3 class="text-xs font-bold text-primary-brand uppercase tracking-[0.15em] mb-6">Giá</h3>
                        <div class="space-y-4">
                            <label class="flex items-center gap-3 cursor-pointer group">
                                <input type="radio" name="price" class="w-4 h-4 accent-primary-brand cursor-pointer" />
                                <span class="text-sm text-on-surface-variant">Dưới $25</span>
                            </label>
                            <label class="flex items-center gap-3 cursor-pointer group">
                                <input type="radio" name="price" checked class="w-4 h-4 accent-primary-brand cursor-pointer" />
                                <span class="text-sm text-on-surface font-medium">$25 - $50</span>
                            </label>
                            <label class="flex items-center gap-3 cursor-pointer group">
                                <input type="radio" name="price" class="w-4 h-4 accent-primary-brand cursor-pointer" />
                                <span class="text-sm text-on-surface-variant">Trên $50</span>
                            </label>
                        </div>
                    </div>
                </div>
            </aside>

            <!-- Book Grid (Đã thu gọn để làm mẫu, bạn giữ nguyên grid sách cũ của bạn) -->
            <div class="flex-grow">
                <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-x-8 gap-y-16">
                    <!-- Sách 1 -->
                    <div class="group flex flex-col cursor-pointer">
                        <div class="relative aspect-[2/3] w-full mb-4 overflow-hidden bg-surface-container-highest shadow-sm group-hover:shadow-md transition-all duration-300 rounded-sm">
                            <img src="https://images.unsplash.com/photo-1544947950-fa07a98d237f?auto=format&fit=crop&q=80&w=400" alt="The Silent Echo" class="object-cover w-full h-full group-hover:scale-105 transition-transform duration-500" />
                            <div class="absolute top-3 right-3 bg-white/90 backdrop-blur-sm px-2 py-1 flex items-center gap-1 rounded-sm shadow-sm">
                                <i data-lucide="star" class="w-3 h-3 text-[#150100] fill-[#150100]"></i>
                                <span class="text-[12px] font-bold text-on-surface">4.8</span>
                            </div>
                        </div>
                        <h3 class="text-lg font-bold text-primary-brand mb-1 line-clamp-1 group-hover:underline underline-offset-2">The Silent Echo</h3>
                        <p class="text-sm text-on-surface-variant mb-3">Elena Rostova</p>
                        <div class="mt-auto flex items-center justify-between">
                            <span class="text-lg font-semibold text-primary-brand">$28.00</span>
                            <span class="text-[10px] uppercase tracking-wider px-2 py-1 bg-surface-container-highest text-on-surface font-medium rounded-sm">Bìa cứng</span>
                        </div>
                    </div>
                    <!-- (Các cuốn sách khác giữ nguyên) -->
                </div>
            </div>
        </div>
    </main>

    <!-- JS Khởi tạo Icons và Toggle Dropdown -->
    <script>
        lucide.createIcons();

        // Xử lý ẩn/hiện User Menu Dropdown
        const userMenuBtn = document.getElementById('userMenuBtn');
        const userDropdown = document.getElementById('userDropdown');

        userMenuBtn.addEventListener('click', function(event) {
            event.stopPropagation();
            userDropdown.classList.toggle('hidden');
        });

        window.addEventListener('click', function(event) {
            if (!userMenuBtn.contains(event.target) && !userDropdown.contains(event.target)) {
                userDropdown.classList.add('hidden');
            }
        });
    </script>
</body>
</html>