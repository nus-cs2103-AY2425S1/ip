package streams.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UiTest {

    private Ui ui;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        ui = new Ui();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testShowMessage() {
        ui.showMessage("Test message");
        assertEquals("Test message\n", outContent.toString());
    }

    @Test
    public void testShowError() {
        ui.showError("Test error");
        assertEquals("Test error\n", outContent.toString());
    }
}
