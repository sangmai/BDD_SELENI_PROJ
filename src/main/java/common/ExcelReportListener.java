package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class ExcelReportListener implements IInvokedMethodListener {
	Map<String, String> testResultData = new HashMap<String, String>();
	Map<String, String> exceptionResult = new HashMap<String, String>();
	Map<String, String> timeToRunResult = new HashMap<String, String>();
	private static HSSFWorkbook excelBook;
	private static HSSFSheet excelSheet;

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult result) {
		// TODO Auto-generated method stub
		String timeStartRun = getTime(result.getStartMillis());
		String timeToRun = timeStartRun + " Take time: " + String
				.valueOf(TimeUnit.MILLISECONDS.toSeconds(result.getEndMillis() - result.getStartMillis())) + "s";
		try {
			if (result.getStatus() == ITestResult.SUCCESS) {
				testResultData.put(method.getTestMethod().getMethodName(), "PASSED");
				exceptionResult.put(method.getTestMethod().getMethodName(), "");
				timeToRunResult.put(method.getTestMethod().getMethodName(), timeToRun);
			} else if (result.getStatus() == ITestResult.FAILURE) {
				testResultData.put(method.getTestMethod().getMethodName(), "FAILED");
				exceptionResult.put(method.getTestMethod().getMethodName(), result.getThrowable().getMessage());
				timeToRunResult.put(method.getTestMethod().getMethodName(), timeToRun);
			} else if (result.getStatus() == ITestResult.SUCCESS_PERCENTAGE_FAILURE) {
				testResultData.put(method.getTestMethod().getMethodName(), "FAILED");
				exceptionResult.put(method.getTestMethod().getMethodName(), result.getThrowable().getMessage());
				timeToRunResult.put(method.getTestMethod().getMethodName(), timeToRun);
			}
		} catch (Exception e) {
			System.out.println("\nLog Message::@AfterMethod: Exception caught");
			e.printStackTrace();
		}

		setCellData(testResultData, exceptionResult, timeToRunResult);
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult result) {
		// TODO Auto-generated method stub

	}

	private void setCellData(Map<String, String> testResultData, Map<String, String> exceptionResult,
			Map<String, String> timeToRunResult) {
		try {
			FileInputStream file = new FileInputStream(new File(Constant.reportFilePath));
			excelBook = new HSSFWorkbook(file);
			excelSheet = excelBook.getSheet("Test");
			for (int i = 0; i <= excelSheet.getLastRowNum(); i++) {
				Cell resultCellValue = excelSheet.getRow(i).getCell(2);
				Cell exceptionCell = excelSheet.getRow(i).getCell(3);
				Cell timeToRunCell = excelSheet.getRow(i).getCell(4);
				String testCaseName = excelSheet.getRow(i).getCell(0).getStringCellValue();
				// Write test result into Excel
				for (Map.Entry<String, String> entry : testResultData.entrySet()) {
					if (entry.getKey().equals(testCaseName)) {
						resultCellValue.setCellValue(entry.getValue());
					}
				}
				// Write cause by into Excel
				for (Map.Entry<String, String> entry : exceptionResult.entrySet()) {
					if (entry.getKey().equals(testCaseName)) {
						exceptionCell.setCellValue(entry.getValue());
					}
				}
				// Write time to run into Excel
				for (Map.Entry<String, String> entry : timeToRunResult.entrySet()) {
					if (entry.getKey().equals(testCaseName)) {
						timeToRunCell.setCellValue(entry.getValue());
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
	private String getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		calendar.setTimeInMillis(millis);
		return format.format(calendar.getTime());
	}
}