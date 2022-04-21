package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.TD002_bean;

public class TD_dao_sokbo {


	
	public ArrayList<TD002_bean>  Selectsokbo(Connection conn,String ymd,String d_ymd) {
		
		Connection con = conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<TD002_bean> bean = new ArrayList<>();
			try {
				String sql = 
						"SELECT  "+
								"       CORP_CODE                                                    AS CORP_CODE "+
								"       ,'영업일'                                                    AS GB "+
								"       ,'대비일'                                                    AS D_GB "+
								"       ,GOAL_AMT                                                    AS GOAL_AMT "+
								"       ,SAL_AMT                                                     AS SAL_AMT "+
								"       ,DECODE(GOAL_AMT,0,0,ROUND(SAL_AMT/GOAL_AMT*100,1))          AS DS "+
								"       ,SAL_AMT11                                                   AS SAL_AMT11 "+
								"       ,SAL_AMT12                                                   AS SAL_AMT12 "+
								"       ,SAL_AMT13                                                   AS SAL_AMT13 "+
								"       ,SAL_AMT14                                                   AS SAL_AMT14 "+
								"       ,SAL_AMT15                                                   AS SAL_AMT15 "+
								"       ,SAL_AMT16                                                   AS SAL_AMT16 "+
								"       ,SAL_AMT17                                                   AS SAL_AMT17 "+
								"       ,SAL_AMT18                                                   AS SAL_AMT18 "+
								"       ,SAL_AMT19                                                   AS SAL_AMT19 "+
								"       ,SAL_AMT20                                                   AS SAL_AMT20 "+
								"       ,SAL_AMT21                                                   AS SAL_AMT21 "+
								"       ,SAL_AMT22                                                   AS SAL_AMT22 "+
								"       ,CUS_CNT                                                     AS CUS_CNT "+
								"       ,DECODE(CUS_CNT,0,0,ROUND(SAL_AMT/CUS_CNT,0))                AS CNT "+
								"       ,D_GOAL_AMT                                                  AS D_GOAL_AMT "+
								"       ,D_SAL_AMT                                                   AS D_SAL_AMT "+
								"       ,DECODE(D_GOAL_AMT,0,0,ROUND(D_SAL_AMT/D_GOAL_AMT*100,1))    AS D_DS "+
								"       ,D_SAL_AMT11                                                 AS D_SAL_AMT11 "+
								"       ,D_SAL_AMT12                                                 AS D_SAL_AMT12 "+
								"       ,D_SAL_AMT13                                                 AS D_SAL_AMT13 "+
								"       ,D_SAL_AMT14                                                 AS D_SAL_AMT14 "+
								"       ,D_SAL_AMT15                                                 AS D_SAL_AMT15 "+
								"       ,D_SAL_AMT16                                                 AS D_SAL_AMT16 "+
								"       ,D_SAL_AMT17                                                 AS D_SAL_AMT17 "+
								"       ,D_SAL_AMT18                                                 AS D_SAL_AMT18 "+
								"       ,D_SAL_AMT19                                                 AS D_SAL_AMT19 "+
								"       ,D_SAL_AMT20                                                 AS D_SAL_AMT20 "+
								"       ,D_SAL_AMT21                                                 AS D_SAL_AMT21 "+
								"       ,D_SAL_AMT22                                                 AS D_SAL_AMT22 "+
								"       ,D_CUS_CNT                                                   AS D_CUS_CNT "+
								"       ,DECODE(D_CUS_CNT,0,0,ROUND(D_SAL_AMT/D_CUS_CNT,0))          AS D_CNT "+
								"       ,CORP_NAME                                                   AS CORP_NAME "+
								"       ,BONBU_CODE                                                  AS BONBU_CODE "+
								"       ,BONBU_NAME                                                  AS BONBU_NAME "+
								"       ,TEAM_CODE                                                   AS TEAM_CODE "+
								"       ,TEAM_NAME                                                   AS TEAM_NAME "+
								"FROM( "+
								"SELECT "+
								"       CORP_CODE                                                    AS CORP_CODE "+
								"       ,'영업일'                                                    AS GB "+
								"       ,'대비일'                                                    AS D_GB "+
								"       ,ROUND(SUM(GOAL_AMT),0)                                               AS GOAL_AMT "+
								"       ,ROUND(SUM(SAL_AMT),0)                                                AS SAL_AMT "+
								"       ,ROUND(SUM(SAL_AMT11),0)                                AS SAL_AMT11 "+
								"       ,ROUND(SUM(SAL_AMT12),0)                                AS SAL_AMT12 "+
								"       ,ROUND(SUM(SAL_AMT13),0)                                AS SAL_AMT13 "+
								"       ,ROUND(SUM(SAL_AMT14),0)                                AS SAL_AMT14 "+
								"       ,ROUND(SUM(SAL_AMT15),0)                                AS SAL_AMT15 "+
								"       ,ROUND(SUM(SAL_AMT16),0)                                AS SAL_AMT16 "+
								"       ,ROUND(SUM(SAL_AMT17),0)                                AS SAL_AMT17 "+
								"       ,ROUND(SUM(SAL_AMT18),0)                                AS SAL_AMT18 "+
								"       ,ROUND(SUM(SAL_AMT19),0)                                AS SAL_AMT19 "+
								"       ,ROUND(SUM(SAL_AMT20),0)                                AS SAL_AMT20 "+
								"       ,ROUND(SUM(SAL_AMT21),0)                                AS SAL_AMT21 "+
								"       ,ROUND(SUM(SAL_AMT22),0)                                AS SAL_AMT22 "+
								"       ,ROUND(SUM(CUS_CNT),0)                                       AS CUS_CNT "+
								"       ,ROUND(SUM(D_GOAL_AMT),0)                                   AS D_GOAL_AMT "+
								"       ,ROUND(SUM(D_SAL_AMT),0)                                AS D_SAL_AMT "+
								"       ,ROUND(SUM(D_SAL_AMT11),0)                              AS D_SAL_AMT11 "+
								"       ,ROUND(SUM(D_SAL_AMT12),0)                              AS D_SAL_AMT12 "+
								"       ,ROUND(SUM(D_SAL_AMT13),0)                              AS D_SAL_AMT13 "+
								"       ,ROUND(SUM(D_SAL_AMT14),0)                              AS D_SAL_AMT14 "+
								"       ,ROUND(SUM(D_SAL_AMT15),0)                              AS D_SAL_AMT15 "+
								"       ,ROUND(SUM(D_SAL_AMT16),0)                              AS D_SAL_AMT16 "+
								"       ,ROUND(SUM(D_SAL_AMT17),0)                              AS D_SAL_AMT17 "+
								"       ,ROUND(SUM(D_SAL_AMT18),0)                              AS D_SAL_AMT18 "+
								"       ,ROUND(SUM(D_SAL_AMT19),0)                              AS D_SAL_AMT19 "+
								"       ,ROUND(SUM(D_SAL_AMT20),0)                              AS D_SAL_AMT20 "+
								"       ,ROUND(SUM(D_SAL_AMT21),0)                              AS D_SAL_AMT21 "+
								"       ,ROUND(SUM(D_SAL_AMT22),0)                              AS D_SAL_AMT22 "+
								"       ,ROUND(SUM(D_CUS_CNT),0)                                AS D_CUS_CNT "+
								"       ,CORP_NAME                                              AS CORP_NAME "+
								"       ,BONBU_CODE                                             AS BONBU_CODE "+
								"       ,BONBU_NAME                                             AS BONBU_NAME "+
								"       ,TEAM_CODE                                              AS TEAM_CODE "+
								"       ,TEAM_NAME                                              AS TEAM_NAME "+
								"  FROM "+
								"( "+
								" SELECT "+
								"         E.CORP_CODE                                                  AS CORP_CODE, "+
								"         E.CORP_NAME                                                  AS CORP_NAME, "+
								"         E.BONBU_CODE                                                 AS BONBU_CODE, "+
								"         E.BONBU_NAME                                                 AS BONBU_NAME, "+
								"         E.TEAM_CODE                                                  AS TEAM_CODE, "+
								"         E.TEAM_NAME                                                  AS TEAM_NAME, "+
								"         E.PC_CODE                                                    AS PC_CODE, "+
								"         E.PC_NAME                                                    AS PC_NAME, "+
								"         A.CLASS_CODE                                                 AS CLASS_CODE, "+
								"         D.CLASS_NAME                                                 AS CLASS_NAME, "+
								"         NVL(SUM(A.SAL_AMT11)/1000,0)                                            AS SAL_AMT11, "+
								"         NVL(SUM(A.SAL_AMT12 )/1000,0)                                            AS SAL_AMT12, "+
								"         NVL(SUM(A.SAL_AMT13 )/1000,0)                                            AS SAL_AMT13, "+
								"         NVL(SUM(A.SAL_AMT14 )/1000,0)                                            AS SAL_AMT14, "+
								"         NVL(SUM(A.SAL_AMT15 )/1000,0)                                            AS SAL_AMT15, "+
								"         NVL(SUM(A.SAL_AMT16 )/1000,0)                                            AS SAL_AMT16, "+
								"         NVL(SUM(A.SAL_AMT17 )/1000,0)                                            AS SAL_AMT17, "+
								"         NVL(SUM(A.SAL_AMT18 )/1000,0)                                            AS SAL_AMT18, "+
								"         NVL(SUM(A.SAL_AMT19 )/1000,0)                                            AS SAL_AMT19, "+
								"         NVL(SUM(A.SAL_AMT20 )/1000,0)                                            AS SAL_AMT20, "+
								"         NVL(SUM(A.SAL_AMT21 )/1000,0)                                            AS SAL_AMT21, "+
								"         NVL(SUM(A.SAL_AMT22+A.SAL_AMT23+A.SAL_AMT24)/1000,0)                     AS SAL_AMT22, "+
								"         NVL(SUM(A.SAL_AMT )/1000,0)                                              AS SAL_AMT, "+
								"         0                                                            AS GOAL_AMT, "+
								"         SUM(A.CUS_CNT )                                              AS CUS_CNT, "+
								"         0                                                               AS D_SAL_AMT11, "+
								"         0                                                               AS D_SAL_AMT12, "+
								"         0                                                               AS D_SAL_AMT13, "+
								"         0                                                               AS D_SAL_AMT14, "+
								"         0                                                               AS D_SAL_AMT15, "+
								"         0                                                               AS D_SAL_AMT16, "+
								"         0                                                               AS D_SAL_AMT17, "+
								"         0                                                               AS D_SAL_AMT18, "+
								"         0                                                               AS D_SAL_AMT19, "+
								"         0                                                               AS D_SAL_AMT20, "+
								"         0                                                               AS D_SAL_AMT21, "+
								"         0                                                               AS D_SAL_AMT22, "+
								"         0                                                               AS D_SAL_AMT, "+
								"         0                                                               AS D_GOAL_AMT, "+
								"         0                                                               AS D_CUS_CNT "+
								"    FROM SL021 A, "+
								"         CD060 D, "+
								"         V_CD020_000 E "+
								"   WHERE A.STR_CODE   = D.STR_CODE "+
								"   AND A.CLASS_CODE = D.CLASS_CODE "+
								"   AND A.STR_CODE   = E.STR_CODE "+
								"   AND D.DEPT_CODE  = E.PC_CODE "+
								"   AND A.SALE_YMD   =? "+
								"   AND E.CORP_CODE = 2000 "+
								"   AND E.BONBU_CODE = 2600 "+
								"   AND E.TEAM_CODE in(2620,2630,2640,2650) "+
								"  GROUP BY E.CORP_CODE, E.CORP_NAME, E.BONBU_CODE, E.BONBU_NAME, E.TEAM_CODE, E.TEAM_NAME, E.PC_CODE, E.PC_NAME, A.CLASS_CODE, D.CLASS_NAME  "+
								" "+
								"UNION ALL "+
								" "+
								"  SELECT "+
								"         E.CORP_CODE                                                  AS CORP_CODE, "+
								"         E.CORP_NAME                                                  AS CORP_NAME, "+
								"         E.BONBU_CODE                                                 AS BONBU_CODE, "+
								"         E.BONBU_NAME                                                 AS BONBU_NAME, "+
								"         E.TEAM_CODE                                                  AS TEAM_CODE, "+
								"         E.TEAM_NAME                                                  AS TEAM_NAME, "+
								"         E.PC_CODE                                                    AS PC_CODE, "+
								"         E.PC_NAME                                                    AS PC_NAME, "+
								"         A.CLASS_CODE                                                 AS CLASS_CODE, "+
								"         D.CLASS_NAME                                                 AS CLASS_NAME, "+
								"         0                                                               AS SAL_AMT11, "+
								"         0                                                               AS SAL_AMT12, "+
								"         0                                                               AS SAL_AMT13, "+
								"         0                                                               AS SAL_AMT14, "+
								"         0                                                               AS SAL_AMT15, "+
								"         0                                                               AS SAL_AMT16, "+
								"         0                                                               AS SAL_AMT17, "+
								"         0                                                               AS SAL_AMT18, "+
								"         0                                                               AS SAL_AMT19, "+
								"         0                                                               AS SAL_AMT20, "+
								"         0                                                               AS SAL_AMT21, "+
								"         0                                                               AS SAL_AMT22, "+
								"         0                                                               AS SAL_AMT, "+
								"         0                                                               AS GOAL_AMT, "+
								"         0                                                               AS CUS_CNT, "+
								"         NVL(SUM(A.SAL_AMT11 )/1000,0)                                            AS D_SAL_AMT11, "+
								"         NVL(SUM(A.SAL_AMT12 )/1000,0)                                            AS D_SAL_AMT12, "+
								"         NVL(SUM(A.SAL_AMT13 )/1000,0)                                            AS D_SAL_AMT13, "+
								"         NVL(SUM(A.SAL_AMT14 )/1000,0)                                            AS D_SAL_AMT14, "+
								"         NVL(SUM(A.SAL_AMT15 )/1000,0)                                            AS D_SAL_AMT15, "+
								"         NVL(SUM(A.SAL_AMT16 )/1000,0)                                            AS D_SAL_AMT16, "+
								"         NVL(SUM(A.SAL_AMT17 )/1000,0)                                            AS D_SAL_AMT17, "+
								"         NVL(SUM(A.SAL_AMT18 )/1000,0)                                            AS D_SAL_AMT18, "+
								"         NVL(SUM(A.SAL_AMT19 )/1000,0)                                            AS D_SAL_AMT19, "+
								"         NVL(SUM(A.SAL_AMT20 )/1000,0)                                            AS D_SAL_AMT20, "+
								"         NVL(SUM(A.SAL_AMT21 )/1000,0)                                            AS D_SAL_AMT21, "+
								"         NVL(SUM(A.SAL_AMT22+A.SAL_AMT23+A.SAL_AMT24)/1000,0)                     AS D_SAL_AMT22, "+
								"         NVL(SUM(A.SAL_AMT )/1000,0)                                              AS D_SAL_AMT, "+
								"         0                                                            AS D_GOAL_AMT, "+
								"         SUM(A.CUS_CNT )                                              AS D_CUS_CNT "+
								"    FROM SL021 A, "+
								"         CD060 D, "+
								"         V_CD020_000 E "+
								"   WHERE A.STR_CODE   = D.STR_CODE "+
								"   AND A.CLASS_CODE = D.CLASS_CODE "+
								"   AND A.STR_CODE   = E.STR_CODE "+
								"   AND D.DEPT_CODE  = E.PC_CODE "+
								"   AND A.SALE_YMD   =? "+
								"   AND E.CORP_CODE = 2000 "+
								"   AND E.BONBU_CODE = 2600 "+
								"   AND E.TEAM_CODE in(2620,2630,2640,2650) "+
								"  GROUP BY E.CORP_CODE, E.CORP_NAME, E.BONBU_CODE, E.BONBU_NAME, E.TEAM_CODE, E.TEAM_NAME, E.PC_CODE, E.PC_NAME, A.CLASS_CODE, D.CLASS_NAME  "+
								" "+
								"UNION ALL "+
								" "+
								"  SELECT "+
								"         E.CORP_CODE                                                  AS CORP_CODE, "+
								"         E.CORP_NAME                                                  AS CORP_NAME, "+
								"         E.BONBU_CODE                                                 AS BONBU_CODE, "+
								"         E.BONBU_NAME                                                 AS BONBU_NAME, "+
								"         E.TEAM_CODE                                                  AS TEAM_CODE, "+
								"         E.TEAM_NAME                                                  AS TEAM_NAME, "+
								"         E.PC_CODE                                                    AS PC_CODE, "+
								"         E.PC_NAME                                                    AS PC_NAME, "+
								"         C.CLASS_CODE                                                 AS CLASS_CODE, "+
								"         D.CLASS_NAME                                                 AS CLASS_NAME, "+
								"         0                                                               AS SAL_AMT11, "+
								"         0                                                               AS SAL_AMT12, "+
								"         0                                                               AS SAL_AMT13, "+
								"         0                                                               AS SAL_AMT14, "+
								"         0                                                               AS SAL_AMT15, "+
								"         0                                                               AS SAL_AMT16, "+
								"         0                                                               AS SAL_AMT17, "+
								"         0                                                               AS SAL_AMT18, "+
								"         0                                                               AS SAL_AMT19, "+
								"         0                                                               AS SAL_AMT20, "+
								"         0                                                               AS SAL_AMT21, "+
								"         0                                                               AS SAL_AMT22, "+
								"         0                                                               AS SAL_AMT, "+
								"         SUM(C.GOAL_AMT)                                                 AS GOAL_AMT, "+
								"         0                                                               AS CUS_CNT, "+
								"         0                                                               AS D_SAL_AMT11, "+
								"         0                                                               AS D_SAL_AMT12, "+
								"         0                                                               AS D_SAL_AMT13, "+
								"         0                                                               AS D_SAL_AMT14, "+
								"         0                                                               AS D_SAL_AMT15, "+
								"         0                                                               AS D_SAL_AMT16, "+
								"         0                                                               AS D_SAL_AMT17, "+
								"         0                                                               AS D_SAL_AMT18, "+
								"         0                                                               AS D_SAL_AMT19, "+
								"         0                                                               AS D_SAL_AMT20, "+
								"         0                                                               AS D_SAL_AMT21, "+
								"         0                                                               AS D_SAL_AMT22, "+
								"         0                                                               AS D_SAL_AMT, "+
								"         0                                                               AS D_GOAL_AMT, "+
								"         0                                                               AS D_CUS_CNT "+
								"    FROM CD201 C, "+
								"         CD060 D, "+
								"         V_CD020_000 E "+
								"   WHERE C.STR_CODE   = D.STR_CODE "+
								"   AND C.CLASS_CODE = D.CLASS_CODE "+
								"   AND C.STR_CODE   = E.STR_CODE "+
								"   AND D.DEPT_CODE  = E.PC_CODE "+
								"   AND C.GOAL_YMD   =? "+
								"   AND E.CORP_CODE = 2000 "+
								"   AND E.BONBU_CODE = 2600 "+
								"   AND E.TEAM_CODE in(2620,2630,2640,2650) "+
								"  GROUP BY E.CORP_CODE, E.CORP_NAME, E.BONBU_CODE, E.BONBU_NAME, E.TEAM_CODE, E.TEAM_NAME, E.PC_CODE, E.PC_NAME, C.CLASS_CODE, D.CLASS_NAME  "+
								" "+
								"UNION ALL "+
								" "+
								"  SELECT "+
								"         E.CORP_CODE                                                  AS CORP_CODE, "+
								"         E.CORP_NAME                                                  AS CORP_NAME, "+
								"         E.BONBU_CODE                                                 AS BONBU_CODE, "+
								"         E.BONBU_NAME                                                 AS BONBU_NAME, "+
								"         E.TEAM_CODE                                                  AS TEAM_CODE, "+
								"         E.TEAM_NAME                                                  AS TEAM_NAME, "+
								"         E.PC_CODE                                                    AS PC_CODE, "+
								"         E.PC_NAME                                                    AS PC_NAME, "+
								"         C.CLASS_CODE                                                 AS CLASS_CODE, "+
								"         D.CLASS_NAME                                                 AS CLASS_NAME, "+
								"         0                                                               AS SAL_AMT11, "+
								"         0                                                               AS SAL_AMT12, "+
								"         0                                                               AS SAL_AMT13, "+
								"         0                                                               AS SAL_AMT14, "+
								"         0                                                               AS SAL_AMT15, "+
								"         0                                                               AS SAL_AMT16, "+
								"         0                                                               AS SAL_AMT17, "+
								"         0                                                               AS SAL_AMT18, "+
								"         0                                                               AS SAL_AMT19, "+
								"         0                                                               AS SAL_AMT20, "+
								"         0                                                               AS SAL_AMT21, "+
								"         0                                                               AS SAL_AMT22, "+
								"         0                                                               AS SAL_AMT, "+
								"         0                                                               AS GOAL_AMT, "+
								"         0                                                               AS CUS_CNT, "+
								"         0                                                               AS D_SAL_AMT11, "+
								"         0                                                               AS D_SAL_AMT12, "+
								"         0                                                               AS D_SAL_AMT13, "+
								"         0                                                               AS D_SAL_AMT14, "+
								"         0                                                               AS D_SAL_AMT15, "+
								"         0                                                               AS D_SAL_AMT16, "+
								"         0                                                               AS D_SAL_AMT17, "+
								"         0                                                               AS D_SAL_AMT18, "+
								"         0                                                               AS D_SAL_AMT19, "+
								"         0                                                               AS D_SAL_AMT20, "+
								"         0                                                               AS D_SAL_AMT21, "+
								"         0                                                               AS D_SAL_AMT22, "+
								"         0                                                               AS D_SAL_AMT, "+
								"         SUM(C.GOAL_AMT)                                                 AS D_GOAL_AMT, "+
								"         0                                                               AS D_CUS_CNT "+
								"    FROM CD201 C, "+
								"         CD060 D, "+
								"         V_CD020_000 E "+
								"   WHERE C.STR_CODE   = D.STR_CODE "+
								"   AND C.CLASS_CODE = D.CLASS_CODE "+
								"   AND C.STR_CODE   = E.STR_CODE "+
								"   AND D.DEPT_CODE  = E.PC_CODE "+
								"   AND C.GOAL_YMD   =? "+
								"   AND E.CORP_CODE = 2000 "+
								"   AND E.BONBU_CODE = 2600 "+
								"   AND E.TEAM_CODE in(2620,2630,2640,2650) "+
								"  GROUP BY E.CORP_CODE, E.CORP_NAME, E.BONBU_CODE, E.BONBU_NAME, E.TEAM_CODE, E.TEAM_NAME, E.PC_CODE, E.PC_NAME, C.CLASS_CODE, D.CLASS_NAME  "+
								" ) "+
								"GROUP BY CORP_CODE, CORP_NAME, BONBU_CODE, BONBU_NAME, TEAM_CODE, TEAM_NAME "+
								") order by 1";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,ymd);
				pstmt.setString(2,d_ymd);
				pstmt.setString(3,ymd);
				pstmt.setString(4,d_ymd);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					TD002_bean subBean = new TD002_bean();
					subBean.setYmd(ymd);
					subBean.setCorp_code(rs.getString("corp_code"));
					subBean.setGb(rs.getString("gb"));
					subBean.setD_gb(rs.getString("d_gb"));
					subBean.setGoal_amt(rs.getDouble("goal_amt"));
					subBean.setSal_amt(rs.getDouble("sal_amt"));
					subBean.setDs(rs.getDouble("ds"));
					subBean.setSal_amt11(rs.getDouble("sal_amt11"));
					subBean.setSal_amt12(rs.getDouble("sal_amt12"));
					subBean.setSal_amt13(rs.getDouble("sal_amt13"));
					subBean.setSal_amt14(rs.getDouble("sal_amt14"));
					subBean.setSal_amt15(rs.getDouble("sal_amt15"));
					subBean.setSal_amt16(rs.getDouble("sal_amt16"));
					subBean.setSal_amt17(rs.getDouble("sal_amt17"));
					subBean.setSal_amt18(rs.getDouble("sal_amt18"));
					subBean.setSal_amt19(rs.getDouble("sal_amt19"));
					subBean.setSal_amt20(rs.getDouble("sal_amt20"));
					subBean.setSal_amt21(rs.getDouble("sal_amt21"));
					subBean.setSal_amt22(rs.getDouble("sal_amt22"));
					subBean.setCus_cnt(rs.getDouble("cus_cnt"));
					subBean.setCnt(rs.getDouble("cnt"));
					subBean.setD_goal_amt(rs.getDouble("d_goal_amt"));
					subBean.setD_sal_amt(rs.getDouble("d_sal_amt"));
					subBean.setD_ds(rs.getDouble("d_ds"));
					subBean.setD_sal_amt11(rs.getDouble("d_sal_amt11"));
					subBean.setD_sal_amt12(rs.getDouble("d_sal_amt12"));
					subBean.setD_sal_amt13(rs.getDouble("d_sal_amt13"));
					subBean.setD_sal_amt14(rs.getDouble("d_sal_amt14"));
					subBean.setD_sal_amt15(rs.getDouble("d_sal_amt15"));
					subBean.setD_sal_amt16(rs.getDouble("d_sal_amt16"));
					subBean.setD_sal_amt17(rs.getDouble("d_sal_amt17"));
					subBean.setD_sal_amt18(rs.getDouble("d_sal_amt18"));
					subBean.setD_sal_amt19(rs.getDouble("d_sal_amt19"));
					subBean.setD_sal_amt20(rs.getDouble("d_sal_amt20"));
					subBean.setD_sal_amt21(rs.getDouble("d_sal_amt21"));
					subBean.setD_sal_amt22(rs.getDouble("d_sal_amt22"));
					subBean.setD_cus_cnt(rs.getDouble("d_cus_cnt"));
					subBean.setD_cnt(rs.getDouble("d_cnt"));
					subBean.setCorp_name(rs.getString("corp_name"));
					subBean.setBonbu_code(rs.getString("bonbu_code"));
					subBean.setBonbu_name(rs.getString("bonbu_name"));
					subBean.setTeam_code(rs.getString("team_code"));
					subBean.setTeam_name(rs.getString("team_name"));
					bean.add(subBean);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		return bean;
	}
	
