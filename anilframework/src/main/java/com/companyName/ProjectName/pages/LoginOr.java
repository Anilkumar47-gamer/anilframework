package com.companyName.ProjectName.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.pagefactory.AndroidFindBy;
import mobileUtility.MobileUtil;

public class LoginOr {
	MobileUtil mobtil;
	public LoginOr(MobileUtil mobtil) {
		 PageFactory.initElements(mobtil.getDriver(), this);
	}
	
	@FindBy(xpath="//android.widget.TextView[@text='GET STARTED']")
	protected WebElement webGetStart;
	
	
	@FindBy(id="com.android.permissioncontroller:id/permission_allow_foreground_only_button")
	protected WebElement whileUsingTheAppText;
	
	@FindBy(id="com.erspl.erspl:id/edtMobileno")
	protected WebElement enterMobNumTB;
	
	@FindBy(id="com.erspl.erspl:id/btnlogin")
	protected WebElement otpBT;
	
	@FindBy(id="com.erspl.erspl:id/edtotp")
	protected WebElement enterOtpTB;
	
	@FindBy(id="com.erspl.erspl:id/btnOTP")
	protected WebElement verifyOtpBT;
	
	
	
	
	
	
	
}
