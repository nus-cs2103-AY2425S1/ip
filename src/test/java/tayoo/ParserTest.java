package tayoo;

import tayoo.command.AddTaskCommand;
import tayoo.command.Command;
import tayoo.command.DeleteAllCommand;
import tayoo.command.DeleteTaskCommand;
import tayoo.command.ExitCommand;
import tayoo.command.FindCommand;
import org.junit.jupiter.api.Test;
import tayoo.command.ListCommand;
import tayoo.command.MarkTaskCommand;
import tayoo.exception.TayooException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void parseCommand_toDoTask_success() {
        assertDoesNotThrow(() -> {
            String commandStr = "todo Return book";
            Command parsedResult = Parser.parseCommand(commandStr);

            assertInstanceOf(AddTaskCommand.class, parsedResult);

            String expected = "[T][ ] Return book";
            String actual = parsedResult.toString();
            assertEquals(expected, actual);
        });
    }

    @Test
    public void parseCommand_deadlineTask_success(){
        assertDoesNotThrow(() -> {
            String commandStr = "deadline return book /by 13/01/2024 18:30";
            Command parsedResult = Parser.parseCommand(commandStr);

            assertInstanceOf(AddTaskCommand.class, parsedResult);

            String expected = "[D][ ] return book (by: 13 Jan 2024 18:30)";
            String actual = parsedResult.toString();
            assertEquals(expected, actual);
        });
    }

    @Test
    public void parseCommand_eventTask_success() {
        assertDoesNotThrow(() -> {
            String commandStr = "event project meeting /from 01-01-2024 1800 /to 19:30";
            Command parsedResult = Parser.parseCommand(commandStr);

            assertInstanceOf(AddTaskCommand.class, parsedResult);

            String expected = "[E][ ] project meeting (from: 01 Jan 2024 18:00 to: 19:30)";
            String actual = parsedResult.toString();
            assertEquals(expected, actual);
        });
    }

    @Test
    public void parseCommand_markTask_success() {
        assertDoesNotThrow(() -> {
            String commandStr = "mark 1";
            Command parsedResult = Parser.parseCommand(commandStr);

            assertInstanceOf(MarkTaskCommand.class, parsedResult);
        });
    }

    @Test
    public void parseCommand_unmarkTask_success() {
        assertDoesNotThrow(() -> {
            String commandStr = "unmark 1";
            Command parsedResult = Parser.parseCommand(commandStr);

            assertInstanceOf(MarkTaskCommand.class, parsedResult);
        });
    }

    @Test
    public void parseCommand_findTask_success() {
        assertDoesNotThrow(() -> {
            String commandStr = "find book";
            Command parsedResult = Parser.parseCommand(commandStr);

            assertInstanceOf(FindCommand.class, parsedResult);
        });
    }

    @Test
    public void parseCommand_deleteTask_success() {
        assertDoesNotThrow(() -> {
            String commandStr = "delete 1";
            Command parsedResult = Parser.parseCommand(commandStr);

            assertInstanceOf(DeleteTaskCommand.class, parsedResult);
        });
    }

    @Test
    public void parseCommand_deleteAll_success() {
        assertDoesNotThrow(() -> {
            String commandStr = "delete all";
            Command parsedResult = Parser.parseCommand(commandStr);

            assertInstanceOf(DeleteAllCommand.class, parsedResult);
        });
    }

    @Test
    public void parseCommand_listTask_success() {
        assertDoesNotThrow(() -> {
            String commandStr = "list";
            Command parsedResult = Parser.parseCommand(commandStr);

            assertInstanceOf(ListCommand.class, parsedResult);
        });
    }

    @Test
    public void parseCommand_exitCode_success(){
        assertDoesNotThrow( () -> {
            assertInstanceOf(ExitCommand.class, Parser.parseCommand("bye"));
            assertInstanceOf(ExitCommand.class, Parser.parseCommand("clOse"));
            assertInstanceOf(ExitCommand.class, Parser.parseCommand("GOODBYe"));
            assertInstanceOf(ExitCommand.class, Parser.parseCommand("qUIT"));
            assertInstanceOf(ExitCommand.class, Parser.parseCommand("EXIT"));
        });
    }

    @Test
    public void parseCommand_unknownCommand_failure(){
        assertThrows(TayooException.class, () -> Parser.parseCommand("./quit"));
    }

}
