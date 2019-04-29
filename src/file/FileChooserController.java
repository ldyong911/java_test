package file;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FileChooserController implements Initializable{
	@FXML AnchorPane anchor;
	
	private Desktop desktop = Desktop.getDesktop();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Stage stage = FileChooserMain.rootStage;
		
		HBox root = new HBox(10);
		root.setAlignment(Pos.CENTER);
		
		// 파일열기 창
		Button btnFileOpen = new Button("OpenFileChooser 실행");
		btnFileOpen.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();

			// 확장자별로 파일 구분하는 필터 등록하기
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
					new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
					new ExtensionFilter("Audio Files", "*.wav", "*.mp3"), new ExtensionFilter("All Files", "*.*"));

			// Dialog창에서 '열기'버튼을 누르면선택한 파일 정보가 반환되고
			// '취소'버튼을 누르면 null이 반환된다.
			File selectFile = fileChooser.showOpenDialog(FileChooserMain.rootStage);
			if (selectFile != null) {
				// 이영역에서 파일내용을 일거오는 작업을 수행한다.
				System.out.println("OPEN :" + selectFile.getPath());
				openFile(selectFile);
			}
		});
		
		//파일 저장 창
		Button btnFileSave = new Button("SaveFileChooser 실행");
		btnFileSave.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
			
			File selFile = fileChooser.showOpenDialog(FileChooserMain.rootStage);
			if (selFile != null) {
				// 이영역에서 파일내용을 저장하는 작업을 수행한다.
				System.out.println("OPEN :" + selFile.getPath());
			}
		});
		
		
		///////////////////////이미지 우클릭시 저장문구 뜨기 이벤트
		ImageView imageView = new ImageView();
		Image image = new Image(getClass().getResourceAsStream("../img/iv.png"));
		imageView.setImage(image);
		
		final ContextMenu cm = new ContextMenu();
		MenuItem cmItem1 = new MenuItem("Copy Image");
		cmItem1.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent e) {
		        Clipboard clipboard = Clipboard.getSystemClipboard();
		        ClipboardContent content = new ClipboardContent();
		        content.putImage(imageView.getImage());
		        clipboard.setContent(content);
		    }
		});

		cm.getItems().add(cmItem1);
		imageView.addEventHandler(MouseEvent.MOUSE_CLICKED,
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		            if (e.getButton() == MouseButton.SECONDARY)  
		                cm.show(imageView, e.getScreenX(), e.getScreenY());
		        }
		});
		
		cmItem1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("하준용");
			}
		});
		
		root.getChildren().addAll(imageView, btnFileOpen, btnFileSave);
		anchor.getChildren().addAll(root);
	}
	
	
	 private void openFile(File file) {
		try {
			//파일 선택하면 열기
			desktop.open(file);
		} catch (IOException ex) {
			Logger.getLogger(FileChooserController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}