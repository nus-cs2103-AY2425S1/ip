package toothless.ui;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;



/**
 * Tests for Ui class.
 */
public class UiTest {
    /**
     * Tests if the welcome message is printed correctly.
     */
    @Test
    public void testBye() {
        Ui ui = new Ui();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.bye();

        String expectedOutput = "Toothless:\n"
                + "Until next time, dragon rider!\n"
                + "Toothless the Night Fury, signing off.\n"
                + "See you soon!\n\n"
                + "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n";

        assertTrue(outContent.toString().contains(expectedOutput));
    }
}
