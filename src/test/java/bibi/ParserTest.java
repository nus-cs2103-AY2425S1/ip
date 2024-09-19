package bibi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bibi.exception.BibiInvalidCommandException;
import bibi.task.TaskList;

public class ParserTest {
    @Test
    public void parseUnknownCommand_getUnknownCommandText_throwsInvalidCommandException() {
        try {
            String e = Parser.parseCommand("test").execute(new TaskList(), new Processor(), new Storage(null));
        } catch (BibiInvalidCommandException e) {
            assertEquals(BibiInvalidCommandException.class, e.getClass());
        }
    }
}
