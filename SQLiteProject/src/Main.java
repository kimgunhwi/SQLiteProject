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
			
			//��������ȸ
			System.out.println("---��������ȸ---");
			Statement stat1 = con.createStatement();
			String sql1 = "select * from g_artists";
			ResultSet rs1 = stat1.executeQuery(sql1);
			while(rs1.next()) {
				String id = rs1.getString("id");
				String name = rs1.getString("name");
				System.out.println(id + " " + name);
			}
			stat1.close();
			
			//�������߰�
			System.out.println("\n---�������߰�---");
			Statement stat2 = con.createStatement();
			String sql2 = "insert into g_artists (name, a_type, a_year, debut, regdate)"
						+ " values ('����', '���ھ��̵�', '2020���', '2020��', datetime('now', 'localtime'));";
			int cnt = stat2.executeUpdate(sql2);
			if(cnt>0) {System.out.println("���ο� �����Ͱ� �߰��Ǿ����ϴ�!");}
			else {System.out.println("[Error] ������ �߰� ����!");}
			stat2.close();
			
			//�������߰� - test
			System.out.println("\n---�������߰�---");
			Statement statTest = con.createStatement();
			String sqlTest = "insert into g_artists (name, a_type, a_year, debut, regdate)"
						+ " values ('test', 'test', '2021', '2021', datetime('now', 'localtime'));";
			int cntTest = statTest.executeUpdate(sqlTest);
			if(cntTest>0) {System.out.println("���ο� �����Ͱ� �߰��Ǿ����ϴ�!");}
			else {System.out.println("[Error] ������ �߰� ����!");}
			statTest.close();
			
			//�����ͼ���
			System.out.println("\n---�����ͼ���---");
			Statement stat3 = con.createStatement();
			String sql3 = "update g_artists set debut = '2008��' "
						+ "where id=1 ;";
			int cnt3 = stat3.executeUpdate(sql3);
			if(cnt3>0) {System.out.println("�����Ͱ� �����Ǿ����ϴ�!");}
			else {System.out.println("[Error] ������ ���� ����!");}
			stat3.close();
			
			//�����ͻ���
			System.out.println("\n---�����ͻ���---");
			Statement stat4 = con.createStatement();
			String sql4 = "delete from g_artists where name='test' ;";
			int cnt4 = stat4.executeUpdate(sql4);
			if(cnt4>0) {System.out.println("�����Ͱ� �����Ǿ����ϴ�!");}
			else {System.out.println("[Error] ������ ���� ����!");}
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
