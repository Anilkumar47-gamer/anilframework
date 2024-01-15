package com.companyName.ProjectName.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import mobileUtility.MobileUtil;

public class HomePageOR {

	
	
	public HomePageOR(MobileUtil mobtil) {
		
		 PageFactory.initElements(mobtil.getDriver(), this);
		
		
	}
	
	
	@FindBy(id="com.erspl.erspl:id/tv2")
	protected WebElement noNearByStoreFoundText;
	
	@FindBy(id="com.erspl.erspl:id/tvcat")
	protected WebElement snacksAndBrandedFoodsText;
	
	
}
