package tecna;

import tecna.command.ToDoCommand;
import tecna.exception.WrongFormatException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoCommandTest {
    @Test
    public void parseToDoCommand_nothingAfter_exceptionThrown() {
        ToDoCommand toDoCommand = new ToDoCommand("todo");

        try {
            toDoCommand.parseToDoCommand();
        } catch (Exception e) {
            assertEquals(e.getClass(), WrongFormatException.class);
        }
    }

    @Test
    public void parseToDoCommand_emptyTaskName_exceptionThrown() {
        ToDoCommand toDoCommand = new ToDoCommand("todo     ");

        try {
            toDoCommand.parseToDoCommand();
        } catch (Exception e) {
            assertEquals(e.getClass(), WrongFormatException.class);
        }
    }
}
