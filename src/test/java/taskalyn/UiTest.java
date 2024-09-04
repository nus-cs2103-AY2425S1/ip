package taskalyn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;


public class UiTest {
    private ByteArrayOutputStream outContent;
    private Ui ui;

    @Test
    public void testWelcomeMessage() {
        String input = "Hey! I'm Taskalyn, your personal Task Manager :)\n" +
                "    What can I do for you?";
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ui = new Ui();
        ui.printWelcome();

        // Solution inspired by: https://stackoverflow.com/questions/41674408/java-test-system-output-including-new-lines-with-assertequals
        StringWriter expectedStringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(expectedStringWriter);
        printWriter.println("    ____________________________________________________________");
        printWriter.println("    " + input);
        printWriter.println("    ____________________________________________________________\n");
        printWriter.close();

        String expectedOutput = expectedStringWriter.toString();

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testScanningOfTodoCommand() {
        String input = "todo eat";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Ui ui = new Ui();
        System.out.flush();
        String command = ui.readCommand();
        assertEquals("todo eat", command);
    }

    @Test
    public void testScanningOfCommandWithSpaceInfront() {
        String input = " todo eat";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Ui ui = new Ui();
        System.out.flush();
        String command = ui.readCommand();
        assertEquals("todo eat", command);
    }

}