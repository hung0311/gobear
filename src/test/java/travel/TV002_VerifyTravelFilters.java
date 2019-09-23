package travel;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import GoBear.actions.Action;
import GoBear.initiations.ConstantLib;
import GoBear.initiations.TestBase;
import GoBear.initiations.TestConfigurations;
import automationLibrary.actions.ActionGen;

public class TV002_VerifyTravelFilters extends TestBase {
	
	@Test(groups = "Travel_Filter", description = "Verify Promotion Filter is working on Travel page")
	/**
	 * TV002_01_VerifyPromotionFilters
	 * Steps:
	 * 	1. Navigate to Home Page
	 * 	2. Click on tab Insurance->Travel to go to Travel page
	 * 	3. Select "Show All" option
	 * 	4. Select "Promos only"
	 * Expected Result:
	 * 	- After step#3: Verify all cards are displayed
	 * 	- After step#4: Verify only cards with promotion are displayed 	  	 
	 */
	public void TV002_01_VerifyPromotionFilters() {
		// Declare variables
		int numberDisplayedCard = 0;
		
		Action.navigateToPage(TestConfigurations.url);
		Action.Travel.goToTravelPage();
		numberDisplayedCard = Action.getNumberOfCard();
		
		// Verify the number of displayed card is less than 3
		ActionGen.verifyTrue(numberDisplayedCard>=3,"The number of displayed card is less than 3");
		
		// Verify the test result
		ActionGen.verifyTest();
	}
	
	@Test(groups = "Travel_Filter", description = "Verify Insurer Filter is working on Travel page")
	/**
	 * TV002_02_VerifyInsurerFilters
	 * Steps:
	 * 	1. Navigate to Home Page
	 * 	2. Click on tab Insurance->Travel to go to Travel page
	 * 	3. Select "Show All" option
	 * 	4. Select "Promos only"
	 * Expected Result:
	 * 	- After step#3: Verify all cards are displayed 
	 * 	- After step#4: Verify only cards with promotion are displayed 	  	 
	 */
	public void TV002_02_VerifyInsurerFilters() {
		// Declare variables
		List<String> insurerList = Arrays.asList(ConstantLib.Travel.Filter.InsurerOpt.FPGInsurance
												,ConstantLib.Travel.Filter.InsurerOpt.Malayan
												,ConstantLib.Travel.Filter.InsurerOpt.PacificCross
												,ConstantLib.Travel.Filter.InsurerOpt.PioneerInsurance
												,ConstantLib.Travel.Filter.InsurerOpt.PrudentialGuarantee
												,ConstantLib.Travel.Filter.InsurerOpt.StandardInsurance
												,ConstantLib.Travel.Filter.InsurerOpt.STARR
												);
		// Go to Travel Page
		Action.navigateToPage(TestConfigurations.url);
		Action.Travel.goToTravelPage();
		
		// Perform filter with each Insurer and check the filter result
		for (String insurer : insurerList)
		{
			Action.Travel.filterByInsurer(insurer);
			Action.Travel.verifyInsurerFilterResult(insurer);
			Action.Travel.clearFilter();
		}
		
		// Verify the test result
		ActionGen.verifyTest();
	}
	
	@Test(groups = "Travel_Filter", description = "Verify Personal Accident is working on Travel page")
	/**
	 * TV002_03_VerifyPersonalAccidentFilters
	 * Steps:
	 * 	1. Navigate to Home Page
	 * 	2. Click on tab Insurance->Travel to go to Travel page
	 * 	3. Filter by Personal Accident with a valid value
	 * Expected Result:
	 * 	- After step#3: Verify all cards are displayed with the property Personal Accident value in range
	 */
	public void TV002_03_VerifyPersonalAccidentFilters() {
		// Declare variables
		int personalAccidentMinValue = 450000;
		int personalAccidentMaxValue = ConstantLib.Travel.Filter.PersonalAccident.maxValue - 100000;
		
		// Go to Travel Page
		Action.navigateToPage(TestConfigurations.url);
		Action.Travel.goToTravelPage();
		
		// Perform filter with a value of Personal Accident and check all cards are displayed with the property Personal Accident value in range
		Action.Travel.filterByPersonalAccident(personalAccidentMinValue,personalAccidentMaxValue);
		Action.Travel.verifyPersonalAccidentFilterResult(personalAccidentMinValue,personalAccidentMaxValue);
		
		// Verify the test result
		ActionGen.verifyTest();
	}
	
