package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class ExcelReportListener implements IInvokedMethodListener {
	Map<String, ArrayList<String>> allDataAfterTest = new HashMap<String, ArrayList<String>>();
	private static HSSFWorkbook excelBook;
	private static HSSFSheet excelSheet;
	private static final Log log = LogFactory.getLog(ExcelReportListener.class);

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult result) {
		// TODO Auto-generated method stub
		ArrayList<String> dataAfterTest = new ArrayList<String>();
		String timeStartRun = getTime(result.getStartMillis());
		String timeToRun = timeStartRun + " Take time: "
				+ String.valueOf(TimeUnit.MILLISECONDS.toSeconds(result.getEndMillis() - result.getStartMillis()))
				+ "s";
		try {
			if (result.getStatus() == ITestResult.SUCCESS) {
				dataAfterTest.add("PASSED");
				dataAfterTest.add(result.getTestContext().getName());
				dataAfterTest.add("");
				dataAfterTest.add(timeToRun);
				allDataAfterTest.put(method.getTestMethod().getMethodName(), dataAfterTest);

			} else if (result.getStatus() == ITestResult.FAILURE) {
				dataAfterTest.add("FAILED");
				dataAfterTest.add(result.getTestContext().getName());
				dataAfterTest.add(result.getThrowable().getMessage());
				dataAfterTest.add(timeToRun);
				allDataAfterTest.put(method.getTestMethod().getMethodName(), dataAfterTest);

			} else if (result.getStatus() == ITestResult.SUCCESS_PERCENTAGE_FAILURE) {
				dataAfterTest.add("FAILED");
				dataAfterTest.add(result.getTestContext().getName());
				dataAfterTest.add(result.getThrowable().getMessage());
				dataAfterTest.add(timeToRun);
				allDataAfterTest.put(method.getTestMethod().getMethodName(), dataAfterTest);
			}
		} catch (Exception e) {
			log.info("\nLog Message::@AfterMethod: Exception caught");
			e.printStackTrace();
		}

		setCellData();
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult result) {
		// TODO Auto-generated method stub
	}

	private void setCellData() {
		try {
			FileInputStream file = new FileInputStream(new File(Constant.reportFilePath));
			excelBook = new HSSFWorkbook(file);
			excelSheet = excelBook.getSheet("Test");
			int lastRowNum = excelSheet.getLastRowNum();
			// Read row Excel
			for (int i = 1; i <= lastRowNum; i++) {
				log.info("row " + i);
				// Set cell
				String testCaseName = excelSheet.getRow(i).getCell(0).getStringCellValue();
				Cell platformCellValue = excelSheet.getRow(i).getCell(1);
				Cell resultCellValue = excelSheet.getRow(i).getCell(2);
				Cell exceptionCell = excelSheet.getRow(i).getCell(3);
				Cell timeToRunCell = excelSheet.getRow(i).getCell(4);
				// Read dataAfterTest
				for (Map.Entry<String, ArrayList<String>> entry : allDataAfterTest.entrySet()) {
					if (entry.getKey().equals(testCaseName)) {
						if (platformCellValue.getStringCellValue().equals("")) {
							log.info("platformCell is empty");
							// Set excel values
							resultCellValue.setCellValue(entry.getValue().get(0));
							platformCellValue.setCellValue(entry.getValue().get(1));
							exceptionCell.setCellValue(entry.getValue().get(2));
							timeToRunCell.setCellValue(entry.getValue().get(3));
						}
						log.info("platformCell is used");
						// Insert new row between
						addNewRow(excelSheet, i, lastRowNum);
						log.info("Add Row");
					}
				}
			}
			FileOutputStream fileOut = new FileOutputStream(new File(Constant.reportFilePath));
			excelBook.write(fileOut);
			fileOut.flush();
			fileOut.close();

		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	private void addNewRow(HSSFSheet excelSheet, int rowIndex, int lastRowNum) {
		// TODO Auto-generated method stub
		if (rowIndex >= 0 && rowIndex < lastRowNum) {
			excelSheet.shiftRows(rowIndex + 1, lastRowNum, 1);
		}
		if (rowIndex == lastRowNum) {
			Row addRow = excelSheet.getRow(rowIndex);
			if (addRow != null) {
				excelSheet.createRow(addRow.getRowNum());
			}
		}
	}

	private String getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		calendar.setTimeInMillis(millis);
		return format.format(calendar.getTime());
	}
}
