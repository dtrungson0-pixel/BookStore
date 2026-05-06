<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Đăng ký tài khoản - LITERA</title>

<!-- Load Fonts & Icons -->
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;900&display=swap"
	rel="stylesheet">
<script src="https://unpkg.com/lucide@latest"></script>

<!-- Tailwind CSS (CDN) -->
<script src="https://cdn.tailwindcss.com"></script>
<script>
	tailwind.config = {
		theme : {
			extend : {
				fontFamily : {
					sans : [ 'Inter', 'sans-serif' ]
				},
				colors : {
					surface : 'var(--surface)',
					'surface-container-low' : 'var(--surface-container-low)',
					'surface-container' : 'var(--surface-container)',
					'surface-container-highest' : 'var(--surface-container-highest)',
					'on-surface' : 'var(--on-surface)',
					'on-surface-variant' : 'var(--on-surface-variant)',
					'outline-variant' : 'var(--outline-variant)',
					'primary-brand' : 'var(--primary-brand)',
					'tertiary-brand' : 'var(--tertiary-brand)',
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
	<header
		class="bg-surface/95 backdrop-blur-md fixed top-0 w-full z-50 border-b border-outline-variant/20 shadow-sm">
		<div
			class="flex items-center justify-between px-6 md:px-12 h-20 max-w-7xl mx-auto">
			<div class="flex items-center gap-8">
				<a href="index.jsp"
					class="text-3xl font-bold tracking-tighter text-primary-brand">LITERA</a>
			</div>

			<div class="flex items-center space-x-4">
				<span class="text-sm text-on-surface-variant">Đã có tài
					khoản?</span> <a href="login.jsp"
					class="text-sm font-bold text-primary-brand hover:underline underline-offset-4 transition-all">Đăng
					nhập</a>
			</div>
		</div>
	</header>

	<!-- Nội dung chính Form Đăng ký -->
	<main class="flex-grow pt-32 pb-20 max-w-4xl mx-auto px-6 w-full">
		<div
			class="bg-white rounded-xl shadow-sm border border-outline-variant/30 overflow-hidden">
			<div class="p-8 md:p-12">
				<div class="mb-10 text-center">
					<h1 class="text-3xl font-bold text-primary-brand mb-3">Tạo tài
						khoản mới</h1>
					<p class="text-sm text-on-surface-variant">Vui lòng điền đầy đủ
						thông tin bên dưới để tham gia cộng đồng LITERA.</p>
				</div>
				<%
				String baoLoi = (String) request.getAttribute("baoLoi");
				String thongBaoThanhCong = (String) request.getAttribute("thongBaoThanhCong");
				%>
				<%
				if (baoLoi != null) {
				%>
				<div class="mb-6 bg-red-50 border-l-4 border-red-500 p-4 rounded-md">
					<p class="text-sm text-red-700 font-medium"><%=baoLoi%></p>
				</div>
				<%
				}
				%>

				<%
				if (thongBaoThanhCong != null) {
				%>
				<div
					class="mb-6 bg-green-50 border-l-4 border-green-500 p-4 rounded-md">
					<p class="text-sm text-green-700 font-medium"><%=thongBaoThanhCong%></p>
					<a href="login.jsp"
						class="text-sm font-bold text-green-800 underline mt-2 inline-block">Đăng
						nhập ngay</a>
				</div>
				<%
				}
				%>

				<form action="register" method="POST" class="space-y-10">

					<!-- Phần 1: Thông tin đăng nhập -->
					<div class="grid grid-cols-1 md:grid-cols-2 gap-6">
						<div class="col-span-1">
							<label
								class="block text-xs font-bold text-primary-brand uppercase tracking-widest mb-2">Tên
								đăng nhập <span class="text-[#e65100]">*</span>
							</label> <input type="text" name="tenDangNhap"
								placeholder="Ví dụ: nva123"
								class="w-full bg-transparent border border-outline-variant rounded-md py-3 px-4 text-sm text-on-surface focus:border-primary-brand focus:outline-none transition-colors placeholder:text-outline-variant">
						</div>
						<div class="col-span-1">
							<label
								class="block text-xs font-bold text-primary-brand uppercase tracking-widest mb-2">Mật
								khẩu <span class="text-[#e65100]">*</span>
							</label> <input type="password" name="matKhau" placeholder="••••••••"
								class="w-full bg-transparent border border-outline-variant rounded-md py-3 px-4 text-sm text-on-surface focus:border-primary-brand focus:outline-none transition-colors placeholder:text-outline-variant">
						</div>
					</div>

					<!-- Phần 2: Thông tin cá nhân -->
					<div class="pt-8 border-t border-outline-variant/30">
						<h2 class="text-lg font-bold text-primary-brand mb-6">Thông
							tin cá nhân</h2>
						<div class="grid grid-cols-1 md:grid-cols-2 gap-6">
							<div class="col-span-1 md:col-span-2">
								<label
									class="block text-xs font-bold text-primary-brand uppercase tracking-widest mb-2">Họ
									và tên <span class="text-[#e65100]">*</span>
								</label> <input type="text" name="hoVaTen" placeholder="Nguyễn Văn A"
									class="w-full bg-transparent border border-outline-variant rounded-md py-3 px-4 text-sm text-on-surface focus:border-primary-brand focus:outline-none transition-colors placeholder:text-outline-variant">
							</div>

							<div>
								<label
									class="block text-xs font-bold text-primary-brand uppercase tracking-widest mb-2">Giới
									tính</label>
								<div class="flex space-x-6 py-3">
									<label class="flex items-center cursor-pointer group">
										<input type="radio" name="gioiTinh" value="Nam"
										class="w-4 h-4 accent-primary-brand cursor-pointer"> <span
										class="ml-2 text-sm text-on-surface-variant group-hover:text-primary-brand transition-colors">Nam</span>
									</label> <label class="flex items-center cursor-pointer group">
										<input type="radio" name="gioiTinh" value="Nữ"
										class="w-4 h-4 accent-primary-brand cursor-pointer"> <span
										class="ml-2 text-sm text-on-surface-variant group-hover:text-primary-brand transition-colors">Nữ</span>
									</label> <label class="flex items-center cursor-pointer group">
										<input type="radio" name="gioiTinh" value="Khác"
										class="w-4 h-4 accent-primary-brand cursor-pointer"> <span
										class="ml-2 text-sm text-on-surface-variant group-hover:text-primary-brand transition-colors">Khác</span>
									</label>
								</div>
							</div>

							<div>
								<label
									class="block text-xs font-bold text-primary-brand uppercase tracking-widest mb-2">Ngày
									sinh</label> <input type="date" name="ngaySinh"
									class="w-full bg-transparent border border-outline-variant rounded-md py-3 px-4 text-sm text-on-surface focus:border-primary-brand focus:outline-none transition-colors">
							</div>

							<div>
								<label
									class="block text-xs font-bold text-primary-brand uppercase tracking-widest mb-2">Số
									điện thoại</label> <input type="tel" name="soDienThoai"
									placeholder="0901xxxxxx"
									class="w-full bg-transparent border border-outline-variant rounded-md py-3 px-4 text-sm text-on-surface focus:border-primary-brand focus:outline-none transition-colors placeholder:text-outline-variant">
							</div>

							<div>
								<label
									class="block text-xs font-bold text-primary-brand uppercase tracking-widest mb-2">Email
									<span class="text-[#e65100]">*</span>
								</label> <input type="email" name="email"
									placeholder="example@gmail.com"
									class="w-full bg-transparent border border-outline-variant rounded-md py-3 px-4 text-sm text-on-surface focus:border-primary-brand focus:outline-none transition-colors placeholder:text-outline-variant">
							</div>
						</div>
					</div>

					<!-- Phần 3: Địa chỉ -->
					<div class="pt-8 border-t border-outline-variant/30">
						<h2 class="text-lg font-bold text-primary-brand mb-6">Thông
							tin địa chỉ</h2>
						<div class="space-y-4">
							<div>
								<label
									class="block text-xs font-bold text-primary-brand uppercase tracking-widest mb-2">Địa
									chỉ thường trú</label> <input type="text" name="diaChi"
									placeholder="Số nhà, tên đường, phường/xã..."
									class="w-full bg-transparent border border-outline-variant rounded-md py-3 px-4 text-sm text-on-surface focus:border-primary-brand focus:outline-none transition-colors placeholder:text-outline-variant">
							</div>
							<div>
								<label
									class="block text-xs font-bold text-primary-brand uppercase tracking-widest mb-2">Địa
									chỉ nhận hàng</label> <input type="text" name="diaChiNhanHang"
									placeholder="Nơi bạn muốn nhận sách"
									class="w-full bg-transparent border border-outline-variant rounded-md py-3 px-4 text-sm text-on-surface focus:border-primary-brand focus:outline-none transition-colors placeholder:text-outline-variant">
							</div>
							<div>
								<label
									class="block text-xs font-bold text-primary-brand uppercase tracking-widest mb-2">Địa
									chỉ mua hàng</label> <input type="text" name="diaChiMuaHang"
									placeholder="Địa chỉ xuất hóa đơn (nếu có)"
									class="w-full bg-transparent border border-outline-variant rounded-md py-3 px-4 text-sm text-on-surface focus:border-primary-brand focus:outline-none transition-colors placeholder:text-outline-variant">
							</div>
						</div>
					</div>

					<!-- Phần 4: Tùy chọn khác -->
					<div class="pt-8 border-t border-outline-variant/30">
						<label class="flex items-start gap-3 cursor-pointer group">
							<input type="checkbox" name="dangKyNhanBanTin" id="newsletter"
							class="mt-1 w-4 h-4 accent-primary-brand border-outline-variant cursor-pointer rounded-sm" />
							<div class="text-sm">
								<span class="font-medium text-on-surface block mb-1">Đăng
									ký nhận bản tin qua Email</span> <span class="text-on-surface-variant">Nhận
									thông báo về các đầu sách mới nhất và chương trình khuyến mãi
									độc quyền.</span>
							</div>
						</label>
					</div>

					<!-- Nút Đăng ký -->
					<div class="pt-6">
						<button type="submit"
							class="w-full bg-primary-brand hover:bg-primary-brand/90 text-white font-bold py-4 rounded-md transition-all">
							Hoàn Tất Đăng Ký</button>
						<p
							class="text-center text-xs text-on-surface-variant mt-5 px-10 leading-relaxed">
							Bằng cách đăng ký, bạn đồng ý với <a href="#"
								class="text-primary-brand font-medium hover:underline">điều
								khoản dịch vụ</a> và <a href="#"
								class="text-primary-brand font-medium hover:underline">chính
								sách bảo mật</a> của LITERA.
						</p>
					</div>

				</form>
			</div>
		</div>

		<!-- Nút quay lại -->
		<div class="text-center mt-8">
			<a href="index.jsp"
				class="inline-flex items-center text-on-surface-variant hover:text-primary-brand transition-colors">
				<i data-lucide="arrow-left" class="w-4 h-4 mr-2"></i> <span
				class="text-sm font-medium">Quay lại trang chủ</span>
			</a>
		</div>
	</main>

	<!-- Footer -->
	<footer class="py-8 text-center text-xs text-on-surface-variant">
		© 2024 LITERA Bookstore. All rights reserved. </footer>

	<!-- Khởi tạo Icon -->
	<script>
		lucide.createIcons();
	</script>
</body>
</html>