package com.seleniumtests.blogexamples.driversetup;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@Slf4j
public class BaseClassOnDemandDriverSetupWithProxy {

    private WebDriver driver;

    @BeforeMethod
    public void setupTest() {
        // Any other set up goes here
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        if (driver == null) {
            ChromeOptions chromeOptions = new ChromeOptions();
            WebDriverManager.chromedriver().setup();

            // ZAP proxy config
            String zapProxyHost = "127.0.0.1";
            String zapProxyPort = "8080";

            //set the proxy to use ZAP host and port
            String proxyAddress = zapProxyHost + ":" + zapProxyPort;
            Proxy zap_proxy = new Proxy();
            zap_proxy.setHttpProxy(proxyAddress).setSslProxy(proxyAddress);
            log.info("Set proxy to host:{} and port:{}", zapProxyHost, zapProxyPort);

            chromeOptions.addArguments("--ignore-certificate-errors");
            chromeOptions.setCapability(CapabilityType.PROXY, zap_proxy);
            driver = new ChromeDriver(chromeOptions);
        }
        return driver;
    }
}
