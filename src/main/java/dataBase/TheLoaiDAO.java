package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.TheLoai;

public class TheLoaiDAO implements DAOInterface<TheLoai>{

	@Override
	public ArrayList<TheLoai> selectAll() {
		ArrayList<TheLoai> listTheLoai = new ArrayList<TheLoai>();
		
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "select * from theloai";
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				 String maTheLoai = rs.getString("maTheLoai");
				 String tenTheLoai = rs.getString("tenTheLoai");
				 TheLoai theLoai = new TheLoai(maTheLoai, tenTheLoai);
				 listTheLoai.add(theLoai);
			}
			JDBCUtil.close(con, pstm, rs);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return listTheLoai;
	}

	@Override
	public TheLoai selectByID(TheLoai t) {
		TheLoai theLoai = null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "select * from theloai where maTheLoai=?";
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, t.getMaTheLoai());
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				 String maTheLoai = rs.getString("maTheLoai");
				 String tenTheLoai = rs.getString("tenTheLoai");
				  theLoai = new TheLoai(maTheLoai, tenTheLoai);
				  break;

			}
			JDBCUtil.close(con, pstm, rs);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return theLoai;
	}

	@Override
	public int insert(TheLoai t) {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "insert into theloai (maTheLoai,tenTheLoai) values (?,?)";
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, t.getMaTheLoai());
			pstm.setString(2, t.getTenTheLoai());
			int kq = pstm.executeUpdate();
			JDBCUtil.close(con, pstm, null);;
			
			return kq;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int insertAll(ArrayList<TheLoai> t) {
		int dem = 0;
		for (TheLoai theLoai : t) {
			
			dem+=this.insert(theLoai);
		}
		return dem;
	}

	@Override
	public int delete(TheLoai t) {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "delete from theloai where maTheLoai =?";
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, t.getMaTheLoai());
			int kq = pstm.executeUpdate();
			JDBCUtil.close(con, pstm, null);;
			
			return kq;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteAll(ArrayList<TheLoai> t) {
		int dem = 0;
		for (TheLoai theLoai : t) {
			dem+=this.delete(theLoai);
			
		}
		return dem;
	}

	@Override
	public int update(TheLoai t) {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "update theloai set tenTheLoai=? where maTheLoai=?";
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, t.getTenTheLoai());
			pstm.setString(2, t.getMaTheLoai());
			int kq = pstm.executeUpdate();
			JDBCUtil.close(con, pstm, null);;
			
			return kq;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	

}