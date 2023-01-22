package tests.US_019_T;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.US_019_P.US_019_1_P;
import utilities.*;

import java.util.ArrayList;
import java.util.List;


public class US_019_1_T extends TestBaseReports {

    WebDriver driver;
    US_019_1_P page19 = new US_019_1_P();

    @Test
    public void test01(){
        // url ye gidecegiz
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        extentTest = extentReports.createTest("PickBazar ana sayfasına gidildi");



        page19.groceryTab.click();
        ReusableMethods.waitForClickablility(page19.groceryTab, 3);
        extentTest = extentReports.createTest("Grocery Butonuna basıldı");

        page19.dailyNeedsTab.click();
        extentTest = extentReports.createTest("Daily Needs sayfasına gidildi");
        //ReusableMethods.waitFor( 2);
        //ReusableMethods.waitForClickablility(page19.dailyNeedsTab, 3);

    }

    @Test(
            dataProvider = "keyData"
    )
    public void test02(String key) throws InterruptedException {


        //Thread.sleep(2000);
        page19.searching.sendKeys(key);
        ReusableMethods.waitFor( 1);

        page19.searching.clear();
        //ReusableMethods.waitForVisibility(page19.searching, 5);
        ReusableMethods.waitFor( 1);
        extentTest = extentReports.createTest("DataProviderClassdaki ürünler arama kısmına yazıldı");

        page19.searching.clear();
        ReusableMethods.waitFor( 1);
        extentTest = extentReports.createTest("search kısmındaki bilgiler silindi");


        //ReusableMethods.waitForVisibility(page19.searchResultsSeen.get(0), 5);
       // extentTest = extentReports.createTest("Aranan ürünlerden ilkinin ekranda görünmesi için  en fazla 5 saniye beklendi");

        System.out.println(page19.searchResultsSeen.size());
        ReusableMethods.waitFor( 1);
        extentTest = extentReports.createTest("Aranan üürnlerin kaç tane olduğu  yazıldı");

        String foundFirst = page19.searchResultsSeen.get(0).getText();
        ReusableMethods.waitFor( 1);
        //ReusableMethods.waitForVisibility(page19.searchResultsSeen.get(0), 5);
        System.out.println(foundFirst);
        extentTest = extentReports.createTest("Bulunan ilk ürünün texti yazıldı");

        Assert.assertTrue(page19.searchResultsSeen.size()>0);
        Assert.assertTrue(page19.searchResultsSeen.get(0).getText().contains(key));
        extentTest = extentReports.createTest("En az bir ürün bulunduğu görüldü ");
        ReusableMethods.waitFor( 1);

    }

    @DataProvider(name="keyData")
    public Object[][] keyData() {
        return new Object[][]{
                {"onion"},
                {"chives"},
                {"cabbag"},
              /*  {"Broccoli"},
                {"Lettuce"},
                {"Spinach"},
                {"Leaves"},
                {"Cucumber"},
                {"Carrots"},
                {"chip"},
                {"chicken"},
                {"cola"},
                {"Oxtail"},*/
                {"Noodle"},
                {"Popcorn"},
                {"Rice"},
                {"Steak"},
        };
    }

}









