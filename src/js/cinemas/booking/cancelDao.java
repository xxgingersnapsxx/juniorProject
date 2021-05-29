package js.cinemas.booking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class cancelDao {
	public void cancelMemTix(int showID, String seatNo) throws Exception { // 멤버 예매 취소
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.22:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("UPDATE MEMTIXHIST SET CANCEL_YN = 'Y'");
		builder.append(" WHERE SHOW_ID = ?");
		builder.append("   AND SEAT_NO =  ?");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, showID);
		statement.setString(2, seatNo);

		int result = statement.executeUpdate();
		if (result > 0) {
			System.out.println("취소 성공!"); // TODO 좌석 선택 화면으로 바꾸기
		} else {
			System.out.println("취소 실패!");
		}
		statement.close();
		connection.close();
	}

	public String returnSeatChar(int seatNo) throws Exception { // 좌석 번호 숫자만 받아서 기호 반환
		String seatChar = null;
		switch (seatNo) {
		case 0:
			seatChar = "⓪";
			break;
		case 1:
			seatChar = "①";
			break;
		case 2:
			seatChar = "②";
			break;
		case 3:
			seatChar = "③";
			break;
		case 4:
			seatChar = "④";
			break;
		case 5:
			seatChar = "⑤";
			break;
		case 6:
			seatChar = "⑥";
			break;
		case 7:
			seatChar = "⑦";
			break;
		case 8:
			seatChar = "⑧";
			break;
		case 9:
			seatChar = "⑨";
			break;

		default:
			break;
		}
		return seatChar;
	}
}
