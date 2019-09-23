package GoBear.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import automationLibrary.actions.ActionGen;
import automationLibrary.drivers.DriverWeb;

public class TravelInsurancePg {
	
	public static WebElement clearFilter() {
		return ActionGen.getElement(By.xpath("//div[@data-gb-name='filter-bar']//a[@data-gb-name='filter-reset']"));
	}
		
	public static class Filter
	{
		public static WebElement header() {
			return ActionGen.getElement(By.xpath("//div[@data-gb-name='filter-bar']//h5[@id='collapseFilterBtn']"));
		}
		public static WebElement promotionOpt(String promotionFilterOpt) {
			return ActionGen.getElement(By.xpath("//div[@data-gb-name='filter-bar']//div[@class='radio radio-primary' and @data-filter-name='" + promotionFilterOpt + "']"));
		}
		public static WebElement insurerOpt(String insurerFilterOpt) {
			return ActionGen.getElement(By.xpath("//div[@data-gb-name='filter-bar']//div[@class='checkbox checkbox-primary' and @data-filter-name='" + insurerFilterOpt + "']"));
		}
		public static WebElement personalAccidentLeftSlider() {
			return ActionGen.getElement(By.xpath("//div[@data-gb-name='filter-bar']//div[@data-type='Number' and contains(.,'Personal Accident')]//div[contains(@class,'min-slider-handle') and @role='slider']"));
		}
		public static WebElement personalAccidentRightSlider() {
			return ActionGen.getElement(By.xpath("//div[@data-gb-name='filter-bar']//div[@data-type='Number' and contains(.,'Personal Accident')]//div[contains(@class,'max-slider-handle') and @role='slider']"));
		}
		public static WebElement medicalExpensesSlider() {
			return ActionGen.getElement(By.xpath("//div[@data-gb-name='filter-bar']//div[@data-type='Number' and contains(.,'MEDICAL EXPENSES WHILE TRAVELING']//div[@role='slider']"));
		}
		public static WebElement tripCancellationSlider() {
			return ActionGen.getElement(By.xpath("//div[@data-gb-name='filter-bar']//div[@data-type='Number' and contains(.,'TRIP CANCELLATION']//div[@role='slider']"));
		}
		public static WebElement tripTerminationSlider() {
			return ActionGen.getElement(By.xpath("//div[@data-gb-name='filter-bar']//div[@data-type='Number' and contains(.,'TRIP TERMINATION']//div[@role='slider']"));
		}
		public static WebElement lossBaggageSlider() {
			return ActionGen.getElement(By.xpath("//div[@data-gb-name='filter-bar']//div[@data-type='Number' and contains(.,'LOSS OF BAGGAGE & PERSONAL BELONGINGS" + 
					"']//div[@role='slider']"));
		}
		public static WebElement seeMoreBtn() {
			return ActionGen.getElement(By.xpath("//div[@data-gb-name='filter-bar']//div[@data-gb-name='filter-see-more']//a[@class='btn-ripple more']"));
		}
	}
	public static class Sort
	{
		public static WebElement header() {
			return ActionGen.getElement(By.xpath("//div[@data-gb-name='sort-bar']//h5[@id='headingTwo']"));
		}
		public static WebElement option(String sortOpt) {
			return ActionGen.getElement(By.xpath("//div[@data-gb-name='sort-bar']//div[@class='radio radio-primary' and contains(.,'"+ sortOpt +"')]"));
		}
	}
	public static class Details
	{
		public static WebElement header() {
			return ActionGen.getElement(By.xpath("//div[@data-gb-name='edit-detail-bar']//h5[@id='detailsHeading']"));
		}
		public static WebElement policyTypeOpt(String policyTypeOpt) {
			return ActionGen.getElement(By.xpath("//div[@data-gb-name='edit-detail-bar']//div[@data-gb-name='triptype']//label[contains(.,'"+ policyTypeOpt +"')]"));
		}
		public static WebElement whoGoingOpt(String whoGoingOpt) {
			return ActionGen.getElement(By.xpath("//div[@data-gb-name='edit-detail-bar']//div[@data-gb-name='traveller']//label[contains(.,'"+ whoGoingOpt +"')]"));
		}
		public static WebElement destinationSel() {
		return ActionGen.getElement(By.xpath("//div[@data-gb-name='edit-detail-bar']//div[@data-gb-name='destinations']//div[@class='select-component']"));
		}
		public static WebElement startDateTxt() {
		return ActionGen.getElement(By.xpath("//div[@data-gb-name='edit-detail-bar']//div[@data-gb-name='dates']//input[@name='dates-startdate']"));
		}
		public static WebElement endDateTxt() {
		return ActionGen.getElement(By.xpath("//div[@data-gb-name='edit-detail-bar']//div[@data-gb-name='dates']//input[@name='dates-enddate']"));
		}
	}
	public static List<WebElement> cardList() {
		return DriverWeb.instance.findElements(By.xpath("//div[@class='grid-row']//div[@class='col-sm-4 card-full']"));
	}
	public static WebElement resultText() {
		return ActionGen.getElement(By.xpath("//div[@data-gb-name='travel-nav-data' and contains(@class,'results-text')]"));
	}
	
}
