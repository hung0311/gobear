package GoBear.actions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import GoBear.initiations.ConstantLib;
import GoBear.pages.TravelInsurancePg;
import GoBear.pages.pg_Home;
import automationLibrary.actions.ActionGen;
import automationLibrary.drivers.DriverWeb;
import automationLibrary.executions.ExecutionWeb;

public class Action {
	public static class Travel
	{
		/**
		 * Go to Travel page
		 * 
		 * @return
		 */
		public static void goToTravelPage() {	
				ActionGen.click(pg_Home.insuranceTab());	
				ActionGen.click(pg_Home.travelTab());
				ActionGen.click(pg_Home.showMyResultButton());
		}
		
		/**
		 * Clear all filters
		 * 
		 * @return
		 */
		public static void clearFilter() {	
				ActionGen.click(TravelInsurancePg.clearFilter());	
		}
		
		/**
		 * Filter By Promotion on Travel Page
		 * 
		 * @param promotionFilterOpt. The value should be picked from ConstantLib.Travel.Filter.filterPromotionOpt
		 * @return
		 */
		public static void filterByPromotion(String promotionFilterOpt) {	
			ActionGen.click(TravelInsurancePg.Filter.promotionOpt(promotionFilterOpt));
		}
		
		/**
		 * Filter By Insurer on Travel Page
		 * 
		 * @param insurerFilterOpt. The value should be picked from ConstantLib.Travel.Filter.insurerOpt
		 * @return
		 */
		public static void filterByInsurer(String insurerFilterOpt) {	
			ActionGen.click(TravelInsurancePg.Filter.insurerOpt(insurerFilterOpt));		 
		}
		
		/**
		 * Verify Insurer Filter Result
		 * 
		 * @param insurerFilterOpt. The value should be picked from ConstantLib.Travel.Filter.insurerOpt
		 * @return
		 */
		public static void verifyInsurerFilterResult(String insurerFilterOpt) {	
			int numberOfCard = Action.getNumberOfCard();
			String cardBrandName = "";
			if(numberOfCard>0)
			{
				List<WebElement> cardList = TravelInsurancePg.cardList();
				for (WebElement card : cardList)
				{
					cardBrandName = card.findElement(By.xpath("//div[@class='card-brand']//h4[contains(@class,'name')]")).getText();
					if(!cardBrandName.toLowerCase().equals(insurerFilterOpt.toLowerCase()))
					{
						ExecutionWeb.setTestFail("The Filter with Insurer " + insurerFilterOpt + " is returning wrong Insurer " + cardBrandName);
					}
					
				}
			}
		}
		
		
		/**
		 * Sort on Travel Page
		 * 
		 * @param sortOpt. The value should be picked from ConstantLib.Sort.sortOpt
		 * @return
		 */
		public static void sort(String sortOpt) {	
			ActionGen.click(TravelInsurancePg.Sort.option(sortOpt));		 
		}
		
		/**
		 * Check sort result on Travel Page
		 * 
		 * @param sortOpt. The value should be picked from ConstantLib.Sort.sortOpt
		 * @return
		 */
		public static void verifySortResult(String sortOpt) {	
			switch(sortOpt)
			{
				case ConstantLib.Travel.Sort.sortOpt.PriceLowToHigh:
					
			}
		}
		
		/**
		 * Check card is displayed in ascending price
		 * 
		 * @return
		 */
		public static void verifyCardDisplayedWithAscendingPrice() {	
			int numberOfCard = Action.getNumberOfCard();
			int currentPrice = 0;
			int previousPrice = 0;
			if(numberOfCard>0)
			{
				List<WebElement> cardList = TravelInsurancePg.cardList();
				for(int i=1;i<numberOfCard;i++)
				{
					previousPrice = Integer.valueOf(cardList.get(i-1).findElement(By.xpath("//div[@class='policy-price']//span[@class='value']"))
							.getText().replace(",",""));
					currentPrice = Integer.valueOf(cardList.get(i).findElement(By.xpath("//div[@class='policy-price']//span[@class='value']"))
							.getText().replace(",",""));
					if(currentPrice<previousPrice)
					{
						ExecutionWeb.setTestFail("Sort by Price:Low to High is returning wrong value!");
					}
				}
			}
		}
		
		/**
		 * Filter by Policy Type on Travel Page
		 * 
		 * @param policyTypeOpt. The value should be picked from ConstantLib.Details.policyTypeOpt
		 * @return
		 */
		public static void filterByPolicyType(String policyTypeOpt) {	
			ActionGen.click(TravelInsurancePg.Details.policyTypeOpt(policyTypeOpt));		 
		}
		
