package taskalyn;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.PrintStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Verifies that the Parser properly handles user inputs.
 */
public class ParserTest {

    /**
     * Verifies that the Parser properly handles the bye command.
     */
    @Test
    public void testParseByeCommand() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        System.setIn(new ByteArrayInputStream("bye\n".getBytes()));

        Ui ui = new Ui();
        Database database = new Database();
        TaskManager taskManager = new TaskManager(database, ui);
        Parser parser = new Parser(ui, taskManager);
        parser.parse(taskManager);
        String output = out.toString();
        // Solution inspired by: https://stackoverflow.com/questions/41674408/java-test-system-output-including-new-lines-with-assertequals
        String expectedOutput = ("    ____________________________________________________________\n" +
                "    Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________")
                .replaceAll("\\n|\\r\\n", System.lineSeparator());
        assertEquals(expectedOutput.trim(), output.trim());
        System.setOut(null);
        System.setIn(null);
    }
}
