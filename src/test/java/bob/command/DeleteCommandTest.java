package bob.command;

import bob.tasks.Deadline;
import bob.tasks.EventTask;
import bob.tasks.TaskList;
import bob.tasks.ToDos;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteCommandTest {

    @Test
    public void IsRunningTest() {
        DeleteCommand deleteCommand = new DeleteCommand(1);
        assertTrue(deleteCommand.isRunning());
    }

    @Test
    public void testExecute() {
        TaskList myTasks = new TaskList();
        myTasks.addTask(new ToDos("Hello"));
        myTasks.addTask(new Deadline("Hello",
                LocalDate.parse("18/12/2024", DateTimeFormatter.ofPattern("dd/MM/uuuu"))));
        myTasks.addTask(new EventTask("Hello",
                LocalDate.parse("18/12/2024", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalDate.parse("18/12/2025", DateTimeFormatter.ofPattern("dd/MM/uuuu"))));
        DeleteCommand deleteCommand = new DeleteCommand(1);
        deleteCommand.execute(myTasks);
        assertEquals(myTasks.size(), 2);
        deleteCommand.execute(myTasks);
        assertEquals(myTasks.size(), 1);
        deleteCommand.execute(myTasks);
        assertEquals(myTasks.size(), 0);
    }
}
