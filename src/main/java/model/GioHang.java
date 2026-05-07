package model;

import java.util.ArrayList;
import java.util.List;

public class GioHang {
    private List<CartItem> items;

    public GioHang() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return items;
    }

    // Thêm sản phẩm vào giỏ (nếu đã có thì tăng số lượng)
    public void addItem(SanPham sp) {
        for (CartItem item : items) {
            // SỬA: Dùng getMaSach() thay vì getMaSanPham()
            if (item.getSanPham().getMaSach().equals(sp.getMaSach())) {
                item.setSoLuong(item.getSoLuong() + 1);
                return;
            }
        }
        items.add(new CartItem(sp, 1));
    }

    // Xóa sản phẩm khỏi giỏ
    public void removeItem(String maSach) {
        // SỬA: Dùng getMaSach()
        items.removeIf(item -> item.getSanPham().getMaSach().equals(maSach));
    }

    // Tính tổng tiền giỏ hàng
    public double getTotalAmount() {
        double total = 0;
        for (CartItem item : items) {
            // SỬA: Dùng getGiaBan()
            total += item.getSanPham().getGiaBan() * item.getSoLuong(); 
        }
        return total;
    }
}