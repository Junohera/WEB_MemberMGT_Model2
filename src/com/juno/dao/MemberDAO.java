package com.juno.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.juno.dto.MemberDTO;
import com.juno.util.DatabaseManager;

public class MemberDAO {
	private MemberDAO() {}
	private static MemberDAO ist = new MemberDAO();
	public static MemberDAO getIst() {return ist;}
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public MemberDTO findMember(String userid) {
		MemberDTO member = null;
		con = DatabaseManager.getConnection();
		String sql = "SELECT * FROM MEMBER WHERE USERID = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				member = new MemberDTO();
				member.setName(rs.getString("NAME"));
				member.setUserid(rs.getString("USERID"));
				member.setPwd(rs.getString("PWD"));
				member.setEmail(rs.getString("EMAIL"));
				member.setPhone(rs.getString("PHONE"));
				member.setAdmin(rs.getInt("ADMIN"));
			}
		} catch (SQLException e) {e.printStackTrace();
		} catch (Exception e) {e.printStackTrace();
		} finally { DatabaseManager.close(con, ps, rs); }
		
		return member;
	}

	public int confirmId(String userid) {
		int result = 0;
		
		MemberDTO member = null;
		con = DatabaseManager.getConnection();
		String sql = "SELECT USERID FROM MEMBER WHERE USERID = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				result = 1;
			}
			
		} catch (SQLException e) {e.printStackTrace();
		} catch (Exception e) {e.printStackTrace();
		} finally { DatabaseManager.close(con, ps, rs); }
		
		return result;
	}

	public int addMember(MemberDTO member) {
		int result = 0;
		String sql = "INSERT INTO MEMBER VALUES(?, ?, ?, ?, ?, ?)";
		con = DatabaseManager.getConnection();
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getName());
			ps.setString(2, member.getUserid());
			ps.setString(3, member.getPwd());
			ps.setString(4, member.getEmail());
			ps.setString(5, member.getPhone());
			ps.setInt(6, member.getAdmin());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {e.printStackTrace();
		} catch (Exception e) {e.printStackTrace();
		} finally { DatabaseManager.close(con, ps, rs); }
		
		return result;
	}

	public int deleteMember(String userid) {
		int result = 0;
		
		String sql = "DELETE FROM MEMBER WHERE USERID = ?";
		con = DatabaseManager.getConnection();
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {e.printStackTrace();
		} catch (Exception e) {e.printStackTrace();
		} finally { DatabaseManager.close(con, ps, rs); }
		
		return result;
	}

	public ArrayList<MemberDTO> selectAll() {
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		
		con = DatabaseManager.getConnection();
		String sql = "SELECT * FROM MEMBER";
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				MemberDTO member = new MemberDTO();
				member.setName(rs.getString("NAME"));
				member.setUserid(rs.getString("USERID"));
				member.setPwd(rs.getString("PWD"));
				member.setEmail(rs.getString("EMAIL"));
				member.setPhone(rs.getString("PHONE"));
				member.setAdmin(rs.getInt("ADMIN"));
				list.add(member);
			}
		} catch (SQLException e) {e.printStackTrace();
		} catch (Exception e) {e.printStackTrace();
		} finally { DatabaseManager.close(con, ps, rs); }
		
		return list;
	}

	public int updateMember(MemberDTO member) {
		int result = 0;
		
		String sql = "UPDATE MEMBER SET";
		if (member.getPwd().equals("")) {
			sql += " NAME = ?"
					+ " , EMAIL = ?"
					+ " , PHONE = ?"
					+ " , ADMIN = ?"
					+ " WHERE USERID = ?";
		} else {
			sql += " NAME = ?"
					+ " , PWD = ?"
					+ " , EMAIL = ?"
					+ " , PHONE = ?"
					+ " , ADMIN = ?"
					+ " WHERE USERID = ?";	
		}
		
		
		try {
			con = DatabaseManager.getConnection();
			ps = con.prepareStatement(sql);
			
			if (member.getPwd().equals("")) {
				ps.setString(1, member.getName());
				ps.setString(2, member.getEmail());
				ps.setString(3, member.getPhone());
				ps.setInt(4, member.getAdmin());
				ps.setString(5, member.getUserid());
			} else {
				ps.setString(1, member.getName());
				ps.setString(2, member.getPwd());
				ps.setString(3, member.getEmail());
				ps.setString(4, member.getPhone());
				ps.setInt(5, member.getAdmin());
				ps.setString(6, member.getUserid());
			}
			
			result = ps.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		} catch (Exception e) {e.printStackTrace();
		} finally { DatabaseManager.close(con, ps, rs); }
		
		return result;
	}

}
