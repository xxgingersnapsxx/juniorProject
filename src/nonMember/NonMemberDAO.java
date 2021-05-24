package nonMember;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class NonMemberDAO {

	public void insertNewNonMem(NonMemberVO vo) throws Exception { // 비회원 임의 아이디 저장
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");

		StringBuilder builder = new StringBuilder();
		// FIXME : 왜 concat하면 안들어가!!!

		builder.append("INSERT INTO NON_MEMBER(N_MEM_ID) ");
		builder.append("             VALUES ");
		// builder.append(" (TO_CHAR(SYSDATE) || ? )");
		builder.append(" (TO_CHAR(SYSDATE) || ? )");
		// builder.append("(TO_CHAR(SYSDATE))");
		// builder.append("(CONCAT(TO_CHAR(SYSDATE), 'b')");
		// builder.append("'" + vo.getnMemId() + "'");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		String newRanID = null;
		// // // TODO math.random 으로 랜덤아이디 생성
		newRanID = vo.getnMemId();
		statement.setString(1, newRanID);

		int result = statement.executeUpdate();

		if (result > 0) {
			System.out.println("등록 완료!");
		} else {
			System.out.println("등록 실패!");
		}

		statement.close();
		connection.close();

	}
}
