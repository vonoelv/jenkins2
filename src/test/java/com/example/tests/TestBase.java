package com.example.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.example.CredentialsConfig;
import com.example.helpers.Attach;
import com.example.utils.RandomDataGenerator;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static java.lang.String.format;

public class TestBase {
    RandomDataGenerator generator = new RandomDataGenerator();

    @BeforeAll
    static void setUp() {
        CredentialsConfig credentialsConfig = ConfigFactory.create(CredentialsConfig.class);
        Configuration.remote = format("https://%s:%s@%s/wd/hub",
                credentialsConfig.login(), credentialsConfig.password(), System.getProperty("selenoidRemote"));
        Configuration.baseUrl = System.getProperty("baseUrl");
        Configuration.browserSize = System.getProperty("browserSize", "1200x1080");
        System.out.println("Configuration.remote=" + Configuration.remote);
        System.out.println("Configuration.baseUrl=" + Configuration.baseUrl);
        System.out.println("Configuration.browserSize=" + Configuration.browserSize);

        SelenideLogger.addListener("allure", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
