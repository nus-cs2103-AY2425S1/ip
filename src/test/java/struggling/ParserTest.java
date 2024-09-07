package struggling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import struggling.command.AddCommand;
import struggling.command.DeleteCommand;
import struggling.command.ExitCommand;
import struggling.command.FindCommand;
import struggling.command.ListCommand;
import struggling.command.MarkCommand;
import struggling.command.UnmarkCommand;


public class ParserTest {
    @Test
    public void parse_exitInput_exitCommand() {
        assertEquals(ExitCommand.class, Parser.parse("bye").getClass());
    }

    @Test
    public void parse_listInput_listCommand() {
        assertEquals(ListCommand.class, Parser.parse("list").getClass());
    }

    @Test
    public void parse_markInput_markCommand() {
        assertEquals(MarkCommand.class, Parser.parse("mark 1").getClass());
    }

    @Test
    public void parse_unmarkInput_unmarkCommand() {
        assertEquals(UnmarkCommand.class, Parser.parse("unmark 1").getClass());
    }

    @Test
    public void parse_addInput_addCommand() {
        assertEquals(AddCommand.class, Parser.parse("todo borrow book").getClass());
        assertEquals(AddCommand.class, Parser.parse("deadline return book /by 2024-08-27").getClass());
        assertEquals(AddCommand.class,
                Parser.parse("event project meeting /from Mon 2pm /to 4pm").getClass());
    }

    @Test
    public void parse_incompleteToDoInput_throwException() {
        try {
            assertEquals(0, Parser.parse("todo"));
            fail();
        } catch (Exception e) {
            assertEquals(StrugglingException.class, e.getClass());
        }
    }

    @Test
    public void parse_deleteInput_deleteCommand() {
        assertEquals(DeleteCommand.class, Parser.parse("delete 1").getClass());
    }

    @Test
    public void parse_findInput_findCommand() {
        assertEquals(FindCommand.class, Parser.parse("find book").getClass());
    }

    @Test
    public void parse_unknownCommand_throwException() {
        try {
            assertEquals(0, Parser.parse("blah"));
            fail();
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

}
