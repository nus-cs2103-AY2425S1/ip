package bruno;

import bruno.command.AddCommand;
import bruno.command.ExitCommand;
import bruno.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parse_validExit_parsedCorrectly() {
        TaskList tasks = new TaskList(new Storage("src/main/data", "src/main/data/bruno.txt"));
        assertEquals(true, Parser.parse("bye", tasks) instanceof ExitCommand);
    }

    @Test
    public void parse_validAdd_parsedCorrectly() {
        TaskList tasks = new TaskList(new Storage("src/main/data", "src/main/data/bruno.txt"));
        assertEquals(true, Parser.parse("todo read book", tasks) instanceof AddCommand);
    }

    @Test
    public void parse_invalidInput_nullReturned() {
        TaskList tasks = new TaskList(new Storage("src/main/data", "src/main/data/bruno.txt"));
        assertEquals(null, Parser.parse("toodoo read book", tasks));
    }
}
