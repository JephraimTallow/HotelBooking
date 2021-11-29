package com.equalexperts.hotelbooking;

import com.equalexperts.hotelbooking.data.CustomerBooking;
import com.equalexperts.hotelbooking.pages.BookingForm;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringJUnitConfig(TestConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    @Autowired
    private WebDriver driver;

    private final String testDataIdentifier = "ARTHUR_dw";

    @Autowired
    protected BookingForm bookingForm;

    @BeforeEach
    private void setupTest() {
        bookingForm.get();
        deleteAllTestData(testDataIdentifier);
    }

    @AfterEach
    private void teardown() {
        deleteAllTestData(testDataIdentifier);
    }

    @AfterAll
    private void closeBrowser() {
        driver.quit();
    }

    private void deleteAllTestData(String dataId) {
        List<WebElement> bookingRows = bookingForm.getSavedBookings();
        if (bookingRows.size() > 0) {
            List<WebElement> webElements = bookingRows.stream().filter(r -> r.getText().contains(dataId))
                    .collect(Collectors.toList());
            bookingForm.deleteBookingRows(webElements);
        }
    }

    protected List<WebElement> findCustomerBooking(Stream<WebElement> stream, CustomerBooking customerBooking) {
        return stream.filter(r -> r.getText().contains(customerBooking.getFirstName())
                && r.getText().contains(customerBooking.getSurName())
                && r.getText().contains(customerBooking.getPrice())
                && r.getText().contains(customerBooking.getDeposit())
                && r.getText().contains(customerBooking.getCheckIn())
                && r.getText().contains(customerBooking.getCheckOut()))
                .collect(Collectors.toList());

    }

    protected void createBooking(BookingForm bookingForm, CustomerBooking customerBooking) {
        bookingForm.inputFirstname(customerBooking.getFirstName());
        bookingForm.inputSurname(customerBooking.getSurName());
        bookingForm.inputPrice(customerBooking.getPrice());
        bookingForm.inputDeposit(customerBooking.getDeposit());
        bookingForm.inputCheckin(customerBooking.getCheckIn());
        bookingForm.inputCheckout(customerBooking.getCheckOut());

        bookingForm.clickSave();
    }
}
