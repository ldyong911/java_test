package drag;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import com.jfoenix.controls.JFXRadioButton;

public class DragController implements Initializable{
	@FXML TextArea ta;
	@FXML TextField tf_memo;
	@FXML TextField tf_slider;
	@FXML Button btn_memo;
	@FXML Button btn_slider;
	@FXML Slider slider;

	boolean flag = false;
	@FXML JFXRadioButton radio;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//라디오 색변경
		radio.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				radio.setStyle("-jfx-selected-color: red;");
			}
		});
		
		btn_memo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				flag = !flag;
				ta.selectedTextProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue,
							String newValue) {

						if(flag) {
							tf_memo.setText(newValue);
							ta.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");
							
							System.out.println(ta.getSelection().getStart());
							System.out.println(ta.getSelection().getEnd());
						}else {
							ta.setStyle("-fx-text-fill: black; -fx-font-size: 12px;");
						}
					}
				});
				
			}
		});
		
		btn_slider.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				slider.increment();
//				slider.decrement();
				tf_slider.setText((int)slider.getValue()+"");
				System.out.println((int)slider.getValue());
				
				slider.valueProperty().addListener(new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						if(newValue.intValue() == 50) {
							tf_slider.setText("준용이는 로아폐인");
						}else {
							tf_slider.setText("도현이는 멍청이");
						}
					}
				});
			}
		});
		
	}
}