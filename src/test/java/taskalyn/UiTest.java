package taskalyn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;

/**
 * Verifies that the Ui handles user interactions properly.
 */
public class UiTest {
//    private ByteArrayOutputStream outContent;
//    private Ui ui;
//
//    /**
//     * Verifies that the welcome message is shown correctly.
//     */
//    @Test
//    public void printWelcome_displaysCorrectMessage() {
//        String input = "Hey! I'm Taskalyn, your personal Task Manager :)\n" +
//                "    What can I do for you?";
//        outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        ui = new Ui();
//        ui.showWelcome();
//
//        // Solution inspired by: https://stackoverflow.com/questions/41674408/java-test-system-output-including-new-lines-with-assertequals
//        StringWriter expectedStringWriter = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(expectedStringWriter);
//        printWriter.println("    ____________________________________________________________");
//        printWriter.println("    " + input);
//        printWriter.println("    ____________________________________________________________\n");
//        printWriter.close();
//
//        String expectedOutput = expectedStringWriter.toString();
//
//        assertEquals(expectedOutput, outContent.toString());
//    }
//
//    /**
//     * Verifies that the todo command is scanned correctly.
//     */
//    @Test
//    public void readCommand_todoCommand_scannedCorrectly() {
//        String input = "todo eat";
//        System.setIn(new ByteArrayInputStream(input.getBytes()));
//        outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        Ui ui = new Ui();
//        String command = ui.readCommand();
//        assertEquals("todo eat", command);
//    }
//
//    /**
//     * Verifies that any user input is trimmed before being parsed.
//     */
//    @Test
//    public void readCommand_todoCommand_ignoredCorrectly() {
//        String input = " todo eat";
//        System.setIn(new ByteArrayInputStream(input.getBytes()));
//        outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        Ui ui = new Ui();
//        String command = ui.readCommand();
//        assertEquals("todo eat", command);
//    }

}