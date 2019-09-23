package GoBear.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import automationLibrary.actions.ActionGen;

public class pg_Home {
	public static WebElement insuranceTab() {
		return ActionGen.getElement(By.xpath("//a[@href='#Insurance' and @role='tab']"));
	}
	public static WebElement travelTab() {
		return ActionGen.getElement(By.xpath("//a[@href='#Travel' and @role='tab']"));
	}
	
	public static WebElement showMyResultButton() {
		return ActionGen.getElement(By.xpath("//button[.='Show my results']"));
	}
}
