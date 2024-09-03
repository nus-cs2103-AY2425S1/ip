package taskalyn;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.PrintStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ParserTest {

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

        String expectedOutput =
                "    ____________________________________________________________\n" +
                "    Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n";
        assertEquals(expectedOutput, out.toString());
        System.setOut(null);
        System.setIn(null);
    }
}
