package model;

import java.sql.Date;

public class DonHang {
	private String maDonHang;
	private String diaChiNguoiMua;
	private String diaChiNhanHang;
	private KhachHang khachHang;
	private String trangThai;
	private String phuongThucThanhToan;
	private String trangThaiThanhToan;
	private String soTienDaThanhToan;
	private String soTienConThieu;
	private Date ngayDatHang;
	private Date ngayGiaoHang;

	public DonHang() {
		super();
	}

	public DonHang(String maDonHang, String diaChiNguoiMua, String diaChiNhanHang, KhachHang khachHang,
			String trangThai, String phuongThucThanhToan, String trangThaiThanhToan, String soTienDaThanhToan,
			String soTienConThieu, Date ngayDatHang, Date ngayGiaoHang) {
		super();
		this.maDonHang = maDonHang;
		this.diaChiNguoiMua = diaChiNguoiMua;
		this.diaChiNhanHang = diaChiNhanHang;
		this.khachHang = khachHang;
		this.trangThai = trangThai;
		this.phuongThucThanhToan = phuongThucThanhToan;
		this.trangThaiThanhToan = trangThaiThanhToan;
		this.soTienDaThanhToan = soTienDaThanhToan;
		this.soTienConThieu = soTienConThieu;
		this.ngayDatHang = ngayDatHang;
		this.ngayGiaoHang = ngayGiaoHang;
	}

	public String getMaDonHang() {
		return maDonHang;
	}

	public void setMaDonHang(String maDonHang) {
		this.maDonHang = maDonHang;
	}

	public String getDiaChiNguoiMua() {
		return diaChiNguoiMua;
	}

	public void setDiaChiNguoiMua(String diaChiNguoiMua) {
		this.diaChiNguoiMua = diaChiNguoiMua;
	}

	public String getDiaChiNhanHang() {
		return diaChiNhanHang;
	}

	public void setDiaChiNhanHang(String diaChiNhanHang) {
		this.diaChiNhanHang = diaChiNhanHang;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public String getPhuongThucThanhToan() {
		return phuongThucThanhToan;
	}

	public void setPhuongThucThanhToan(String phuongThucThanhToan) {
		this.phuongThucThanhToan = phuongThucThanhToan;
	}

	public String getTrangThaiThanhToan() {
		return trangThaiThanhToan;
	}

	public void setTrangThaiThanhToan(String trangThaiThanhToan) {
		this.trangThaiThanhToan = trangThaiThanhToan;
	}

	public String getSoTienDaThanhToan() {
		return soTienDaThanhToan;
	}

	public void setSoTienDaThanhToan(String soTienDaThanhToan) {
		this.soTienDaThanhToan = soTienDaThanhToan;
	}

	public String getSoTienConThieu() {
		return soTienConThieu;
	}

	public void setSoTienConThieu(String soTienConThieu) {
		this.soTienConThieu = soTienConThieu;
	}

	public Date getNgayDatHang() {
		return ngayDatHang;
	}

	public void setNgayDatHang(Date ngayDatHang) {
		this.ngayDatHang = ngayDatHang;
	}

	public Date getNgayGiaoHang() {
		return ngayGiaoHang;
	}

	public void setNgayGiaoHang(Date ngayGiaoHang) {
		this.ngayGiaoHang = ngayGiaoHang;
	}

	
}
