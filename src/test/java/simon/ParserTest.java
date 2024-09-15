package simon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


public class ParserTest {

    @Test
    public void testParseListCommand() throws Exception {
        Command command = Parser.parse("list");
        assertEquals(ListCommand.class, command.getClass());
    }



    @Test
    public void testParseEventCommand() throws Exception {
        Command command = Parser.parse("event Meeting /from 8/9/2024 0900 /to 8/9/2024 1000");
        assertEquals(EventCommand.class, command.getClass());
        EventCommand eventCommand = (EventCommand) command;
        assertEquals("Meeting", eventCommand.getName());
        assertEquals("8/9/2024 0900", eventCommand.getFrom());
        assertEquals("8/9/2024 1000", eventCommand.getTo());
    }

    @Test
    public void testParseTodoCommand() throws Exception {
        Command command = Parser.parse("todo Buy groceries");
        assertEquals(ToDoCommand.class, command.getClass());
    }

    @Test
    public void testParseDeleteCommand() throws Exception {
        Command command = Parser.parse("delete 3");
        assertEquals(DeleteCommand.class, command.getClass());
        DeleteCommand deleteCommand = (DeleteCommand) command;
        assertEquals(2, deleteCommand.getIndex());
    }


    @Test
    public void testParseInvalidCommand() {
        assertThrows(IllegalArgumentException.class, () -> Parser.parse("invalidCommand"));
    }
}
