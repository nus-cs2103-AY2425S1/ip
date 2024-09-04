package chatbot.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;

import chatbot.logic.Ui;
import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    public void runStatus() {
        Ui ui = new Ui();
        assertEquals(ui.isRunning(), true);
    }

    @Test
    public void endRun() {
        Ui ui = new Ui();
        ui.endRun();
        assertEquals(ui.isRunning(), false);
    }

    @Test
    public void getInput() {
        ByteArrayInputStream in = new ByteArrayInputStream("test input".getBytes());
        System.setIn(in);
        Ui ui = new Ui();
        assertEquals(ui.getInput(), "test input");
    }
}
