package travel;

import org.testng.annotations.Test;

import GoBear.initiations.TestBase;
import GoBear.actions.Action;
import GoBear.initiations.TestConfigurations;
import automationLibrary.actions.ActionGen;

public class TV001_VerifyAtLeast3CardsDisplayAtDefault extends TestBase {
	@Test(groups = "Travel", description = "Verify at least 3 cards display at default when accessing to Travel page")
	/**
	 * TV001_VerifyAtLeast3CardsDisplayAtDefault
	 * Steps:
	 * 	1. Navigate to Home Page
	 * 	2. Click on tab Insurance->Travel to go to Travel page
	 * Expected Result:
	 * 	- Verify there are 3 cards displaying at least	 
	 */
	public void CO016_01_CreateOrderAndVerifyOrderInBuyer() {
		// Declare variables
		int numberDisplayedCard = 0;
		
		// Go to Travel page
		Action.navigateToPage(TestConfigurations.url);
		Action.Travel.goToTravelPage();
		
		// Get the number of cards and verify the number of displayed card is greater than 2 
		numberDisplayedCard = Action.getNumberOfCard();
		ActionGen.verifyTrue(numberDisplayedCard>2,"The number of displayed card is less than 3");
		
		// Verify the test result
		ActionGen.verifyTest();
	}
	
	
}
