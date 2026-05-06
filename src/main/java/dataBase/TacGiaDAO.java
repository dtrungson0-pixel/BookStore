package dataBase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import model.TacGia;

public class TacGiaDAO implements DAOInterface<TacGia> {

	@Override
	public ArrayList<TacGia> selectAll() {
		ArrayList<TacGia> tacGiaList = new ArrayList<TacGia>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "select * from tacgia";
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				 String maTacGia = rs.getString("maTacGia");
				 String hoVaTen= rs.getString("hoVaTen");
				 Date ngaySinh= rs.getDate("ngaySinh");
				 String tieuSu= rs.getString("tieuSu");
				 TacGia tg = new TacGia(maTacGia, hoVaTen, ngaySinh, tieuSu);
				 tacGiaList.add(tg);
				 
			}
			JDBCUtil.close(con, pstm, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tacGiaList;
	}

	@Override
	public TacGia selectByID(TacGia t) {
		TacGia tacGia =null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "select * from tacgia where maTacGia =? ";
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, t.getMaTacGia());
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				 String maTacGia = rs.getString("maTacGia");
				 String hoVaTen= rs.getString("hoVaTen");
				 Date ngaySinh= rs.getDate("ngaySinh");
				 String tieuSu= rs.getString("tieuSu");
				 tacGia = new TacGia(maTacGia, hoVaTen, ngaySinh, tieuSu);
				 break;
				 
			}
			JDBCUtil.close(con, pstm, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return tacGia;
	}

	@Override
	public int insert(TacGia t) {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "insert into tacgia (maTacGia,hoVaTen,ngaySinh,tieuSu) values(?,?,?,?)";
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, t.getMaTacGia());
			pstm.setString(2, t.getHoVaTen());
			pstm.setDate(3, t.getNgaySinh());
			pstm.setString(4, t.getTieuSu());
			int kq = pstm.executeUpdate();
			JDBCUtil.close(con, pstm, null);
			return kq;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int insertAll(ArrayList<TacGia> arr) {
		int dem = 0;
		for (TacGia tacgia : arr) {
			dem = dem + this.insert(tacgia);
		}
		return dem;
	}

	@Override
	public int delete(TacGia t) {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "delete from tacgia where maTacGia=?";
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, t.getMaTacGia());
			int kq = pstm.executeUpdate();
			JDBCUtil.close(con, pstm, null);
			return kq;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int deleteAll(ArrayList<TacGia> arr) {
		int dem = 0;
		for (TacGia tacGia : arr) {
			dem+=this.delete(tacGia);
		}
		return dem;
	}

	@Override
	public int update(TacGia t) {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "update tacgia set hoVaTen=?,ngaySinh=?,tieuSu=? where maTacGia =?";
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, t.getHoVaTen());
			pstm.setDate(2, t.getNgaySinh());
			pstm.setString(3, t.getTieuSu());
			pstm.setString(4, t.getMaTacGia());
		
			int kq = pstm.executeUpdate();
			JDBCUtil.close(con, pstm, null);
			return kq;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	

}
