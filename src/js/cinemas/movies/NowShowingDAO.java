package js.cinemas.movies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import js.cinemas.locs.LocationsDAO;
import js.cinemas.locs.zoneDao;

public class NowShowingDAO {

	public List<NowShowingVO> selectAllNowShowingList() throws Exception { // 전체 상영 일정 조회
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.22:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append(
				"     SELECT SHOW_ID, MOVIE_ID, STARTS_AT, ENDS_AT, RATING_ID, PRICE_ID, ZONE_ID, LOCATION_ID, SEATS, SHOW_YN, LOCATION_NAME");
		builder.append("       FROM NOWSHOWING");
		builder.append("      ORDER BY SHOW_ID");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		List<NowShowingVO> list = new ArrayList<NowShowingVO>();
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			int showId = resultSet.getInt("SHOW_ID");
			int movieId = resultSet.getInt("MOVIE_ID");
			LocalDateTime startsAt = resultSet.getTimestamp("STARTS_AT").toLocalDateTime();
			LocalDateTime endsAt = resultSet.getTimestamp("ENDS_AT").toLocalDateTime();
			String ratingId = resultSet.getString("RATING_ID");
			int priceId = resultSet.getInt("PRICE_ID");
			int zoneId = resultSet.getInt("ZONE_ID");
			String locationId = resultSet.getString("LOCATION_ID");
			String seats = resultSet.getString("SEATS");
			String showYn = resultSet.getString("SHOW_YN");
			String locationName = resultSet.getString("LOCATION_NAME");

			list.add(new NowShowingVO(showId, movieId, startsAt, endsAt, null, priceId, zoneId, locationId, seats,
					showYn));
		}
		resultSet.close();
		statement.close();
		connection.close();

