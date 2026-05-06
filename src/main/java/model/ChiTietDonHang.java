package model;

public class ChiTietDonHang {
	private String maChiTietDonHang;
	private DonHang donHang;
	private SanPham sanPham;
	private int soLuong;
	private double giaGoc;
	private double giamGia;
	private double giaBan;
	private double thueVAT;
	private double tongThanhTien;

	public ChiTietDonHang() {
		super();
	}

	public ChiTietDonHang(String maChiTietDonHang, DonHang donHang, SanPham sanPham, int soLuong, double giaGoc,
			double giamGia, double giaBan, double thueVAT, double tongThanhTien) {
		super();
		this.maChiTietDonHang = maChiTietDonHang;
		this.donHang = donHang;
		this.sanPham = sanPham;
		this.soLuong = soLuong;
		this.giaGoc = giaGoc;
		this.giamGia = giamGia;
		this.giaBan = giaBan;
		this.thueVAT = thueVAT;
		this.tongThanhTien = tongThanhTien;
	}

	public String getMaChiTietDonHang() {
		return maChiTietDonHang;
	}

	public void setMaChiTietDonHang(String maChiTietDonHang) {
		this.maChiTietDonHang = maChiTietDonHang;
	}

	public DonHang getDonHang() {
		return donHang;
	}

	public void setDonHang(DonHang donHang) {
		this.donHang = donHang;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getGiaGoc() {
		return giaGoc;
	}

	public void setGiaGoc(double giaGoc) {
		this.giaGoc = giaGoc;
	}

	public double getGiamGia() {
		return giamGia;
	}

	public void setGiamGia(double giamGia) {
		this.giamGia = giamGia;
	}

	public double getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}

	public double getThueVAT() {
		return thueVAT;
	}

	public void setThueVAT(double thueVAT) {
		this.thueVAT = thueVAT;
	}

	public double getTongThanhTien() {
		return tongThanhTien;
	}

	public void setTongThanhTien(double tongThanhTien) {
		this.tongThanhTien = tongThanhTien;
	}

	@Override
	public String toString() {
		return "ChiTietDonHang [maChiTietDonHang=" + maChiTietDonHang + ", donHang=" + donHang + ", sanPham=" + sanPham
				+ ", soLuong=" + soLuong + ", giaGoc=" + giaGoc + ", giamGia=" + giamGia + ", giaBan=" + giaBan
				+ ", thueVAT=" + thueVAT + ", tongThanhTien=" + tongThanhTien + "]";
	}

}
