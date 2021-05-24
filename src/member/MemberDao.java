package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

	public boolean memberLogin(String memIDInput, String memPassInput) throws Exception {// 로그인 결과 true/false 반환
		String memPass = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT MEM_PASS");
		builder.append("  FROM MEMBER");
		builder.append(" WHERE MEM_ID = ?");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, memIDInput);

		List<MemberVO> list = new ArrayList<MemberVO>();
		ResultSet resultSet = statement.executeQuery(); 
		MemberVO vo = null;

		if (resultSet.next()) {
			memPass = resultSet.getString("MEM_PASS");
			vo = new MemberVO(memPass);
			resultSet.close();
			statement.close();
			connection.close();
		} else {
			System.out.println("회원 정보 없음");
			resultSet.close();
			statement.close();
			connection.close();
			return false;
		}

		if (memPass.equals(memPassInput)) {
			System.out.println("로그인 성공");
			return true;
		} else {
			System.out.println("비밀번호 오류");
			resultSet.close();
			statement.close();
			connection.close();
			return false;
		}
	}

	public MemberVO selectMemberMileage(String memID) throws Exception { // 회원 마일리지 조회
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT MEM_ID");
		builder.append("     , MEM_NAME");
		builder.append("     , MEM_MILEAGE");
		builder.append("  FROM MEMBER");
		builder.append(" WHERE MEM_ID = ?");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, memID);

		List<MemberVO> list = new ArrayList<MemberVO>();
		ResultSet resultSet = statement.executeQuery();
		MemberVO vo = null;

		if (resultSet.next()) {
			String memId = resultSet.getString("MEM_ID");
			String memName = resultSet.getString("MEM_NAME");
			int memMileage = resultSet.getInt("MEM_MILEAGE");

			vo = new MemberVO(memId, memName, memMileage);
		}
		resultSet.close();
		statement.close();
		connection.close();

		return vo;
	}
}
