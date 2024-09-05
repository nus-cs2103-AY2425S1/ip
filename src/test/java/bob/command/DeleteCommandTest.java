package bob.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import bob.exceptions.InvalidTaskNumberException;
import bob.tasks.Deadline;
import bob.tasks.EventTask;
import bob.tasks.TaskList;
import bob.tasks.ToDo;

public class DeleteCommandTest {

    @Test
    public void isRunningTest() {
        DeleteCommand deleteCommand = new DeleteCommand(1);
        assertTrue(deleteCommand.isRunning());
    }

    @Test
    public void testExecute() throws InvalidTaskNumberException {
        TaskList myTasks = new TaskList();
        myTasks.addTask(new ToDo("Hello"));
        myTasks.addTask(new Deadline("Hello",
                LocalDate.parse("18/12/2024", DateTimeFormatter.ofPattern("dd/MM/uuuu"))));
        myTasks.addTask(new EventTask("Hello",
                LocalDate.parse("18/12/2024", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalDate.parse("18/12/2025", DateTimeFormatter.ofPattern("dd/MM/uuuu"))));
        DeleteCommand deleteCommand = new DeleteCommand(0);
        deleteCommand.execute(myTasks);
        assertEquals(2, myTasks.size());
        deleteCommand.execute(myTasks);
        assertEquals(1, myTasks.size());
        deleteCommand.execute(myTasks);
        assertEquals(0, myTasks.size());
    }
}
