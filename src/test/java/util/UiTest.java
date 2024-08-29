package util;

import org.junit.jupiter.api.Test;

public class UiTest {

    @Test
    public void testShowWelcome() {
        Ui ui = new Ui();
        ui.showWelcome(); // This is hard to test, normally you'd mock console I/O
    }

    @Test
    public void testShowError() {
        Ui ui = new Ui();
        ui.showError("Test error message");
    }
}
