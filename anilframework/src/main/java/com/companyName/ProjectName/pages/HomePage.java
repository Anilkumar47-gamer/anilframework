package com.companyName.ProjectName.pages;

import mobileUtility.MobileUtil;

public class HomePage extends HomePageOR{
	
	
	
	private MobileUtil mobUtil;
	
	public HomePage(MobileUtil mobUtil) {
		
		super(mobUtil);
		this.mobUtil= mobUtil;
		
	}
	
	
	public void verifyAddToCart() {
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mobUtil.click(snacksAndBrandedFoodsText, "Snacks & BrandedFoods");
		mobUtil.verifyText(noNearByStoreFoundText, "No Nearby Store Found");
		mobUtil.pressBackButton();
		
		
		
		
	}

}
