package chatbot;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void runStatus() {
        Ui ui = new Ui();
        assertEquals(ui.runStatus(), true);
    }

    @Test
    public void endRun() {
        Ui ui = new Ui();
        ui.endRun();
        assertEquals(ui.runStatus(), false);
    }

    @Test
    public void getInput() {
        ByteArrayInputStream in = new ByteArrayInputStream("test input".getBytes());
        System.setIn(in);
        Ui ui = new Ui();
        assertEquals(ui.getInput(), "test input");
    }
}
