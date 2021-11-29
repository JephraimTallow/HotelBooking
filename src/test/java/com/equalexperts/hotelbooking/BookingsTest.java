package com.equalexperts.hotelbooking;

import com.equalexperts.hotelbooking.data.CustomerBooking;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;

import org.openqa.selenium.WebElement;


import java.util.List;

public class BookingsTest extends BaseTest {

    @Test
    public void testValidEntry() throws InterruptedException {

        String firstName = "ARTHUR_dw";
        String lastName = "Askey";
        String price = "25";
        String deposit = "true";
        String checkIn = "2021-11-01";
        String checkOut = "2021-11-05";
        CustomerBooking customerBooking = new CustomerBooking(firstName, lastName, price, deposit, checkIn, checkOut);
        createBooking(bookingForm, customerBooking);

        // TODO: Change this to conditional wait - until the element appears in the bookings section
        Thread.sleep(5000);

        List<WebElement> bookingRows = bookingForm.getSavedBookings();
        final List<WebElement> bookingRowsFound = findCustomerBooking(bookingRows.stream(), customerBooking);

        Assertions.assertEquals(1, bookingRowsFound.size(), "!Expected row count not found!");
    }


    @Test
    public void testDeleteEntry() throws InterruptedException {

        String firstName = "ARTHUR_dw";
        String lastName = "Delete";
        String price = "25";
        String deposit = "true";
        String checkIn = "2021-11-01";
        String checkOut = "2021-11-05";
        CustomerBooking customerBooking = new CustomerBooking(firstName, lastName, price, deposit, checkIn, checkOut);
        createBooking(bookingForm, customerBooking);

        // TODO: Change this to conditional wait - until the element appears in the bookings section
        Thread.sleep(5000);

        List<WebElement> bookingRows = bookingForm.getSavedBookings();
        List<WebElement> bookingRowsFound = findCustomerBooking(bookingRows.stream(), customerBooking);

        Assertions.assertEquals(1, bookingRowsFound.size(), "!Expected row count not found!");
        bookingForm.deleteBookingRows(bookingRowsFound);

        // TODO: Change this to conditional wait - until the element appears in the bookings section
        Thread.sleep(1000);

        bookingRows = bookingForm.getSavedBookings();
        bookingRowsFound = findCustomerBooking(bookingRows.stream(), customerBooking);

        Assertions.assertEquals(0, bookingRowsFound.size(), "!Expected no results found!");

    }

}