		/**
		 * Filter by 'Who Going' on Travel Page
		 * 
		 * @param whoGoingOpt. The value should be picked from ConstantLib.Details.whoGoingOpt
		 * @return
		 */
		public static void filterByWhoGoing(String whoGoingOpt) {	
			ActionGen.click(TravelInsurancePg.Details.whoGoingOpt(whoGoingOpt));		 
		}
		
		/**
		 * Filter by Destination on Travel Page
		 * 
		 * @param whoGoingOpt. The value should be picked from ConstantLib.Details.destinationOpt
		 * @return
		 */
		public static void filterByDestination(String destinationOpt) {	
			ActionGen.scroll("down", 2, 1000);
			//ActionGenWeb.scrollToElement(TravelInsurancePg.Details.destinationSel());
			
			ActionGen.click(TravelInsurancePg.Details.destinationSel());
			WebElement destinationOptElm = DriverWeb.instance.findElements(By.xpath("//div[@data-gb-name='edit-detail-bar']//div[@data-gb-name='destinations']//li[contains(.,'"+ destinationOpt +"')]")).get(0);
			ActionGen.click(destinationOptElm);
		}
		
		/**
		 * Check Filter by Destination result on Travel Page
		 * 
		 * @param whoGoingOpt. The value should be picked from ConstantLib.Details.destinationOpt
		 * @return
		 */
		public static void verifyFilterByDestinationResult(String destinationOpt) {	
			String resultText = TravelInsurancePg.resultText().getText();
			if(!resultText.contains("travel to " + destinationOpt))
			{
				ExecutionWeb.setTestFail("Sort by Destination is returning wrong value!");
			}
		}
		
		/**
		 * Select Start Date on Travel Page
		 * 
		 * @param Date. The value should be in the format DD-MM-YYYY
		 * @return
		 */
		public static void selectStartDate(String date) {	
			ActionGen.scroll("down", 7, 1000);
			System.out.println("Start executing jsExecutor on StartDate");
			WebElement startDate = DriverWeb.instance.findElement(By.xpath("//div[@data-gb-name='edit-detail-bar']//div[@data-gb-name='dates']//input[@name='dates-startdate']"));
			((JavascriptExecutor) DriverWeb.instance).executeScript("arguments[0].removeAttribute('readonly','readonly')",startDate);
			System.out.println("Start clear to enter start date");
			TravelInsurancePg.Details.startDateTxt().clear();
			TravelInsurancePg.Details.startDateTxt().sendKeys(date);
		}
		
		/**
		 * Select End Date on Travel Page
		 * 
		 * @param Date. The value should be in the format DD-MM-YYYY
		 * @return
		 */
		public static void selectEndDate(String date) {	
			ActionGen.scroll("down", 1, 1000);
			System.out.println("Start executing jsExecutor on EndDate");

			WebElement endDate = DriverWeb.instance.findElement(By.xpath("//div[@data-gb-name='edit-detail-bar']//div[@data-gb-name='dates']//input[@name='dates-enddate']"));
			((JavascriptExecutor) DriverWeb.instance).executeScript("arguments[0].removeAttribute('readonly','readonly')",endDate);
			System.out.println("Start clear to enter end date");
			TravelInsurancePg.Details.endDateTxt().clear();
			TravelInsurancePg.Details.endDateTxt().sendKeys(date);
		}
		
		/**
		 * Check Start Date and End Date filter
		 * 
		 * @param startDate. The value should be in the format DD-MM-YYYY
		 * @param endDate. The value should be in the format DD-MM-YYYY

		 * @return
		 */
		public static void verifyStartDateEndDateFilterResult(String startDate,String endDate) {	
			String resultText = TravelInsurancePg.resultText().getText();
			System.out.println(resultText);
			System.out.println("from "+ startDate + " to " + endDate);

			if(!resultText.contains("from "+ startDate + " to " + endDate))
			{
				ExecutionWeb.setTestFail("Sort by Start Date/End Date is returning wrong value!");
			}
		}
		
