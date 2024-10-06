package trackbot.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import trackbot.TrackBotException;
import trackbot.commands.AddCommand;
import trackbot.commands.Command;
import trackbot.commands.DeleteCommand;
import trackbot.commands.ExitCommand;
import trackbot.commands.InvalidCommand;
import trackbot.commands.ListCommand;
import trackbot.commands.MarkCommand;
import trackbot.commands.UnmarkCommand;
import trackbot.task.Deadline;
import trackbot.task.Event;
import trackbot.task.ToDo;

public class ParserTest {
    private final Parser parser = new Parser();

    @Test
    public void parse_list_success() throws TrackBotException {
        Command command = parser.parse("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    public void parse_list_fail() throws TrackBotException {
        //assertThrows(TrackBotException.class, () -> parser.parse("list extra"));
        Command command = parser.parse("list extra");
        assertInstanceOf(InvalidCommand.class, command);
    }

    @Test
    public void parse_todo_success() throws TrackBotException {
        Command command = parser.parse("todo read book");
        assertInstanceOf(AddCommand.class, command);
        assertInstanceOf(ToDo.class, ((AddCommand) command).getTask());
        assertEquals("read book", ((AddCommand) command).getTask().getDesc());
    }

    @Test
    public void parse_deadline_success() throws TrackBotException {
        Command command = parser.parse("deadline return book /by 2024-12-29");
        assertInstanceOf(AddCommand.class, command);
        assertInstanceOf(Deadline.class, ((AddCommand) command).getTask());
        assertEquals("return book", ((AddCommand) command).getTask().getDesc());
        assertEquals("Dec 29 2024", ((Deadline) ((AddCommand) command).getTask()).getBy());
    }

    @Test
    public void parse_event_success() throws TrackBotException {
        Command command = parser.parse("event project zoom /from 2024-12-02 /to 2024-12-03");
        assertInstanceOf(AddCommand.class, command);
        assertInstanceOf(Event.class, ((AddCommand) command).getTask());
        assertEquals("project zoom", ((AddCommand) command).getTask().getDesc());
        assertEquals("Dec 2 2024", ((Event) ((AddCommand) command).getTask()).getDateFrom());
        assertEquals("Dec 3 2024", ((Event) ((AddCommand) command).getTask()).getDateTo());
    }

    @Test
    public void parse_delete_success() throws TrackBotException {
        Command command = parser.parse("delete 2");
        assertInstanceOf(DeleteCommand.class, command);
        assertEquals(1, ((DeleteCommand) command).getTaskIndex());
    }

    @Test
    void parse_mark_success() throws TrackBotException {
        Command command = parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, command);
        assertEquals(0, ((MarkCommand) command).getTaskIndex());
    }

    @Test
    void parse_unmark_success() throws TrackBotException {
        Command command = parser.parse("unmark 1");
        assertInstanceOf(UnmarkCommand.class, command);
        assertEquals(0, ((UnmarkCommand) command).getTaskIndex());
    }

    @Test
    void parse_bye_success() throws TrackBotException {
        Command command = parser.parse("bye");
        assertInstanceOf(ExitCommand.class, command);
    }

}
