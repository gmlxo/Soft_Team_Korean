package Casino;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.AppUtil;
import util.JDBCUtil;

public class CasinoController {

	int a;
	int b;
	int c;
	int d;
	int e;
	int w;
	int m;

	int gb;
	int mych;

	String as;
	String bs;
	String cs;
	String ds;
	String es;

	@FXML
	private Button gc;
	@FXML
	private Button stop;
	@FXML
	private Button start;
	@FXML
	private Button Times2;
	@FXML
	private Button Times3;
	@FXML
	private Button Times5;

	@FXML
	private Label FirstNumber;
	@FXML
	private Label SecondNumber;
	@FXML
	private Label ThirdNumber;
	@FXML
	private Label FourthNumber;
	@FXML
	private Label FifthNumber;

	@FXML
	private TextField bet;

	Random ra = new Random();

	int arr[] = new int[5]; // ¹è¿­¼±¾ð

	public void NumberController() {
		MoneyYouCurrentlyHave();
		
		if (mych > 0) {
			if (m > 0) {
				number();
				System.out.println("\n" + "as : " + as + "\n" + "bs : " + bs + "\n" + "cs : " + cs + "\n" + "ds : " + ds
						+ "\n" + "es : " + es);

				FirstNumber.setText(as);
				SecondNumber.setText(bs);
				ThirdNumber.setText(cs);
				FourthNumber.setText(ds);
				FifthNumber.setText(es);

				calculate();
			} else {
				AppUtil.alert("µ·À» °É°í ½ÃÀÛÇØ ÁÖ¼¼¿ä", null);
			}
		} else {
			expulsion();
		}
	}

	public void number() {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = ra.nextInt(4);
			for (int j = 0; j < i; j++) {
				if (arr[i] == arr[j]) {
					w++;
					// }
				}
			}
		}
		System.out.println("\n" + "w : " + w);
		a = arr[0];
		b = arr[1];
		c = arr[2];
		d = arr[3];
		e = arr[4];

		as = a + "";
		bs = b + "";
		cs = c + "";
		ds = d + "";
		es = e + "";

	}

	public void calculate() {
		System.out.println("ÀÛµ¿");

		CheckTheAmountBet();
		MoneyYouCurrentlyHave();
		System.out.println("\n" + mych + "\n" + gb);
		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;

		if (w < 3) {

			String sql = "UPDATE `score` SET `clickscore_DB`='" + (mych - gb) + "'";

			try {
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
				System.out.println("»ðÀÔ ¼º°ø!!");

				AppUtil.alert("³Ê ÀÒÀ½ ¤»", null);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("»ðÀÔ ½ÇÆÐ!");
			}
		} else if (w == 3) {

			String sql = "UPDATE `score` SET `clickscore_DB`='" + (mych + (gb * 1.3)) + "'";

			try {
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
				System.out.println("»ðÀÔ ¼º°ø!!");
				AppUtil.alert("1.3¹è ¤º¤»", null);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("»ðÀÔ ½ÇÆÐ!");
			}
		} else if (w == 4) {

			String sql = "UPDATE `score` SET `clickscore_DB`='" + (mych + (gb * 1.5)) + "'";

			try {
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
				System.out.println("»ðÀÔ ¼º°ø!!");
				AppUtil.alert("1.5¹è ¤º¤» ", null);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("»ðÀÔ ½ÇÆÐ!");
			}
		} else if (w >= 5) {
			String sql = "UPDATE `score` SET `clickscore_DB`='" + (mych + (gb * 3)) + "'";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
				System.out.println("»ðÀÔ ¼º°ø!!");
				AppUtil.alert("¿ç 3¹è ¤º¤» ", null);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("»ðÀÔ ½ÇÆÐ!");
			}
		}
	}

	public void expulsion() {
			AppUtil.alert("ºúÀïÀÌ °ÅºÎ¿ä", null);
			GoBack();
	}

	public void CheckTheAmountBet() {

		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String ns = "SELECT `gambling_db` FROM `game`";
		try {
			pstmt = con.prepareStatement(ns);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int www = rs.getInt("gambling_db");
				gb = www;
				System.out.println("gb : " + gb);
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

	public void MoneyYouCurrentlyHave() {

		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String ns = "SELECT `clickscore_DB` FROM `score`";
		try {
			pstmt = con.prepareStatement(ns);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int sss = rs.getInt("clickscore_DB");
				mych = sss;
				System.out.println("mych : " + mych);
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

	public void GamblingController() {
		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;

		String sql = "UPDATE `game` SET `gambling_db`=(?) WHERE 1";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bet.getText());
			pstmt.executeUpdate();
			System.out.println("»ðÀÔ ¼º°ø!!");
			m = 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("»ðÀÔ ½ÇÆÐ!");
		}
	}

	public void GoBack() {

		try {
			Parent m = FXMLLoader.load(getClass().getResource("/Game/MainGame.fxml"));
			Scene scene = new Scene(m);
			Stage primaryStage = (Stage) stop.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
