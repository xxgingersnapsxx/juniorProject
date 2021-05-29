package temp.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import js.cinemas.booking.seatDAO;

public class refundDAO {
	public void memTixRefund(int showId, String seatNo) throws Exception { // 예매 취소 memTixHist에 반영
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.22:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("UPDATE MEMTIXHIST SET CANCEL_YN = 'Y'");
		builder.append(" WHERE SHOW_ID = ?");
		builder.append("   AND SEAT_NO =  ?");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, showId);
		statement.setString(2, seatNo);

		int result = statement.executeUpdate();
		// if (result > 0) {
		// System.out.println("등록 성공!");
		// } else {
		// System.out.println("등록 실패!");
		// }
		statement.close();
		connection.close();
	}

}
