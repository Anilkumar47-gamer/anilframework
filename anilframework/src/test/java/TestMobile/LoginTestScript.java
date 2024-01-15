package TestMobile;

import org.testng.annotations.Test;

import com.companyName.ProjectName.pages.HomePage;
import com.companyName.ProjectName.pages.Login;
import basetest.TestBase;

public class LoginTestScript extends TestBase {
	
	@Test
	public void login() throws InterruptedException {
		
		Login lgn=new Login(utilityObj);
		lgn.loginApplication(prop.getProperty("mobileNo"),prop.getProperty("OTP"));
		HomePage home=new HomePage(utilityObj);
		home.verifyAddToCart();
		
	}

	
	 
	
	
	
}
