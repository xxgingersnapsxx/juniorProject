package js.cinemas.booking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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

	public static void main(String[] args) throws Exception {
		BookingDAO dao = new BookingDAO();
		dao.insertMTixHist("ginps", 8);
	}

	public void selectSeat() throws Exception { // 좌석 선택 memtixhist에 넣어주고, nowshowing seat data 변경
		
		// FIXME 오늘 여기부터 해!
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("        UPDATE MOVIES");
		builder.append("           SET MOVIE_YN = ?");
		builder.append("         WHERE MOVIE_ID = ?");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, yN);
		statement.setInt(2, movieId);

		int result = statement.executeUpdate();
		if (result > 0) {
			System.out.println("수정 성공!");
		} else {
			System.out.println("수정 실패!");
		}
		statement.close();
		connection.close();
	}
}
