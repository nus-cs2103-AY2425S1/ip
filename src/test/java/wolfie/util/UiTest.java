package wolfie.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        String expectedOutput = """
                         Hello Dean's Lister! I'm Wolfie
                         What can I do for you?
                        """;
        assertTrue(outputStreamCaptor.toString().trim().contains(expectedOutput));
    }

    @Test
    void testReadCommand() {
        String simulatedUserInput = "bye\n";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        ui = new Ui(); // Reinitialize Ui to ensure it reads from the new System.in
        String command = ui.readCommand();
        assertEquals("bye", command);
    }
}
