package js.cinemas.booking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class cancelDao {
	public void cancelMemTix(String memId, int showId) throws Exception { // 멤버 예매 취소
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
}
