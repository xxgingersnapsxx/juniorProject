package js.cinemas.movies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MoviesDAO {
	public List<MoviesVO> selectAllMoviesList() throws Exception { // 전체 영화 목록 조회
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");
		Statement statement = connection.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append("        SELECT MOVIE_ID, MV_TITLE, DURATION, TYPE_ID, MOVIE_YN");
		builder.append("          FROM MOVIES");
		List<MoviesVO> list = new ArrayList<MoviesVO>();
		ResultSet resultSet = statement.executeQuery(builder.toString());
		while (resultSet.next()) {
			int movieId = resultSet.getInt("MOVIE_ID");
			String mvTitle = resultSet.getString("MV_TITLE");
			int duration = resultSet.getInt("DURATION");
			String typeId = resultSet.getString("TYPE_ID");
			String movieYn = resultSet.getString("MOVIE_YN");
			list.add(new MoviesVO(movieId, mvTitle, duration, typeId, movieYn));
		}
		resultSet.close();
		statement.close();
		connection.close();

		return list;
	}

	public List<MoviesVO> selectNowShowingMVList() throws Exception { // 현재 상영중 영화 목록 조회
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");
		Statement statement = connection.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append("        SELECT MOVIE_ID, MV_TITLE, DURATION, TYPE_ID, MOVIE_YN");
		builder.append("          FROM MOVIES");
		builder.append("         WHERE MOVIE_YN != 'N'");
		List<MoviesVO> list = new ArrayList<MoviesVO>();
		ResultSet resultSet = statement.executeQuery(builder.toString());
		while (resultSet.next()) {
			int movieId = resultSet.getInt("MOVIE_ID");
			String mvTitle = resultSet.getString("MV_TITLE");
			int duration = resultSet.getInt("DURATION");
			String typeId = resultSet.getString("TYPE_ID");
			String movieYn = resultSet.getString("MOVIE_YN");
			list.add(new MoviesVO(movieId, mvTitle, duration, typeId, movieYn));
		}
		resultSet.close();
		statement.close();
		connection.close();

		return list;
	}

	public List<moviesByLocVO> selectNowMVListByLoc(String locName) throws Exception { // 현재 상영중 영화 지점별 조회, 매개변수 : 지점명
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");
		// 테이블이 여러개인 경우 vo는 테이블기준이 아닌 메소드 단위로 생성
		StringBuilder builder = new StringBuilder();

		builder.append("        SELECT SHOW_ID, B.MV_TITLE AS MV_TITLE");
		builder.append("             , STARTS_AT");
		builder.append("             , ENDS_AT");
		builder.append("             , C.PRICE_AMT AS PRICE_AMT");
		builder.append("             , ZONE_ID, LOCATION_ID");
		builder.append("             , SHOW_YN");
		builder.append("          FROM NOWSHOWING A, MOVIES B, TIX_PRICE C");
		builder.append("         WHERE A.MOVIE_ID = B.MOVIE_ID");
		builder.append("          AND A.PRICE_ID = C.PRICE_ID");
		builder.append("          AND LOCATION_ID = (SELECT LOCATION_ID");
		builder.append("                               FROM LOCATIONS");
		builder.append("                              WHERE LOCATION_NAME = 'CGV'||?)");
		// TODO : ? 사용해서 지점별로 바꿔야 함

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
	
		statement.setString(1, locName);

		List<moviesByLocVO> list = new ArrayList<moviesByLocVO>();
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			int showId = resultSet.getInt("SHOW_ID");
			String mvTitle = resultSet.getString("MV_TITLE");
			// 여기 localDateTime으로 받아오는건지 확신 없음 stackoverflow보고 함
			LocalDateTime startsAt = resultSet.getTimestamp("STARTS_AT").toLocalDateTime();
			LocalDateTime endsAt = resultSet.getTimestamp("ENDS_AT").toLocalDateTime();
			int priceAmt = resultSet.getInt("PRICE_AMT");
			int zoneId = resultSet.getInt("ZONE_ID");
			String locationId = resultSet.getString("LOCATION_ID");
			String showYn = resultSet.getString("SHOW_YN");
			// TODO nowShowingVO로 바꾸고 생성자 바꿔주기
			list.add(new moviesByLocVO(showId, mvTitle, startsAt, endsAt, priceAmt, zoneId, locationId, showYn));

		}
		resultSet.close();
		statement.close();
		connection.close();

		return list;
	}

	public void insertMovies(MoviesVO vo) throws Exception { // 영화 추가
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();
		builder.append("        INSERT INTO MOVIES (MOVIE_ID, MV_TITLE");
		builder.append("                          , MV_O_TITLE, MV_S_TITLE");
		builder.append("                          , DURATION, TYPE_ID, MOVIE_YN)");
		builder.append("        SELECT MAX(MOVIE_ID) + 1, ? ");
		builder.append("             , NULL, NULL");
		builder.append("             , ?, NULL, NULL ");
		builder.append("          FROM MOVIES ");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, vo.getMvTitle());
		statement.setInt(2, vo.getDuration());

		int result = statement.executeUpdate();
		if (result > 0) {
			System.out.println("등록 성공!");
		} else {
			System.out.println("등록 실패!");
		}
		statement.close();
		connection.close();
	}
	// TODO 영화 정보 수정 메소드 만들기

	public void changeMovieStatus(int movieId, String yN) throws Exception { // 영화 상영 상태 변경 = 영화 삭제
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

	public void insertNowShowing(MoviesVO vo) throws Exception { // 상영 추가
		// TODO vo nowshowingVO로 바꾸기
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();
		builder.append("        INSERT INTO NOWSHOWING(SHOW_ID, MOVIE_ID, STARTS_AT");
		builder.append("                             , ENDS_AT, RATING_ID, PRICE_ID");
		builder.append("                             , ZONE_ID, LOCATION_ID, SEATS, SHOW_YN)");
		builder.append("             SELECT ?, ?, TO_DATE('?', 'YYYYMMDDHH24MI')");
		builder.append("                        , TO_DATE('?', 'YYYYMMDDHH24MI') + DURATION/(24*60), NULL, ?, ?");
		builder.append("                        , ?");
		builder.append("                        , ?, NULL");
		builder.append("               FROM MOVIES");
		builder.append("              WHERE MOVIE_ID = ?");
		//
		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, 1); // SHOW_ID
		statement.setInt(2, 2); // MOVIE_ID
		statement.setString(3, "202105302458"); // STARTS_AT YYYYMMDD HH42MI
		statement.setString(4, "202105302458"); // ENDS_AT = STARTS_AT
		statement.setInt(5, 1); // PRICE_ID
		statement.setInt(6, 1); // ZONE_ID
		statement.setString(7, "100010504"); // LOCATION_ID
		statement.setString(8, "ⓞ①②③④⑤⑥⑦⑧⑨ⓞ①②③④⑤⑥⑦⑧⑨ⓞ①②③④⑤⑥⑦⑧⑨ⓞ①②③④⑤⑥⑦⑧⑨ⓞ①②③④⑤⑥⑦⑧⑨"); // SEATS
	}
}
