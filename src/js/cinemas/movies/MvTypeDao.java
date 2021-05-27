package js.cinemas.movies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MvTypeDao {
	public List<MvTypeVO> selectMvTypeList() throws Exception { // 영화 타입 목록 조회
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "CINEMAPROJECT",
				"java");
		Statement statement = connection.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT TYPE_ID, TYPE_NAME     ");
		builder.append("  FROM MOVIE_TYPE");
		List<MvTypeVO> list = new ArrayList<MvTypeVO>();
		ResultSet resultSet = statement.executeQuery(builder.toString());
		while (resultSet.next()) {
			int typeId = resultSet.getInt("TYPE_ID");
			String typeName = resultSet.getNString("TYPE_NAME");
			list.add(new MvTypeVO(typeId, typeName));
		}
		resultSet.close();
		statement.close();
		connection.close();

		return list;
	}
}
