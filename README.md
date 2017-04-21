# BDD_SELENI_PROJ
Selenium Automation Testing Framework
Excel Report for Selenium TestNG
How does it work ?
  1. Using Apache POI to read and write data from Excel 
  2. Using IInvokedMethodListener to get data after test via @Override afterInvocation method
  3. After testcase completed, using ITestListener via @Override onFinish method to write data into Excel file
  4. It will compare row by row to avoid overwrite data
