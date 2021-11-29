package com.equalexperts.hotelbooking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;
import org.junit.jupiter.api.Assertions;

import java.util.List;

@Component
public class BookingForm extends LoadableComponent<BookingForm> {

    private String BASE_URL = "http://hotel-test.equalexperts.io/";

    @CacheLookup
    @FindBy(id = "firstname")
    WebElement firstNameInput;

    @CacheLookup
    @FindBy(id = "lastname")
    WebElement surnameInput;

    @CacheLookup
    @FindBy(id = "totalprice")
    WebElement priceInput;

    @CacheLookup
    @FindBy(id = "depositpaid")
    WebElement depositDropdown;

    @CacheLookup
    @FindBy(id = "checkin")
    WebElement checkinInput;

    @CacheLookup
    @FindBy(id = "checkout")
    WebElement checkoutInput;

    @CacheLookup
    @FindBy(xpath = "//input[@value=' Save ']")
    WebElement saveBtn;

    private WebDriver driver;

    public BookingForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void inputFirstname(String valueIn) {
        firstNameInput.sendKeys(valueIn);
    }

    public void inputSurname(String valueIn) {
        surnameInput.sendKeys(valueIn);
    }

    public void inputPrice(String valueIn) {
        priceInput.sendKeys(valueIn);
    }

    public void inputDeposit(String valueIn) {
        Select selectDD = new Select(depositDropdown);
        selectDD.selectByVisibleText(valueIn);
    }

    public void inputCheckin(String valueIn) {
        checkinInput.sendKeys(valueIn);
    }

    public void inputCheckout(String valueIn) {
        checkoutInput.sendKeys(valueIn);
    }

    public void clickSave() {
        saveBtn.click();
    }

    public List<WebElement> getSavedBookings() {
        return driver.findElement(By.id("bookings")).findElements(By.className("row"));
    }

    public void deleteBookingRows(List<WebElement> bookings) {
        for (WebElement row : bookings) {
            row.findElement(By.tagName("input")).click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException("! Thread sleep interrupted !");
            }
        }
    }

    @Override
    protected void load() {
        this.driver.get(BASE_URL);
    }

    @Override
    protected void isLoaded() throws Error {
        Assertions.assertTrue(driver.getCurrentUrl().contains(BASE_URL), "!Booking page is not loaded!");
    }
}
