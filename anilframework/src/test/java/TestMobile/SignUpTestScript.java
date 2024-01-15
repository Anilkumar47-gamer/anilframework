package TestMobile;

import org.testng.annotations.Test;

import com.companyName.ProjectName.pages.SignUpPage;

import basetest.TestBase;
import mobileUtility.RandomUtilization;

public class SignUpTestScript extends TestBase{
	
	
	
	@Test
	public void signUp() throws InterruptedException {
		
		SignUpPage signup=new SignUpPage(utilityObj);
		signup.signUp(prop.getProperty("firstName"), prop.getProperty("lastName"),RandomUtilization.randomreturn(), prop.getProperty("OTP"), prop.getProperty("flatDetails"), prop.getProperty("streetDetails"));
	}
	

}
