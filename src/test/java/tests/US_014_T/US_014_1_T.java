package tests.US_014_T;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.US_014_P.US_014_1_P;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseReports;

import java.util.ArrayList;
import java.util.List;

public class US_014_1_T extends TestBaseReports {

    WebDriver driver;

    @Test
    public void test01() throws InterruptedException {
        // url ye gidecegiz
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        extentTest = extentReports.createTest("PickBazar ana sayfasına gidildi");
        ReusableMethods.waitFor(1);


        US_014_1_P page = new US_014_1_P();
        page.groceryTab.click();
        extentTest = extentReports.createTest("Grocery Butonuna basıldı");
        ReusableMethods.waitFor(2);

        page.bagTab.click();
        extentTest = extentReports.createTest("Bags sayfasına gidildi");
        ReusableMethods.waitFor(2);


        List<String> bagsList = new ArrayList<String>();


        for (WebElement element : page.bags) {
            String str[] = element.getText().toLowerCase().split(" ");
            if (!bagsList.contains(str[0])) {
                bagsList.add(str[0]);
            }
        }
        ReusableMethods.waitFor(3);
        System.out.println(bagsList);
        extentTest = extentReports.createTest("Bags sayfasında bulunan her bir üründe geçen kelimelerle  unique bir liste oluşturuldu");
        System.out.println(bagsList.size()); // Ürünlerin oluştuğu listenin eleman sayısı yazıldı.

        for (int i = 0; i < bagsList.size(); i++) { //bagsList.size()
            System.out.println(bagsList.get(i));
            page.searchResults.sendKeys(bagsList.get(i));
            page.searchButton.click();
            extentTest = extentReports.createTest("Bags sayfasında bulunan ürünlerin her bir kelimesi, search box da arandı");

            ReusableMethods.waitFor(3);
            Actions actions = new Actions(Driver.getDriver());
            actions.scrollToElement(page.searchResults).perform();
            extentTest = extentReports.createTest("Aranan her kelime sonunda, search box kutusuna tekrar gidildi");
            ReusableMethods.waitFor(3);
            page.searchResults.clear();
            extentTest = extentReports.createTest("Aranan her kelime sonunda,  search box temizlendi");
            ReusableMethods.waitFor(3);
            for (WebElement element : page.searchedBags) {
                Assert.assertTrue(element.getText().toLowerCase().contains(bagsList.get(i)));
                extentTest = extentReports.createTest("Aranan kelimenin  bags sayfasındaki üürnler arasında olup olmadığı kontrol edildi");
            }


        }

    }
}


