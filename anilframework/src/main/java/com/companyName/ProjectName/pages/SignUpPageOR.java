package com.companyName.ProjectName.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import mobileUtility.MobileUtil;

public class SignUpPageOR {

	
	MobileUtil mobtil;
	public SignUpPageOR(MobileUtil mobtil) {
		 PageFactory.initElements(mobtil.getDriver(), this);
	}
	
	@FindBy(xpath="//android.widget.TextView[@text='GET STARTED']")
	protected WebElement webGetStart;
	
	@FindBy(id="com.android.permissioncontroller:id/permission_allow_foreground_only_button")
	protected WebElement whileUsingTheAppText;
	
	@FindBy(xpath="//android.widget.TextView[@text='New User ?']")
	protected WebElement newUserLK;
	
	@FindBy(id="com.erspl.erspl:id/edfname")
	protected WebElement firstNameTB;
	
	@FindBy(id="com.erspl.erspl:id/edlmidname")
	protected WebElement lastNameTB;
	
	@FindBy(id="com.erspl.erspl:id/edmobile")
	protected WebElement mobileNumTB;
	
	@FindBy(id="com.erspl.erspl:id/selectgender")
	protected WebElement genderDD;
	
	@FindBy(xpath="//android.widget.CheckedTextView[@text='Male']")
	protected WebElement maleText;
	
	@FindBy(id="com.erspl.erspl:id/signupbtn")
	protected WebElement sendOTPBT;
	
	@FindBy(id="com.erspl.erspl:id/edtotp")
	protected WebElement enterOtpTB;
	
	@FindBy(id="com.erspl.erspl:id/btnOTP")
	protected WebElement verifyOtpBT;
	
	@FindBy(id="com.erspl.erspl:id/confirm_button")
	protected WebElement confirmLocBT;
	
	@FindBy(id="com.erspl.erspl:id/flat")
	protected WebElement enterFlatDetailsTB;
	
	@FindBy(id="com.erspl.erspl:id/edtlandmark")
	protected WebElement enterStreetTB;
	
	@FindBy(id="com.erspl.erspl:id/btnconfprcd")
	protected WebElement confirmAndProceedBT;

	
	
}
