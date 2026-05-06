package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.SanPham;
import model.TacGia;
import model.TheLoai;

public class SanPhamDAO implements DAOInterface<SanPham> {

	@Override
	public ArrayList<SanPham> selectAll() {
		ArrayList<SanPham> sanphamList = new ArrayList<SanPham>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "select * from sanpham";
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				 String maSach = rs.getString("maSach");
				 String tenSach= rs.getString("maSach");
				 String matacGia= rs.getString("maTacGia");
				 int namXuatBan= rs.getInt("namXuatBan");
				 double giaNhap= rs.getDouble("giaNhap");
				 double giaGoc= rs.getDouble("giaGoc");
				 double giaBan= rs.getDouble("giaBan");
				 int soLuong= rs.getInt("soLuong");
				 String matheLoai= rs.getString("matheLoai");
				 String ngonNgu= rs.getString("ngonNgu");
				 String moTa= rs.getString("moTa");
				 TacGia tacGia = new TacGiaDAO().selectByID(new TacGia(matacGia, "", null, ""));
				 TheLoai theLoai = new TheLoaiDAO().selectByID(new TheLoai(matheLoai, ""));
				 SanPham sanPham = new SanPham(maSach, tenSach, tacGia, namXuatBan, giaNhap, giaGoc, giaBan, soLuong, theLoai, ngonNgu, moTa);
				 sanphamList.add(sanPham);
			}
			JDBCUtil.close(con, pstm, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sanphamList;
	}

	@Override
	public SanPham selectByID(SanPham t) {
		SanPham sanPham = null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "select * from sanpham where maSach=?";
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, t.getMaSach());
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				 String maSach = rs.getString("maSach");
				 String tenSach= rs.getString("tenSach");
				 String matacGia= rs.getString("maTacGia");
				 int namXuatBan= rs.getInt("namXuatBan");
				 double giaNhap= rs.getDouble("giaNhap");
				 double giaGoc= rs.getDouble("giaGoc");
				 double giaBan= rs.getDouble("giaBan");
				 int soLuong= rs.getInt("soLuong");
				 String matheLoai= rs.getString("matheLoai");
				 String ngonNgu= rs.getString("ngonNgu");
				 String moTa= rs.getString("moTa");
				 TacGia tacGia = new TacGiaDAO().selectByID(new TacGia(matacGia, "", null, ""));
				 TheLoai theLoai = new TheLoaiDAO().selectByID(new TheLoai(matheLoai, ""));
				 sanPham = new SanPham(maSach, tenSach, tacGia, namXuatBan, giaNhap, giaGoc, giaBan, soLuong, theLoai, ngonNgu, moTa);
				 break;
			}
			JDBCUtil.close(con, pstm, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sanPham;
	}

	@Override
	public int insert(SanPham t) {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "insert into sanpham (maSach,tenSach,maTacGia,namXuatBan,giaNhap,giaGoc, giaBan,soLuong,matheLoai,ngonNgu,moTa) values(?,?,?,?,?,?,?,?,?,?,?)"; 
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, t.getMaSach());
			pstm.setString(2, t.getTenSach());
			pstm.setString(3, t.getTacGia().getMaTacGia());
			pstm.setInt(4, t.getNamXuatBan());
			pstm.setDouble(5, t.getGiaNhap());
			pstm.setDouble(6, t.getGiaGoc());
			pstm.setDouble(7, t.getGiaBan());
			pstm.setInt(8, t.getSoLuong());
			pstm.setString(9, t.getTheLoai().getMaTheLoai());
			pstm.setString(10, t.getNgonNgu());
			pstm.setString(11, t.getMoTa());
			int kq = pstm.executeUpdate();
			JDBCUtil.close(con, pstm, null);
			return kq;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int insertAll(ArrayList<SanPham> t) {
		int dem =0;
		for (SanPham sanPham : t) {
			dem+=this.insert(sanPham);
		}
		return dem;
	}

	@Override
	public int delete(SanPham t) {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "delete from sanpham where maSach=?"; 
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, t.getMaSach());
			int kq = pstm.executeUpdate();
			JDBCUtil.close(con, pstm, null);
			return kq;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteAll(ArrayList<SanPham> t) {
		int dem =0;
		for (SanPham sanPham : t) {
			dem+=this.delete(sanPham);
		}
		return dem;
	}

	@Override
	public int update(SanPham t) {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "update sanpham set tenSach=?,maTacGia=?,namXuatBan=?,giaNhap=?,giaGoc=?, giaBan=?,soLuong=?,matheLoai=?,ngonNgu=?,moTa=? where maSach =? "; 
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, t.getTenSach());
			pstm.setString(2, t.getTacGia().getMaTacGia());
			pstm.setInt(3, t.getNamXuatBan());
			pstm.setDouble(4, t.getGiaNhap());
			pstm.setDouble(5, t.getGiaGoc());
			pstm.setDouble(6, t.getGiaBan());
			pstm.setInt(7, t.getSoLuong());
			pstm.setString(8, t.getTheLoai().getMaTheLoai());
			pstm.setString(9, t.getNgonNgu());
			pstm.setString(10, t.getMoTa());
			pstm.setString(11, t.getMaSach());
			int kq = pstm.executeUpdate();
			JDBCUtil.close(con, pstm, null);
			return kq;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
public static void main(String[] args) {
	SanPhamDAO dao = new SanPhamDAO();
	
	TacGia tacGia = new TacGia();
	tacGia.setMaTacGia("MTG_02");
	TheLoai theLoai = new TheLoai();
	theLoai.setMaTheLoai("MTL_02");
	SanPham sanPham = new SanPham("MS_01", "Lập trình C#" , tacGia, 2025, 93000, 93000,95000 , 200, theLoai, "Tiếng Anh", "Không có");
	int kq = dao.update(sanPham);
	System.out.println(kq);
}
	
}
