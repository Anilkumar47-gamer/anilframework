package com.companyName.ProjectName.pages;

import mobileUtility.MobileUtil;

public class SignUpPage extends SignUpPageOR{
	
	
	
	MobileUtil mobUtil;
	public SignUpPage(MobileUtil mobUtil) {
		super(mobUtil);
		this.mobUtil= mobUtil;
	}
	
	
	public void signUp(String firstName, String lastName, String mobNum, String enterOTP, String flatDetails, String streetDetails) throws InterruptedException {
		
		System.out.println("Start Test Cases Of SignUp Pages");
		mobUtil.click(webGetStart, "Get Started");
		mobUtil.click(whileUsingTheAppText, "While Using The App");
		mobUtil.click(newUserLK, "New User");
		mobUtil.inputData(firstNameTB, firstName);
		mobUtil.inputData(lastNameTB, lastName);
		mobUtil.inputData(mobileNumTB, mobNum);
		mobUtil.click(genderDD, "Gender");
		mobUtil.click(maleText,"Male Gender");
		mobUtil.click(sendOTPBT, "Send OTP ");
		mobUtil.inputData(enterOtpTB, enterOTP);
		mobUtil.click(verifyOtpBT, "Verify OTP");
		mobUtil.click(confirmLocBT, "Conifirm Location");
		mobUtil.inputData(enterFlatDetailsTB, flatDetails);
		mobUtil.inputData(enterStreetTB, streetDetails);
		mobUtil.click(confirmAndProceedBT, "Confirm and Proceed");
		
	}
	
	
	

}