		/**
		 * Filter by Personal Accident on Travel Page
		 * 
		 * @param minValue. The minimize value to
		 * @param minValue. The maximize value to  
		 * @return
		 */
		public static void filterByPersonalAccident(int minValue,int maxValue) {	
			ActionGen.click(TravelInsurancePg.Filter.seeMoreBtn());
			if(minValue>ConstantLib.Travel.Filter.PersonalAccident.minValue)
			{
				ActionGen.moveSliderToRight(TravelInsurancePg.Filter.personalAccidentLeftSlider(), minValue,ConstantLib.Travel.Filter.PersonalAccident.minValue,ConstantLib.Travel.Filter.PersonalAccident.maxValue);
			}
			if(maxValue<ConstantLib.Travel.Filter.PersonalAccident.maxValue)
			{
				ActionGen.moveSliderToLeft(TravelInsurancePg.Filter.personalAccidentRightSlider(), maxValue,ConstantLib.Travel.Filter.PersonalAccident.minValue,ConstantLib.Travel.Filter.PersonalAccident.maxValue);
			}
		}
		
		/**
		 * Verify Insurer Filter Result
		 * 
		 * @param insurerFilterOpt. The value should be picked from ConstantLib.Travel.Filter.insurerOpt
		 * @return
		 */
		public static void verifyPersonalAccidentFilterResult(int minValue,int maxValue) {	
			int numberOfCard = Action.getNumberOfCard();
			if(numberOfCard>0)
			{
				List<WebElement> cardList = TravelInsurancePg.cardList();
				int value = 0;
				if(cardList.size()>0)
				{
					for (WebElement card : cardList)
					{
						value = Integer.valueOf(card.findElement(By.xpath("//div[@class='card-content-details row']//div[contains(.,'Personal Accident')]//p[contains(@class,'detail-value')]"))
								.getText().substring(1).replace(",", ""));
						if((value<minValue)||(value>maxValue))
						{
							ExecutionWeb.setTestFail("The Filter with Personal Accident in range From " + minValue + " To " + ConstantLib.Travel.Filter.PersonalAccident.maxValue + " is returning wrong value " + value);
						}
						
					}
				}
			}
		}
		
		/**
		 * Filter by Medical Expenses on Travel Page
		 * 
		 * @param amount
		 * @return
		 */
		public static void filterByMedicalExpenses(int amount) {	
			WebElement slider=TravelInsurancePg.Filter.medicalExpensesSlider();
			ActionGen.moveSliderToRight(slider, amount,ConstantLib.Travel.Filter.MedicalExpenses.minValue,ConstantLib.Travel.Filter.MedicalExpenses.maxValue);
		}
		
		/**
		 * Filter by Trip Cancellation on Travel Page
		 * 
		 * @param amount
		 * @return
		 */
		public static void filterByTripCancellation(int amount) {	
			WebElement slider=TravelInsurancePg.Filter.tripCancellationSlider();
			ActionGen.moveSliderToRight(slider, amount,ConstantLib.Travel.Filter.TripCancellation.minValue,ConstantLib.Travel.Filter.TripCancellation.maxValue);
		}
		
		/**
		 * Filter by Trip Termination on Travel Page
		 * 
		 * @param amount
		 * @return
		 */
		public static void filterByTripTermination(int amount) {	
			WebElement slider=TravelInsurancePg.Filter.tripTerminationSlider();
			ActionGen.moveSliderToRight(slider, amount,ConstantLib.Travel.Filter.TripTermination.minValue,ConstantLib.Travel.Filter.TripTermination.maxValue);
		}
		
		/**
		 * Filter by Loss Baggage on Travel Page
		 * 
		 * @param amount
		 * @return
		 */
		public static void filterLossBaggage(int amount) {	
			WebElement slider=TravelInsurancePg.Filter.lossBaggageSlider();
			ActionGen.moveSliderToRight(slider, amount,ConstantLib.Travel.Filter.LossBaggage.minValue,ConstantLib.Travel.Filter.LossBaggage.maxValue);
		}
	}

	/**
	 * This action is used to navigate to specific web page from link
	 * 
	 * @param url
	 * 
	 */
	public static void navigateToPage(String url) {
		ActionGen.launch(url);
	}
	
	/**
	 * This action is used to get number of cards displaying on the page
	 * 
	 * @param url
	 * 
	 */
	public static int getNumberOfCard() {
		String filterResultText = DriverWeb.instance.findElement(By.xpath("//div[@data-gb-name='travel-nav-data' and contains(@class,'results-text')]//h5[contains(.,'plans found')]")).getText();
		return Integer.valueOf(filterResultText.substring(0,filterResultText.length()-13));
		
	}
	
	
	
	
}
