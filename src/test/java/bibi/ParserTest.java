package bibi;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
}
