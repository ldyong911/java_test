package fileopen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FileChooserMain extends Application{
	static Stage rootStage = null;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("fileChooser.fxml"));
		
		Scene scene = new Scene(parent);
		rootStage = primaryStage;
		rootStage.setScene(scene);
		rootStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}