		return list;
	}

	public List<NowShowingVO> selectNowShowingListWithShowId(int showID) throws Exception { // showID로일정 조회
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.22:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append(
				"     SELECT SHOW_ID, MOVIE_ID, STARTS_AT, ENDS_AT, RATING_ID, PRICE_ID, ZONE_ID, LOCATION_ID, SEATS, SHOW_YN, LOCATION_NAME");
		builder.append("       FROM NOWSHOWING");
		builder.append("      WHERE SHOW_ID = ?");
		builder.append("      ORDER BY SHOW_ID");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, showID);
		List<NowShowingVO> list = new ArrayList<NowShowingVO>();
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			int showId = resultSet.getInt("SHOW_ID");
			int movieId = resultSet.getInt("MOVIE_ID");
			LocalDateTime startsAt = resultSet.getTimestamp("STARTS_AT").toLocalDateTime();
			LocalDateTime endsAt = resultSet.getTimestamp("ENDS_AT").toLocalDateTime();
			String ratingId = resultSet.getString("RATING_ID");
			int priceId = resultSet.getInt("PRICE_ID");
			int zoneId = resultSet.getInt("ZONE_ID");
			String locationId = resultSet.getString("LOCATION_ID");
			String seats = resultSet.getString("SEATS");
			String showYn = resultSet.getString("SHOW_YN");
			String locationName = resultSet.getString("LOCATION_NAME");

			list.add(new NowShowingVO(showId, movieId, startsAt, endsAt, null, priceId, zoneId, locationId, seats,
					showYn));
		}
		resultSet.close();
		statement.close();
		connection.close();

		return list;
	}

	public List<NowShowingVO> selectNowShowingListByLoc(String locName) throws Exception { // 지점별 상영 일정 조회
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.22:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append(
				"     SELECT SHOW_ID, MOVIE_ID, STARTS_AT, ENDS_AT, RATING_ID, PRICE_ID, ZONE_ID, LOCATION_ID, SEATS, SHOW_YN, LOCATION_NAME");
		builder.append("       FROM NOWSHOWING");
		builder.append("      WHERE LOCATION_ID = ?");
		builder.append("      ORDER BY SHOW_ID");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		LocationsDAO locationsDAO = new LocationsDAO();
		statement.setString(1, locationsDAO.returnLocId(locName));

		List<NowShowingVO> list = new ArrayList<NowShowingVO>();
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			int showId = resultSet.getInt("SHOW_ID");
			int movieId = resultSet.getInt("MOVIE_ID");
			LocalDateTime startsAt = resultSet.getTimestamp("STARTS_AT").toLocalDateTime();
			LocalDateTime endsAt = resultSet.getTimestamp("ENDS_AT").toLocalDateTime();
			String ratingId = resultSet.getString("RATING_ID");
			int priceId = resultSet.getInt("PRICE_ID");
			int zoneId = resultSet.getInt("ZONE_ID");
			String locationId = resultSet.getString("LOCATION_ID");
			String seats = resultSet.getString("SEATS");
			String showYn = resultSet.getString("SHOW_YN");
			String locationName = resultSet.getString("LOCATION_NAME");

			list.add(new NowShowingVO(showId, movieId, startsAt, endsAt, null, priceId, zoneId, locationId, seats,
					showYn));
		}
		resultSet.close();
		statement.close();
		connection.close();

		return list;
	}

	public List<NowShowingVO> selectAvaNowShowingListByLoc(String locName) throws Exception { // 지점별 예매 가능한 상영 일정 조회
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.22:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append(
				"     SELECT SHOW_ID, MOVIE_ID, STARTS_AT, ENDS_AT, RATING_ID, PRICE_ID, ZONE_ID, LOCATION_ID, SEATS, SHOW_YN, LOCATION_NAME");
		builder.append("       FROM NOWSHOWING");
		builder.append("      WHERE LOCATION_ID = ?");
		builder.append("        AND STARTS_AT > SYSDATE ");
		builder.append("      ORDER BY SHOW_ID");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		LocationsDAO locationsDAO = new LocationsDAO();
		statement.setString(1, locationsDAO.returnLocId(locName));

		List<NowShowingVO> list = new ArrayList<NowShowingVO>();
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			int showId = resultSet.getInt("SHOW_ID");
			int movieId = resultSet.getInt("MOVIE_ID");
			LocalDateTime startsAt = resultSet.getTimestamp("STARTS_AT").toLocalDateTime();
			LocalDateTime endsAt = resultSet.getTimestamp("ENDS_AT").toLocalDateTime();
			String ratingId = resultSet.getString("RATING_ID");
			int priceId = resultSet.getInt("PRICE_ID");
			int zoneId = resultSet.getInt("ZONE_ID");
			String locationId = resultSet.getString("LOCATION_ID");
			String seats = resultSet.getString("SEATS");
			String showYn = resultSet.getString("SHOW_YN");
			String locationName = resultSet.getString("LOCATION_NAME");

			list.add(new NowShowingVO(showId, movieId, startsAt, endsAt, null, priceId, zoneId, locationId, seats,
					showYn));
		}
		resultSet.close();
		statement.close();
		connection.close();

		return list;
	}

	// // FIXME : 고객용
	// public static void main(String[] args) throws Exception {
	// NowShowingDAO nowShowingDAO = new NowShowingDAO();
	// List<NowShowingVO> selectNowShowingListByLoc =
	// nowShowingDAO.selectNowShowingListByLoc("강남");
	// for (NowShowingVO nowShowingVO : selectNowShowingListByLoc) {
	// if (nowShowingVO.getShowYn().equals("N")) {
	// continue;
	// }
	// System.out.println(nowShowingVO.getShowYn());
	//
	// }
	// }

	public List<NowShowingVO> selectNowShowingListByLocZone(String locName, int zoneID) throws Exception { // 지점별 상영관별
																											// 상영 일정 조회
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.22:1521/xe", "CINEMAPROJECT",
				"java");

		StringBuilder builder = new StringBuilder();

		builder.append(
				"     SELECT SHOW_ID, MOVIE_ID, STARTS_AT, ENDS_AT, RATING_ID, PRICE_ID, ZONE_ID, LOCATION_ID, SEATS, SHOW_YN, LOCATION_NAME");
		builder.append("       FROM NOWSHOWING");
		builder.append("      WHERE LOCATION_ID = ? ");
		builder.append("        AND ZONE_ID = ?");
		builder.append("     ORDER BY SHOW_ID, ZONE_ID");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		LocationsDAO locationsDAO = new LocationsDAO();
		statement.setString(1, locationsDAO.returnLocId(locName));
		statement.setInt(2, zoneID);

		List<NowShowingVO> list = new ArrayList<NowShowingVO>();
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {

			int showId = resultSet.getInt("SHOW_ID");
			int movieId = resultSet.getInt("MOVIE_ID");
			LocalDateTime startsAt = resultSet.getTimestamp("STARTS_AT").toLocalDateTime();
			LocalDateTime endsAt = resultSet.getTimestamp("ENDS_AT").toLocalDateTime();
			String ratingId = resultSet.getString("RATING_ID");
			int priceId = resultSet.getInt("PRICE_ID");
			int zoneId = resultSet.getInt("ZONE_ID");
			String locationId = resultSet.getString("LOCATION_ID");
			String seats = resultSet.getString("SEATS");
			String showYn = resultSet.getString("SHOW_YN");
			String locationName = resultSet.getString("LOCATION_NAME");

			list.add(new NowShowingVO(showId, movieId, startsAt, endsAt, null, priceId, zoneId, locationId, seats,
					showYn));
		}
		resultSet.close();
		statement.close();
		connection.close();

		return list;
	}

	public int createNewShowId() throws Exception { // nowshowing 입력용 max_show id값 생성
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.22:1521/xe", "CINEMAPROJECT",
				"java");

		StringBuilder builder = new StringBuilder();

		builder.append("SELECT MAX(SHOW_ID) + 1 AS MAX_SHOW_ID");
		builder.append("  FROM NOWSHOWING");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		List<moviesByLocVO> list = new ArrayList<moviesByLocVO>();
		ResultSet resultSet = statement.executeQuery();
		int newShowId = 0;
		while (resultSet.next()) {
			newShowId = resultSet.getInt("MAX_SHOW_ID");
		}
		resultSet.close();
		statement.close();
		connection.close();

		return newShowId;
	}

	public String generateSeatsString(String locId, int zoneId) throws Exception { // locId와 zoneID를 받아 좌석수에 따라
																					// String 생성

		zoneDao zoneDao = new zoneDao();

		int seatCnt = zoneDao.returnSeatCnt(locId, zoneId);

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < seatCnt / 10; i++) {
			builder.append("⓪①②③④⑤⑥⑦⑧⑨");
		}
		return builder.toString();
	}

	public void insertMovieSchedule(NowShowingInsertVO vo) throws Exception { // 상영 영화 추가

		// FIXME 20210527 0813 여기 하는중
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.22:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();
		builder.append("        INSERT INTO NOWSHOWING(SHOW_ID, MOVIE_ID");
		builder.append("                             , STARTS_AT");
		builder.append("                             , ENDS_AT");
		builder.append("                             , RATING_ID");
		builder.append("                             , PRICE_ID, ZONE_ID");
		builder.append("                             , LOCATION_ID");
		builder.append("                             , SEATS)");
		builder.append("             SELECT ?, ?");
		builder.append("                  , TO_DATE(?, 'YYYYMMDDHH24MI')");
		builder.append("                  , TO_DATE(?, 'YYYYMMDDHH24MI') + DURATION/(24*60)");
		builder.append("                  , NULL");
		builder.append("                  , ?, ?");
		builder.append("                  , ?");
		builder.append("                  , ?");
		builder.append("               FROM MOVIES");
		builder.append("              WHERE MOVIE_ID = ?  ");
		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, vo.getShowId()); // SHOW_ID
		statement.setInt(2, vo.getMovieId()); // MOVIE_ID
		statement.setString(3, vo.getStartsAt()); // STARTS_AT
		statement.setString(4, vo.getStartsAt()); // ENDS_AT = STARTS_AT
		statement.setInt(5, vo.getPriceId()); // PRICE_ID
		statement.setInt(6, vo.getZoneId()); // ZONE_ID
		statement.setString(7, vo.getLocationId()); // LOCATION_ID
		statement.setString(8, vo.getSeats()); // SEATS
		statement.setInt(9, vo.getMovieId()); // MOVIE_ID for WHERE절

		int result = statement.executeUpdate();
		if (result > 0) {
			System.out.println("등록 성공!");
		} else {
			System.out.println("등록 실패!");
		}
		statement.close();
		connection.close();
	}

	// public static void main(String[] args) throws Exception {
	// NowShowingDAO nowShowingDAO = new NowShowingDAO();
	// String str = "202105270809";
	// nowShowingDAO.insertMovieSchedule(new NowShowingInsertVO(2, 7, str, str,
	// null, 1, 1, "1006007", "d", "Y"));
	// }

	public int availableSeatCnt(int showId) throws Exception { // showId 받아서 잔여 좌석수 return 메소드

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.22:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("    SELECT LENGTH(SEATS) - REGEXP_COUNT(SEATS,'●') AS AVAILABLE_SEAT_CNT");
		builder.append("      FROM NOWSHOWING");
		builder.append("     WHERE SHOW_ID = ? ");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, showId);

		int availableSeatCnt = 0;
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			availableSeatCnt = resultSet.getInt("AVAILABLE_SEAT_CNT");
		}

		resultSet.close();
		statement.close();
		connection.close();

		return availableSeatCnt;
	}

	public int SeatCnt(int showId, String locId) throws Exception { // showId, locId 받아서 좌석수 return 메소드

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.22:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("    SELECT SEAT_CNT");
		builder.append("      FROM ZONE");
		builder.append("     WHERE ZONE_ID = (SELECT ZONE_ID");
		builder.append("                        FROM NOWSHOWING");
		builder.append("                       WHERE SHOW_ID = ?)");
		builder.append("      AND LOCATION_ID = ?");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, showId);
		statement.setString(2, locId);

		int SeatCnt = 0;
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			SeatCnt = resultSet.getInt("SEAT_CNT");
		}

		resultSet.close();
		statement.close();
		connection.close();

		return SeatCnt;
	}

	public String ifSoldOut(int availableSeatCnt) { // 매진 판별 메소드
		String ifSoldOut = null;
		if (availableSeatCnt == 0) {
			ifSoldOut = "매진";
		}
		return ifSoldOut;
	}

	public int returnZoneId(int showId) throws Exception { // showId 받아서 zoneId return 메소드

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.22:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("    SELECT ZONE_ID");
		builder.append("      FROM NOWSHOWING");
		builder.append("     WHERE SHOW_ID = ?");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, showId);

		int zondId = 0;
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			zondId = resultSet.getInt("ZONE_ID");
		}

		resultSet.close();
		statement.close();
		connection.close();

		return zondId;
	}

	public String returnLocID(int showId) throws Exception { // showId 받아서 locID return 메소드

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.22:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("    SELECT LOCATION_ID");
		builder.append("      FROM NOWSHOWING");
		builder.append("     WHERE SHOW_ID = ?");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, showId);

		String locID = null;
		ResultSet resultSet = statement.executeQuery();
		LocationsDAO locationsDAO = new LocationsDAO();
		while (resultSet.next()) {
			locID = resultSet.getString("LOCATION_ID");
		}

		resultSet.close();
		statement.close();
		connection.close();

		return locID;
	}

	public int returnMovieId(int showId) throws Exception { // showId 받아서 movieId return 메소드

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.22:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("    SELECT MOVIE_ID");
		builder.append("      FROM NOWSHOWING");
		builder.append("     WHERE SHOW_ID = ?");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, showId);

		int movieID = 0;
		ResultSet resultSet = statement.executeQuery();
		LocationsDAO locationsDAO = new LocationsDAO();
		while (resultSet.next()) {
			movieID = resultSet.getInt("MOVIE_ID");
		}

		resultSet.close();
		statement.close();
		connection.close();

		return movieID;
	}

	public LocalDateTime returnRunDate(int showId) throws Exception { // showId 받아서 상영일 return 메소드

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.22:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("    SELECT STARTS_AT");
		builder.append("      FROM NOWSHOWING");
		builder.append("     WHERE SHOW_ID = ?");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, showId);

		ResultSet resultSet = statement.executeQuery();
		LocalDateTime startsAt = null;
		while (resultSet.next()) {
			startsAt = resultSet.getTimestamp("STARTS_AT").toLocalDateTime();
		}

		resultSet.close();
		statement.close();
		connection.close();

		return startsAt;
	}

}
