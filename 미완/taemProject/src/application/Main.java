package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import util.AppUtil;
//import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/Game/MainGame.fxml"));
			SplitPane ap = (SplitPane) loader.load();
			Scene scene = new Scene(ap, 600, 650);
			primaryStage.setScene(scene);
			primaryStage.show();
			AppUtil.alert("클릭시 시작됩니다", null);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
