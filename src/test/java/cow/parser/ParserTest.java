package cow.parser;

import cow.commands.*;
import cow.exceptions.CowExceptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void listTest() throws CowExceptions {
        Command c = Parser.parse("list");
        assertInstanceOf(ListCommand.class, c);
    }

    @Test
    public void byeTest() throws CowExceptions {
        Command c = Parser.parse("bye");
        assertInstanceOf(ByeCommand.class, c);
    }

    @Test
    public void todoTest() throws CowExceptions {
        Command c = Parser.parse("todo test");
        assertInstanceOf(TodoCommand.class, c);
    }

    @Test
    public void deadlineTest() throws CowExceptions {
        Command c = Parser.parse("deadline return book /by 2/12/2019 1800");
        assertInstanceOf(DeadlineCommand.class, c);
    }

    @Test
    public void eventTest() throws CowExceptions {
        Command c = Parser.parse("event project meeting /from Mon 2pm /to 4pm");
        assertInstanceOf(EventCommand.class, c);
    }

    @Test
    public void markTest() throws CowExceptions {
        Command c = Parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, c);
    }

    @Test
    public void unmarkTest() throws CowExceptions {
        Command c = Parser.parse("unmark 1");
        assertInstanceOf(UnmarkCommand.class, c);
    }

    @Test
    public void deleteTest() throws CowExceptions {
        Command c = Parser.parse("delete 1");
        assertInstanceOf(DeleteCommand.class, c);
    }

    @Test
    public void dueTest() throws CowExceptions {
        Command c = Parser.parse("due 2/12/2019");
        assertInstanceOf(DueCommand.class, c);
    }

    @Test
    public void helpTest() throws CowExceptions {
        Command c = Parser.parse("help");
        assertInstanceOf(HelpCommand.class, c);
    }

    @Test
    public void unknownTest() throws CowExceptions {
        Command c = Parser.parse("random");
        assertInstanceOf(HelpCommand.class, c);
    }
}
