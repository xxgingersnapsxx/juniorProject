package js.cinemas.signin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

public class NonMemberDAO {

	public void insertNewNonMem(String mobile, String pass) throws Exception { // 비회원 임의 아이디 저장
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.22:1521/xe", "CINEMAPROJECT",
				"java");

		StringBuilder builder = new StringBuilder();

		builder.append("INSERT INTO NON_MEMBER(N_MEM_ID, N_MEM_MOBILE, N_MEM_PW) ");
		builder.append("             VALUES ");
		builder.append(" (TO_CHAR(SYSDATE, 'YYYYMMDDHHMISS') || ?, ?, ? )");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		
		// 랜덤 아이디 생성
		NonMemberDAO nonMemberDAO = new NonMemberDAO();
		String newRanID = nonMemberDAO.ranIdgen();
		
		
		statement.setString(1, newRanID);
		statement.setString(2, mobile);
		statement.setString(3, pass);

		int result = statement.executeUpdate();

		if (result > 0) {
			System.out.println("등록 완료!");
		} else {
			System.out.println("등록 실패!");
		}

		statement.close();
		connection.close();

	}
	
	public String ranIdgen() throws Exception { // randomId 만드는 메소드
		Random random = new Random();
		int num = random.nextInt(9999 - 1000) + 1000; // 1000부터 9999까지
		int a = num / 1000;
		int b = (num % 1000) / 100;
		int c = ((num % 1000) % 100) / 10;
		int d = (((num % 1000) % 100) % 10);
		char e = (char) (a + 65);
		char f = (char) (b + 65);
		char g = (char) (c + 65);
		char h = (char) (d + 65);
		
		String newRanId = String.valueOf(e) + String.valueOf(f) + String.valueOf(g) + String.valueOf(h);
		return newRanId;
	}
	
	
}
