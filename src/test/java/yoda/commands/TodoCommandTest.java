package yoda.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import yoda.TaskList;
import yoda.exceptions.YodaException;

public class TodoCommandTest {
    @Test
    public void run_validInput_success() throws YodaException {
        TaskList taskList = new TaskList();
        TodoCommand command = new TodoCommand(taskList, "todo sleep");

        command.run();

        assertEquals("sleep", taskList.get(0).getDescription());
        assertEquals(1, taskList.getLength());
    }

    @Test
    public void run_invalidInput_exceptionThrown() {
        TaskList taskList = new TaskList();
        TodoCommand command = new TodoCommand(taskList, "todo");

        try {
            command.run();
            fail();
        } catch (Exception e) {
            assertEquals("A todo must have a description, no...?", e.getMessage());
        }
    }

    @Test
    public void checkValidToDo_validInput_returnTrue() {
        assertEquals(true, new TodoCommand(new TaskList(), "todo sleep").hasValidFormat());
    }
    @Test
    public void checkValidToDo_invalidInput_returnFalse() {
        assertEquals(false, new TodoCommand(new TaskList(), "todo").hasValidFormat());
        assertEquals(false, new TodoCommand(new TaskList(), "").hasValidFormat());
        assertEquals(false, new TodoCommand(new TaskList(), "deadline").hasValidFormat());
    }
}
