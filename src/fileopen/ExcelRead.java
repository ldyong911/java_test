package fileopen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {
	public static void main(String[] args) {
		
		try {
			FileInputStream fis = new FileInputStream("d:/excelTest.xlsx"); //파일 읽기
			
			XSSFWorkbook workbook = new XSSFWorkbook(fis); //엑셀파일을 관리하는 객체로 받아오기
			
			XSSFSheet sheet = workbook.getSheetAt(0); // 인덱스로 원하는 시트 가져오기

			XSSFCell cell = sheet.getRow(0).getCell(0); // 해당 시트에서 x행 y열의 셀 가져오기

			CellType cellType = cell.getCellType(); // 메서드로 타입확인 후 타입별로 데이터 받기
			                                        //(NumberCell, StringCell, BooleanCell등..)
			
			// 셀에 해당되는 데이터 읽어오기
			switch(cellType) {
			case NUMERIC:
				System.out.println(cell.getNumericCellValue());
				break;
			case STRING:
				System.out.println(cell.getStringCellValue());
				break;
			case FORMULA: //계산식
				System.out.println(cell.getCellFormula());
				break;
			case BLANK: //아무것도 설정되어 있지 않을때
				System.out.println("");
				break;
			case ERROR: //에러코드의 경우
				System.out.println(cell.getErrorCellValue());
				break;
			default: // 그외의 경우
				System.out.println(cell.getRichStringCellValue());
				break;
			}
			
			workbook.close();
			fis.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}