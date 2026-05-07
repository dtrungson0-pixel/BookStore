<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập - bookstore</title>
    
    <!-- Load Fonts & Icons -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;900&display=swap" rel="stylesheet">
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

    <!-- Custom CSS Variables đồng bộ với giao diện chính -->
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
    </style>
</head>
<body class="min-h-screen flex flex-col">

    <!-- Header / Nav -->
    <header class="bg-surface/95 backdrop-blur-md fixed top-0 w-full z-50 border-b border-outline-variant/20 shadow-sm">
        <div class="flex items-center justify-between px-6 md:px-12 h-20 max-w-7xl mx-auto">
            <div class="flex items-center gap-8">
                <a href="${pageContext.request.contextPath}/index.jsp" class="text-3xl font-bold tracking-tighter text-primary-brand">bookstore</a>
            </div>

            <div class="flex items-center space-x-4">
                <span class="text-sm text-on-surface-variant hidden sm:inline">Chưa có tài khoản?</span>
                <a href="${pageContext.request.contextPath}/register.jsp" class="text-sm font-bold text-primary-brand hover:underline underline-offset-4 transition-all">Đăng ký ngay</a>
            </div>
        </div>
    </header>

    <!-- Nội dung chính Form Đăng nhập -->
    <main class="flex-grow flex items-center justify-center pt-28 pb-20 px-6 w-full">
        <div class="bg-white rounded-xl shadow-sm border border-outline-variant/30 overflow-hidden w-full max-w-md">
            <div class="p-8 sm:p-10">
                <div class="mb-8 text-center">
                    <h1 class="text-3xl font-bold text-primary-brand mb-3">Chào mừng trở lại</h1>
                    <p class="text-sm text-on-surface-variant">Vui lòng đăng nhập để tiếp tục khám phá bookstore.</p>
                </div>

                <!-- Hiển thị thông báo lỗi nếu đăng nhập thất bại -->
                <%
                String baoLoi = (String) request.getAttribute("baoLoi");
                if (baoLoi != null) {
                %>
                <div class="mb-6 bg-red-50 border-l-4 border-red-500 p-4 rounded-md flex items-start gap-3">
                    <i data-lucide="alert-circle" class="w-5 h-5 text-red-500 mt-0.5 shrink-0"></i>
                    <p class="text-sm text-red-700 font-medium"><%=baoLoi%></p>
                </div>
                <%
                }
                %>

                <!-- Hiển thị thông báo thành công (từ trang đăng ký chuyển qua) -->
                <%
                String thongBao = (String) request.getAttribute("thongBao");
                if (thongBao != null) {
                %>
                <div class="mb-6 bg-green-50 border-l-4 border-green-500 p-4 rounded-md flex items-start gap-3">
                    <i data-lucide="check-circle-2" class="w-5 h-5 text-green-500 mt-0.5 shrink-0"></i>
                    <p class="text-sm text-green-700 font-medium"><%=thongBao%></p>
                </div>
                <%
                }
                %>

                <form action="${pageContext.request.contextPath}/login" method="POST" class="space-y-6">
                    
                    <!-- Tên đăng nhập -->
                    <div>
                        <label class="block text-xs font-bold text-primary-brand uppercase tracking-widest mb-2">
                            Tên đăng nhập
                        </label> 
                        <div class="relative">
                            <i data-lucide="user" class="absolute left-4 top-1/2 -translate-y-1/2 w-4 h-4 text-outline-variant"></i>
                            <input type="text" name="tenDangNhap" placeholder="Nhập tên đăng nhập" required
                                class="w-full bg-transparent border border-outline-variant rounded-md py-3 pl-11 pr-4 text-sm text-on-surface focus:border-primary-brand focus:outline-none transition-colors placeholder:text-outline-variant">
                        </div>
                    </div>

                    <!-- Mật khẩu -->
                    <div>
                        <label class="block text-xs font-bold text-primary-brand uppercase tracking-widest mb-2">
                            Mật khẩu
                        </label> 
                        <div class="relative">
                            <i data-lucide="lock" class="absolute left-4 top-1/2 -translate-y-1/2 w-4 h-4 text-outline-variant"></i>
                            <input type="password" name="matKhau" placeholder="••••••••" required
                                class="w-full bg-transparent border border-outline-variant rounded-md py-3 pl-11 pr-4 text-sm text-on-surface focus:border-primary-brand focus:outline-none transition-colors placeholder:text-outline-variant">
                        </div>
                    </div>

                    <!-- Ghi nhớ đăng nhập & Quên mật khẩu -->
                    <div class="flex items-center justify-between pt-2">
                        <label class="flex items-center gap-2 cursor-pointer group">
                            <input type="checkbox" name="ghiNho" 
                            class="w-4 h-4 accent-primary-brand border-outline-variant cursor-pointer rounded-sm" />
                            <span class="text-sm text-on-surface-variant group-hover:text-primary-brand transition-colors">Ghi nhớ tôi</span>
                        </label>
                        
                        <a href="#" class="text-sm font-medium text-primary-brand hover:underline underline-offset-4 transition-all">
                            Quên mật khẩu?
                        </a>
                    </div>

                    <!-- Nút Đăng nhập -->
                    <div class="pt-4">
                        <button type="submit"
                            class="w-full bg-primary-brand hover:bg-primary-brand/90 text-white font-bold py-4 rounded-md transition-all flex items-center justify-center gap-2">
                            Đăng Nhập
                            <i data-lucide="arrow-right" class="w-4 h-4"></i>
                        </button>
                    </div>

                </form>
            </div>
            
            <div class="bg-surface-container-low p-6 text-center border-t border-outline-variant/30">
                <p class="text-sm text-on-surface-variant">
                    Hoặc đăng nhập bằng
                </p>
                <div class="flex justify-center gap-4 mt-4">
                    <button class="w-12 h-12 flex items-center justify-center rounded-full border border-outline-variant hover:border-primary-brand hover:bg-surface transition-all">
                        <svg class="w-5 h-5" viewBox="0 0 24 24" fill="currentColor" xmlns="http://www.w3.org/2000/svg"><path d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z" fill="#4285F4"/><path d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z" fill="#34A853"/><path d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l2.85-2.22.81-.62z" fill="#FBBC05"/><path d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z" fill="#EA4335"/></svg>
                    </button>
                    <button class="w-12 h-12 flex items-center justify-center rounded-full border border-outline-variant hover:border-primary-brand hover:bg-surface transition-all">
                        <svg class="w-5 h-5 text-[#1877F2]" fill="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="M24 12.073c0-6.627-5.373-12-12-12s-12 5.373-12 12c0 5.99 4.388 10.954 10.125 11.854v-8.385H7.078v-3.469h3.047V9.43c0-3.007 1.792-4.669 4.533-4.669 1.312 0 2.686.235 2.686.235v2.953H15.83c-1.491 0-1.956.925-1.956 1.874v2.25h3.328l-.532 3.469h-2.796v8.385C19.612 23.027 24 18.062 24 12.073z"/></svg>
                    </button>
                </div>
            </div>
        </div>
    </main>

    <!-- Footer -->
    <footer class="py-6 text-center text-xs text-on-surface-variant w-full">
        © 2024 bookstore. All rights reserved.
    </footer>

    <!-- Khởi tạo Icon -->
    <script>
        lucide.createIcons();
    </script>
</body>
</html>