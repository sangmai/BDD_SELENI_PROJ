package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class TestAnnotationTransformerListener implements IAnnotationTransformer {
	private static HSSFSheet excelSheet;
	private static HSSFWorkbook excelBook;

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		// TODO Auto-generated method stub
		annotation.setEnabled(false);
		try {
			for (String testcaseName : getTestcaseName("Test")) {
				if (testMethod.getName().equals(testcaseName)) {
					annotation.setEnabled(true);
					System.out.println(testMethod.getName() + " is running");
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ArrayList<String> getTestcaseName(String excelSheetName) throws IOException {
		ArrayList<String> testcaseName = new ArrayList<>();
		try {
			FileInputStream file = new FileInputStream(new File(Constant.reportFilePath));
			excelBook = new HSSFWorkbook(file);
			excelSheet = excelBook.getSheet(excelSheetName);
			for (int i = 1; i <= excelSheet.getLastRowNum(); i++) {
				testcaseName.add(excelSheet.getRow(i).getCell(0).getStringCellValue());
			}
			excelBook.close();
			file.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return testcaseName;
	}
}
