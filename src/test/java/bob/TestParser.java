package bob;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestParser {
    @Test
    public void parse_bye_commandReturned() {
        Command cmd = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, cmd);
    }

    @Test
    public void parse_list_commandReturned() {
        Command cmd = Parser.parse("list");
        assertInstanceOf(ListCommand.class, cmd);
    }

    @Test
    public void parse_mark_commandReturned() {
        Command cmd = Parser.parse("mark 1");
        assertEquals(new MarkCommand(1), cmd);
    }

    @Test
    public void parse_mark_exceptionThrown() {
        assertThrows(TaskIndexException.class, () -> Parser.parse("mark abc"));
    }

    @Test
    public void parse_unmark_commandReturned() {
        Command cmd = Parser.parse("unmark 1");
        assertEquals(new UnmarkCommand(1), cmd);
    }

    @Test
    public void parse_unmark_exceptionThrown() {
        assertThrows(TaskIndexException.class, () -> Parser.parse("unmark abc"));
    }

    @Test
    public void parse_delete_commandReturned() {
        Command cmd = Parser.parse("delete 1");
        assertEquals(new DeleteCommand(1), cmd);
    }

    @Test
    public void parse_find_commandReturned() {
        Command cmd = Parser.parse("find 2024-08-30");
        assertEquals(new FindDateCommand("2024-08-30"), cmd);
    }

    @Test
    public void parse_todo_commandReturned() {
        Command cmd = Parser.parse("todo Read a Book");
        assertEquals(new TodoCommand("Read a Book"), cmd);
    }

    @Test
    public void parse_deadline_commandReturned() {
        Command cmd = Parser.parse("deadline Do Homework /by 2024-08-30");
        assertEquals(new DeadlineCommand("Do Homework", "2024-08-30"), cmd);
    }

    @Test
    public void parse_event_commandReturned() {
        Command cmd = Parser.parse("event Attend University /from 2023-08-01 /to 2027-06-01");
        assertEquals(new EventCommand("Attend University", "2023-08-01", "2027-06-01"), cmd);
    }

    @Test
    public void parse_unknownCommand_exceptionThrown() {
        assertThrows(UnknownCommandException.class, () -> Parser.parse("unknownCommand"));
    }

    @Test
    public void parse_extraParams_exceptionThrown() {
        assertThrows(ExtraParamException.class, () -> Parser.parse("list 2"));
    }

    @Test
    public void parse_missingParams_exceptionThrown() {
        assertThrows(MissingParamException.class, () -> Parser.parse("event"));
    }
}
