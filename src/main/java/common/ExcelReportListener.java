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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExcelReportListener implements IInvokedMethodListener, ITestListener {
	Map<String, ArrayList<String>> allDataAfterTest = new HashMap<String, ArrayList<String>>();
	private static HSSFWorkbook excelBook;
	private static HSSFSheet excelSheet;
	private static HSSFCell testCaseName;
	private static HSSFCell platformCellValue;
	private static HSSFCell resultCellValue;
	private static HSSFCell exceptionCell;
	private static HSSFCell timeToRunCell;
	private static final Log log = LogFactory.getLog(ExcelReportListener.class);

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		setCellData(context);
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult result) {
		// TODO Auto-generated method stub
		addDataAfterTest(method, result);
		// setCellData();
	}

	private void addDataAfterTest(IInvokedMethod method, ITestResult result) {
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

	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult result) {
		// TODO Auto-generated method stub
	}

	private void setCellData(ITestContext context) {
		try {
			FileInputStream file = new FileInputStream(new File(Constant.reportFilePath));
			excelBook = new HSSFWorkbook(file);
			excelSheet = excelBook.getSheet("Test");
			int lastRowNum = excelSheet.getLastRowNum();
			// Read row Excel
			for (int i = 1; i <= lastRowNum; i++) {
				// Set cell
				testCaseName = excelSheet.getRow(i).getCell(0);
				platformCellValue = excelSheet.getRow(i).getCell(1);
				resultCellValue = excelSheet.getRow(i).getCell(2);
				exceptionCell = excelSheet.getRow(i).getCell(3);
				timeToRunCell = excelSheet.getRow(i).getCell(4);
				// Read dataAfterTest
				for (Map.Entry<String, ArrayList<String>> entry : allDataAfterTest.entrySet()) {
					if (entry.getKey().equals(testCaseName.getStringCellValue())) {
						// Check platform
						int dem = 0;
						if (platformCellValue.getStringCellValue().isEmpty()) {
							// Set excel values
							resultCellValue.setCellValue(entry.getValue().get(0));
							platformCellValue.setCellValue(entry.getValue().get(1));
							exceptionCell.setCellValue(entry.getValue().get(2));
							timeToRunCell.setCellValue(entry.getValue().get(3));
							dem++;
						} else {
							// Add more row
							excelSheet.shiftRows(i + 1, lastRowNum, 1);
							Row newRow = excelSheet.createRow(i + 1);
							// Copy testcase to new Cell
							Cell newTestCaseCell = newRow.createCell(0);
							newTestCaseCell.setCellValue(testCaseName.getStringCellValue());
							// Add new Cell
							for (int j = 1; j <= 4; j++) {
								newRow.createCell(j);
							}
							// Update last row number
							lastRowNum = excelSheet.getLastRowNum();
							break;
						}
						if (dem != 0) {
							mergeCell(excelSheet, i - 1, i);
						}
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

	private void mergeCell(HSSFSheet excelSheet, int firstRow, int lastRow) {
		excelSheet.addMergedRegion(new CellRangeAddress(firstRow, // first row
				lastRow, // last row (0-based)
				0, // first column (0-based)
				0 // last column (0-based)
		));
	}

	private String getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		calendar.setTimeInMillis(millis);
		return format.format(calendar.getTime());
	}

}
