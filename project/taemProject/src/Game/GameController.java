package Game;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.ResultSet;

//import ThreadProhect.BeepTaek;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import util.JDBCUtil;

public class GameController {

	@FXML
	private Pane sc;

	// ClickScore
	// public void ClickScore(ActionEvent i) {
	public void ClickScore() {
		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String n = "SELECT `clickscore_db` FROM `score` WHERE 1";
		try {
			pstmt = con.prepareStatement(n);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				int clickscore_db = rs.getInt("clickscore_db");
				System.out.println(clickscore_db);

				String sql = "UPDATE `score` SET `clickscore_db`='" + (clickscore_db + 1) + "'";
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
