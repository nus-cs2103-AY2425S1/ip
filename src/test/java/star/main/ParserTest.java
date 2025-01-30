package star.main;

import org.junit.jupiter.api.Test;
import star.command.*;
import star.exception.StarException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void parseTest() throws StarException {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
        assertTrue(Parser.parse("mark 1") instanceof DoneCommand);
        assertTrue(Parser.parse("list") instanceof ListCommand);
        assertTrue(Parser.parse("delete 4") instanceof DeleteCommand);
        assertTrue(Parser.parse("todo run") instanceof AddTodoCommand);
        assertTrue(Parser.parse("deadline project /by 13-09-2020 2359 /tag homework")
                instanceof AddDeadlineCommand);
        assertTrue(Parser.parse("event mid-autumn festival /from 19-09-2024 0000 /to 20-09-2024 0000")
                instanceof AddEventCommand);
        assertTrue(Parser.parse("find run") instanceof FindCommand);
        assertTrue(Parser.parse("unmark 3") instanceof UndoneCommand);
    }
}
