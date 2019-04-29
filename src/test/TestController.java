package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TestController implements Initializable{
	@FXML Label label;
	@FXML TextField tf;
	@FXML Button btn;
	@FXML ImageView iv1;
	@FXML ImageView iv2;
	@FXML JFXButton btn_jfx;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			FileInputStream input1 = new FileInputStream("d:/달려라달리.jpg");
//			FileInputStream input1 = new FileInputStream("D:/A_TeachingMaterial/4.MiddleProject/workspace/ETN_Client/src/kr/or/ddit/etn/img/달려라달리.jpg");
			
			//이미지 경로를 넣어줄때는 네비게이터뷰에서 bin을 확인해야함
			Image image1 = new Image(getClass().getResourceAsStream("../img/iv.png"));
			
//			iv1.setImage(image1);
			iv2.setImage(image1);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		label.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				tf.setText("준용이는 로아폐인 - 라벨");
			}
		});
		
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tf.setText("준용이는 로아폐인 - 버튼");
			}
		});
		
		btn_jfx.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				tf.setText("중프라니~~~~~~~~~");
			}
		});
	}
}