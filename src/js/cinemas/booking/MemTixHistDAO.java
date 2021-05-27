package js.cinemas.booking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import js.cinemas.movies.MoviesVO;

public class MemTixHistDAO {

	public List<MemTixHistVO> selectMemTixHist(String loginId) throws Exception { // 특정 회원 예매 내역 조회
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("    SELECT MEMTIXHIST_ID, MEM_ID, SHOW_ID, SEAT_NO, CANCEL_YN");
		builder.append("      FROM MEMTIXHIST");
		builder.append("     WHERE MEM_ID = ?");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, loginId);
		ResultSet resultSet = statement.executeQuery();
		List<MemTixHistVO> list = new ArrayList<MemTixHistVO>();
		while (resultSet.next()) {
			String memId = loginId;
			int memTixHist = resultSet.getInt("MEMTIXHIST_ID");
			int showId = resultSet.getInt("SHOW_ID");
			String seatNo = resultSet.getString("SEAT_NO");
			String cancelYn = resultSet.getString("CANCEL_YN");
			list.add(new MemTixHistVO(memTixHist, memId, showId, seatNo, cancelYn));
		}
		resultSet.close();
		statement.close();
		connection.close();

		return list;
	}
	public List<MemTixHistVO> selectAvaMemTixHist(String loginId) throws Exception { // 특정 회원 출력 가능한 예매 내역 조회
		// FIXME 어젯밤에 여기 하다 맒
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();
		
		builder.append("    SELECT MEM_ID, SHOW_ID, SEAT_NO, SHOW_DATE, CANCEL_YN");
		builder.append("      FROM V_M_TIXHIS");
		builder.append("     WHERE MEM_ID = ?");
		builder.append("       AND SHOW_DATE >= SYSDATE");
		builder.append("       AND CANCEL_YN = 'N'");
		
		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, loginId);
		
		ResultSet resultSet = statement.executeQuery();
		List<MemTixHistVO> list = new ArrayList<MemTixHistVO>();
		while (resultSet.next()) {
			String memId = loginId;
			int memTixHist = resultSet.getInt("MEMTIXHIST_ID");
			int showId = resultSet.getInt("SHOW_ID");
			String seatNo = resultSet.getString("SEAT_NO");
			String cancelYn = resultSet.getString("CANCEL_YN");
			list.add(new MemTixHistVO(memTixHist, memId, showId, seatNo, cancelYn));
		}
		resultSet.close();
		statement.close();
		connection.close();
		
		return list;
	}

	public void insertMemTixHist(String loginID, int showId, String seatNo) throws Exception { // 예매 내역 추가
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("    INSERT INTO MEMTIXHIST (MEMTIXHIST_ID, MEM_ID, SHOW_ID, SEAT_NO)");
		builder.append("    SELECT CASE WHEN MAX(MEMTIXHIST_ID) IS NULL THEN 1");
		builder.append("                ELSE MAX(MEMTIXHIST_ID) + 1");
		builder.append("            END");
		builder.append("          , ?, ?, ?");
		builder.append("      FROM MEMTIXHIST");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, loginID);
		statement.setInt(2, showId);
		statement.setString(3, seatNo);

		int result = statement.executeUpdate();
		if (result > 0) {
			System.out.println("등록 성공!");
		} else {
			System.out.println("등록 실패!");
		}
		statement.close();
		connection.close();
	}

	public int returnTixId(String memId, int showId, String seatNo) throws Exception { // 티켓번호 반환 메소드
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("    SELECT MEMTIXHIST_ID");
		builder.append("      FROM MEMTIXHIST");
		builder.append("     WHERE MEM_ID = ?");
		builder.append("       AND SHOW_ID = ?");
		builder.append("       AND SEAT_NO = ?");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, memId);
		statement.setInt(2, showId);
		statement.setString(3, seatNo);

		int tixId = 0;
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			tixId = resultSet.getInt("MEMTIXHIST_ID");
		}

		resultSet.close();
		statement.close();
		connection.close();

		return tixId;
	}

}
