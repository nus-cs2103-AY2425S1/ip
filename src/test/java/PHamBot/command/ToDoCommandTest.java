package PHamBot.command;

import Johnson.command.ToDoCommand;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoCommandTest {
    @Test
    public void testToDoCommand() {
        try {
            ToDoCommand toDoCommand = new ToDoCommand("read book");
            assertEquals("read book", toDoCommand.getToDo().getTaskName());
        } catch (Exception e) {
            fail();
        }
    }
}
