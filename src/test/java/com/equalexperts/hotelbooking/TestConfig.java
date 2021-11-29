package com.equalexperts.hotelbooking;


import com.equalexperts.hotelbooking.pages.BookingForm;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "com.equalexperts.hotelbooking")
@PropertySource({"classpath:application.properties"})
public class TestConfig {

    @Value( "${browser}" )
    private String browser;

    @Autowired
    private WebDriver driver;

    @Bean("driver")
    public WebDriver webDriver() {

        if ("chrome".equals(browser)) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else {
            throw new RuntimeException("** Browser not set in application.properties **");
        }
    }

    @Bean
    @DependsOn("driver")
    public BookingForm bookingForm() {
        return new BookingForm(driver);
    }
}
