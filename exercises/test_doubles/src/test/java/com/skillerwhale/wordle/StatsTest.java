package com.skillerwhale.wordle;

import com.microsoft.playwright.*;
import com.skillerwhale.wordle.helpers.Browser;
import com.skillerwhale.wordle.helpers.TestServer;
import com.skillerwhale.wordle.Database;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.assertj.core.api.Assertions.*;

class StatsTest {
    private static TestServer server;
    private static Playwright playwright;
    private static com.microsoft.playwright.Browser playwrightBrowser;
    private static BrowserContext context;

    private Browser browser;

    @BeforeAll
    static void setup() throws Exception {
        Database database = new Database();
        server = new TestServer(database);
        server.start();
        playwright = Playwright.create();
        playwrightBrowser = playwright.chromium().launch(new BrowserType.LaunchOptions()
            .setHeadless(true)
            .setArgs(java.util.Arrays.asList("--disable-dev-shm-usage", "--no-sandbox")));
        context = playwrightBrowser.newContext();
    }

    @AfterAll
    static void cleanup() {
        if (context != null) context.close();
        if (playwrightBrowser != null) playwrightBrowser.close();
        if (playwright != null) playwright.close();
        if (server != null) server.close();
    }

    @BeforeEach
    void createBrowser() {
        Page page = context.newPage();
        browser = new Browser(page, server.getBaseUrl());
    }

    @AfterEach
    void closeBrowser() {
        if (browser != null) browser.close();
    }

    private void winGame(Browser browser) {
        browser.visit("/");
        browser.clickNewGameButton();
        browser.enterGuess("whale");
    }

    private void loseGame(Browser browser) {
        browser.visit("/");
        browser.clickNewGameButton();
        browser.enterGuess("fishy");
        browser.enterGuess("shark");
        browser.enterGuess("shell");
        browser.enterGuess("trout");
        browser.enterGuess("salty");
        browser.enterGuess("ocean");
    }

    @Test
    @Disabled("TODO: Implement test")
    void showsStatsAtEndOfGame() {
        // Arrange: win and lose some games
        winGame(browser);
        loseGame(browser);
        winGame(browser);
        loseGame(browser);

        // Act: get stats test
        // This returns the text of an element that uses the css class 'stats'
        String statsHtml = browser.getStats();

        // Assert: check stats content
    }
}
