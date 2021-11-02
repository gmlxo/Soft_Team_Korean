package Game;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import util.JDBCUtil;

public class GameController {

	@FXML
	private Pane sc;

	// ClickScore
	public void ClickScore(ActionEvent i) {

		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		
		
		if (sc == i.getSource()) {
			String n = "SELECT `score` FROM `clickscore_db`";
			try {
				pstmt = con.prepareStatement(n);
			} catch (Exception e) {
				e.printStackTrace();
			}

			String sql = "UPDATE `clickscore_db` SET `score`='" + (n + 1) + "'";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
