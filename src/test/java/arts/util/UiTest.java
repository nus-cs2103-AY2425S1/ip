package arts.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    private Ui ui;

    @BeforeEach
    public void setUp() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("test command\n".getBytes());
        System.setIn(in);
        ui = new Ui();
        System.setIn(sysInBackup);
    }

    @Test
    public void testReadCommand() {
        assertEquals("test command", ui.readCommand(), "The command read should match the input.");
    }

    @Test
    public void testShowWelcome() {
        ui.showWelcome();
    }

    @Test
    public void testShowGoodbye() {
        ui.showGoodbye();
    }
}