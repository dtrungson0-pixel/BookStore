package dataBase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.KhachHang;

public class KhachHangDAO implements DAOInterface<KhachHang>{

	@Override
	public ArrayList<KhachHang> selectAll() {
		ArrayList<KhachHang> arrayList = new ArrayList<KhachHang>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "select * from khachhang";
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				 String maKhachHang = rs.getString("maKhachHang");
				 String tenDangNhap= rs.getString("tenDangNhap");
				 String matKhau= rs.getString("matKhau");
				 String gioiTinh= rs.getString("gioiTinh");
				 String hoVaTen= rs.getString("hoVaTen");
				 String diaChi= rs.getString("diaChi");
				 String diaChiNhanHang= rs.getString("diaChiNhanHang");
				 String diaChiMuaHang= rs.getString("diaChiMuaHang");
				 Date ngaySinh= rs.getDate("ngaySinh");
				 String soDienThoai= rs.getString("soDienThoai");
				 String email= rs.getString("email");
				 boolean dangKyNhanBanTin= rs.getBoolean("dangKyNhanBanTin");
				 KhachHang khachHang = new KhachHang(maKhachHang, tenDangNhap, matKhau, gioiTinh, hoVaTen, diaChi, diaChiNhanHang, diaChiMuaHang, ngaySinh, soDienThoai, email, dangKyNhanBanTin);
				 arrayList.add(khachHang);
			}
			JDBCUtil.close(con, pstm, rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	@Override
	public KhachHang selectByID(KhachHang t) {
		KhachHang khachHang = null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "select * from khachhang where maKhachHang = ?";
			PreparedStatement pstm = con.prepareStatement(sql);
			
			pstm.setString(1, t.getMaKhachHang());
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				 String maKhachHang = rs.getString("maKhachHang");
				 String tenDangNhap= rs.getString("tenDangNhap");
				 String matKhau= rs.getString("matKhau");
				 String gioiTinh= rs.getString("gioiTinh");
				 String hoVaTen= rs.getString("hoVaTen");
				 String diaChi= rs.getString("diaChi");
				 String diaChiNhanHang= rs.getString("diaChiNhanHang");
				 String diaChiMuaHang= rs.getString("diaChiMuaHang");
				 Date ngaySinh= rs.getDate("ngaySinh");
				 String soDienThoai= rs.getString("soDienThoai");
				 String email= rs.getString("email");
				 boolean dangKyNhanBanTin= rs.getBoolean("dangKyNhanBanTin");
				 khachHang = new KhachHang(maKhachHang, tenDangNhap, matKhau, gioiTinh, hoVaTen, diaChi, diaChiNhanHang, diaChiMuaHang, ngaySinh, soDienThoai, email, dangKyNhanBanTin);
				 break;
			}
			JDBCUtil.close(con, pstm, rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return khachHang;
	}

	@Override
	public int insert(KhachHang t) {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "insert into khachhang (maKhachHang, tenDangNhap, matKhau, gioiTinh, hoVaTen, diaChi, diaChiNhanHang, diaChiMuaHang, ngaySinh, soDienThoai, email, dangKyNhanBanTin) values (?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, t.getMaKhachHang());
			pstm.setString(2, t.getTenDangNhap());
			pstm.setString(3, t.getMatKhau());
			pstm.setString(4, t.getGioiTinh());
			pstm.setString(5, t.getHoVaTen());
			pstm.setString(6, t.getDiaChi());
			pstm.setString(7, t.getDiaChiNhanHang());
			pstm.setString(8, t.getDiaChiMuaHang());
			pstm.setDate(9, t.getNgaySing());
			pstm.setString(10, t.getSoDienThoai());
			pstm.setString(11, t.getEmail());
			pstm.setBoolean(12,t.isDangKyNhanBanTin());
			int kq = pstm.executeUpdate();
			JDBCUtil.close(con, pstm, null);
			return kq;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int insertAll(ArrayList<KhachHang> t) {
		int dem =0;;
		for (KhachHang khachHang : t) {
			dem = dem+this.insert(khachHang);
			
		}
		return dem;
	}

	@Override
	public int delete(KhachHang t) {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "delete from khachhang where maKhachHang=?";
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, t.getMaKhachHang());
			int kq = pstm.executeUpdate();
			JDBCUtil.close(con, pstm, null);
			return kq;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteAll(ArrayList<KhachHang> t) {
		int dem =0;;
		for (KhachHang khachHang : t) {
			dem = dem+this.delete(khachHang);
			
		}
		return dem;
	}

	@Override
	public int update(KhachHang t) {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "update  khachhang set (tenDangNhap=?, matKhau=?, gioiTinh=?, hoVaTen=?, diaChi=?, diaChiNhanHang=?, diaChiMuaHang=?, ngaySinh=?, soDienThoai=?, email=?, dangKyNhanBanTin=?) where maKhachHang=?)";
			PreparedStatement pstm = con.prepareStatement(sql);
			
			pstm.setString(1, t.getTenDangNhap());
			pstm.setString(2, t.getMatKhau());
			pstm.setString(3, t.getGioiTinh());
			pstm.setString(4, t.getHoVaTen());
			pstm.setString(5, t.getDiaChi());
			pstm.setString(6, t.getDiaChiNhanHang());
			pstm.setString(7, t.getDiaChiMuaHang());
			pstm.setDate(8, t.getNgaySing());
			pstm.setString(9, t.getSoDienThoai());
			pstm.setString(10, t.getEmail());
			pstm.setBoolean(11,t.isDangKyNhanBanTin());
			pstm.setString(12, t.getMaKhachHang());
			int kq = pstm.executeUpdate();
			JDBCUtil.close(con, pstm, null);
			return kq;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	

}
