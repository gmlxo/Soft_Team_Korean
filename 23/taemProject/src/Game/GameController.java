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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.AppUtil;
import util.JDBCUtil;

public class GameController {

	int m;
	
	int set;
	int per;
	int cli;
	int lev;

	int i = 0;
	int j = 0;
	int x = 0;
	int c = 0;
	int z = 0;
	
	String lvl;
	String sps;
	String ass;
	String click = "";
	
	@FXML
	private Pane sc;
	
	@FXML
	private ImageView back;
	
	@FXML
	private Label asset;	
	@FXML
	private Label Send;
	@FXML
	private Label Send2;
	@FXML
	private Label labelLvl;
	@FXML
	private Text SpsLabel;
	//@FXML
	//private Label ClickLabel;

	@FXML
	private TextField receive;
	@FXML
	private TextField receive2;
	
	@FXML
	private Button re;
	@FXML
	private Button up;
	@FXML
	private Button mc;
	@FXML
	private Button mini;
	@FXML
	private Button developer;

	private String viewName = "m";

//라벨 값 불러오는거

	public void initialize() {
		if (viewName.equals("m")) {

			ClickController();
			ClickScoreController();
			ScorePerSecondController();

			//ClickLabel.setText(click);
			//asset.setText(ass);
			//SpsLabel.setText(sps);
		}

		if (viewName.equals("loader")) {

			JDBCUtil db = new JDBCUtil();
			Connection con = db.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			String ns = "SELECT `main` FROM `level_db`";
			try {
				pstmt = con.prepareStatement(ns);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					int num = rs.getInt("main");

					lev = num;
					System.out.println(num);
					lvl = "Lv " + lev;
					System.out.println("lvl 값 : " + lvl);

					//labelLvl.setText(lvl);
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
	}
	public void ClickController() {

		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String snm = "SELECT `click_DB` FROM `score`";
		try {
			pstmt = con.prepareStatement(snm);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int fls = rs.getInt("click_DB");

				cli = fls;
				System.out.println(per);
				click = (String)(cli + " 원 / 클릭");
				System.out.println("click 값 : " + click);

				//ClickLabel.setText(click);

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
	
	public void ScorePerSecondController() {

		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String snm = "SELECT `ScorePerSecond_DB` FROM `score`";
		try {
			pstmt = con.prepareStatement(snm);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int bnd = rs.getInt("ScorePerSecond_DB");

				per = bnd;
				System.out.println(per);
				sps = (String)(per + " 원 / 초");
				System.out.println("sps 값 : " + sps);

				//SpsLabel.setText((String)sps);

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
	
	public void ClickScoreController() {

		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sn = "SELECT `clickscore_DB` FROM `score`";
		try {
			pstmt = con.prepareStatement(sn);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int msa = rs.getInt("clickscore_DB");

				set = msa;
				System.out.println(msa);
				ass = "총 자산 " + set + " 원";
				System.out.println("ass 값 : " + ass);

				//asset.setText(ass);
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

	// 클릭시 점수 오름

	public void ClickScore() {
		i++;
		j++;

		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("\n" + "클릭 수 : " + j);

		String n = "SELECT `clickscore_db` FROM `score` WHERE 1";
		try {
			pstmt = con.prepareStatement(n);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				Long clickscore_db = rs.getLong("clickscore_db");
				System.out.println("DB값 : " + (clickscore_db + i + "\n"));

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

	// 메인 캐릭터 레벨업 창으로 이동

	public void MainCharacter() {
		viewName = "loader";
		try { //MainCharacterController
			Parent loader = FXMLLoader.load(getClass().getResource("MainCharacterController.fxml"));
			Scene scene = new Scene(loader);
			Stage primaryStage = (Stage) mc.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 메인 캐릭터 레벨업 컨롤러
	
	public void CurrentLevel() {
		m++;

		int lV1 = 100;
		int lV2 = 500;
		int lV3 = 1000;
		int lV4 = 5000;
		int lV5 = 35000;
		int lV6 = 60000;

		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String xc = "SELECT `main` FROM `level_db` WHERE 1";
		try {
			pstmt = con.prepareStatement(xc);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int xs = rs.getInt("main");
				System.out.println("xs : " + xs);

				if (xs == 0) {
					x = lV1;
					z = 1;
					m = 1;
				} else if (xs == 1) {
					x = lV2;
					z = 2;
					m = 2;
				} else if (xs == 2) {
					x = lV3;
					z = 3;
					m = 3;
				} else if (xs == 3) {
					x = lV4;
					z = 4;
					m = 4;
				} else if (xs == 4) {
					x = lV5;
					z = 5;
					m = 5;
				} else if (xs == 5) {
					x = lV6;
					z = 6;
					m = 6;
				} else if (xs > 5 || m > 6) {
					AppUtil.alert("업데이트를 기달려 주세요", null);
				}
				System.out.println("xs : " + xs + "\n" + "x : " + x + "\n" + "z : " + z);
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

	public void MainLevelUp() {
		CurrentLevel();
		System.out.println("전체 레벨 값 : " + (100 + 500 + 1000 + 5000 + 35000 + 60000));
		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String n = "SELECT `clickscore_db` FROM `score`";
		try {
			pstmt = con.prepareStatement(n);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int c = rs.getInt("clickscore_db");
				System.out.println("c : " + c);
				System.out.println("x : " + x);

				if (c > x) {
					MainLevelController();
					MainLevelController2();

					//labelLvl.setText(lvl);

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
				} else if (c < x) {
					AppUtil.alert("돈이 부족합니다", null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void MainLevelController() {
		System.out.println("z : " + z);
		if (z < 0) {
			AppUtil.alert("응 버그 찾으시고 ㅋ", null);
		}
		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String level = "UPDATE `level_DB` SET `main`= '" + z + "'";
		try {

			pstmt = con.prepareStatement(level);
			pstmt.executeUpdate();

			//labelLvl.setText(lvl);

			System.out.println("메인 캐릭터 레벨 : " + lev);

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

	public void MainLevelController2() {
		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String n = "SELECT `clickscore_db` FROM `score`";
		try {
			pstmt = con.prepareStatement(n);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int cl = rs.getInt("clickscore_db");
				String eve = "UPDATE `score` SET `clickscore_db`= '" + (cl - x) + "'";
				try {

					pstmt = con.prepareStatement(eve);
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
			} catch (Exception e) {
			}
		if (con != null)
			try {
				con.close();
			} catch (Exception e) {
			}
	}

	// 미니겜 창으로 연결

	public void MiniGame() {
		viewName = "main";
		try {
			Parent main = FXMLLoader.load(getClass().getResource("AuxiliaryGame.fxml"));
			Scene scene = new Scene(main);
			Stage primaryStage = (Stage) mini.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 미니 게임 컨트롤러

	public void DeveloperModeController2() {
		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;

		String sql = "UPDATE `score` SET `clickscore_db`=(?) WHERE 1";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, receive.getText());
			pstmt.executeUpdate();
			System.out.println("삽입 성공!!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("삽입 실패!");
		}
	}

	// 개발자 모드로 이동

	public void name() {
		viewName = "s";
		try {
			Parent s = FXMLLoader.load(getClass().getResource("DeveloperMode.fxml"));
			Scene scene = new Scene(s);
			Stage primaryStage = (Stage) developer.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 개발자 모드

	public void DeveloperModeController() {
		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;

		String sql = "UPDATE `score` SET `clickscore_db`=(?) WHERE 1";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, receive.getText());
			pstmt.executeUpdate();
			System.out.println("삽입 성공!!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("삽입 실패!");
		}
	}

	// 초기화

	public void temporary() {
		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;

		String reset = "UPDATE `score` SET `clickscore_db`= 0";
		String reset2 = "UPDATE `level_db` SET `main`='0', `serve`='0' WHERE 1";
		try {
			pstmt = con.prepareStatement(reset);
			pstmt.executeUpdate();
			pstmt = con.prepareStatement(reset2);
			pstmt.executeUpdate();

			System.out.println("DB값 : " + 0);
			System.out.println("메인 캐릭터 레벨 : " + 0);
			AppUtil.alert("초기화되었습니다", null);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("실패");
		}
	}

	// 이미지 숨기 & 표시

	// 뒤로가기 키

	public void GoBack() {
		viewName = "m";
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
