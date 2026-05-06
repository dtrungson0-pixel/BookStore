package dataBase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.DonHang;
import model.KhachHang;

public class DonHangDAO implements DAOInterface<DonHang> {

	@Override
	public ArrayList<DonHang> selectAll() {
		ArrayList<DonHang> arrayList = new ArrayList<DonHang>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "select * from donhang";
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				String maDonHang = rs.getString("maDonHang");
				String diaChiNguoiMua = rs.getString("diaChiNguoiMua");
				String diaChiNhanHang = rs.getString("diaChiNhanHang");
				String makhachHang = rs.getString("maDonHang");
				String trangThai = rs.getString("trangThai");
				String phuongThucThanhToan = rs.getString("phuongThucThanhToan");
				String trangThaiThanhToan = rs.getString("trangThaiThanhToan");
				String soTienDaThanhToan = rs.getString("soTienDaThanhToan");
				String soTienConThieu = rs.getString("soTienConThieu");
				Date ngayDatHang = rs.getDate("ngayDatHang");
				Date ngayGiaoHang = rs.getDate("ngayGiaoHang");
				KhachHang khachHang = new KhachHangDAO().selectByID(
						new KhachHang(makhachHang, null, null, null, null, null, null, null, null, null, null, false));
				DonHang donHang = new DonHang(maDonHang, diaChiNguoiMua, diaChiNhanHang, khachHang, trangThai,
						phuongThucThanhToan, trangThaiThanhToan, soTienDaThanhToan, soTienConThieu, ngayDatHang,
						ngayGiaoHang);
				arrayList.add(donHang);

			}
			JDBCUtil.close(con, pstm, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	@Override
	public DonHang selectByID(DonHang t) {
		DonHang donHang = null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "select * from donhang where maDonHang=?";
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, t.getMaDonHang());
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				String maDonHang = rs.getString("maDonHang");
				String diaChiNguoiMua = rs.getString("diaChiNguoiMua");
				String diaChiNhanHang = rs.getString("diaChiNhanHang");
				String makhachHang = rs.getString("makhachHang");
				String trangThai = rs.getString("trangThai");
				String phuongThucThanhToan = rs.getString("phuongThucThanhToan");
				String trangThaiThanhToan = rs.getString("trangThaiThanhToan");
				String soTienDaThanhToan = rs.getString("soTienDaThanhToan");
				String soTienConThieu = rs.getString("soTienConThieu");
				Date ngayDatHang = rs.getDate("ngayDatHang");
				Date ngayGiaoHang = rs.getDate("ngayGiaoHang");
				KhachHang khachHang = new KhachHangDAO().selectByID(
						new KhachHang(makhachHang, null, null, null, null, null, null, null, null, null, null, false));
				donHang = new DonHang(maDonHang, diaChiNguoiMua, diaChiNhanHang, khachHang, trangThai,
						phuongThucThanhToan, trangThaiThanhToan, soTienDaThanhToan, soTienConThieu, ngayDatHang,
						ngayGiaoHang);
				break;

			}
			JDBCUtil.close(con, pstm, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return donHang;
	}

	@Override
	public int insert(DonHang t) {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "insert into donhang(maDonHang, diaChiNguoiMua, diaChiNhanHang, khachHang, trangThai, phuongThucThanhToan, trangThaiThanhToan, soTienDaThanhToan, soTienConThieu, ngayDatHang, ngayGiaoHang) values (?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, t.getMaDonHang());
			pstm.setString(2, t.getDiaChiNguoiMua());
			pstm.setString(3, t.getDiaChiNhanHang());
			pstm.setString(4, t.getKhachHang().getMaKhachHang());
			pstm.setString(5, t.getTrangThai());
			pstm.setString(6, t.getPhuongThucThanhToan());
			pstm.setString(7, t.getTrangThaiThanhToan());
			pstm.setString(8, t.getSoTienDaThanhToan());
			pstm.setString(9, t.getSoTienConThieu());
			pstm.setDate(10, t.getNgayDatHang());
			pstm.setDate(11, t.getNgayGiaoHang());

			int kq = pstm.executeUpdate();
			JDBCUtil.close(con, pstm, null);
			return kq;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int insertAll(ArrayList<DonHang> t) {
		int dem = 0;
		for (DonHang donHang : t) {

			dem += this.insert(donHang);
		}
		return dem;
	}

	@Override
	public int delete(DonHang t) {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "delete from donhang where maDonHang=?";
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, t.getMaDonHang());

			int kq = pstm.executeUpdate();
			JDBCUtil.close(con, pstm, null);
			return kq;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteAll(ArrayList<DonHang> t) {
		int dem = 0;
		for (DonHang donHang : t) {

			dem += this.delete(donHang);
		}
		return dem;
	}

	@Override
	public int update(DonHang t) {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "update from donhang set(diaChiNguoiMua=?, diaChiNhanHang=?, khachHang=?, trangThai=?, phuongThucThanhToan=?, trangThaiThanhToan=?, soTienDaThanhToan=?, soTienConThieu=?, ngayDatHang=?, ngayGiaoHang=?) where maDonHang=?";
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, t.getDiaChiNguoiMua());
			pstm.setString(2, t.getDiaChiNhanHang());
			pstm.setString(3, t.getKhachHang().getMaKhachHang());
			pstm.setString(4, t.getTrangThai());
			pstm.setString(5, t.getPhuongThucThanhToan());
			pstm.setString(6, t.getTrangThaiThanhToan());
			pstm.setString(7, t.getSoTienDaThanhToan());
			pstm.setString(8, t.getSoTienConThieu());
			pstm.setDate(19, t.getNgayDatHang());
			pstm.setDate(10, t.getNgayGiaoHang());
			pstm.setString(11, t.getMaDonHang());

			int kq = pstm.executeUpdate();
			JDBCUtil.close(con, pstm, null);
			return kq;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
