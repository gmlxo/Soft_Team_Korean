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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.AppUtil;
import util.JDBCUtil;
import javafx.scene.Scene;

public class GameController {

	int es;
	int lc;
	int set;
	int cli;
	int lev;

	int i = 0;
	int j = 0;
	int x = 0;
	int c = 0;
	int z = 0;
	int m = 0;

	// 캐릭터 레벨 가격 LevelPrice

	int lp1 = 100;
	int lp2 = 300;
	int lp3 = 700;
	int lp4 = 1000;
	int lp5 = 2400;
	int lp6 = 4500;
	int lp7 = 10000;
	int lp8 = 35000;
	int lp9 = 57000;
	int lp10 = 90000;
	int lp11 = 254000;

	int flp = (lp1 + lp2 + lp5 + lp6 + lp7 + lp8 + lp9 + lp10 + lp11);

	// 클릭당 오르는 돈 MoneyPerClick

	int mpc1 = 3;
	int mpc2 = 5;
	int mpc3 = 7;
	int mpc4 = 16;
	int mpc5 = 50;
	int mpc6 = 70;
	int mpc7 = 130;
	int mpc8 = 300;
	int mpc9 = 650;
	int mpc10 = 800;
	int mpc11 = 3500;

	String lvl;
	String ass;
	String click;
	String LpLabal;

	@FXML
	private Pane sc;

	@FXML
	private ImageView back;

	@FXML
	private Label Send;
	@FXML
	private Label asset;
	@FXML
	private Label labelLvl;
	@FXML
	private Label LevelPrice;
	@FXML
	private Label ClickLabel;

	@FXML
	private Text SpsLabel;

	@FXML
	private TextField receive;

	@FXML
	private Button re;
	@FXML
	private Button up;
	@FXML
	private Button nn;
	@FXML
	private Button gtg;
	@FXML
	private Button mini;
	@FXML
	private Button developer;

	private String viewName = "m";

//라벨 값 불러오는거
	@FXML
	private void initialize() {
		if (viewName.equals("lo")) { // MainCharacter

			LevelController();
			labelLvl.setText(lvl);
		}
	}

