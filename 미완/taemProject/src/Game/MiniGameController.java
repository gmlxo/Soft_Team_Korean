package Game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.AppUtil;
import util.JDBCUtil;

public class MiniGameController {
	
	String u;
	
	boolean b = (u == u);
	boolean w = (u != u);
	
	@FXML
	private Button ap;
	@FXML
	private Button bc;
	@FXML
	private Button deo;
	@FXML
	private ImageView back2;
	
	@FXML
	private TextField apc;
	
	public void GoBack2() {
		try {
			Parent m = FXMLLoader.load(getClass().getResource("MainGame.fxml"));
			Scene scene = new Scene(m);
			Stage primaryStage = (Stage) back2.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void AdditionalRequestController() {
		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;

		RedundancyCheckController();
		
		if (b) {
			AppUtil.alert("이미 추가 요청이 되어있는 게임입니다", null);
		}else if(w){
		String sql = "INSERT INTO `game`(`AdditionalRequest_DB`) VALUES (?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, apc.getText());
			pstmt.executeUpdate();
			AppUtil.alert("요청이 되었었습니다"+"\n"+"추가될때까지 조금만 기달려주세요", null);
			System.out.println("삽입 성공!!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("삽입 실패!");
		}if (con != null)
			try {
				con.close();

			} catch (Exception e) {
			}
		}
	}
	
	public void RedundancyCheckController() {
		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		System.out.println("u : " + u);
		
		String ns = "SELECT `AdditionalRequest_DB` FROM `game` WHERE 1";
		try {
			pstmt = con.prepareStatement(ns);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				u = rs.getString("AdditionalRequest_DB");
				b = (u == u);
				w = (u != u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rs != null)
			try {
				rs.close();
			} catch (Exception e) {
			}
		if (con != null)
			try {
				con.close();

			} catch (Exception e) {
			}
	}
	
	public void AdditionalRequest() {
		try {
			Parent aec = FXMLLoader.load(getClass().getResource("AdditionalRequestScreen.fxml"));
			Scene scene = new Scene(aec);
			Stage primaryStage = (Stage) ap.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void BackGo() {
		try {
			Parent in = FXMLLoader.load(getClass().getResource("AuxiliaryGame.fxml"));
			Scene scene = new Scene(in);
			Stage primaryStage = (Stage) bc.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
