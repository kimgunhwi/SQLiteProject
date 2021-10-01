import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
	public static void main(String[] args) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			
			String dbFile = "myfirst.db";
			con = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			
			//데이터조회
			System.out.println("---데이터조회---");
			Statement stat1 = con.createStatement();
			String sql1 = "select * from g_artists";
			ResultSet rs1 = stat1.executeQuery(sql1);
			while(rs1.next()) {
				String id = rs1.getString("id");
				String name = rs1.getString("name");
				System.out.println(id + " " + name);
			}
			stat1.close();
			
			//데이터추가
			System.out.println("\n---데이터추가---");
			Statement stat2 = con.createStatement();
			String sql2 = "insert into g_artists (name, a_type, a_year, debut, regdate)"
						+ " values ('있지', '여자아이돌', '2020년대', '2020년', datetime('now', 'localtime'));";
			int cnt = stat2.executeUpdate(sql2);
			if(cnt>0) {System.out.println("새로운 데이터가 추가되었습니다!");}
			else {System.out.println("[Error] 데이터 추가 오류!");}
			stat2.close();
			
			//데이터추가 - test
			System.out.println("\n---데이터추가---");
			Statement statTest = con.createStatement();
			String sqlTest = "insert into g_artists (name, a_type, a_year, debut, regdate)"
						+ " values ('test', 'test', '2021', '2021', datetime('now', 'localtime'));";
			int cntTest = statTest.executeUpdate(sqlTest);
			if(cntTest>0) {System.out.println("새로운 데이터가 추가되었습니다!");}
			else {System.out.println("[Error] 데이터 추가 오류!");}
			statTest.close();
			
			//데이터수정
			System.out.println("\n---데이터수정---");
			Statement stat3 = con.createStatement();
			String sql3 = "update g_artists set debut = '2008년' "
						+ "where id=1 ;";
			int cnt3 = stat3.executeUpdate(sql3);
			if(cnt3>0) {System.out.println("데이터가 수정되었습니다!");}
			else {System.out.println("[Error] 데이터 수정 오류!");}
			stat3.close();
			
			//데이터삭제
			System.out.println("\n---데이터삭제---");
			Statement stat4 = con.createStatement();
			String sql4 = "delete from g_artists where name='test' ;";
			int cnt4 = stat4.executeUpdate(sql4);
			if(cnt4>0) {System.out.println("데이터가 삭제되었습니다!");}
			else {System.out.println("[Error] 데이터 삭제 오류!");}
			stat4.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con!=null) {
				try {con.close();}
				catch(Exception e) {}
			}
		}
	}

}
