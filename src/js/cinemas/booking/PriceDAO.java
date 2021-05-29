package js.cinemas.booking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PriceDAO {
	public int returnPriceAmt(int priceId) throws Exception { // priceId 받아서 가격리턴 메소드

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.22:1521/xe", "CINEMAPROJECT",
				"java");
		StringBuilder builder = new StringBuilder();

		builder.append("    SELECT PRICE_AMT");
		builder.append("      FROM TIX_PRICE");
		builder.append("     WHERE PRICE_ID = ?");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, priceId);

		int rate = 0;
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			rate = resultSet.getInt("PRICE_AMT");
		}

		resultSet.close();
		statement.close();
		connection.close();

		return rate;
	}
}
