package wolfie.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UiTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream(); // Captures System.out
    private Ui ui;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        ui = new Ui();
    }

    @Test
    void testShowWelcome() {
        ui.showWelcome();
        assertEquals("Hello! I'm Wolfie\nWhat can I do for you?\n", outputStreamCaptor.toString().trim());
    }

    @Test
    void testReadCommand() {
        String simulatedUserInput = "bye\n";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        String command = ui.readCommand();
        assertEquals("bye", command);
    }
}
