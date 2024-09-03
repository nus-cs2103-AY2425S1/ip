package bruno;

import bruno.command.AddCommand;
import bruno.command.ExitCommand;
import bruno.command.UnknownCommand;
import bruno.exceptions.BrunoException;
import bruno.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parse_validExit_parsedCorrectly() {
        try {
            TaskList tasks = new TaskList(new Storage("src/main/data", "src/main/data/bruno.txt"));
            assertEquals(true, Parser.parse("bye", tasks) instanceof ExitCommand);
        } catch (BrunoException e) {
            fail();
        }
    }

    @Test
    public void parse_validAdd_parsedCorrectly() {
        try {
            TaskList tasks = new TaskList(new Storage("src/main/data", "src/main/data/bruno.txt"));
            assertEquals(true, Parser.parse("todo read book", tasks) instanceof AddCommand);
        } catch (BrunoException e) {
            fail();
        }
    }

    @Test
    public void parse_invalidInput_nullReturned() {
        try {
            TaskList tasks = new TaskList(new Storage("src/main/data", "src/main/data/bruno.txt"));
            assertEquals(true, Parser.parse("toodoo read book", tasks) instanceof UnknownCommand);
        } catch (BrunoException e) {
            fail();
        }
    }
}