	public void LevelController() {
		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String ns = "SELECT `level_db` FROM `score`";
		try {
			pstmt = con.prepareStatement(ns);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int num = rs.getInt("level_db");

				lev = num;
				System.out.println(num);
				lvl = "Lv " + lev;
				System.out.println("lvl 값 : " + lvl);
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
				click = cli + " 원 / 클릭";
				System.out.println("click 값 : " + click);

				// ClickLabel.setText(click);

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
		IController();

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
				ass = "총 자산 " + (set + i) + " 원";
				System.out.println("ass 값 : " + ass);

				// asset.setText(ass);
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

	public void IController() {
		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String ns = "SELECT `click_DB` FROM `score`";
		try {
			pstmt = con.prepareStatement(ns);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int sl = rs.getInt("click_DB");
				i = sl;
				System.out.println("i : " + i);
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

	public void ClickScore() {
		if (es >= 1) {
			j = 0;
		}
		j++;
		System.out.println("\n");

		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("\n" + "클릭 수 : " + j);

		String n = "SELECT `clickscore_db` FROM `score` WHERE 1";
		try {

			ClickController();
			ClickScoreController();
			IController();

			ClickLabel.setText(click);
			asset.setText(ass);

			pstmt = con.prepareStatement(n);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				Long clickscore_db = rs.getLong("clickscore_db");
				System.out.println("현재 가지고 있는 돈 : " + (clickscore_db + i) + "\n");

				String sql = "UPDATE `score` SET `clickscore_db`='" + (clickscore_db + i) + "'";
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

		try {
			viewName = "lo";

			Parent lo = FXMLLoader.load(getClass().getResource("MainCharacterController.fxml"));
			Scene scene = new Scene(lo);
			Stage primaryStage = (Stage) nn.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void LevelCheck() {
		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String xc = "SELECT `level_db` FROM `score` WHERE 1";
		try {
			pstmt = con.prepareStatement(xc);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int q = rs.getInt("level_db");
				lc = q;
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

	public void GoToGambling() {
		LevelCheck();
		System.out.println("lc : " + lc);

		if (lc >= 3) {
			try {
				viewName = "to";

				Parent to = FXMLLoader.load(getClass().getResource("/Casino/GamblingRoomScreen.fxml"));
				Scene scene = new Scene(to);
				Stage primaryStage = (Stage) gtg.getScene().getWindow();
				primaryStage.setScene(scene);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			AppUtil.alert("3레벨 이상만 사용가능합니다", null);
		}
	}

	// 메인 캐릭터 레벨업 컨롤러

	public void CurrentLevel() {

		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String xc = "SELECT `level_db` FROM `score` WHERE 1";
		try {
			pstmt = con.prepareStatement(xc);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int xs = rs.getInt("level_db");
				System.out.println("xs : " + xs);

				if (xs == 0) {
					x = lp1;
					z = 1;
					m = mpc1;
					es = 1;
					// img1
				} else if (xs == 1) {
					x = lp2;
					z = 2;
					m = mpc2;
					es = 1;
				} else if (xs == 2) {
					x = lp3;
					z = 3;
					m = mpc3;
					es = 3;
				} else if (xs == 3) {
					x = lp4;
					z = 4;
					m = mpc4;
					es = 3;
					// img3
				} else if (xs == 4) {
					x = lp5;
					z = 5;
					m = mpc5;
					es = 3;
				} else if (xs == 5) {
					x = lp6;
					z = 6;
					m = mpc6;
					es = 3;
					// img4
				} else if (xs == 6) {
					x = lp7;
					z = 7;
					m = mpc7;
					es = 1;
				} else if (xs == 7) {
					x = lp8;
					z = 8;
					m = mpc8;
					es = 5;
					// img5
				} else if (xs == 8) {
					x = lp9;
					z = 9;
					m = mpc9;
					es = 5;
				} else if (xs == 9) {
					x = lp10;
					z = 10;
					m = mpc10;
					es = 5;
				} else if (xs == 10) {
					x = lp11;
					z = 11;
					m = mpc11;
					es = 6;
					// img6
				} else if (xs > 10) {
					AppUtil.alert("업데이트를 기달려 주세요", null);
					z = 11;
					m = mpc11;
					es = 6;
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

	public void LevelPriceLabelName() {
		LpLabal = "다음 레벨 가격은 " + x + "원 입니다";
		LevelPrice.setText(LpLabal);
	}

	public void MainLevelUp() {
		CurrentLevel();

		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		LevelController();
		labelLvl.setText(lvl);
		LevelPriceLabelName();
		LevelPrice.setText(LpLabal);

		String n = "SELECT `clickscore_db` FROM `score`";
		try {
			pstmt = con.prepareStatement(n);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int c = rs.getInt("clickscore_db");
				System.out.println("c : " + c + "\n" + "x : " + x);

				if (c >= x) {

					MainLevelController();
					MainLevelController2();
					MoneyPerClickController();
					LevelController();
					LevelPriceLabelName();

					labelLvl.setText(lvl);
					LevelPrice.setText(LpLabal);

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

	public void MoneyPerClickController() {
		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;

		String mpc = "UPDATE `score` SET `click_DB`='" + m + "'";
		try {

			pstmt = con.prepareStatement(mpc);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (con != null)
			try {
				con.close();

			} catch (Exception e) {
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

		String level = "UPDATE `score` SET `level_db`= '" + z + "'";
		try {

			pstmt = con.prepareStatement(level);
			pstmt.executeUpdate();

			labelLvl.setText(lvl);

			System.out.println("메인 캐릭터 레벨 : " + lev);

		} catch (Exception e) {
			e.printStackTrace();
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
				System.out.println("x : " + x);
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
		viewName = "in";
		try {
			Parent in = FXMLLoader.load(getClass().getResource("AuxiliaryGame.fxml"));
			Scene scene = new Scene(in);
			Stage primaryStage = (Stage) mini.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 개발자 모드로 이동

	public void name() {

		viewName = "sw";

		try {
			Parent sw = FXMLLoader.load(getClass().getResource("DeveloperMode.fxml"));
			Scene scene = new Scene(sw);
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
		System.out.println("전체 레벨 값 : " + flp);

		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;

		String reset = "UPDATE `score` SET `clickscore_db`= 0, `level_db`='0',`click_DB`= 1";

		try {
			pstmt = con.prepareStatement(reset);
			pstmt.executeUpdate();

			AppUtil.alert("초기화되었습니다" + "\n" + "\n" + "자산 : " + 0 + "\n" + "클릭 : " + 1 + "\n" + "캐릭터 레벨 : " + 0 + "\n",
					null);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("실패");
		}
	}

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
