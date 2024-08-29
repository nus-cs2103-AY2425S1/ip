package command;

import org.junit.jupiter.api.Test;
import task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CommandTest {
    @Test
    public void testGetCommandType() {
        Command command = new Command("list");
        assertEquals(Command.CommandType.List, command.getCommandType());
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
        assertEquals(Task.TYPE.EVENT, command.getTaskType());
    }

    @Test
    public void testGetDescription() {
        Command command = new Command("event project meeting /from 30-07-2024 1400 /to 1600\n");
        assertEquals("project meeting", command.getDescription());
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
