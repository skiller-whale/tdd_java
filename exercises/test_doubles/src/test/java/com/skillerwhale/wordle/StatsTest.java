package com.skillerwhale.wordle;

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

@Disabled("TODO: Implement tests")
class StatsTest {
    private static TestServer server;
    private static Browser browser;

    @BeforeAll
    static void setup() throws Exception {
        // Start test server on random port
        Database database = new Database();
        server = new TestServer(database);
        server.start();
        browser = new Browser(server.getBaseUrl());
    }

    @AfterAll
    static void cleanup() {
        if (server != null) {
            server.close();
        }
        if (browser != null) {
            browser.close();
        }
    }

    private void winGame(Browser browser) throws Exception {
        browser.visit("/");
        browser.clickNewGameButton();
        browser.enterGuess("whale");
    }

    private void loseGame(Browser browser) throws Exception {
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
    void showsStatsAtEndOfGame() throws Exception {
        // Arrange: win and lose some games
        winGame(browser);
        loseGame(browser);
        winGame(browser);
        loseGame(browser);

        // Act: get stats test
        // This returns the text of an element that uses the css class 'stats'
        String statsText = browser.getStats();

        // Assert: check stats content
    }
}
