package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	//Data Providers 1
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		String path=".\\testData\\OpenCart_LoginData.xlsx";
		ExcelUtility utility=new ExcelUtility(path);
		int totalrows=utility.getRowCount("Sheet1");
		int totalcols=utility.getCellCount("Sheet1",1);
		String login_data[][]=new String[totalrows][totalcols]; //created for two dimensional array which can stores data from excel.
		for(int i=1;i<=totalrows;i++) {			//first row table header is there so we have to start form 1(means from 2nd row)
			for(int j=0;j<totalcols;j++) {
				login_data[i-1][j]=utility.getCellData("Sheet1", i, j);
			}
		}
		return login_data;
	}
	
}
