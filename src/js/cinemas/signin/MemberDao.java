package js.cinemas.signin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

	public boolean memberLogin(String memIDInput, String memPassInput) throws Exception {// 로그인 결과 true/false 반환
		String memPass = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.22:1521/xe", "CINEMAPROJECT",
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

	public List<MemberVO> selectAllMemberList() throws Exception { // 전체 회원 목록 조회
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.22:1521/xe", "CINEMAPROJECT",
				"java");
		Statement statement = connection.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append(
				"SELECT MEM_ID, MEM_PASS, MEM_NAME, MEM_BIR, MEM_ADD1, MEM_ADD2, MEM_MOBILE, MEM_MILEAGE, MEM_DELETE, LOCATION_ID");
		builder.append("              FROM MEMBER");
		List<MemberVO> list = new ArrayList<MemberVO>();
		ResultSet resultSet = statement.executeQuery(builder.toString());
		while (resultSet.next()) {
			String memId = resultSet.getString("MEM_ID");
			String memPass = resultSet.getString("MEM_PASS");
			String memName = resultSet.getString("MEM_NAME");
			LocalDate memBir = resultSet.getDate("MEM_BIR").toLocalDate();
			String memAdd1 = resultSet.getString("MEM_ADD1");
			String memAdd2 = resultSet.getString("MEM_ADD2");
			String memMobile = resultSet.getString("MEM_MOBILE");
			int memMileage = resultSet.getInt("MEM_MILEAGE");
			String memDelete = resultSet.getString("MEM_DELETE");
			String locationId = resultSet.getString("LOCATION_ID");

			list.add(new MemberVO(memId, memPass, memName, memBir, memAdd1, memAdd2, memMobile, memMileage, memDelete,
					locationId));
		}
		resultSet.close();
		statement.close();
		connection.close();

		return list;
	}

	public MemberVO selectMemberMileage(String memID) throws Exception { // 회원 마일리지 조회
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.22:1521/xe", "CINEMAPROJECT",
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

	public String replaceMobileNum(String mobile) throws Exception { // 핸드폰번호 정규화
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.22:1521/xe", "CINEMAPROJECT",
				"java");

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT REGEXP_REPLACE(REGEXP_REPLACE(?, '[[:punct:]]'), '( ){1,}', '') ");
		builder.append("    AS MOBILE ");
		builder.append("  FROM DUAL");
		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, mobile);

		ResultSet resultSet = statement.executeQuery();
		String regMobileNum = null;

		if (resultSet.next()) {
			regMobileNum = resultSet.getString("MOBILE");

		}
		resultSet.close();
		statement.close();
		connection.close();

		return regMobileNum;
	}

	public void updateMileage(String memId, int mileage) throws Exception { // 회원 마일리지 정보 수정
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.22:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("            UPDATE MEMBER");
		builder.append("               SET MEM_MILEAGE = MEM_MILEAGE + ?");
		builder.append("             WHERE MEM_ID = ?  ");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, mileage);
		statement.setString(2, memId);

		statement.executeUpdate();

		statement.close();
		connection.close();
	}

	// FIXME : 20210528 집에서 함
	public void memberSignIn(MemberVO vo) throws Exception { // 회원 가입
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.22:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("          INSERT INTO MEMBER");
		builder.append("                 (MEM_ID, MEM_PASS, MEM_NAME, MEM_BIR, MEM_ADD1, MEM_ADD2, MEM_MOBILE)");
		builder.append("                 VALUES(?, ?, ?, TO_DATE(?, 'YYYYMMDD'), ?, ?, ?)  ");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, vo.getMemId());
		statement.setString(2, vo.getMemPass());
		statement.setString(3, vo.getMemName());
		statement.setString(4, vo.getMemBirString());
		statement.setString(5, vo.getMemAdd1());
		statement.setString(6, vo.getMemAdd2());
		statement.setString(7, vo.getMemMobile());

		int result = statement.executeUpdate();
		if (result > 0) {
			System.out.println("회원 가입 성공!");
		} else {
			System.out.println("가입 실패!");
		}
		statement.close();
		connection.close();

		statement.close();
		connection.close();
	}

	public boolean memberIdValidation(String newId) throws Exception { // 회원 가입 중복 아이디 검증
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.22:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("    SELECT  ");
		builder.append("      CASE WHEN ? IN (SELECT MEM_ID FROM MEMBER) THEN 1");
		builder.append("           ELSE 0");
		builder.append("           END AS ID_VALIDATION");
		builder.append("      FROM DUAL");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, newId);

		ResultSet resultSet = statement.executeQuery();

		boolean result = false;
		while (resultSet.next()) {
			int ifValidate = resultSet.getInt("ID_VALIDATION");
			if (ifValidate == 0) {
				result = true;
			}
		}
		resultSet.close();
		statement.close();
		connection.close();

		return result;
	}

}