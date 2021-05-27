package js.cinemas.locs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class zoneDao {
	public List<ZoneVO> selectAllZoneList() throws Exception { // 전체 상영관 목록 조회
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");
		Statement statement = connection.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append(
				"           SELECT ZONE.ZONE_ID, ZONE.SEAT_CNT, ZONE.LOCATION_ID, ZONE.OPENED_YN, LOCATIONS.LOCATION_NAME");
		builder.append("              FROM ZONE, LOCATIONS");
		builder.append("             WHERE LOCATIONS.LOCATION_ID = ZONE.LOCATION_ID");
		List<ZoneVO> list = new ArrayList<ZoneVO>();
		ResultSet resultSet = statement.executeQuery(builder.toString());
		while (resultSet.next()) {
			String locationId = resultSet.getString("LOCATION_ID");
			int zoneId = resultSet.getInt("ZONE_ID");
			int seatCnt = resultSet.getInt("SEAT_CNT");
			String openedYn = resultSet.getString("OPENED_YN");
			String locationName = resultSet.getString("LOCATION_NAME");
			list.add(new ZoneVO(zoneId, seatCnt, openedYn, locationId, locationName));
		}
		resultSet.close();
		statement.close();
		connection.close();

		return list;
	}

	public List<ZoneVO> selectAllZoneListByLoc(String locName) throws Exception { // 상영관 목록 지점별 조회, 매개변수 : 지점명
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");

		StringBuilder builder = new StringBuilder();
		builder.append(
				"           SELECT ZONE.ZONE_ID AS ZONE_ID, ZONE.SEAT_CNT AS SEAT_CNT, ZONE.LOCATION_ID AS LOCATION_ID, LOCATIONS.LOCATION_NAME AS LOCATION_NAME, ZONE.OPENED_YN AS OPENED_YN");
		builder.append("              FROM ZONE, LOCATIONS");
		builder.append("             WHERE ZONE.LOCATION_ID = (SELECT LOCATIONS.LOCATION_ID");
		builder.append("                                         FROM LOCATIONS");
		builder.append("                                        WHERE LOCATION_NAME = 'CGV'||?)");
		builder.append("              AND LOCATIONS.LOCATION_ID = ZONE.LOCATION_ID");
		builder.append("     ORDER BY ZONE.ZONE_ID");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, locName);

		List<ZoneVO> list = new ArrayList<ZoneVO>();
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			String locationId = resultSet.getString("LOCATION_ID");
			int zoneId = resultSet.getInt("ZONE_ID");
			int seatCnt = resultSet.getInt("SEAT_CNT");
			String openedYn = resultSet.getString("OPENED_YN");
			String locationName = resultSet.getString("LOCATION_NAME");
			list.add(new ZoneVO(zoneId, seatCnt, openedYn, locationId, locationName));
		}
		resultSet.close();
		statement.close();
		connection.close();

		return list;
	}

	public int returnSeatCnt(String locId, int zoneId) throws Exception { // 상영관 별 좌석수 반환 메소드
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT SEAT_CNT");
		builder.append("        FROM ZONE");
		builder.append("       WHERE LOCATION_ID = ?");
		builder.append("         AND ZONE_ID = ?");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, locId);
		statement.setInt(2, zoneId);

		int seatCnt = 0;
		List<ZoneVO> list = new ArrayList<ZoneVO>();
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			seatCnt = resultSet.getInt("SEAT_CNT");
		}
		resultSet.close();
		statement.close();
		connection.close();

		return seatCnt;
	}

	public void insertZone(String locName, int seatCnt) throws Exception { // 지점 이름으로 상영관 추가
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("INSERT INTO ZONE(LOCATION_ID, LOCATION_NAME, ZONE_ID, SEAT_CNT)");
		builder.append("        SELECT A.LOCATION_ID, A.LOCATION_NAME,");
		builder.append("           CASE WHEN A.MAX_ZONE_ID IS NULL THEN 1");
		builder.append("                ELSE A.MAX_ZONE_ID");
		builder.append("                END");
		builder.append("            , ?");
		builder.append("          FROM (SELECT  B.LOCATION_ID, B.LOCATION_NAME, MAX(ZONE.ZONE_ID) AS MAX_ZONE_ID                      ");
		builder.append("                  FROM ZONE, ( SELECT LOCATION_ID, LOCATION_NAME");
		builder.append("                                  FROM LOCATIONS");
		builder.append("                                 WHERE LOCATION_NAME LIKE 'CGV'||?) B ");
		builder.append("                 WHERE ZONE.LOCATION_ID = B.LOCATION_ID");
		builder.append("                 GROUP BY B.LOCATION_ID, B.LOCATION_NAME              ");
		builder.append("                                    UNION ALL             ");
		builder.append("                 SELECT  B.LOCATION_ID, B.LOCATION_NAME, NULL AS MAX_ZONE_ID");
		builder.append("                   FROM DUAL, ( SELECT LOCATION_ID, LOCATION_NAME");
		builder.append("                                  FROM LOCATIONS");
		builder.append("                                 WHERE LOCATION_NAME LIKE 'CGV'||?) B        ");
		builder.append("                 GROUP BY B.LOCATION_ID, B.LOCATION_NAME) A");
		builder.append("          WHERE MAX_ZONE_ID IS NOT NULL OR ROWNUM = 1   ");


		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, seatCnt);
		statement.setString(2, locName);
		statement.setString(3, locName);
	

		int result = statement.executeUpdate();
		if (result > 0) {
			System.out.println("등록 성공!");
		} else {
			System.out.println("등록 실패!");
		}
		statement.close();
		connection.close();
	}

	public void updateZoneSeatCnt(String locName, int zoneId, int seatCnt) throws Exception { // 지점명, 상영관 번호로 좌석 정보
																								// update
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("        UPDATE ZONE");
		builder.append("           SET SEAT_CNT = ?");
		builder.append("         WHERE LOCATION_ID = (SELECT LOCATION_ID");
		builder.append("                                FROM LOCATIONS");
		builder.append("                               WHERE LOCATION_NAME = 'CGV'||?)");
		builder.append("           AND ZONE_ID = ?");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, seatCnt);
		statement.setString(2, locName);
		statement.setInt(3, zoneId);

		int result = statement.executeUpdate();
		if (result > 0) {
			System.out.println("등록 성공!");
		} else {
			System.out.println("등록 실패!");
		}
		statement.close();
		connection.close();
	}

	public void deleteZone(String locName, int zoneId) throws Exception { // 지점명, 상영관 번호로 상영관 YN -> N
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("        UPDATE ZONE");
		builder.append("           SET OPENED_YN = 'N'");
		builder.append("         WHERE LOCATION_ID = (SELECT LOCATION_ID ");
		builder.append("                                FROM LOCATIONS");
		builder.append("                               WHERE LOCATION_NAME = 'CGV'||?)");
		builder.append("           AND ZONE_ID = ?");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, locName);
		statement.setInt(2, zoneId);

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
