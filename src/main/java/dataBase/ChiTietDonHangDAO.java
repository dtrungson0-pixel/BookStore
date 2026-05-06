package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.ChiTietDonHang;
import model.DonHang;
import model.SanPham;

public class ChiTietDonHangDAO implements DAOInterface<ChiTietDonHang> {

	@Override
	public ArrayList<ChiTietDonHang> selectAll() {
		ArrayList<ChiTietDonHang> arrayList = new ArrayList<ChiTietDonHang>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "select * from chitietdonhang";
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				String maChiTietDonHang = rs.getString("maChiTietDonHang");
				String madonHang = rs.getString("madonHang");
				String maSach = rs.getString("maSach");
				int soLuong = rs.getInt("soLuong");
				double giaGoc = rs.getDouble("giaGoc");
				double giamGia = rs.getDouble("giamGia");
				double giaBan = rs.getDouble("giaBan");
				double thueVAT = rs.getDouble("thueVAT");
				double tongThanhTien = rs.getDouble("tongThanhTien");
				DonHang donHang = new DonHang(madonHang, null, null, null, null, null, null, null, null, null, null);
				SanPham sanPham = new SanPham(maSach, null, null, 0, 0, 0, 0, 0, null, null, null);
				ChiTietDonHang chiTietDonHang  = new ChiTietDonHang(maChiTietDonHang, donHang, sanPham, soLuong, giaGoc, giamGia, giaBan, thueVAT, tongThanhTien);
				arrayList.add(chiTietDonHang);
			}
			JDBCUtil.close(con, pstm, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	return arrayList;
	}

	@Override
	public ChiTietDonHang selectByID(ChiTietDonHang t) {
		ChiTietDonHang chiTietDonHang = null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "select * from chitietdonhang where maChiTietDonHang=?";
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, t.getMaChiTietDonHang());
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				String maChiTietDonHang = rs.getString("maChiTietDonHang");
				String madonHang = rs.getString("madonHang");
				String maSanPham = rs.getString("maSanPham");
				int soLuong = rs.getInt("soLuong");
				double giaGoc = rs.getDouble("giaGoc");
				double giamGia = rs.getDouble("giamGia");
				double giaBan = rs.getDouble("giaBan");
				double thueVAT = rs.getDouble("thueVAT");
				double tongThanhTien = rs.getDouble("tongThanhTien");
				DonHang donHang = new DonHang(madonHang, null, null, null, null, null, null, null, null, null, null);
				SanPham sanPham = new SanPham(maSanPham, null, null, 0, 0, 0, 0, 0, null, null, null);
				chiTietDonHang  = new ChiTietDonHang(maChiTietDonHang, donHang, sanPham, soLuong, giaGoc, giamGia, giaBan, thueVAT, tongThanhTien);
				break;
			}
			JDBCUtil.close(con, pstm, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	return chiTietDonHang;
	}

	@Override
	public int insert(ChiTietDonHang t) {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "insert into chitietdonhang(maChiTietDonHang, donHang, sanPham, soLuong, giaGoc, giamGia, giaBan, thueVAT, tongThanhTien) values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, t.getMaChiTietDonHang());
			pstm.setString(2, t.getDonHang().getMaDonHang());
			pstm.setString(3, t.getSanPham().getMaSach());
			pstm.setInt(4, t.getSoLuong());
			pstm.setDouble(5, t.getGiaGoc());
			pstm.setDouble(6, t.getGiamGia());
			pstm.setDouble(7, t.getGiaBan());
			pstm.setDouble(8, t.getThueVAT());
			pstm.setDouble(9, t.getTongThanhTien());

			int kq = pstm.executeUpdate();
			JDBCUtil.close(con, pstm, null);
			return kq;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}

	@Override
	public int insertAll(ArrayList<ChiTietDonHang> t) {
		int dem = 0;
		for (ChiTietDonHang chiTietDonHang : t) {
			dem+=this.insert(chiTietDonHang);
		}
		return dem;
	}

	@Override
	public int delete(ChiTietDonHang t) {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "delete from chitietdonhang where maChiTietDonHang=?";
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, t.getMaChiTietDonHang());
			int kq = pstm.executeUpdate();
			JDBCUtil.close(con, pstm, null);
			return kq;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteAll(ArrayList<ChiTietDonHang> t) {
		int dem = 0;
		for (ChiTietDonHang chiTietDonHang : t) {
			dem+=this.delete(chiTietDonHang);
		}
		return dem;
	}

	@Override
	public int update(ChiTietDonHang t) {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "update from chitietdonhang set(donHang=?, sanPham=?, soLuong=?, giaGoc=?, giamGia=?, giaBan=?, thueVAT=?, tongThanhTien=?) where maChiTietDonHang=?";
			PreparedStatement pstm = con.prepareStatement(sql);
			
			pstm.setString(1, t.getDonHang().getMaDonHang());
			pstm.setString(2, t.getSanPham().getMaSach());
			pstm.setInt(3, t.getSoLuong());
			pstm.setDouble(4, t.getGiaGoc());
			pstm.setDouble(5, t.getGiamGia());
			pstm.setDouble(6, t.getGiaBan());
			pstm.setDouble(7, t.getThueVAT());
			pstm.setDouble(8, t.getTongThanhTien());
			pstm.setString(9, t.getMaChiTietDonHang());

			int kq = pstm.executeUpdate();
			JDBCUtil.close(con, pstm, null);
			return kq;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
public static void main(String[] args) {
	ChiTietDonHangDAO chiTietDonHangDAO = new ChiTietDonHangDAO();
	ArrayList<ChiTietDonHang> arrayList = chiTietDonHangDAO.selectAll();
	for (ChiTietDonHang chiTietDonHang : arrayList) {
		System.out.println(arrayList.toString());
	}
}
}