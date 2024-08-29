import com.meow.Meowception;
import com.meow.Parser;
import com.meow.TaskList;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    public void test1() throws IOException, Meowception {
        Parser parser = new Parser(new TaskList());

        assertEquals("    Meow has added this task hehe:\n            [T][ ] meow with martin\n            Neow you have 1 tasks in the list",parser.commandValidation("todo meow with martin") );
    }

}
