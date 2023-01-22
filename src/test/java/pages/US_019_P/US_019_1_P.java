package pages.US_019_P;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class US_019_1_P {

    public US_019_1_P() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//span[@class='whitespace-nowrap']")
    public WebElement groceryTab;

    @FindBy(xpath = "//span[text()='Daily Needs']")
    public WebElement dailyNeedsTab;

    @FindBy(xpath = "//div[@class='os-padding']")
    public List<WebElement> dailyNeedsCategory;

    @FindBy(id = "search")
    public WebElement searching;


    @FindBy(xpath="//div[@class='max-h-52']/div/span")
    public List<WebElement> searchResultsSeen;



    @FindBy(xpath = "//button[text()='Search']")
    public WebElement searchButton;

    @FindBy(xpath = "//h3[@role='button']")
    public List<WebElement> searchedBags;


}