	@Test(groups = "Travel_Filter", description = "Verify Sort by Price:Low to High is working on Travel page")
	/**
	 * TV002_04_VerifyAscendingPriceSort
	 * Steps:
	 * 	1. Navigate to Home Page
	 * 	2. Click on tab Insurance->Travel to go to Travel page
	 * 	3. Sort By Price:Low to High
	 * Expected Result:
	 * 	- After step#3: Verify all cards are displayed ordered by ascending prices
	 */
	public void TV002_04_VerifyAscendingPriceSort() {
		// Declare variables
		
		
		// Go to Travel Page
		Action.navigateToPage(TestConfigurations.url);
		Action.Travel.goToTravelPage();
		
		// Perform a sort with Price:Low To High and verify all cards are displayed ordered by ascending prices
		Action.Travel.sort(ConstantLib.Travel.Sort.sortOpt.PriceLowToHigh);
		Action.Travel.verifyCardDisplayedWithAscendingPrice();
		
		// Verify the test result
		ActionGen.verifyTest();
	}
	
	@Test(groups = "Travel_Filter", description = "Verify Filter by Destination is working on Travel page")
	/**
	 * TV002_05_VerifyDestinationFilter
	 * Steps:
	 * 	1. Navigate to Home Page
	 * 	2. Click on tab Insurance->Travel to go to Travel page
	 * 	3. Filter by a <destination> 
	 * Expected Result:
	 * 	- After step#3: Verify "travel to <Destination>" is displayed in the result text 
	 */
	public void TV002_05_VerifyDestinationFilter() {
		// Declare variables
		
		
		// Go to Travel Page
		Action.navigateToPage(TestConfigurations.url);
		Action.Travel.goToTravelPage();

		// Perform a sort with Price:Low To High and verify "travel to <Destination>" is displayed in the result text 
		Action.Travel.filterByDestination(ConstantLib.Travel.Details.detinationsOpt.Japan);
		Action.Travel.verifyFilterByDestinationResult(ConstantLib.Travel.Details.detinationsOpt.Japan);
		
		// Verify the test result
		ActionGen.verifyTest();
	}
	
	@Test(groups = "Travel_Filter", description = "Verify Filter by Start/End Date is working on Travel page")
		/**
		 * TV002_06_VerifyStartDateEndDateFilter
		 * Steps:
		 * 	1. Navigate to Home Page
		 * 	2. Click on tab Insurance->Travel to go to Travel page
		 * 	3. Select a <start date> and <end date> 
		 * Expected Result:
		 * 	- After step#3: Verify "from <start date> to <end date>" is displayed in the result text 
		 */
		public void TV002_06_VerifyStartDateEndDateFilter() {
			// Declare variables
			String startDate = "22-10-2019";
			String endDate = "06-11-2019";
			String displayedStartDate = "22 Oct 2019";
			String dispalyedEndDate = "06 Nov 2019";
			
			// Go to Travel Page
			Action.navigateToPage(TestConfigurations.url);
			Action.Travel.goToTravelPage();

			// Perform a filter with <start date> and <from date> and verify "from <start date> to <end date>" is displayed in the result text  
			Action.Travel.selectStartDate(startDate);
			Action.Travel.selectEndDate(endDate);
			Action.Travel.verifyStartDateEndDateFilterResult(displayedStartDate,dispalyedEndDate);
			// Verify the test result
			ActionGen.verifyTest();
		}
	
	
}
