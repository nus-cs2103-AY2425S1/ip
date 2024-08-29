package util;

import org.junit.jupiter.api.Test;

/**
 * Unit test for the Ui class.
 */
public class UiTest {

    /**
     * Tests the showWelcome method of the Ui class.
     */
    @Test
    public void testShowWelcome() {
        Ui ui = new Ui();
        ui.showWelcome(); // This is hard to test, normally you'd mock console I/O
    }

    /**
     * Tests the showError method of the Ui class.
     */
    @Test
    public void testShowError() {
        Ui ui = new Ui();
        ui.showError("Test error message");
    }
}
