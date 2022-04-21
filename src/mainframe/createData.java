package mainframe;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import DAO.*;
import DB.DBConnection;
import bean.TD001_bean;
import bean.TD002_bean;
import bean.TD003_bean;
import bean.TD004_bean;
import bean.TD005_bean;

public class createData {
	
	public Connection getconnection() {
		Connection conn = null;
		
		DBConnection DBc = DBConnection.getinstance();
		
		conn = DBc.getconnection();

		return conn;
	}
	
	public int datacheck(TD005_bean tde) {
		
		Connection con = getconnection();

		int check = 0;
		TD_dao_nugae tda = new TD_dao_nugae();
		TD_dao_sokbo tds = new TD_dao_sokbo();
		TD_dao_eik tdk = new TD_dao_eik();
		TD_dao_internet tdi = new TD_dao_internet();
		TD_dao_etc tdc = new TD_dao_etc();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		Calendar c3 = Calendar.getInstance();
		Calendar c4 = Calendar.getInstance();
		c1.add(Calendar.DATE, -1);
		c2.add(Calendar.YEAR, -1);
		c3.add(Calendar.YEAR, -1);
		c3.add(Calendar.DATE, -1);
		c4.add(Calendar.DATE, -2);
		String ymd = sdf.format(c1.getTime());
		String d_ymd = sdf.format(c2.getTime());
		String d_d_ymd = sdf.format(c3.getTime());
		String yesterday = sdf.format(c4.getTime());

		tde.setYmd(ymd);
		
//	==========================================DB작업 시작===========================================================
		
		//-----------------------------------------------매출실적누계 작업--------------------------------------------------------
		ProgressBar pb = new ProgressBar();
		pb.start_timer(0, 10, "매출실적누계 조회");
		ArrayList<TD001_bean> td001 = tda.Selectnugae(con,ymd);
		pb.start_timer(10, 20, "매출실적누계 입력");
		check = tda.Insertnugae(con,td001);

		if(check != 1)
		{
			System.out.println("seq1 Errer");
			pb.end_timer();
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return check;
		}
		
		//--------------------------------------------시간대별 매출속보 작업-----------------------------------------------------
		pb.start_timer(20, 30, "시간대별 매출속보 조회");
		ArrayList<TD002_bean> td002 = tds.Selectsokbo(con, ymd,d_ymd);
		pb.start_timer(30, 40, "시간대별 매출속보 입력");
		check = tds.Insertsokbo(con, td002);
		if(check != 1)
		{
			System.out.println("seq2 Errer");
			pb.end_timer();
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return check;
		}
		
		//--------------------------------------------매출이익율검색 작업-----------------------------------------------------
		pb.start_timer(40, 50, "매출이익율 조회");
		TD003_bean td003 = tdk.Selecteik_sik(con, ymd);
		pb.start_timer(50, 60, "매출이익율 입력");		
		td003 = tdk.Selecteik_etc(con, td003, ymd,d_d_ymd);
		check = tdk.Inserteik(con, td003);
		if(check != 1)
		{
			System.out.println("seq3 Errer");
			pb.end_timer();
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return check;
		}
		//--------------------------------------------인터넷매출검색 작업-----------------------------------------------------
		pb.start_timer(60, 70, "인터넷 매출 조회");
		TD004_bean td004 = tdi.Selectinternet(con, ymd);
		td004 = tdi.SelectIntSum(con, td004, yesterday);
		td004 = tdi.SelectIntCnt(con,td004,ymd);
		pb.start_timer(70, 80, "인터넷 매출 입력");
		check = tdi.Inserteik(con, td004);
		if(check != 1)
		{
			System.out.println("seq4 Errer");
			pb.end_timer();
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return check;
		}
		//--------------------------------------------기타 입력정보 작업-----------------------------------------------------
		pb.start_timer(80, 100, "기타 입력정보 입력");
		TD005_bean td005 = tdc.Selectetc(con, d_d_ymd);
		
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;
	}
}










