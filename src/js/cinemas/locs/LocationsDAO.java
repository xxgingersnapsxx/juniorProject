package js.cinemas.locs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LocationsDAO {
	public List<LocationsWithCityVO> selectAllLocationsList() throws Exception { // 전체 영화관 목록 조회
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");
		Statement statement = connection.createStatement();
		StringBuilder builder = new StringBuilder();

		builder.append("            SELECT LOCATIONS.LOCATION_ID, CITIES.CITY_NAME, LOCATIONS.LOCATION_NAME");
		builder.append("              FROM LOCATIONS, CITIES");
		builder.append("             WHERE LOCATIONS.CITY_ID = CITIES.CITY_ID");

		List<LocationsWithCityVO> list = new ArrayList<LocationsWithCityVO>();
		ResultSet resultSet = statement.executeQuery(builder.toString());
		while (resultSet.next()) {
			String locationId = resultSet.getString("LOCATION_ID");
			String cityName = resultSet.getString("CITY_NAME");
			String locationName = resultSet.getString("LOCATION_NAME");
			list.add(new LocationsWithCityVO(locationId, cityName, locationName));
		}
		resultSet.close();
		statement.close();
		connection.close();

		return list;
	}

	// FIXME 여기부터 하기
	public List<LocationsWithCityVO> selectAllLocListByCity(String ctName) throws Exception { // 영화관 목록 도시별 조회, 매개변수 :
																								// 도시명
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");

		StringBuilder builder = new StringBuilder();
		builder.append("        SELECT LOCATIONS.LOCATION_ID, CITIES.CITY_NAME, LOCATIONS.LOCATION_NAME");
		builder.append("              FROM LOCATIONS, CITIES");
		builder.append("             WHERE LOCATIONS.CITY_ID = CITIES.CITY_ID");
		builder.append("               AND CITIES.CITY_NAME = ?");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, ctName);

		List<LocationsWithCityVO> list = new ArrayList<LocationsWithCityVO>();
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			String locationId = resultSet.getString("LOCATION_ID");
			String cityName = resultSet.getString("CITY_NAME");
			String locationName = resultSet.getString("LOCATION_NAME");
			list.add(new LocationsWithCityVO(locationId, cityName, locationName));
		}

		resultSet.close();
		statement.close();
		connection.close();

		return list;
	}

	public String returnLocId(String locName) throws Exception { // 지점 location_id 리턴 메소드
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");

		StringBuilder builder = new StringBuilder();
		builder.append("      SELECT LOCATION_ID");
		builder.append("        FROM LOCATIONS");
		builder.append("       WHERE LOCATION_NAME LIKE 'CGV'||?");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, locName);

		String locationId = null;
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			locationId = resultSet.getString("LOCATION_ID");
		}

		resultSet.close();
		statement.close();
		connection.close();

		return locationId;
	}

	public void insertLocation(LocationsVO vo) throws Exception { // 영화관 추가
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("           INSERT INTO LOCATIONS (LOCATION_ID");
		builder.append("                                , CITY_ID, LOCATION_NAME");
		builder.append("                                , LOC_ADDR1, LOC_ADDR2");
		builder.append("                                , LOC_TYPE_ID)                                         ");
		builder.append(
				"                SELECT  TO_CHAR(SEQ_LOCATIONS.NEXTVAL)|| TRIM(TO_CHAR(SEQ_LOCATIONS02.NEXTVAL, '000'))");
		builder.append("                      , CITY_ID                         ");
		builder.append("                      , 'CGV'||?");
		builder.append("                      , ?, ?");
		builder.append("                      , NULL");
		builder.append("                  FROM CITIES");
		builder.append("                 WHERE CITY_NAME LIKE '%'||?||'%'");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, vo.getLocationName());
		statement.setString(2, vo.getLocAddr1());
		statement.setString(3, vo.getLocAddr2());
		statement.setString(4, vo.getLocAddr1().substring(0, 1));

		int result = statement.executeUpdate();
		if (result > 0) {
			System.out.println("등록 성공!");
		} else {
			System.out.println("등록 실패!");
		}
		statement.close();
		connection.close();
	}

	public void updateLocationYN(String locName) throws Exception { // 영화관 운영 정보 YN 변경

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("           UPDATE LOCATIONS");
		builder.append("              SET LOCATIONS_YN = 'N'");
		builder.append("            WHERE LOCATION_ID = (SELECT LOCATION_ID");
		builder.append("                                   FROM LOCATIONS");
		builder.append("                                  WHERE LOCATION_NAME = 'CGV'||?)");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, locName);

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
