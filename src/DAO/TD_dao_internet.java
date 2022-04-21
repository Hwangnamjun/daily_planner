package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.TD004_bean;

public class TD_dao_internet {

	public TD004_bean  Selectinternet(Connection conn,String ymd) {
		
		Connection con = conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TD004_bean bean = new TD004_bean();
		bean.setYmd(ymd);
		String team[] = {"2630","2640","2650"};
		for(int a = 0; a < team.length; a++)
		{
			try {
				String sql = 
						"WITH GUSUNG AS ( "+
								"SELECT  "+
								"SUM(GU_SAL_AMT)/1000       AS GU_SAL_AMT "+
								"FROM "+
								"  ( "+
								"  SELECT /*+ use_hash(A C) */ "+
								"  A.SAL_AMT        AS GU_SAL_AMT, "+
								"  A.CLASS_CODE    AS CLASS_CODE, "+
								"  A.SALE_YMD        AS SALE_YMD "+
								"  FROM SL011 A, "+
								"     CD060 B, "+
								"     V_CD020_000 C "+
								"  WHERE    A.STR_CODE = B.STR_CODE "+
								"  AND      A.CLASS_CODE = B.CLASS_CODE "+
								"  AND      A.STR_CODE = C.STR_CODE "+
								"  AND      B.DEPT_CODE = C.PC_CODE "+
								"  AND      A.SALE_YMD = ? "+
								"  AND      C.CORP_CODE LIKE    2000 || '%' "+
								"  AND      C.BONBU_CODE LIKE   2600 || '%' "+
								"  AND      C.TEAM_CODE LIKE    ? || '%' "+
								"  ) "+
								")  "+
								"SELECT  "+
								"SALE_YMD                                                       AS SALE_YMD, "+
								"GOAL_AMT                                                       AS GOAL_AMT, "+
								"SAL_AMT                                                        AS SAL_AMT, "+
								"DC_AMT                                                         AS SUBDC_AMT, "+
								"CLASS_CNT                                                      AS CLASS_CNT, "+
								"D_SALE_YMD                                                     AS D_SALE_YMD, "+
								"D_SAL_AMT                                                      AS D_SAL_AMT, "+
								"DAY_NAME                                                       AS DAYNAME, "+
								"DECODE(D_SAL_AMT,0,0,ROUND(((SAL_AMT/D_SAL_AMT)*100)-100,1))   AS SINJANG, "+
								"DECODE(GOAL_AMT,0,0,ROUND(SAL_AMT/GOAL_AMT*100,1))             AS DALSUNG, "+
								"DECODE(SAL_AMT,0,0,ROUND(DC_AMT/SAL_AMT*100,1))                AS SUBDC, "+
								"SAL_AMT-DC_AMT                                                 AS AMT, "+
								"DECODE(CLASS_CNT,0,0,ROUND(SAL_AMT/CLASS_CNT,0))               AS CNT_AMT, "+
								"DECODE(GU_SAL_AMT,0,0,ROUND(SAL_AMT/GU_SAL_AMT*100,1))         AS GUSUNGBI, "+
								"INT_SAL_AMT                                                    AS INT_SAL_AMT "+
								"FROM "+
								"( "+
								"  SELECT  "+
								"  MAX(A.SALE_YMD)                    AS SALE_YMD, "+
								"  A.D_SALE_YMD                       AS D_SALE_YMD, "+
								"  A.DAY_NAME                         AS DAY_NAME, "+
								"  ROUND(SUM(A.GOAL_AMT),0)           AS GOAL_AMT, "+
								"  ROUND(SUM(A.SAL_AMT),0)            AS SAL_AMT, "+
								"  ROUND(SUM(A.DC_AMT),0)             AS DC_AMT, "+
								"  SUM(A.CLASS_CNT)                   AS CLASS_CNT, "+
								"  ROUND(SUM(A.D_SAL_AMT),0)          AS D_SAL_AMT, "+
								"  ROUND(MAX(GUSUNG.GU_SAL_AMT),0)    AS GU_SAL_AMT, "+
								"  ROUND(SUM(INT_SAL_AMT),0)          AS INT_SAL_AMT "+
								"  FROM "+
								"  ( "+
								"    SELECT /*+ use_hash(A F) */ "+
								"    A.CLASS_CODE                                             AS CLASS_CODE, "+
								"    A.SALE_YMD                                               AS SALE_YMD, "+
								"    TO_CHAR(TO_DATE(A.SALE_YMD,'YYYYMMDD')-364,'YYYYMMDD')   AS D_SALE_YMD, "+
								"    0                                                        AS GOAL_AMT, "+
								"    NVL(SUM(A.SAL_AMT)/1000,0)                               AS SAL_AMT, "+
								"    NVL(SUM(A.DC_AMT)/1000,0)                                AS DC_AMT, "+
								"    NVL(SUM(A.CLASS_CNT),0)                                  AS CLASS_CNT, "+
								"    F_GET_DAY('K',A.SALE_YMD)                                AS DAY_NAME, "+
								"    0                                                        AS D_SAL_AMT, "+
								"    NVL(SUM(A.INT_SAL_AMT)/1000,0)                           AS INT_SAL_AMT "+
								"    FROM SL011 A, "+
								"         CD060 E, "+
								"         V_CD020_000 F  "+
								"    WHERE    A.STR_CODE = E.STR_CODE "+
								"    AND      A.CLASS_CODE = E.CLASS_CODE "+
								"    AND      A.STR_CODE = F.STR_CODE "+
								"    AND      E.STR_CODE = F.STR_CODE "+
								"    AND      E.DEPT_CODE = F.PC_CODE "+
								"    AND      F.TEAM_CODE LIKE    ? || '%' "+
								"    AND      F.BONBU_CODE LIKE   2600 || '%' "+
								"    AND      F.CORP_CODE LIKE    2000 || '%' "+
								"    AND      A.SALE_YMD = ? "+
								"    GROUP BY A.CLASS_CODE, A.SALE_YMD "+
								"    UNION ALL "+
								"    SELECT /*+ use_hash(A F) */ "+
								"    A.CLASS_CODE                 AS CLASS_CODE, "+
								"    ''                           AS SALE_YMD, "+
								"    A.SALE_YMD                   AS D_SALE_YMD, "+
								"    0                            AS GOAL_AMT, "+
								"    0                            AS SAL_AMT, "+
								"    0                            AS DC_AMT, "+
								"    0                            AS CLASS_CNT, "+
								"    F_GET_DAY('K',A.SALE_YMD)    AS DAY_NAME, "+
								"    NVL(SUM(A.SAL_AMT)/1000,0)   AS D_SAL_AMT, "+
								"    0                            AS INT_SAL_AMT "+
								"    FROM SL011 A, "+
								"         CD060 E, "+
								"         V_CD020_000 F  "+
								"    WHERE    A.STR_CODE = E.STR_CODE "+
								"    AND      A.CLASS_CODE = E.CLASS_CODE "+
								"    AND      A.STR_CODE = F.STR_CODE "+
								"    AND      E.STR_CODE = F.STR_CODE "+
								"    AND      E.DEPT_CODE = F.PC_CODE "+

								"    AND      F.TEAM_CODE LIKE    ? || '%' "+
								"    AND      F.BONBU_CODE LIKE   2600 || '%' "+
								"    AND      F.CORP_CODE LIKE    2000 || '%' "+

								"    AND      A.SALE_YMD BETWEEN TO_CHAR(TO_DATE(?,'YYYYMMDD')-364,'YYYYMMDD') AND TO_CHAR(TO_DATE(?,'YYYYMMDD')-364,'YYYYMMDD') "+
								"    GROUP BY A.CLASS_CODE, A.SALE_YMD "+
								"    UNION ALL  "+
								"    SELECT /*+ use_hash(C F) */ "+
								"    C.CLASS_CODE                                               AS CLASS_CODE, "+
								"    C.GOAL_YMD                                                 AS SALE_YMD, "+
								"    TO_CHAR(TO_DATE(C.GOAL_YMD,'YYYYMMDD')-364,'YYYYMMDD')     AS D_SALE_YMD, "+
								"    NVL(SUM(C.GOAL_AMT),0)                                     AS GOAL_AMT, "+
								"    0                                                          AS SAL_AMT, "+
								"    0                                                          AS DC_AMT, "+
								"    0                                                          AS CLASS_CNT, "+
								"    F_GET_DAY('K',C.GOAL_YMD)                                  AS DAY_NAME, "+
								"    0                                                          AS D_SAL_AMT, "+
								"    0                                                          AS INT_SAL_AMT "+
								"    FROM CD201 C, "+
								"         CD060 E, "+
								"         V_CD020_000 F  "+
								"    WHERE    C.STR_CODE = E.STR_CODE "+
								"    AND      C.CLASS_CODE = E.CLASS_CODE "+
								"    AND      E.STR_CODE = F.STR_CODE "+
								"    AND      E.DEPT_CODE = F.PC_CODE "+
								"    AND      F.TEAM_CODE LIKE    ? || '%' "+
								"    AND      F.BONBU_CODE LIKE   2600 || '%' "+
								"    AND      F.CORP_CODE LIKE    2000 || '%' "+
								"    AND      C.GOAL_YMD = ? "+
								"    GROUP BY C.CLASS_CODE, C.GOAL_YMD "+
								"  ) A, "+
								"    GUSUNG "+
								"GROUP BY A.D_SALE_YMD,A.DAY_NAME "+
								"ORDER BY A.D_SALE_YMD "+
								")";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,ymd);
				pstmt.setString(2,team[a]);
				pstmt.setString(3,team[a]);
				pstmt.setString(4,ymd);
				pstmt.setString(5,team[a]);
				pstmt.setString(6,ymd);
				pstmt.setString(7,ymd);
				pstmt.setString(8,team[a]);
				pstmt.setString(9,ymd);
				rs = pstmt.executeQuery();
				if(rs.next())
				{
					switch (a) {
					case 0:
						bean.setInt_sal_amt_jab(rs.getDouble("INT_SAL_AMT"));
						break;
					case 1:
						bean.setInt_sal_amt_wo(rs.getDouble("INT_SAL_AMT"));
						break;
					case 2:
						bean.setInt_sal_amt_man(rs.getDouble("INT_SAL_AMT"));
						break;
						
					default:
						break;
					}
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
		}
		return bean;
	}
	
	public TD004_bean SelectIntSum(Connection conn, TD004_bean td004_2, String ymd)
	{
		Connection con = conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

			try {
				String sql = 
						"SELECT * FROM TD004 WHERE YMD = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, ymd);
				rs = pstmt.executeQuery();
				if(rs.next())
				{
					td004_2.setInt_sal_amt_jab_sum((rs.getDouble("INT_SAL_AMT_JOB_SUM")+(td004_2.getInt_sal_amt_jab())));
					td004_2.setInt_sal_amt_wo_sum((rs.getDouble("INT_SAL_AMT_WO_SUM")+(td004_2.getInt_sal_amt_wo())));
					td004_2.setInt_sal_amt_man_sum((rs.getDouble("INT_SAL_AMT_MAN_SUM")+(td004_2.getInt_sal_amt_man())));
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
			return td004_2;
		}
	
	public TD004_bean SelectIntCnt(Connection conn, TD004_bean td004_3, String ymd)
	{
		Connection con = conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = 
					" SELECT COUNT(CASE WHEN TEAM_CODE = '2630' THEN 1 END)              AS CODE_JAB, "+
							"        COUNT(CASE WHEN TEAM_CODE = '2640' THEN 1 END)              AS CODE_WO, "+
							"        COUNT(CASE WHEN TEAM_CODE = '2650' THEN 1 END)              AS CODE_MAN, "+
							"        SUM((CASE WHEN A.CLASS_CODE IN('104330', '104412') THEN 1 END))  AS CNT_CHECK "+
							" FROM SL011 A, CD060 B, V_CD020_000 C "+
							" WHERE    A.STR_CODE = B.STR_CODE "+
							" AND      A.CLASS_CODE = B.CLASS_CODE "+
							" AND      A.STR_CODE = C.STR_CODE "+
							" AND      B.DEPT_CODE = C.PC_CODE "+
							" AND      A.SALE_YMD = ? "+
							" AND      C.CORP_CODE LIKE    2000 || '%' "+
							" AND      C.BONBU_CODE LIKE   2600 || '%' "+
							" AND PROFIT_FLAG IN (SELECT PROFIT_FLAG FROM CD062 WHERE PROFIT_FLAG > 'I')";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ymd);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				if(rs.getDouble("CNT_CHECK") > 1.0)
				{
					td004_3.setInt_sal_amt_jab_cnt((rs.getDouble("CODE_JAB")) - 1);
				}
				else
				{
					td004_3.setInt_sal_amt_jab_cnt(rs.getDouble("CODE_JAB"));
				}
				td004_3.setInt_sal_amt_wo_cnt(rs.getDouble("CODE_WO"));
				td004_3.setInt_sal_amt_man_cnt(rs.getDouble("CODE_MAN"));
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
		
		return td004_3;
	}
	
	public int Inserteik(Connection conn,TD004_bean td004_3)
	{
		int result = 1;
		Connection con = conn;
		PreparedStatement pstmt = null;
		try {
				String sql = "insert into TD004 values(?,?,?,?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, td004_3.getYmd());
				pstmt.setDouble(2, td004_3.getInt_sal_amt_jab());
				pstmt.setDouble(3, td004_3.getInt_sal_amt_wo());
				pstmt.setDouble(4, td004_3.getInt_sal_amt_man());
				pstmt.setDouble(5, td004_3.getInt_sal_amt_jab_sum());
				pstmt.setDouble(6, td004_3.getInt_sal_amt_wo_sum());
				pstmt.setDouble(7, td004_3.getInt_sal_amt_man_sum());
				pstmt.setDouble(8, td004_3.getInt_sal_amt_jab_cnt());
				pstmt.setDouble(9, td004_3.getInt_sal_amt_wo_cnt());
				pstmt.setDouble(10, td004_3.getInt_sal_amt_man_cnt());
				
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






















