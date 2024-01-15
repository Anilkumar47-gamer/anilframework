package com.companyName.ProjectName.pages;

import mobileUtility.MobileUtil;

public class Login extends  LoginOr {
	MobileUtil mobUtil;
	public Login(MobileUtil mobUtil) {
		super(mobUtil);
		this.mobUtil= mobUtil;

	}



	public void loginApplication(String MoNumber,String enterOTP) throws InterruptedException {
		System.out.println("start testcase");
		mobUtil.click(webGetStart, "get start");
		//Thread.sleep(5000);
		mobUtil.click(whileUsingTheAppText, "Clik on while Using The App Text");
		//Thread.sleep(5000);

		mobUtil.inputData(enterMobNumTB, MoNumber);
		mobUtil.click(otpBT, "OPT Button Click Performed");
		mobUtil.inputData(enterOtpTB, enterOTP);
		mobUtil.click(verifyOtpBT, "Verify Click Perform Successfully");
	}
	
}
