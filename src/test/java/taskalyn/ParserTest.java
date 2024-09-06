package taskalyn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.PrintStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Verifies that the Parser properly handles user inputs.
 */
public class ParserTest {
    private Taskalyn taskalyn;

    @BeforeEach
    public void setUp() {
        taskalyn = new Taskalyn();
    }

    /**
     * Verifies that the Parser properly handles the bye command (Unused for GUI).
     */
    @Test
    public void parse_byeCommand_exitsGracefully() {
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(out));
//        System.setIn(new ByteArrayInputStream("bye\n".getBytes()));
//
//        Ui ui = new Ui();
//        Database database = new Database();
//        TaskManager taskManager = new TaskManager(database, ui);
//        Parser parser = new Parser(ui, taskManager);
//        // Empty string used since unit test previously did not require 2nd argument.
//        parser.parse(taskManager, "");
//        String output = out.toString();
//        // Solution inspired by: https://stackoverflow.com/questions/41674408/java-test-system-output-including-new-lines-with-assertequals
//        String expectedOutput = ("    ____________________________________________________________\n" +
//                "    Bye. Hope to see you again soon!\n" +
//                "    ____________________________________________________________")
//                .replaceAll("\\n|\\r\\n", System.lineSeparator());
//        assertEquals(expectedOutput.trim(), output.trim());
//        System.setOut(null);
//        System.setIn(null);
        String response = taskalyn.getResponse("bye");
        assertEquals("Bye! Hope to see you again soon!", response);
    }
}
