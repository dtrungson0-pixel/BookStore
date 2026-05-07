<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.TheLoai" %>
<%
    ArrayList<TheLoai> listTheLoai = (ArrayList<TheLoai>) request.getAttribute("listTheLoai");
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý Thể Loại - Admin</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://unpkg.com/lucide@latest"></script>
</head>
<body class="bg-gray-50 text-gray-800 p-8">

    <div class="max-w-6xl mx-auto bg-white p-6 rounded-xl shadow-sm border border-gray-200">
        <h1 class="text-2xl font-bold mb-6 text-gray-900">Quản lý Thể Loại</h1>

        <div class="bg-gray-50 p-5 rounded-lg border border-gray-200 mb-8">
            <h2 id="formTitle" class="text-lg font-semibold mb-4">Thêm Thể Loại Mới</h2>
            <form action="${pageContext.request.contextPath}/admin/the-loai" method="POST" class="flex items-end gap-4">
                <input type="hidden" name="action" id="formAction" value="add">
                
                <div class="flex-1">
                    <label class="block text-sm font-bold text-gray-700 mb-1">Mã Thể Loại</label>
                    <input type="text" name="maTheLoai" id="maTheLoai" required class="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:border-blue-500">
                </div>
                
                <div class="flex-1">
                    <label class="block text-sm font-bold text-gray-700 mb-1">Tên Thể Loại</label>
                    <input type="text" name="tenTheLoai" id="tenTheLoai" required class="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:border-blue-500">
                </div>

                <button type="submit" id="btnSubmit" class="bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-6 rounded transition-colors">
                    Lưu
                </button>
                <button type="button" onclick="resetForm()" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded transition-colors">
                    Hủy
                </button>
            </form>
        </div>

        <table class="w-full text-left border-collapse">
            <thead>
                <tr class="bg-gray-100 text-gray-700">
                    <th class="p-3 border-b">Mã Thể Loại</th>
                    <th class="p-3 border-b">Tên Thể Loại</th>
                    <th class="p-3 border-b w-32">Thao tác</th>
                </tr>
            </thead>
            <tbody>
                <% if(listTheLoai != null) { 
                    for(TheLoai tl : listTheLoai) { %>
                <tr class="hover:bg-gray-50">
                    <td class="p-3 border-b font-medium"><%= tl.getMaTheLoai() %></td>
                    <td class="p-3 border-b"><%= tl.getTenTheLoai() %></td>
                    <td class="p-3 border-b flex gap-3">
                        <button onclick="editMode('<%= tl.getMaTheLoai() %>', '<%= tl.getTenTheLoai() %>')" class="text-blue-600 hover:text-blue-800">
                            <i data-lucide="edit" class="w-5 h-5"></i>
                        </button>
                        <a href="${pageContext.request.contextPath}/admin/the-loai?action=delete&id=<%= tl.getMaTheLoai() %>" 
                           onclick="return confirm('Bạn có chắc muốn xóa thể loại này?')" 
                           class="text-red-600 hover:text-red-800">
                            <i data-lucide="trash-2" class="w-5 h-5"></i>
                        </a>
                    </td>
                </tr>
                <%  } 
                   } %>
            </tbody>
        </table>
    </div>

    <script>
        lucide.createIcons();

        // JS giúp chuyển đổi qua lại giữa trạng thái Thêm và Sửa trên cùng 1 Form
        function editMode(ma, ten) {
            document.getElementById('formTitle').innerText = "Cập nhật Thể Loại";
            document.getElementById('formAction').value = "edit";
            
            let inputMa = document.getElementById('maTheLoai');
            inputMa.value = ma;
            inputMa.readOnly = true; // Không cho sửa mã
            inputMa.classList.add('bg-gray-100');
            
            document.getElementById('tenTheLoai').value = ten;
            document.getElementById('btnSubmit').innerText = "Cập nhật";
        }

        function resetForm() {
            document.getElementById('formTitle').innerText = "Thêm Thể Loại Mới";
            document.getElementById('formAction').value = "add";
            
            let inputMa = document.getElementById('maTheLoai');
            inputMa.value = "";
            inputMa.readOnly = false;
            inputMa.classList.remove('bg-gray-100');
            
            document.getElementById('tenTheLoai').value = "";
            document.getElementById('btnSubmit').innerText = "Lưu";
        }
    </script>
</body>
</html>