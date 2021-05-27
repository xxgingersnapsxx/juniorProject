package js.cinemas.booking;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookingDAO {
	public void insertMTixHist(String memId, int showId) throws Exception { // 예매 시작 tixhist에 Id, show_id 추가
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("    INSERT INTO MEMTIXHIST (MEM_ID, SHOW_ID, SEAT_NO, CANCEL_YN)");
		builder.append("         VALUES (?, ?, null, null)  ");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, memId);
		statement.setInt(2, showId);

		int result = statement.executeUpdate();
		if (result > 0) {
			System.out.println("예매 성공!"); // TODO 좌석 선택 화면으로 바꾸기
		} else {
			System.out.println("예매 실패!");
		}
		statement.close();
		connection.close();
	}

	// public static void main(String[] args) throws Exception {
	// BookingDAO dao = new BookingDAO();
	// dao.insertMTixHist("ginps", 8);
	// }

	public int returnTimeID(String startTime) throws Exception { // startTime에 따른 Time_id 반환 메소드

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("   SELECT");
		builder.append("   CASE WHEN SUBSTR(?, 9, 4) >= 1000 AND SUBSTR(?, 9, 4) <= 1259 THEN 2");
		builder.append("        WHEN SUBSTR(?, 9, 4) >= 1300 AND SUBSTR(?, 9, 4) <= 2359 THEN 3");
		builder.append("        ELSE 1");
		builder.append("    END AS TIME_INTERVAL");
		builder.append("    FROM DUAL");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, startTime);
		statement.setString(2, startTime);
		statement.setString(3, startTime);
		statement.setString(4, startTime);

		int timeId = 0;
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			timeId = resultSet.getInt("TIME_INTERVAL");
		}

		resultSet.close();
		statement.close();
		connection.close();

		return timeId;
	}

	public String returnTypeID(int movieId) throws Exception { // price_id 구하기 위해 movieId로부터 type_id 구하는 메소드

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("SELECT TYPE_ID");
		builder.append("        FROM MOVIES");
		builder.append("       WHERE MOVIE_ID = ?");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, movieId);

		String typeId = null;
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			typeId = resultSet.getString("TYPE_ID");
		}

		resultSet.close();
		statement.close();
		connection.close();

		return typeId;
	}

	public int returnDay(String startAt) throws Exception { // 시작 시간 요일 구해서 숫자로 반환해주는 메소드
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append(" SELECT TO_NUMBER(TO_CHAR(TO_DATE(?, 'YYYYMMDDHH24MI'), 'D')) AS DAY_NUM");
		builder.append(" FROM DUAL");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, startAt);

		int dayNum = 0;
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			dayNum = resultSet.getInt("DAY_NUM");
		}

		resultSet.close();
		statement.close();
		connection.close();

		return dayNum;
	}

	public String returnDayType(int dayNum) throws Exception { // 요일 번호 받아서 주중 주말 String으로 반환 메소드

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("    SELECT CASE WHEN ? IN (1, 6, 7) THEN '주말'");
		builder.append("                ELSE '주중'");
		builder.append("           END AS DAY_TYPE");
		builder.append("      FROM DUAL");
		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, dayNum);

		String dayType = null;
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			dayType = resultSet.getString("DAY_TYPE");
		}

		resultSet.close();
		statement.close();
		connection.close();

		return dayType; // 주중, 주말
	}

	public int returnPriceId(int timeId, String typeId, String dayType) throws Exception { // PriceID구하는 메소드

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("   SELECT PRICE_ID");
		builder.append("     FROM TIX_PRICE");
		builder.append("    WHERE TIME_ID = ?");
		builder.append("      AND TYPE_ID = ?");
		builder.append("      AND PRICE_NAME LIKE ?||'%'");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, timeId);
		statement.setString(2, typeId);
		statement.setString(3, dayType);

		int priceId = 0;
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			priceId = resultSet.getInt("PRICE_ID");
		}

		resultSet.close();
		statement.close();
		connection.close();

		return priceId;
	}

}
