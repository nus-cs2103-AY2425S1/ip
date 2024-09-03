package arts.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Test class for the Ui class.
 * This class contains unit tests for methods in the Ui class, verifying user input and output functionality.
 */
public class UiTest {

    private Ui ui;

    /**
     * Sets up the test environment before each test.
     * Redirects System.in to provide predefined input for testing and initializes the Ui instance.
     */
    @BeforeEach
    public void setUp() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("test command\n".getBytes());
        System.setIn(in);
        ui = new Ui();
        System.setIn(sysInBackup);
    }

    /**
     * Tests the readCommand() method of the Ui class.
     * Verifies that the command read from input matches the expected predefined input.
     */
    @Test
    public void testReadCommand() {
        assertEquals("test command", ui.readCommand(), "The command read should match the input.");
    }

    /**
     * Tests the showWelcome() method of the Ui class.
     * This test ensures that the welcome message is displayed correctly.
     * Note: This test does not have assertions as it is typically visual.
     */
    @Test
    public void testShowWelcome() {
        ui.showWelcome();
    }

    /**
     * Tests the showGoodbye() method of the Ui class.
     * This test ensures that the goodbye message is displayed correctly.
     * Note: This test does not have assertions as it is typically visual.
     */
    @Test
    public void testShowGoodbye() {
        ui.showGoodbye();
    }
}
