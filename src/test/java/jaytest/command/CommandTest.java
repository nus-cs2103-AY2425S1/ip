package jaytest.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import jay.command.Command;
import jay.command.InvalidCommandException;
import jay.task.InvalidDescriptionException;
import jay.task.Task;

public class CommandTest {
    @Test
    public void testGetCommandType() {
        Command command = new Command("list");
        assertEquals(Command.CommandType.List, command.getCommandType());

        command = new Command("bye");
        assertEquals(Command.CommandType.Exit, command.getCommandType());

        command = new Command("mark 1");
        assertEquals(Command.CommandType.Mark, command.getCommandType());

        command = new Command("unmark 1");
        assertEquals(Command.CommandType.Unmark, command.getCommandType());

        command = new Command("delete 1");
        assertEquals(Command.CommandType.Delete, command.getCommandType());

        command = new Command("find test");
        assertEquals(Command.CommandType.Find, command.getCommandType());

        command = new Command("todo test");
        assertEquals(Command.CommandType.Add, command.getCommandType());

        command = new Command("deadline test /by 30-07-2024 1400");
        assertEquals(Command.CommandType.Add, command.getCommandType());

        command = new Command("event test /from 30-07-2024 1400 /to 1600");
        assertEquals(Command.CommandType.Add, command.getCommandType());
    }

    @Test
    public void testGetTaskNumber() {
        try {
            Command command = new Command("mark 1");
            assertEquals(1, command.getTaskNumber());
        } catch (InvalidCommandException e) {
            fail();
        }
    }

    @Test
    public void testGetTaskType() {
        Command command = new Command("event project meeting /from 30-07-2024 1400 /to 1600\n");
        assertEquals(Task.Type.Event, command.getTaskType());
    }

    @Test
    public void testGetDescription() {
        try {
            Command command = new Command("event project meeting /from 30-07-2024 1400 /to 1600\n");
            assertEquals("project meeting", command.getDescription());
        } catch (InvalidDescriptionException e) {
            fail();
        }
    }

    @Test
    public void testGetDate() {
        Command command = new Command("event project meeting /from 30-07-2024 1400 /to 1600\n");
        assertEquals("30-07-2024", command.getDate());
    }

    @Test
    public void testGetStartTime() {
        Command command = new Command("event project meeting /from 30-07-2024 1400 /to 1600\n");
        assertEquals("1400", command.getStartTime());
    }

    @Test
    public void testGetEndTime() {
        Command command = new Command("event project meeting /from 30-07-2024 1400 /to 1600\n");
        assertEquals("1600", command.getEndTime());
    }
}
