package pages.US_014_P;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class US_014_1_P {

    public US_014_1_P() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//span[@class='whitespace-nowrap']")
    public WebElement groceryTab;

    @FindBy(xpath = "//div[@id='headlessui-menu-item-8']")
    public WebElement bagTab;

    @FindBy(xpath = "//h3[@role='button']")
    public List<WebElement> bags;

    @FindBy( id="search")
    public WebElement searchResults;


    @FindBy( xpath="//button[text()='Search']")
    public WebElement searchButton;

    @FindBy(xpath = "//h3[@role='button']")
    public List<WebElement> searchedBags;






}
