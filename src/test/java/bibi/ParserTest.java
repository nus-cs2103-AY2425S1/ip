package bibi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class ParserTest {
    // Inspired from https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    // Before tests, set output to an array rather than to console
    private void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    // After tests, set back to System.out
    private void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void parseCommand_unknownCommand_success() {
        setUpStreams();
        Processor ui = new Processor();
        ui.printUnknownCommandMessage("cmd");

        String expected =
                "------------------------------------------------------------\r\n"
                + "cmd is an unknown command\r\n"
                + "------------------------------------------------------------";

        assertEquals(expected.trim(), outContent.toString().trim());
        restoreStreams();
    }

    @Test
    public void parseCommand_byeCommand_success() {
        setUpStreams();
        Processor ui = new Processor();
        ui.printExitMessage();

        String expected =
                "------------------------------------------------------------\r\n"
                + "See you soon :3\r\n"
                + "------------------------------------------------------------";

        assertEquals(expected.trim(), outContent.toString().trim());
        restoreStreams();
    }
}
