package js.cinemas.signin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ManagerDAO {

	public boolean managerLogin(String IDInput, String PassInput) throws Exception { // 매니저 로그인
		String managerId = null;
		String managerPw = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.22:1521/xe", "CINEMAPROJECT",
				"java");

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT MANAGER_PW");
		builder.append("  FROM MANAGER");
		builder.append(" WHERE MANAGER_ID = ?");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, IDInput);

		List<ManagerVO> list = new ArrayList<ManagerVO>();
		ResultSet resultSet = statement.executeQuery();
		ManagerVO vo = null;

		if (resultSet.next()) {
			managerId = IDInput;
			managerPw = resultSet.getString("MANAGER_PW");
			vo = new ManagerVO(managerId, managerPw);
			resultSet.close();
			statement.close();
			connection.close();
		} else {
			System.out.println("관리자 아이디 정보 없음");
			resultSet.close();
			statement.close();
			connection.close();
			return false;
		}

		if (managerPw.equals(PassInput)) {
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
}
