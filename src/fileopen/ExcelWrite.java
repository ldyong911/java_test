package fileopen;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWrite {
	public static void main(String[] args) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(); // 새 엑셀 파일 만들기
			
			XSSFSheet sheet = workbook.createSheet(); // 엑셀 워크북에서 새 시트 만들기

			XSSFRow row = sheet.createRow(0); // x행에 만들기(접근)

			XSSFCell cell = row.createCell(0); // 해당 행의 y열 셀 만들기(접근)

			cell.setCellValue("엑셀 테스트~~"); // 접근한 셀에 값 입력하기
			
			FileOutputStream fos = new FileOutputStream("d:/excelTest.xlsx");
			
			workbook.write(fos); //파일 출력하기
			
			fos.close();
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}