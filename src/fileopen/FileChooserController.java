package fileopen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FileChooserController implements Initializable{
	@FXML AnchorPane anchor;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Stage stage = FileChooserMain.rootStage;
		
		// 파일열기 창
		Button btnFileOpen = new Button("OpenFileChooser 실행");
		btnFileOpen.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();

			// 확장자별로 파일 구분하는 필터 등록하기
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Excel Files", "*.xls", "*.xlsx"),
					new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
					new ExtensionFilter("Text Files", "*.txt"));

			// Dialog창에서 '열기'버튼을 누르면선택한 파일 정보가 반환되고
			// '취소'버튼을 누르면 null이 반환된다.
			File selectFile = fileChooser.showOpenDialog(FileChooserMain.rootStage);
			String fileName = selectFile.toString();
			String xlsName = fileName.substring(fileName.length()-3, fileName.length());
			String xlsxName = fileName.substring(fileName.length()-4, fileName.length());
			if ((selectFile != null) && (xlsName.equals("xls") || xlsxName.equals("xlsx"))) {
				// 이영역에서 파일내용을 일거오는 작업을 수행한다.
				System.out.println("OPEN :" + selectFile.getPath());
				
				try {
					FileInputStream fis = new FileInputStream(selectFile); //파일 읽기
					
					XSSFWorkbook workbook = new XSSFWorkbook(fis); //엑셀파일을 관리하는 객체로 받아오기
					
					XSSFSheet sheet = workbook.getSheetAt(0); // 인덱스로 원하는 시트 가져오기
					
//					XSSFCell cell = sheet.getRow(0).getCell(0); // 해당 시트에서 x행 y열의 셀 가져오기

//					CellType cellType = cell.getCellType(); // 메서드로 타입확인 후 타입별로 데이터 받기
					                                        //(NumberCell, StringCell, BooleanCell등..)
					
					System.out.println("행번호" + sheet.getPhysicalNumberOfRows());
					System.out.println("열번호" + sheet.getRow(0).getPhysicalNumberOfCells());
					
					//엑셀 시트의 행, 열
					for(int i=0; i<sheet.getLastRowNum(); i++) {
						for(int j=0; j<sheet.getRow(i).getLastCellNum(); j++) {
							XSSFCell cell2 = sheet.getRow(i).getCell(j);
							
							CellType cellType2 = cell2.getCellType(); 
							
							// 셀에 해당되는 데이터 읽어오기
							switch(cellType2) {
							case NUMERIC:
								System.out.println(cell2.getNumericCellValue());
								break;
							case STRING:
								System.out.println(cell2.getStringCellValue());
								break;
							case FORMULA: //계산식
								System.out.println(cell2.getCellFormula());
								break;
							case BLANK: //아무것도 설정되어 있지 않을때
								System.out.println("");
								break;
							case ERROR: //에러코드의 경우
								System.out.println(cell2.getErrorCellValue());
								break;
							default: // 그외의 경우
								System.out.println(cell2.getRichStringCellValue());
								break;
							}
						}
					}
					
					//워크북 닫고, 스트림 닫는다
					workbook.close();
					fis.close();
					
				} catch (FileNotFoundException ee) {
					ee.printStackTrace();
				} catch (IOException ee) {
					ee.printStackTrace();
				}
			}
		});
		
		anchor.getChildren().addAll(btnFileOpen);
	}
}