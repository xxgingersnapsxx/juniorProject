package js.cinemas.movies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import js.cinemas.locs.LocationsDAO;
import js.cinemas.locs.zoneDao;

public class NowShowingDAO {

	public int createNewShowId() throws Exception { // nowshowing 입력용 max_show id값 생성
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
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

	public void insertMovies(NowShowingInsertVO vo) throws Exception { // 상영 영화 추가
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();
		// FIXME : 지금 여기 하는 중
		builder.append("        INSERT INTO NOWSHOWING(SHOW_ID, MOVIE_ID");
		builder.append("                             , STARTS_AT");
		builder.append("                             , ENDS_AT");
		builder.append("                             , RATING_ID");
		builder.append("                             , PRICE_ID, ZONE_ID");
		builder.append("                             , LOCATION_ID");
		builder.append("                             , SEATS");
		builder.append("                             , SHOW_YN)");
		builder.append("             SELECT ?, ?");
		builder.append("                  , TO_DATE(?, 'YYYYMMDDHH24MI')");
		builder.append("                  , TO_DATE(?, 'YYYYMMDDHH24MI') + DURATION/(24*60)");
		builder.append("                  , NULL");
		builder.append("                  , ?, ?");
		builder.append("                  , ?");
		builder.append("                  , ?");
		builder.append("                  , NULL");
		builder.append("               FROM MOVIES");
		builder.append("              WHERE MOVIE_ID = ?  ");
		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		// FIXME get하고 맞는게 set 될 수 있도록 수정하기 0527 여기부터
		NowShowingDAO nowShowingDAO = new NowShowingDAO();
		LocationsDAO locationsDAO = new LocationsDAO();
		
		statement.setInt(1, nowShowingDAO.createNewShowId()); // SHOW_ID
		statement.setInt(2, vo.getMovieId()); // MOVIE_ID
		statement.setString(3, vo.getStartsAt()); // STARTS_AT
		statement.setString(4, vo.getStartsAt()); // ENDS_AT = STARTS_AT
		statement.setInt(5, vo.getPriceId()); // PRICE_ID
		statement.setInt(6, vo.getZoneId()); // ZONE_ID
		statement.setString(7, vo.getLocationId()); // LOCATION_ID
		statement.setString(8, nowShowingDAO.generateSeatsString(vo.getLocationId(), vo.getZoneId())); // SEATS
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
}
