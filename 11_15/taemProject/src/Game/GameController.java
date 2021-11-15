package Game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.AppUtil;
import util.JDBCUtil;

public class GameController {

	/*
	 * Ŭ���ø��� ������ 1�� ����
	 */
	
	@FXML
	private Pane sc;
	
	
	int i = 0;
	int j = 0;
	
	private static int level;
	
	public GameController() {
		level = 0;
	}

	public void ClickScore() {

		i++;
		j++;

		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("Ŭ�� �� : " + j);

		// if ((i % 3) == 0) {
		String n = "SELECT `clickscore_db` FROM `score` WHERE 1";
		try {
			pstmt = con.prepareStatement(n);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				Long clickscore_db = rs.getLong("clickscore_db");
				System.out.println("\n" + "DB�� : " + (clickscore_db + i + "\n"));

				String sql = "UPDATE `score` SET `clickscore_db`='" + (clickscore_db + i) + "'";
				try {

					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					i = 0;

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// }
		}
		if (rs != null)
			try {
				rs.close();
			} catch (Exception e) {// TODO: handle exception
			}
		if (con != null)
			try {
				con.close();

			} catch (Exception e) {
			}
	}

	/*
	 * ���� ĳ���� ������ â���� �̵�
	 */
	
	@FXML
	private Button MC;

	public void MainCharacter() {
		try {
			Parent main = FXMLLoader.load(getClass().getResource("MainCharacterController.fxml"));
			Scene scene = new Scene(main);
			Stage primaryStage = (Stage) mini.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * ���� ĳ���� ������ ��Ʈ�ѷ�
	 */
	
	@FXML
	private Button up;

	int c;
	int m;
	
	@FXML
	private Label labelLvl;

	public void MainLevelUp() {
		m++;
		
		level++;
		String lvl = "Lv " + level;
		
		labelLvl.setText(lvl);

//		int MainLv1 = 100;
//		int MainLv2 = 1000;
//		int MainLv3 = 17000;
//		int MainLv4 = 50000;
//		int MainLv5 = 150000;
//		int MainLv6 = 500000000;

		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String n = "SELECT `clickscore_db` FROM `score` WHERE 1";
		try {
			pstmt = con.prepareStatement(n);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int c = rs.getInt("clickscore_db");
				System.out.println(c);
				if (m == 1) {
					if (c > 99) {
						String a = "SELECT `main` FROM `level_db` WHERE 1";
						try {
							pstmt = con.prepareStatement(a);

							rs = pstmt.executeQuery();

							if (rs.next()) {
								String level = "UPDATE `level_DB` SET `main`= '1'";
								try {

									pstmt = con.prepareStatement(level);
									pstmt.executeUpdate();

								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (rs != null)
							try {
								rs.close();
							} catch (Exception e) {// TODO: handle exception
							}
						if (con != null)
							try {
								con.close();

							} catch (Exception e) {
							}
					} else if (c < 99) {
						AppUtil.alert("���� �����մϴ�", null);
						m = 0;
					}
				} else if (m >= 2) {
					AppUtil.alert("���", null);
					m = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * �̴ϰ��� â���� ����
	 */

	@FXML
	private Button mini;

	public void MiniGame() {
		try {
			Parent main = FXMLLoader.load(getClass().getResource("AuxiliaryGame.fxml"));
			Scene scene = new Scene(main);
			Stage primaryStage = (Stage) mini.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * �̴ϰ��� ��Ʈ�ѷ�
	 */
	
	@FXML
	private TextField receive2;
	@FXML
	private Label Send2;

	public void DeveloperModeController2() {
		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;

		String sql = "UPDATE `score` SET `clickscore_db`=(?) WHERE 1";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, receive.getText());
			pstmt.executeUpdate();
			System.out.println("���� ����!!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���� ����!");
		}
	}

	/*
	 * ������ ���� �̵� Ű
	 */

	@FXML
	private Button developer;

	public void name() {

		try {
			Parent s = FXMLLoader.load(getClass().getResource("DeveloperMode.fxml"));
			Scene scene = new Scene(s);
			Stage primaryStage = (Stage) developer.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * ���߸��
	 */

	@FXML
	private TextField receive;
	@FXML
	private Label Send;

	public void DeveloperModeController() {
		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;

		String sql = "UPDATE `score` SET `clickscore_db`=(?) WHERE 1";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, receive.getText());
			pstmt.executeUpdate();
			System.out.println("���� ����!!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���� ����!");
		}
	}
	/*
	 * �ʱ�ȭ
	 */

	@FXML
	private Button re;

	public void temporary() {
		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;

		String reset = "UPDATE `score` SET `clickscore_db`= 0";
		try {
			pstmt = con.prepareStatement(reset);
			pstmt.executeUpdate();
			System.out.println("DB�� : " + 0);
			AppUtil.alert("�ʱ�ȭ�Ǿ����ϴ�", null);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("����");
		}
	}
	/*
	 * �̹��� ���� & ǥ��
	 */

	/*
	 * �ڷΰ��� Ű
	 */

	@FXML
	private ImageView back;

	public void GoBack() {
		try {
			Parent m = FXMLLoader.load(getClass().getResource("MainGame.fxml"));
			Scene scene = new Scene(m);
			Stage primaryStage = (Stage) back.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
