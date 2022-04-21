package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.TD005_bean;

public class TD_dao_etc {

	public TD005_bean  Selectetc(Connection conn,String ymd) {
		
		Connection con = conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TD005_bean bean = new TD005_bean();
		bean.setYmd(ymd);

			try {
				String sql = 
								")";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,ymd);
				rs = pstmt.executeQuery();
				
				if(rs.next())
				{
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			 finally {
					try {
						pstmt.close();
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			return bean;
		}

	
	public int Inserteik(Connection conn,TD005_bean td004_3)
	{
		int result = 1;
		Connection con = conn;
		PreparedStatement pstmt = null;
		try {
				String sql = "insert into TD005 values(?,?,?,?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				
				result = pstmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result; 
	}

}






















