package swoswithpacknegative;

import org.testng.annotations.Test;

public class SwoswithpackNegativeTest extends TestSWOSNegative {
	
	//THis is for the DeleteProcess
	
	@Test(priority = 0)
	public void TestaddGroups() throws InterruptedException {
		OpenPs();
		CheckGroups();
		CreatePackageCheck();	
	}
	@Test(priority = 1)
	public void Test() throws InterruptedException {
		
	}
	
	

}
