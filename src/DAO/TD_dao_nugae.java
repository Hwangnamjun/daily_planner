package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.TD001_bean;

public class TD_dao_nugae {


	
	public ArrayList<TD001_bean>  Selectnugae(Connection conn, String ymd) {
		
		Connection con = conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<TD001_bean> bean = new ArrayList<>();

			try {
				String sql = 
						" SELECT         MAX(Y.CORP_CODE)                                                                     AS CORP_CODE,    "+
								"                DECODE(GROUPING(Y.CORP_CODE)    "+
								"                     + GROUPING(Y.TEAM_CODE),0,MAX(Y.CORP_NAME),1,'법인소계',2,'법인합계',NULL)      AS CORP_NAME,    "+
								"                MAX(Y.DEPT_GBN)                                                                      AS DEPT_GBN,    "+
								"                DECODE(GROUPING(Y.CORP_CODE)    "+
								"                     + GROUPING(Y.TEAM_CODE),0,MAX(Y.TEAM_CODE),1,'8888',2,'9999',NULL)                                AS TEAM_CODE,    "+
								"                DECODE(GROUPING(Y.CORP_CODE)    "+
								"                     + GROUPING(Y.TEAM_CODE),0,MAX(Y.TEAM_NAME),NULL)                                AS TEAM_NAME,    "+
								"                SUM(Y.GOAL_AMT_D)                                                                    AS GOAL_AMT_D,    "+
								"                ROUND(SUM(Y.SAL_AMT_D)/1000,0)                                                       AS SAL_AMT_D,    "+
								"                DECODE(SUM(Y.GOAL_AMT_D),0,0,ROUND(SUM(Y.SAL_AMT_D)/1000/SUM(Y.GOAL_AMT_D)*100,1))       AS SUCC_D,    "+
								"                ROUND(SUM(Y.SAL_LST_D)/1000,0)                                                       AS SAL_LST_D,    "+
								"                DECODE(SUM(Y.SAL_LST_D),0,0,ROUND((SUM(Y.SAL_AMT_D)-SUM(Y.SAL_LST_D))/SUM(Y.SAL_LST_D)*100,1)) AS INCR_D,    "+
								"                SUM(Y.GOAL_AMT_M)                                                                    AS GOAL_AMT_M,    "+
								"                ROUND(SUM(Y.SAL_AMT_M)/1000,0)                                                       AS SAL_AMT_M,    "+
								"                DECODE(SUM(Y.GOAL_AMT_M),0,0,ROUND(SUM(Y.SAL_AMT_M)/1000/SUM(Y.GOAL_AMT_M)*100,1))       AS SUCC_M,    "+
								"                ROUND(SUM(Y.SAL_LST_M)/1000,0)                                                       AS SAL_LST_M,    "+
								"                DECODE(SUM(Y.SAL_LST_M),0,0,ROUND((SUM(Y.SAL_AMT_M)-SUM(Y.SAL_LST_M))/SUM(Y.SAL_LST_M)*100,1)) AS INCR_M,    "+
								"                ROUND((SUM(Y.SAL_AMT_M)/1000) - SUM(Y.GOAL_AMT_M),0)                                      AS DIFF_AMT_M,    "+
								"                SUM(Y.GOAL_AMT_Y)                                                                    AS GOAL_AMT_Y,    "+
								"                ROUND(SUM(Y.SAL_AMT_Y)/1000,0)                                                       AS SAL_AMT_Y,    "+
								"                DECODE(SUM(Y.GOAL_AMT_Y),0,0,ROUND(SUM(Y.SAL_AMT_Y)/1000/SUM(Y.GOAL_AMT_Y)*100,1))                AS SUCC_Y,    "+
								"                ROUND(SUM(Y.SAL_LST_Y)/1000,0)                                                       AS SAL_LST_Y,    "+
								"                DECODE(SUM(Y.SAL_LST_Y),0,0,ROUND((SUM(Y.SAL_AMT_Y)-SUM(Y.SAL_LST_Y))/SUM(Y.SAL_LST_Y)*100,1)) AS INCR_Y,    "+
								"                ROUND((SUM(Y.SAL_AMT_Y)/1000) - SUM(Y.GOAL_AMT_Y),0)                                      AS DIFF_AMT_Y    "+
								"          FROM (    "+
								"                  SELECT    "+
								"                          E.CORP_CODE                                                AS CORP_CODE,    "+
								"                          E.CORP_NAME                                                AS CORP_NAME,    "+
								"                          E.DEPT_GBN                                                 AS DEPT_GBN,    "+
								"                          E.TEAM_CODE                                                AS TEAM_CODE,    "+
								"                          E.TEAM_NAME                                                AS TEAM_NAME,    "+
								"                          SUM(C.GOAL_AMT )                                           AS GOAL_AMT_D,    "+
								"                          0                                                          AS SAL_AMT_D,    "+
								"                          0                                                          AS SAL_LST_D,    "+
								"                          0                                                          AS GOAL_AMT_M,    "+
								"                          0                                                          AS SAL_AMT_M,    "+
								"                          0                                                          AS SAL_LST_M,    "+
								"                          0                                                          AS GOAL_AMT_Y,    "+
								"                          0                                                          AS SAL_AMT_Y,    "+
								"                          0                                                          AS SAL_LST_Y    "+
								"                     FROM CD201 C,    "+
								"                          CD060 D,    "+
								"                          V_CD020_009 E    "+
								"                    WHERE C.STR_CODE   = D.STR_CODE    "+
								"                      AND C.CLASS_CODE = D.CLASS_CODE    "+
								"                      AND C.STR_CODE   = E.STR_CODE    "+
								"                      AND D.DEPT_CODE  = E.PC_CODE    "+
								"                      AND C.GOAL_YMD   = ?     "+
								"                      AND E.CORP_CODE in (2000,5000)     "+
								"                      AND E.TEAM_CODE NOT IN ('3010', '3030', '2691')     "+
								"                   GROUP BY E.CORP_CODE, E.CORP_NAME, E.DEPT_GBN, E.TEAM_CODE, E.TEAM_NAME    "+
								"                  UNION ALL    "+
								"                     SELECT    "+
								"                          E.CORP_CODE                                                AS CORP_CODE,    "+
								"                          E.CORP_NAME                                                AS CORP_NAME,    "+
								"                          E.DEPT_GBN                                                 AS DEPT_GBN,    "+
								"                          E.TEAM_CODE                                                AS TEAM_CODE,    "+
								"                          E.TEAM_NAME                                                AS TEAM_NAME,    "+
								"                          0                                                          AS GOAL_AMT_D,    "+
								"                          SUM(DECODE(A.SALE_YMD,?,A.SAL_AMT,0))              AS SAL_AMT_D,     "+
								"                          SUM(DECODE(A.SALE_YMD,?,0,A.SAL_AMT))              AS SAL_LST_D,     "+
								"                          0                                                          AS GOAL_AMT_M,    "+
								"                          0                                                          AS SAL_AMT_M,    "+
								"                          0                                                          AS SAL_LST_M,    "+
								"                          0                                                          AS GOAL_AMT_Y,    "+
								"                          0                                                          AS SAL_AMT_Y,    "+
								"                          0                                                          AS SAL_LST_Y    "+
								"                     FROM SL011 A,    "+
								"                          CD060 D,    "+
								"                          V_CD020_009 E    "+
								"                    WHERE A.STR_CODE   = D.STR_CODE    "+
								"                      AND A.CLASS_CODE = D.CLASS_CODE    "+
								"                      AND D.STR_CODE   = E.STR_CODE    "+
								"                      AND D.DEPT_CODE  = E.PC_CODE    "+
								"                      AND A.SALE_YMD   IN (?, TO_CHAR(ADD_MONTHS(TO_DATE(?,'YYYYMMDD'),-12),'YYYYMMDD') )     "+
								"                      AND E.CORP_CODE in (2000,5000)   "+
								"                      AND E.TEAM_CODE NOT IN ('3010', '3030', '2691')     "+
								"                   GROUP BY E.CORP_CODE, E.CORP_NAME, E.DEPT_GBN, E.TEAM_CODE, E.TEAM_NAME    "+
								"                  UNION ALL    "+
								"                   SELECT /*+ NO_MERGE(A) NO_PUSH_PRED(A) */    "+
								"                          E.CORP_CODE                                                AS CORP_CODE,    "+
								"                          E.CORP_NAME                                                AS CORP_NAME,    "+
								"                          E.DEPT_GBN                                                 AS DEPT_GBN,    "+
								"                          E.TEAM_CODE                                                AS TEAM_CODE,    "+
								"                          E.TEAM_NAME                                                AS TEAM_NAME,    "+
								"                          0                                                          AS GOAL_AMT_D,    "+
								"                          0                                                          AS SAL_AMT_D,    "+
								"                          0                                                          AS SAL_LST_D,    "+
								"                          SUM(A.GOAL_AMT)                                            AS GOAL_AMT_M,    "+
								"                          0                                                          AS SAL_AMT_M,    "+
								"                          0                                                          AS SAL_LST_M,    "+
								"                          0                                                          AS GOAL_AMT_Y,    "+
								"                          0                                                          AS SAL_AMT_Y,    "+
								"                          0                                                          AS SAL_LST_Y    "+
								"                     FROM (    "+
								"                          SELECT A.STR_CODE, D.DEPT_CODE, SUM(A.GOAL_AMT) AS GOAL_AMT    "+
								"                            FROM CD201 A,    "+
								"                                 CD060 D    "+
								"                           WHERE A.STR_CODE   = D.STR_CODE    "+
								"                             AND A.CLASS_CODE = D.CLASS_CODE    "+
								"                             AND A.GOAL_YMD   BETWEEN SUBSTR(?,1,6)||'01' AND ?     "+
								"                           GROUP BY A.STR_CODE, D.DEPT_CODE ) A, V_CD020_009 E    "+
								"                    WHERE A.STR_CODE   = E.STR_CODE    "+
								"                      AND A.DEPT_CODE  = E.PC_CODE    "+
								"                      AND E.CORP_CODE in (2000,5000)   "+
								"                      AND E.TEAM_CODE NOT IN ('3010', '3030', '2691')     "+
								"                   GROUP BY E.CORP_CODE, E.CORP_NAME, E.DEPT_GBN, E.TEAM_CODE, E.TEAM_NAME    "+
								"                  UNION ALL    "+
								"                   SELECT /*+ NO_MERGE(A) NO_PUSH_PRED(A) */    "+
								"                          E.CORP_CODE                                                AS CORP_CODE,    "+
								"                          E.CORP_NAME                                                AS CORP_NAME,    "+
								"                          E.DEPT_GBN                                                 AS DEPT_GBN,    "+
								"                          E.TEAM_CODE                                                AS TEAM_CODE,    "+
								"                          E.TEAM_NAME                                                AS TEAM_NAME,    "+
								"                          0                                                          AS GOAL_AMT_D,    "+
								"                          0                                                          AS SAL_AMT_D,    "+
								"                          0                                                          AS SAL_LST_D,    "+
								"                          0                                                          AS GOAL_AMT_M,    "+
								"                          SUM(A.SAL_AMT)                                             AS SAL_AMT_M,    "+
								"                          0                                                          AS SAL_LST_M,    "+
								"                          0                                                          AS GOAL_AMT_Y,    "+
								"                          0                                                          AS SAL_AMT_Y,    "+
								"                          0                                                          AS SAL_LST_Y    "+
								"                     FROM (    "+
								"                          SELECT A.STR_CODE, D.DEPT_CODE, SUM(A.SAL_AMT) AS SAL_AMT    "+
								"                            FROM SL011 A,    "+
								"                                 CD060 D    "+
								"                           WHERE A.STR_CODE   = D.STR_CODE    "+
								"                             AND A.CLASS_CODE = D.CLASS_CODE    "+
								"                             AND A.SALE_YMD   BETWEEN SUBSTR(?,1,6)||'01' AND ?     "+
								"                           GROUP BY A.STR_CODE, D.DEPT_CODE ) A, V_CD020_009 E    "+
								"                    WHERE A.STR_CODE   = E.STR_CODE    "+
								"                      AND A.DEPT_CODE  = E.PC_CODE    "+
								"                      AND E.CORP_CODE in (2000,5000)   "+
								"                      AND E.TEAM_CODE NOT IN ('3010', '3030', '2691')     "+
								"                   GROUP BY E.CORP_CODE, E.CORP_NAME, E.DEPT_GBN, E.TEAM_CODE, E.TEAM_NAME    "+
								"                  UNION ALL    "+
								"                   SELECT /*+ NO_MERGE(A) NO_PUSH_PRED(A) */    "+
								"                          E.CORP_CODE                                                AS CORP_CODE,    "+
								"                          E.CORP_NAME                                                AS CORP_NAME,    "+
								"                          E.DEPT_GBN                                                 AS DEPT_GBN,    "+
								"                          E.TEAM_CODE                                                AS TEAM_CODE,    "+
								"                          E.TEAM_NAME                                                AS TEAM_NAME,    "+
								"                          0                                                          AS GOAL_AMT_D,    "+
								"                          0                                                          AS SAL_AMT_D,    "+
								"                          0                                                          AS SAL_LST_D,    "+
								"                          0                                                          AS GOAL_AMT_M,    "+
								"                          0                                                          AS SAL_AMT_M,    "+
								"                          SUM(A.SAL_AMT)                                             AS SAL_LST_M,    "+
								"                          0                                                          AS GOAL_AMT_Y,    "+
								"                          0                                                          AS SAL_AMT_Y,    "+
								"                          0                                                          AS SAL_LST_Y    "+
								"                     FROM (    "+
								"                          SELECT A.STR_CODE, D.DEPT_CODE, SUM(A.SAL_AMT) AS SAL_AMT    "+
								"                            FROM SL011 A,    "+
								"                                 CD060 D    "+
								"                           WHERE A.STR_CODE   = D.STR_CODE    "+
								"                             AND A.CLASS_CODE = D.CLASS_CODE    "+
								"                             AND A.SALE_YMD   BETWEEN SUBSTR(TO_CHAR(ADD_MONTHS(TO_DATE(?,'YYYYMMDD'),-12),'YYYYMMDD'),1,6)||'01'     "+
								"                                              AND TO_CHAR(ADD_MONTHS(TO_DATE(?,'YYYYMMDD'),-12),'YYYYMMDD')      "+
								"                           GROUP BY A.STR_CODE, D.DEPT_CODE ) A, V_CD020_009 E    "+
								"                    WHERE A.STR_CODE   = E.STR_CODE    "+
								"                      AND A.DEPT_CODE  = E.PC_CODE    "+
								"                      AND E.CORP_CODE in (2000,5000)   "+
								"                      AND E.TEAM_CODE NOT IN ('3010', '3030', '2691')     "+
								"                   GROUP BY E.CORP_CODE, E.CORP_NAME, E.DEPT_GBN, E.TEAM_CODE, E.TEAM_NAME    "+
								"                  UNION ALL    "+
								"                   SELECT /*+ NO_MERGE(A) NO_PUSH_PRED(A) */    "+
								"                          E.CORP_CODE                                                AS CORP_CODE,    "+
								"                          E.CORP_NAME                                                AS CORP_NAME,    "+
								"                          E.DEPT_GBN                                                 AS DEPT_GBN,    "+
								"                          E.TEAM_CODE                                                AS TEAM_CODE,    "+
								"                          E.TEAM_NAME                                                AS TEAM_NAME,    "+
								"                          0                                                          AS GOAL_AMT_D,    "+
								"                          0                                                          AS SAL_AMT_D,    "+
								"                          0                                                          AS SAL_LST_D,    "+
								"                          0                                                          AS GOAL_AMT_M,    "+
								"                          0                                                          AS SAL_AMT_M,    "+
								"                          0                                                          AS SAL_LST_M,    "+
								"                          SUM(A.GOAL_AMT)                                            AS GOAL_AMT_Y,    "+
								"                          0                                                          AS SAL_AMT_Y,    "+
								"                          0                                                          AS SAL_LST_Y    "+
								"                     FROM (    "+
								"                          SELECT A.STR_CODE, D.DEPT_CODE, SUM(A.GOAL_AMT) AS GOAL_AMT    "+
								"                            FROM CD201 A,    "+
								"                                 CD060 D    "+
								"                           WHERE A.STR_CODE   = D.STR_CODE    "+
								"                             AND A.CLASS_CODE = D.CLASS_CODE    "+
								"                             AND A.GOAL_YMD   BETWEEN SUBSTR(?,1,4)||'0101' AND ?     "+
								"                           GROUP BY A.STR_CODE, D.DEPT_CODE ) A, V_CD020_009 E    "+
								"                    WHERE A.STR_CODE   = E.STR_CODE    "+
								"                      AND A.DEPT_CODE  = E.PC_CODE    "+
								"                      AND E.CORP_CODE in (2000,5000)    "+
								"                      AND E.TEAM_CODE NOT IN ('3010', '3030', '2691')     "+
								"                   GROUP BY E.CORP_CODE, E.CORP_NAME, E.DEPT_GBN, E.TEAM_CODE, E.TEAM_NAME    "+
								"                  UNION ALL    "+
								"                   SELECT /*+ NO_MERGE(A) NO_PUSH_PRED(A) */    "+
								"                          E.CORP_CODE                                                AS CORP_CODE,    "+
								"                          E.CORP_NAME                                                AS CORP_NAME,    "+
								"                          E.DEPT_GBN                                                 AS DEPT_GBN,    "+
								"                          E.TEAM_CODE                                                AS TEAM_CODE,    "+
								"                          E.TEAM_NAME                                                AS TEAM_NAME,    "+
								"                          0                                                          AS GOAL_AMT_D,    "+
								"                          0                                                          AS SAL_AMT_D,    "+
								"                          0                                                          AS SAL_LST_D,    "+
								"                          0                                                          AS GOAL_AMT_M,    "+
								"                          0                                                          AS SAL_AMT_M,    "+
								"                          0                                                          AS SAL_LST_M,    "+
								"                          0                                                          AS GOAL_AMT_Y,    "+
								"                          SUM(A.SAL_AMT)                                             AS SAL_AMT_Y,    "+
								"                          0                                                          AS SAL_LST_Y    "+
								"                     FROM (    "+
								"                          SELECT A.STR_CODE, D.DEPT_CODE, SUM(A.SAL_AMT) AS SAL_AMT    "+
								"                            FROM SL011 A,    "+
								"                                 CD060 D    "+
								"                           WHERE A.STR_CODE   = D.STR_CODE    "+
								"                             AND A.CLASS_CODE = D.CLASS_CODE    "+
								"                             AND A.SALE_YMD   BETWEEN SUBSTR(?,1,4)||'0101' AND ?     "+
								"                           GROUP BY A.STR_CODE, D.DEPT_CODE ) A, V_CD020_009 E    "+
								"                    WHERE A.STR_CODE   = E.STR_CODE    "+
								"                      AND A.DEPT_CODE  = E.PC_CODE    "+
								"                      AND E.CORP_CODE in (2000,5000)    "+
								"                      AND E.TEAM_CODE NOT IN ('3010', '3030', '2691')     "+
								"                   GROUP BY E.CORP_CODE, E.CORP_NAME, E.DEPT_GBN, E.TEAM_CODE, E.TEAM_NAME    "+
								"                  UNION ALL    "+
								"                   SELECT /*+ NO_MERGE(A) NO_PUSH_PRED(A) */    "+
								"                          E.CORP_CODE                                                AS CORP_CODE,    "+
								"                          E.CORP_NAME                                                AS CORP_NAME,    "+
								"                          E.DEPT_GBN                                                 AS DEPT_GBN,   "+
								"                          E.TEAM_CODE                                                AS TEAM_CODE,    "+
								"                          E.TEAM_NAME                                                AS TEAM_NAME,    "+
								"                          0                                                          AS GOAL_AMT_D,    "+
								"                          0                                                          AS SAL_AMT_D,    "+
								"                          0                                                          AS SAL_LST_D,    "+
								"                          0                                                          AS GOAL_AMT_M,    "+
								"                          0                                                          AS SAL_AMT_M,    "+
								"                          0                                                          AS SAL_LST_M,    "+
								"                          0                                                          AS GOAL_AMT_Y,    "+
								"                          0                                                          AS SAL_AMT_Y,    "+
								"                          SUM(A.SAL_AMT)                                             AS SAL_LST_Y    "+
								"                     FROM (    "+
								"                          SELECT A.STR_CODE, D.DEPT_CODE, SUM(A.SAL_AMT) AS SAL_AMT    "+
								"                            FROM SL011 A,    "+
								"                                 CD060 D    "+
								"                           WHERE A.STR_CODE   = D.STR_CODE    "+
								"                             AND A.CLASS_CODE = D.CLASS_CODE    "+
								"                             AND A.SALE_YMD   BETWEEN SUBSTR(?,1,4)-1||'0101' AND SUBSTR(?,1,4)-1||SUBSTR(?,5,4)     "+
								"                           GROUP BY A.STR_CODE, D.DEPT_CODE ) A, V_CD020_009 E    "+
								"                    WHERE A.STR_CODE   = E.STR_CODE    "+
								"                      AND A.DEPT_CODE  = E.PC_CODE    "+
								"                      AND E.CORP_CODE in (2000,5000)    "+
								"                      AND E.TEAM_CODE NOT IN ('3010', '3030', '2691')     "+
								"                   GROUP BY E.CORP_CODE, E.CORP_NAME, E.DEPT_GBN, E.TEAM_CODE, E.TEAM_NAME    "+
								"                  ) Y    "+
								" GROUP BY ROLLUP(Y.CORP_CODE,Y.TEAM_CODE)     "+
								"  ORDER BY Y.CORP_CODE,Y.TEAM_CODE";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,ymd);
				pstmt.setString(2,ymd);
				pstmt.setString(3,ymd);
				pstmt.setString(4,ymd);
				pstmt.setString(5,ymd);
				pstmt.setString(6,ymd);
				pstmt.setString(7,ymd);
				pstmt.setString(8,ymd);
				pstmt.setString(9,ymd);
				pstmt.setString(10,ymd);
				pstmt.setString(11,ymd);
				pstmt.setString(12,ymd);
				pstmt.setString(13,ymd);
				pstmt.setString(14,ymd);
				pstmt.setString(15,ymd);
				pstmt.setString(16,ymd);
				pstmt.setString(17,ymd);
				pstmt.setString(18,ymd);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					TD001_bean subBean = new TD001_bean();
					subBean.setYmd(ymd);
					subBean.setCorp_code(rs.getString("corp_code"));
					subBean.setCorp_name(rs.getString("corp_name"));
					subBean.setDept_gbn(rs.getString("dept_gbn"));
					subBean.setTeam_code(rs.getString("team_code"));
					subBean.setTeam_name(rs.getString("team_name"));
					subBean.setGoal_amt_d(rs.getDouble("goal_amt_d"));
					subBean.setSal_amt_d(rs.getDouble("sal_amt_d"));
					subBean.setSucc_d(rs.getDouble("succ_d"));
					subBean.setSal_lst_d(rs.getDouble("sal_lst_d"));
					subBean.setIncr_d(rs.getDouble("incr_d"));
					subBean.setGoal_amt_m(rs.getDouble("goal_amt_m"));
					subBean.setSal_amt_m(rs.getDouble("sal_amt_m"));
					subBean.setSucc_m(rs.getDouble("succ_m"));
					subBean.setSal_lst_m(rs.getDouble("sal_lst_m"));
					subBean.setIncr_m(rs.getDouble("incr_m"));
					subBean.setDiff_amt_m(rs.getDouble("diff_amt_m"));
					subBean.setGoal_amt_y(rs.getDouble("goal_amt_y"));
					subBean.setSal_amt_y(rs.getDouble("sal_amt_y"));
					subBean.setSucc_y(rs.getDouble("succ_y"));
					subBean.setSal_lst_y(rs.getDouble("sal_lst_y"));
					subBean.setIncr_y(rs.getDouble("incr_y"));
					subBean.setDiff_amt_y(rs.getDouble("diff_amt_y"));
					
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
	
	public int Insertnugae(Connection conn,ArrayList<TD001_bean> td001)
	{
		int result = 1;
		Connection con = conn;
		PreparedStatement pstmt = null;
		
		try {
			
			for(int a = 0; a < td001.size(); a++)
			{
				String sql = "insert into TD001 values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, td001.get(a).getYmd());
				pstmt.setString(2, td001.get(a).getCorp_code());
				pstmt.setString(3, td001.get(a).getCorp_name());
				pstmt.setString(4, td001.get(a).getDept_gbn());
				pstmt.setString(5, td001.get(a).getTeam_code());
				pstmt.setString(6, td001.get(a).getTeam_name());
				pstmt.setDouble(7, td001.get(a).getGoal_amt_d());
				pstmt.setDouble(8, td001.get(a).getSal_amt_d());
				pstmt.setDouble(9, td001.get(a).getSucc_d());
				pstmt.setDouble(10, td001.get(a).getSal_lst_d());
				pstmt.setDouble(11, td001.get(a).getIncr_d());
				pstmt.setDouble(12, td001.get(a).getGoal_amt_m());
				pstmt.setDouble(13, td001.get(a).getSal_amt_m());
				pstmt.setDouble(14, td001.get(a).getSucc_m());
				pstmt.setDouble(15, td001.get(a).getSal_lst_m());
				pstmt.setDouble(16, td001.get(a).getIncr_m());
				pstmt.setDouble(17, td001.get(a).getDiff_amt_m());
				pstmt.setDouble(18, td001.get(a).getGoal_amt_y());
				pstmt.setDouble(19, td001.get(a).getSal_amt_y());
				pstmt.setDouble(20, td001.get(a).getSucc_y());
				pstmt.setDouble(21, td001.get(a).getSal_lst_y());
				pstmt.setDouble(22, td001.get(a).getIncr_y());
				pstmt.setDouble(23, td001.get(a).getDiff_amt_y());
				
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






















