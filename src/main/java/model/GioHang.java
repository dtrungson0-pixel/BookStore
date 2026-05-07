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

    // Thêm sản phẩm vào giỏ (nếu có rồi thì tăng số lượng)
    public void addItem(SanPham sp) {
        for (CartItem item : items) {
            if (item.getSanPham().getMaSanPham().equals(sp.getMaSanPham())) {
                item.setSoLuong(item.getSoLuong() + 1);
                return;
            }
        }
        items.add(new CartItem(sp, 1));
    }

    // Xóa sản phẩm khỏi giỏ
    public void removeItem(String maSanPham) {
        items.removeIf(item -> item.getSanPham().getMaSanPham().equals(maSanPham));
    }

    // Tính tổng tiền giỏ hàng
    public double getTotalAmount() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getSanPham().getGia() * item.getSoLuong();
        }
        return total;
    }
}