	public int Insertsokbo(Connection conn,ArrayList<TD002_bean> td002)
	{
		int result = 1;
		Connection con = conn;
		PreparedStatement pstmt = null;
		
		try {
			
			for(int a = 0; a < td002.size(); a++)
			{
				String sql = "insert into TD002 values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, td002.get(a).getYmd());
				pstmt.setString(2, td002.get(a).getCorp_code());
				pstmt.setString(3, td002.get(a).getGb());
				pstmt.setString(4, td002.get(a).getD_gb());
				pstmt.setDouble(5, td002.get(a).getGoal_amt());
				pstmt.setDouble(6, td002.get(a).getSal_amt());
				pstmt.setDouble(7, td002.get(a).getDs());
				pstmt.setDouble(8, td002.get(a).getSal_amt11());
				pstmt.setDouble(9, td002.get(a).getSal_amt12());
				pstmt.setDouble(10, td002.get(a).getSal_amt13());
				pstmt.setDouble(11, td002.get(a).getSal_amt14());
				pstmt.setDouble(12, td002.get(a).getSal_amt15());
				pstmt.setDouble(13, td002.get(a).getSal_amt16());
				pstmt.setDouble(14, td002.get(a).getSal_amt17());
				pstmt.setDouble(15, td002.get(a).getSal_amt18());
				pstmt.setDouble(16, td002.get(a).getSal_amt19());
				pstmt.setDouble(17, td002.get(a).getSal_amt20());
				pstmt.setDouble(18, td002.get(a).getSal_amt21());
				pstmt.setDouble(19, td002.get(a).getSal_amt22());
				pstmt.setDouble(20, td002.get(a).getCus_cnt());
				pstmt.setDouble(21, td002.get(a).getCnt());
				pstmt.setDouble(22, td002.get(a).getD_goal_amt());
				pstmt.setDouble(23, td002.get(a).getD_sal_amt());
				pstmt.setDouble(24, td002.get(a).getD_ds());
				pstmt.setDouble(25, td002.get(a).getD_sal_amt11());
				pstmt.setDouble(26, td002.get(a).getD_sal_amt12());
				pstmt.setDouble(27, td002.get(a).getD_sal_amt13());
				pstmt.setDouble(28, td002.get(a).getD_sal_amt14());
				pstmt.setDouble(29, td002.get(a).getD_sal_amt15());
				pstmt.setDouble(30, td002.get(a).getD_sal_amt16());
				pstmt.setDouble(31, td002.get(a).getD_sal_amt17());
				pstmt.setDouble(32, td002.get(a).getD_sal_amt18());
				pstmt.setDouble(33, td002.get(a).getD_sal_amt19());
				pstmt.setDouble(34, td002.get(a).getD_sal_amt20());
				pstmt.setDouble(35, td002.get(a).getD_sal_amt21());
				pstmt.setDouble(36, td002.get(a).getD_sal_amt22());
				pstmt.setDouble(37, td002.get(a).getD_cus_cnt());
				pstmt.setDouble(38, td002.get(a).getD_cnt());
				pstmt.setString(39, td002.get(a).getCorp_name());
				pstmt.setString(40, td002.get(a).getBonbu_code());
				pstmt.setString(41, td002.get(a).getBonbu_name());
				pstmt.setString(42, td002.get(a).getTeam_code());
				pstmt.setString(43, td002.get(a).getTeam_name());
				
				result = pstmt.executeUpdate();
			}
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






















