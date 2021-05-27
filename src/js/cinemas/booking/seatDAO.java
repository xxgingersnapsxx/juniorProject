package js.cinemas.booking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import js.cinemas.locs.LocationsDAO;
import js.cinemas.movies.NowShowingDAO;

public class seatDAO {

	public void printSeat(String locName, int zoneId, int showId) throws Exception { // 좌석 출력 메소드
		NowShowingDAO nowShowingDAO = new NowShowingDAO();
		LocationsDAO locationsDAO = new LocationsDAO();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");

		StringBuilder builder = new StringBuilder();
		builder.append("    SELECT SEATS");
		builder.append("      FROM NOWSHOWING");
		builder.append("     WHERE LOCATION_ID = ?");
		builder.append("       AND ZONE_ID = ?");
		builder.append("       AND SHOW_ID = ?");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, locationsDAO.returnLocId(locName));
		statement.setInt(2, zoneId);
		statement.setInt(3, showId);
		ResultSet resultSet = statement.executeQuery();
		String seatOracleData = null;
		while (resultSet.next()) {
			seatOracleData = resultSet.getString("SEATS");
		}
		int numberOfSeat = nowShowingDAO.SeatCnt(showId, locationsDAO.returnLocId(locName));
		int col = 10;
		int row = numberOfSeat / col;

		String[][] seat = new String[row][col];

		String rowAlphabet = "ⓐⓑⓒⓓⓔⓕⓖⓗⓘⓙⓚⓛⓜⓝⓞⓟⓠⓡⓢⓣⓤⓥⓦⓧⓨⓩ";

		System.out.println("      screen       ");
		System.out.println("-------------------");
		for (int i = 0; i < seat.length; i++) {
			System.out.print(rowAlphabet.substring(i, i + 1) + " ");
			for (int j = 0; j < seat[i].length; j++) {
				String iToString = String.valueOf(i);
				String jToString = String.valueOf(j);
				int startIndex = Integer.parseInt(iToString + jToString);
				seat[i][j] = seatOracleData.substring(startIndex, startIndex + 1);
				System.out.print(seat[i][j]);
			}
			System.out.println();
		}

	}

	public String getSeatData(String locName, int zoneId, int showId) throws Exception { // 좌석 데이터 가져오는 메소드
		NowShowingDAO nowShowingDAO = new NowShowingDAO();
		LocationsDAO locationsDAO = new LocationsDAO();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");

		StringBuilder builder = new StringBuilder();
		builder.append("    SELECT SEATS");
		builder.append("      FROM NOWSHOWING");
		builder.append("     WHERE LOCATION_ID = ?");
		builder.append("       AND ZONE_ID = ?");
		builder.append("       AND SHOW_ID = ?");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, locationsDAO.returnLocId(locName));
		statement.setInt(2, zoneId);
		statement.setInt(3, showId);
		ResultSet resultSet = statement.executeQuery();
		String seatOracleData = null;
		while (resultSet.next()) {
			seatOracleData = resultSet.getString("SEATS");
		}

		return seatOracleData;
	}

	public String returnOriginalSeatString(String locName, int zoneId, int showId) throws Exception { // 좌석 String 반환
																										// 메소드
		NowShowingDAO nowShowingDAO = new NowShowingDAO();
		LocationsDAO locationsDAO = new LocationsDAO();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");

		StringBuilder builder = new StringBuilder();
		builder.append("    SELECT SEATS");
		builder.append("      FROM NOWSHOWING");
		builder.append("     WHERE LOCATION_ID = ?");
		builder.append("       AND ZONE_ID = ?");
		builder.append("       AND SHOW_ID = ?");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, locationsDAO.returnLocId(locName));
		statement.setInt(2, zoneId);
		statement.setInt(3, showId);
		ResultSet resultSet = statement.executeQuery();
		String seatOracleData = null;
		while (resultSet.next()) {
			seatOracleData = resultSet.getString("SEATS");
		}
		return seatOracleData;
	}

	public String SeatString(String seatData, String seatSelection) throws Exception { // 교체된 좌석 String 반환 메소드
		StringBuilder builder = new StringBuilder(seatData);

		char rowAlphbet = seatSelection.charAt(0);
		int getColNum = Integer.parseInt(seatSelection.substring(1));
		int seatNumToChange = Integer.parseInt(String.valueOf((int) rowAlphbet - 65) + String.valueOf(getColNum));
		builder.setCharAt(seatNumToChange, '●');
		String seatOracleData = builder.toString();

		return seatOracleData;
	}

	public void updateSeatStatus(String seatString, int showId) throws Exception { // 좌석정보 변경 메소드

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("UPDATE NOWSHOWING");
		builder.append("   SET SEATS = ?");
		builder.append(" WHERE SHOW_ID = ?");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, seatString);
		statement.setInt(2, showId);

		// int result = statement.executeUpdate();
		statement.executeUpdate();
		// if (result > 0) {
		// System.out.println("예매 성공!");
		// } else {
		// System.out.println("예매 실패!");
		// }
		statement.close();
		connection.close();
	}

	public static String deepToString(Object[] a) {
		if (a == null)
			return "null";

		int bufLen = 20 * a.length;
		if (a.length != 0 && bufLen <= 0)
			bufLen = Integer.MAX_VALUE;
		StringBuilder buf = new StringBuilder(bufLen);
		deepToString(a, buf, new HashSet<Object[]>());
		return buf.toString();
	}

	private static void deepToString(Object[] a, StringBuilder buf, Set<Object[]> dejaVu) {
		if (a == null) {
			buf.append("null");
			return;
		}
		int iMax = a.length - 1;
		if (iMax == -1) {
			return;
		}

		dejaVu.add(a);
		for (int i = 0;; i++) {

			Object element = a[i];
			if (element == null) {
				buf.append("null");
			} else {
				Class<?> eClass = element.getClass();

				if (eClass.isArray()) {
					if (eClass == byte[].class)
						buf.append(toString((byte[]) element));
					else { // element is an array of object references
						if (dejaVu.contains(element))
							buf.append("...");
						else
							deepToString((Object[]) element, buf, dejaVu);
					}
				} else { // element is non-null and not an array
					buf.append(element.toString());
				}
			}
			if (i == iMax)
				break;
		}
		dejaVu.remove(a);
	}

	public static String toString(byte[] a) {
		if (a == null)
			return "null";
		int iMax = a.length - 1;
		if (iMax == -1)
			return "";

		StringBuilder b = new StringBuilder();
		for (int i = 0;; i++) {
			b.append(a[i]);
			if (i == iMax)
				return b.toString();
		}
	}
}
