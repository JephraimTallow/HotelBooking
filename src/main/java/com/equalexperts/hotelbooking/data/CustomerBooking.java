package com.equalexperts.hotelbooking.data;

import java.util.Objects;

public class CustomerBooking {

    private String firstName;
    private String surName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    private String price;
    private String deposit;
    private String checkIn;
    private String checkOut;

    public CustomerBooking(String first, String last, String price, String deposit, String checkIn, String checkOut) {
        this.firstName = first;
        this.surName =  last;
        this.price = price;
        this.deposit = deposit;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerBooking that = (CustomerBooking) o;
        return Objects.equals(firstName, that.firstName)
                && Objects.equals(surName, that.surName)
                && Objects.equals(price, that.price)
                && Objects.equals(deposit, that.deposit)
                && Objects.equals(checkIn, that.checkIn)
                && Objects.equals(checkOut, that.checkOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, surName, price, deposit, checkIn, checkOut);
    }
}
