package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.TD003_bean;

public class TD_dao_eik {

	public TD003_bean  Selecteik_sik(Connection conn,String ymd) {
		
		Connection con = conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String ym = ymd.substring(0,6);
		TD003_bean bean = new TD003_bean();
			try {
				String sql = 
						"WITH TOTAL AS ( "+
								"    SELECT CORP_CODE            AS CORP_CODE, "+
								"           BONBU_CODE           AS BONBU_CODE, "+
								"           TEAM_CODE            AS TEAM_CODE, "+
								"           SUM(IK_AMT2)         AS T_IK_AMT2, "+
								"           SUM(N_IK_AMT2)       AS T_N_IK_AMT2 "+
								"      FROM "+
								"          ( "+
								"           SELECT C.CORP_CODE                       AS CORP_CODE, "+
								"                  C.BONBU_CODE                      AS BONBU_CODE, "+
								"                  C.TEAM_CODE                       AS TEAM_CODE, "+
								"                  0                                 AS IK_AMT2, "+
								"                  SUM(A.IK_AMT2)                    AS N_IK_AMT2 "+
								"             FROM iV070 A, "+
								"                  CD060 B, "+
								"                  V_CD020_000 C "+
								"            WHERE A.STR_CODE = B.STR_CODE "+
								"              AND A.CLASS_CODE = B.CLASS_CODE "+
								"              AND B.DEPT_CODE  = C.PC_CODE "+
								"              AND C.CORP_CODE  = 2000 "+
								"              AND C.BONBU_CODE = 2600 "+
								"              AND C.TEAM_CODE  LIKE 2620 ||'%' "+
								"              AND B.GRE_GB <= DECODE(0,0,'5',1,'4') "+
								"              AND A.MONTH = ? "+
								"            GROUP BY C.CORP_CODE, C.BONBU_CODE ,TEAM_CODE "+
								"           UNION ALL "+
								"            SELECT C.CORP_CODE                       AS CORP_CODE, "+
								"                   C.BONBU_CODE                      AS BONBU_CODE, "+
								"                   C.TEAM_CODE                       AS TEAM_CODE, "+
								"                   SUM(A.IK_AMT2)                    AS IK_AMT2, "+
								"                   0                                 AS N_IK_AMT2 "+
								"              FROM IV070 A, "+
								"                   CD060 B, "+
								"                   V_CD020_000 C "+
								"             WHERE A.STR_CODE = B.STR_CODE "+
								"               AND A.CLASS_CODE = B.CLASS_CODE "+
								"               AND B.DEPT_CODE = C.PC_CODE "+
								"               AND C.CORP_CODE = 2000 "+
								"               AND C.BONBU_CODE = 2600 "+
								"               AND C.TEAM_CODE  LIKE 2620 ||'%' "+
								"               AND B.GRE_GB <= DECODE(0,0,'5',1,'4') "+
								"               AND A.MONTH  = ? "+
								"             GROUP BY C.CORP_CODE, C.BONBU_CODE, C.TEAM_CODE "+
								"       ) "+
								"       GROUP BY CORP_CODE, BONBU_CODE, TEAM_CODE "+
								") "+
								"SELECT A.GOAL_AMT                                                             AS GOAL_AMT, "+
								"       ROUND(A.SA_AMT6 / 1000, 0)                                             AS SA_AMT6, "+
								"       DECODE(A.GOAL_AMT,0,0,ROUND(A.SA_AMT6/1000/A.GOAL_AMT*100,1))          AS DALSUNG, "+
								"       ROUND(A.D_SA_AMT6  / 1000, 0)                                          AS D_SA_AMT6, "+
								"       DECODE(A.D_SA_AMT6,0,0,ROUND((A.SA_AMT6/A.D_SA_AMT6*100)-100,1))       AS SINJANG, "+
								"       ROUND(A.IK_AMT2 / 1000,0)                                              AS IK_AMT2, "+
								"       ROUND(A.D_IK_AMT2 /1000, 0)                                            AS D_IK_AMT2, "+
								"       DECODE(A.D_IK_AMT2,0,0,ROUND((A.IK_AMT2/A.D_IK_AMT2*100)-100,1))       AS IK_SINJANG, "+
								"       DECODE(T.T_IK_AMT2,0,0,ROUND(A.IK_AMT2/T.T_IK_AMT2*100,1))             AS IK_GUSUNG, "+
								"       DECODE(A.SA_AMT6,0,0,ROUND(A.IK_AMT2/A.SA_AMT6*100,2))                 AS IK_RATE, "+
								"       DECODE(A.D_SA_AMT6,0,0,ROUND(A.D_IK_AMT2/A.D_SA_AMT6*100,2))           AS D_IK_RATE, "+
								"       DECODE(A.SA_AMT6,0,0,ROUND(A.IK_AMT2/A.SA_AMT6*100,2)) "+
								"        - DECODE(A.D_SA_AMT6,0,0,ROUND(A.D_IK_AMT2/A.D_SA_AMT6*100,2))        AS IK_RATE_SINJANG, "+
								"       ROUND(A.SA_AMT4 /1000,0)                                               AS SA_AMT4, "+
								"       DECODE(A.SA_AMT,0,0,ROUND(A.SA_AMT4/A.SA_AMT*100,1))                   AS DC_RATE, "+
								"       ROUND(A.D_SA_AMT4 /1000,0)                                             AS D_SA_AMT4, "+
								"       DECODE(A.D_SA_AMT,0,0,ROUND(A.D_SA_AMT4/A.D_SA_AMT*100,1))             AS D_DC_RATE, "+
								"       ROUND((A.SA_AMT4 - A.D_SA_AMT4) / 1000,0)                              AS DC_SINJANG, "+
								"       ROUND(A.N_GOAL_AMT / 1000, 0)                                          AS N_GOAL_AMT, "+
								"       ROUND(A.N_SA_AMT6 / 1000, 0)                                           AS N_SA_AMT6, "+
								"       DECODE(A.N_GOAL_AMT,0,0,ROUND(A.N_SA_AMT6/1000/A.N_GOAL_AMT*100,1))    AS N_DALSUNG, "+
								"       ROUND(A.D_N_SA_AMT6 / 1000, 0)                                         AS D_N_SA_AMT6, "+
								"       DECODE(A.D_N_SA_AMT6,0,0,ROUND((A.N_SA_AMT6/A.D_N_SA_AMT6*100)-100,1)) AS N_SINJANG, "+
								"       ROUND(A.N_IK_AMT2 / 1000, 0)                                           AS N_IK_AMT2, "+
								"       ROUND(A.D_N_IK_AMT2 / 1000, 0)                                         AS D_N_IK_AMT2, "+
								"       DECODE(A.D_N_IK_AMT2,0,0,ROUND((A.N_IK_AMT2/A.D_N_IK_AMT2*100)-100,1)) AS N_IK_SINJANG, "+
								"       DECODE(T.T_N_IK_AMT2,0,0,ROUND(A.N_IK_AMT2/T.T_N_IK_AMT2*100,1))       AS N_IK_GUSUNG, "+
								"       DECODE(A.N_SA_AMT6,0,0,ROUND(A.N_IK_AMT2/A.N_SA_AMT6*100,2))           AS N_IK_RATE, "+
								"       DECODE(A.D_N_SA_AMT6,0,0,ROUND(A.D_N_IK_AMT2/A.D_N_SA_AMT6*100,2))     AS D_N_IK_RATE, "+
								"       DECODE(A.N_SA_AMT6,0,0,ROUND(A.N_IK_AMT2/A.N_SA_AMT6*100,2)) "+
								"        - DECODE(A.D_N_SA_AMT6,0,0,ROUND(A.D_N_IK_AMT2/A.D_N_SA_AMT6*100,2))  AS N_IK_RATE_SINJANG, "+
								"       ROUND(A.N_SA_AMT4 / 1000, 0)                                           AS N_SA_AMT4, "+
								"       DECODE(A.N_SA_AMT,0,0,ROUND(A.N_SA_AMT4/A.N_SA_AMT*100,1))             AS N_DC_RATE, "+
								"       ROUND(A.D_SA_AMT4 / 1000, 0)                                           AS D_N_SA_AMT4, "+
								"       DECODE(A.D_N_SA_AMT,0,0,ROUND(A.D_N_SA_AMT4/A.D_N_SA_AMT*100,1))       AS D_N_DC_RATE, "+
								"       ROUND((A.N_SA_AMT4 - A.D_N_SA_AMT4) / 1000,0)                          AS N_DC_SINJANG, "+
								"       ROUND(T.T_IK_AMT2 / 1000, 0)                                           AS T_IK_AMT2, "+
								"       ROUND(T.T_N_IK_AMT2 / 1000, 0)                                         AS T_N_IK_AMT2, "+
								"       A.CORP_CODE                                                            AS CORP_CODE, "+
								"       A.BONBU_CODE                                                           AS BONBU_CODE, "+
								"       A.TEAM_CODE                                                            AS TEAM_CODE        "+
								"  FROM "+
								"     ( "+
								"        SELECT SUM(GOAL_AMT)                             AS GOAL_AMT, "+
								"               SUM(SA_AMT6)                              AS SA_AMT6, "+
								"               SUM(D_SA_AMT6)                            AS D_SA_AMT6, "+
								"               SUM(IK_AMT2)                              AS IK_AMT2, "+
								"               SUM(D_IK_AMT2)                            AS D_IK_AMT2, "+
								"               SUM(SA_AMT4)                              AS SA_AMT4, "+
								"               SUM(D_SA_AMT4)                            AS D_SA_AMT4, "+
								"               SUM(N_GOAL_AMT)                           AS N_GOAL_AMT, "+
								"               SUM(N_SA_AMT6)                            AS N_SA_AMT6, "+
								"               SUM(D_N_SA_AMT6)                          AS D_N_SA_AMT6, "+
								"               SUM(N_IK_AMT2)                            AS N_IK_AMT2, "+
								"               SUM(D_N_IK_AMT2)                          AS D_N_IK_AMT2, "+
								"               SUM(N_SA_AMT4)                            AS N_SA_AMT4, "+
								"               SUM(D_N_SA_AMT4)                          AS D_N_SA_AMT4, "+
								"               SUM(SA_AMT6)+SUM(SA_AMT4)                 AS SA_AMT, "+
								"               SUM(D_SA_AMT6)+SUM(D_SA_AMT4)             AS D_SA_AMT, "+
								"               SUM(N_SA_AMT6)+SUM(N_SA_AMT4)             AS N_SA_AMT, "+
								"               SUM(D_N_SA_AMT6)+SUM(D_N_SA_AMT4)         AS D_N_SA_AMT, "+
								"               CORP_CODE                                 AS CORP_CODE, "+
								"               BONBU_CODE                                AS BONBU_CODE, "+
								"               TEAM_CODE                                 AS TEAM_CODE "+
								"          FROM "+
								"             ( "+
								"              SELECT C.CORP_CODE                                                   AS CORP_CODE, "+
								"                     C.BONBU_CODE                                                  AS BONBU_CODE, "+
								"                     C.TEAM_CODE                                                   AS TEAM_CODE, "+
								"                     SUM(DECODE(SUBSTR(A.GOAL_YMD,1,6),?,A.GOAL_AMT,0))  AS GOAL_AMT, "+
								"                     0                                                             AS SA_AMT6, "+
								"                     0                                                             AS IK_AMT2, "+
								"                     0                                                             AS SA_AMT4, "+
								"                     0                                                             AS D_SA_AMT6, "+
								"                     0                                                             AS D_IK_AMT2, "+
								"                     0                                                             AS D_SA_AMT4, "+
								"                     SUM(A.GOAL_AMT)                                               AS N_GOAL_AMT, "+
								"                     0                                                             AS N_SA_AMT6, "+
								"                     0                                                             AS N_IK_AMT2, "+
								"                     0                                                             AS N_SA_AMT4, "+
								"                     0                                                             AS D_N_SA_AMT6, "+
								"                     0                                                             AS D_N_IK_AMT2, "+
								"                     0                                                             AS D_N_SA_AMT4 "+
								"                FROM CD201 A, "+
								"                     CD060 B, "+
								"                     V_CD020_000 C "+
								"               WHERE A.STR_CODE   = B.STR_CODE "+
								"                 AND A.CLASS_CODE = B.CLASS_CODE "+
								"                 AND B.DEPT_CODE  = C.PC_CODE "+
								"                 AND C.CORP_CODE  = 2000 "+
								"                 AND C.BONBU_CODE = 2600 "+
								"                 AND C.TEAM_CODE  LIKE 2620 ||'%' "+
								"                 AND B.GRE_GB     <= DECODE(0,0,'5',1,'4') "+
								"                 AND A.GOAL_YMD   BETWEEN ?||'01' AND ?||'31' "+
								"               GROUP BY C.CORP_CODE, C.BONBU_CODE, C.TEAM_CODE "+
								"            UNION ALL "+
								"              SELECT C.CORP_CODE                                   AS CORP_CODE, "+
								"                     C.BONBU_CODE                                  AS BONBU_CODE, "+
								"                     C.TEAM_CODE                                   AS TEAM_CODE, "+
								"                     0                                             AS GOAL_AMT, "+
								"                     SUM(DECODE(A.MONTH,?,A.SA_AMT6,0))  AS SA_AMT6, "+
								"                     SUM(DECODE(A.MONTH,?,A.IK_AMT2,0))  AS IK_AMT2, "+
								"                     SUM(DECODE(A.MONTH,?,A.SA_AMT4,0))  AS SA_AMT4, "+
								"                     0                                             AS D_SA_AMT6, "+
								"                     0                                             AS D_IK_AMT2, "+
								"                     0                                             AS D_SA_AMT4, "+
								"                     0                                             AS N_GOAL_AMT, "+
								"                     SUM(A.SA_AMT6)                                AS N_SA_AMT6, "+
								"                     SUM(A.IK_AMT2)                                AS N_IK_AMT2, "+
								"                     SUM(A.SA_AMT4)                                AS N_SA_AMT4, "+
								"                     0                                             AS D_N_SA_AMT6, "+
								"                     0                                             AS D_N_IK_AMT2, "+
								"                     0                                             AS D_N_SA_AMT4 "+
								"                FROM IV070 A, "+
								"                     CD060 B, "+
								"                     V_CD020_000 C "+
								"               WHERE A.STR_CODE   = B.STR_CODE "+
								"                 AND A.CLASS_CODE = B.CLASS_CODE "+
								"                 AND B.DEPT_CODE  = C.PC_CODE "+
								"                 AND C.CORP_CODE  = 2000 "+
								"                 AND C.BONBU_CODE = 2600 "+
								"                 AND C.TEAM_CODE  LIKE 2620||'%' "+
								"                 AND B.GRE_GB     <= DECODE(0,0,'5',1,'4') "+
								"                 AND A.MONTH      = ? "+
								"               GROUP BY C.CORP_CODE, C.BONBU_CODE, C.TEAM_CODE "+
								"            UNION ALL "+
								"              SELECT C.CORP_CODE                                    AS CORP_CODE, "+
								"                     C.BONBU_CODE                                   AS BONBU_CODE, "+
								"                     C.TEAM_CODE                                    AS TEAM_CODE, "+
								"                     0                                              AS GOAL_AMT, "+
								"                     0                                              AS SA_AMT6, "+
								"                     0                                              AS IK_AMT2, "+
								"                     0                                              AS SA_AMT4, "+
								"                     SUM(DECODE(A.MONTH,SUBSTR(?,1,4)-1||SUBSTR(?,5,2),A.SA_AMT6,0)) AS D_SA_AMT6, "+
								"                     SUM(DECODE(A.MONTH,SUBSTR(?,1,4)-1||SUBSTR(?,5,2),A.IK_AMT2,0)) AS D_IK_AMT2, "+
								"                     SUM(DECODE(A.MONTH,SUBSTR(?,1,4)-1||SUBSTR(?,5,2),A.SA_AMT4,0)) AS D_SA_AMT4, "+
								"                     0                                              AS N_GOAL_AMT, "+
								"                     0                                              AS N_SA_AMT6, "+
								"                     0                                              AS N_IK_AMT2, "+
								"                     0                                              AS N_SA_AMT4, "+
								"                     SUM(A.SA_AMT6)                                 AS D_N_SA_AMT6, "+
								"                     SUM(A.IK_AMT2)                                 AS D_N_IK_AMT2, "+
								"                     SUM(A.SA_AMT4)                                 AS D_N_SA_AMT4 "+
								"                FROM IV070 A, "+
								"                     CD060 B, "+
								"                     V_CD020_000 C "+
								"               WHERE A.STR_CODE   = B.STR_CODE "+
								"                 AND A.CLASS_CODE = B.CLASS_CODE "+
								"                 AND B.DEPT_CODE  = C.PC_CODE "+
								"                 AND C.CORP_CODE  = 2000 "+
								"                 AND C.BONBU_CODE = 2600 "+
								"                 AND C.TEAM_CODE  LIKE 2620||'%' "+
								"                 AND B.GRE_GB     <= DECODE(0,0,'5',1,'4') "+
								"                 AND A.MONTH      BETWEEN SUBSTR(?,1,4)-1||SUBSTR(?,5,2) AND SUBSTR(?,1,4)-1||SUBSTR(?,5,2) "+
								"               GROUP BY C.CORP_CODE, C.BONBU_CODE, C.TEAM_CODE "+
								"             ) "+
								"             GROUP BY CORP_CODE, BONBU_CODE,TEAM_CODE "+
								"        ) A, TOTAL T "+
								" WHERE A.CORP_CODE  = T.CORP_CODE(+) "+
								"   AND A.BONBU_CODE = T.BONBU_CODE(+) "+
								"   AND A.TEAM_CODE  = T.TEAM_CODE(+) "+
								" ORDER BY A.TEAM_CODE "+
								"";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,ym);
				pstmt.setString(2,ym);
				pstmt.setString(3,ym);
				pstmt.setString(4,ym);
				pstmt.setString(5,ym);
				pstmt.setString(6,ym);
				pstmt.setString(7,ym);
				pstmt.setString(8,ym);
				pstmt.setString(9,ym);
				pstmt.setString(10,ym);
				pstmt.setString(11,ym);
				pstmt.setString(12,ym);
				pstmt.setString(13,ym);
				pstmt.setString(14,ym);
				pstmt.setString(15,ym);
				pstmt.setString(16,ym);
				pstmt.setString(17,ym);
				pstmt.setString(18,ym);
				pstmt.setString(19,ym);
				rs = pstmt.executeQuery();
				if(rs.next())
				{
					bean.setYmd(ymd);
					bean.setIk_rate_sik(rs.getDouble("IK_RATE"));
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
	
	public TD003_bean Selecteik_etc(Connection conn, TD003_bean td003_2, String ymd, String d_d_ymd)
	{
		Connection con = conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String startymd = ymd.substring(0,6) + "01";
		String d_d_stratymd = d_d_ymd.substring(0,6) + "01"; 
		String team[] = {"2630","2640","2650","3330","3410"};
		String targetymd;
		String start_targetymd;
		for(int a = 0; a < 2; a++)
		{
			if(a == 0)
			{
				targetymd = ymd;
				start_targetymd = startymd;
			}
			else
			{
				targetymd = d_d_ymd;
				start_targetymd = d_d_stratymd;
			}
		for(int b = 0; b < team.length; b++)
		{
			try {
				String sql = 
						"SELECT  "+						
								" E.TEAM_CODE	AS TEAM_CODE, "+ 
								" COUNT(E.IK_RATE)	AS COUNTDATA, "+
								" SUM(E.IK_RATE)	AS SUMDATA, "+
								" ROUND(AVG(E.IK_RATE),1)	 AS RATE "+
								" FROM " +
								" ( "+
								" SELECT "+
								" A.PC_CODE||' '||A.PC_NAME                                                                                                         AS PC_CODE, "+
								" A.VEN_CODE||' '||A.VEN_NAME                                                                                                       AS VEN_CODE, "+
								" A.VEN_NAME                                                                                                                         AS VEN_NAME, "+
								" A.CLASS_CODE||' '||A.CLASS_NAME                                                                                                   AS CLASS_CODE, "+
								" A.PROFIT_FLAG                                                                                                                       AS PROFIT_GB,  "+
								" A.CLASS_NAME                                                                                                                      AS CLASS_NAME, "+
								" SUM(A.SAL_AMT)                                                                                                                    AS SAL_AMT, "+
								" SUM(A.DC_AMT)                                                                                                                   AS SUBDC_AMT, "+
								" SUM(A.SAL_AMT)-SUM(A.DC_AMT)                                                                                                    AS AMT, "+
								" DECODE(SUM(A.SAL_AMT),0,0,ROUND(SUM(A.PROFIT_AMT)/SUM(A.SAL_AMT)*100,1))                                                           AS PROFIT_RATE, "+
								" ROUND(SUM(A.PROFIT_AMT),0)                                                                                                         AS PROFIT, "+
								" ROUND((SUM(A.PROFIT_AMT)-SUM(A.DC_AMT))/DECODE(A.TAX_GB,0,1.1,1),0)                                                               AS IK_AMT, "+
								" DECODE(SUM(A.SAL_AMT)-SUM(A.DC_AMT),0,0,ROUND((SUM(A.PROFIT_AMT)-SUM(A.DC_AMT))/(SUM(SAL_AMT)-SUM(DC_AMT))*100,1))        AS IK_RATE, "+
								" A.TEAM_CODE||' '||A.TEAM_NAME                                                                                                      AS TEAM_CODE, "+
								" A.TAX_GB                                                                                                                           AS TAX_GB "+
								" FROM "+
								" ( "+
								" SELECT  C.VEN_CODE                    AS VEN_CODE, "+
								"        C.VEN_NAME                    AS VEN_NAME, "+
								"        A.CLASS_CODE                   AS CLASS_CODE, "+
								"        B.CLASS_NAME                   AS CLASS_NAME, "+
								"        D.TEAM_CODE                    AS TEAM_CODE, "+
								"        D.PC_CODE                    AS PC_CODE, "+
								"        SUM(A.SAL_AMT)                AS SAL_AMT, "+
								"        SUM(A.DC_AMT)            AS DC_AMT, "+
								"        SUM(A.SAL_AMT)*A.PROFIT_RATE/100  AS PROFIT_AMT, "+
								"        A.PROFIT_RATE                AS PROFIT_RATE, "+
								"        A.PROFIT_FLAG                AS PROFIT_FLAG, "+
								"        D.PC_NAME                    AS PC_NAME, "+
								"        D.TEAM_NAME                  AS TEAM_NAME, "+
								"        B.TAX_GB                     AS TAX_GB "+
								" FROM SL011 A, "+
								"     CD060 B, "+
								"     CD050 C, "+
								"     V_CD020_000 D "+
								" WHERE A.STR_CODE = B.STR_CODE "+
								" AND   A.CLASS_CODE = B.CLASS_CODE "+
								" AND      B.VEN_CODE = C.VEN_CODE "+
								" AND      A.STR_CODE = D.STR_CODE "+
								" AND      B.STR_CODE = D.STR_CODE "+
								" AND      B.DEPT_CODE = D.PC_CODE "+
								" AND      D.TEAM_CODE = ? "+
								" AND      A.SALE_YMD BETWEEN ? AND ? "+
								" GROUP BY C.VEN_CODE,C.VEN_NAME,A.CLASS_CODE,B.CLASS_NAME,A.PROFIT_RATE,A.PROFIT_FLAG, D.TEAM_CODE, D.PC_CODE, D.PC_NAME, D.TEAM_NAME, B.TAX_GB "+
								" ) A "+
								" GROUP BY A.VEN_CODE,A.VEN_NAME,A.CLASS_CODE,A.CLASS_NAME, A.TEAM_CODE, A.PC_CODE, A.PC_NAME, A.TEAM_NAME, A.PROFIT_FLAG,A.TAX_GB "+
								" ) E "+
								" GROUP BY E.TEAM_CODE ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,team[b]);
				pstmt.setString(2,start_targetymd);
				pstmt.setString(3,targetymd);
				rs = pstmt.executeQuery();
				if(rs.next())
				{
					if(a == 0)
					{
						switch (b) {
						case 0:
							td003_2.setIk_rate_jab(rs.getDouble("RATE"));
							break;
						case 1:
							td003_2.setIk_rate_wo(rs.getDouble("RATE"));
							break;
						case 2:
							td003_2.setIk_rate_man(rs.getDouble("RATE"));
							break;
						case 3:
							td003_2.setIk_rate_ncw(rs.getDouble("RATE"));
							break;
						case 4:
							td003_2.setIk_rate_gum(rs.getDouble("RATE"));
							break;
							
						default:
							break;
						}
					}
					else
					{
						switch (b) {
						case 0:
							td003_2.setD_ik_rate_jab(rs.getDouble("RATE"));
							break;
						case 1:
							td003_2.setD_ik_rate_wo(rs.getDouble("RATE"));
							break;
						case 2:
							td003_2.setD_ik_rate_man(rs.getDouble("RATE"));
							break;
						case 3:
							td003_2.setD_ik_rate_ncw(rs.getDouble("RATE"));
							break;
						case 4:
							td003_2.setD_ik_rate_gum(rs.getDouble("RATE"));
							break;
							
						default:
							break;
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		}
		return td003_2;
	}
	
	public int Inserteik(Connection conn,TD003_bean td003_3)
	{
		int result = 1;
		Connection con = conn;
		PreparedStatement pstmt = null;
		
		try {
			
				String sql = "insert into TD003 values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, td003_3.getYmd());
				pstmt.setDouble(2, td003_3.getIk_rate_sik());
				pstmt.setDouble(3, td003_3.getIk_rate_jab());
				pstmt.setDouble(4, td003_3.getIk_rate_wo());
				pstmt.setDouble(5, td003_3.getIk_rate_man());
				pstmt.setDouble(6, td003_3.getIk_rate_ncw());
				pstmt.setDouble(7, td003_3.getD_ik_rate_gum());
				pstmt.setDouble(8, td003_3.getD_ik_rate_sik());
				pstmt.setDouble(9, td003_3.getD_ik_rate_jab());
				pstmt.setDouble(10, td003_3.getD_ik_rate_wo());
				pstmt.setDouble(11, td003_3.getD_ik_rate_man());
				pstmt.setDouble(12, td003_3.getD_ik_rate_ncw());
				pstmt.setDouble(13, td003_3.getD_ik_rate_gum());
				
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






















