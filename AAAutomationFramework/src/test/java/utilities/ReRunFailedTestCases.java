package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class ReRunFailedTestCases implements IRetryAnalyzer{

 private int retrycount = 0;
 private static final int maxcount=2;
 

	@Override
	public boolean retry(ITestResult result) {
		if(retrycount < maxcount) {
			retrycount++;
			return true;
		}
		return false;
	}

}
