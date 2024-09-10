package bob.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import bob.tasks.Deadline;
import bob.tasks.EventTask;
import bob.tasks.TaskList;
import bob.tasks.ToDo;

public class AddTaskCommandTest {

    @Test
    public void isRunningTest() {
        AddTaskCommand addTaskCommandToDo = new AddTaskCommand(new ToDo("Hello"));
        AddTaskCommand addTaskCommandDeadline = new AddTaskCommand(new Deadline("Hello",
                LocalDate.parse("18/12/2024", DateTimeFormatter.ofPattern("dd/MM/uuuu"))));
        AddTaskCommand addTaskCommandEvent = new AddTaskCommand(new EventTask("Hello",
                LocalDate.parse("18/12/2024", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalDate.parse("18/12/2025", DateTimeFormatter.ofPattern("dd/MM/uuuu"))));

        assertTrue(addTaskCommandToDo.isRunning());
        assertTrue(addTaskCommandDeadline.isRunning());
        assertTrue(addTaskCommandEvent.isRunning());
    }

    @Test
    public void addTaskTest() {
        AddTaskCommand addTaskCommandToDo = new AddTaskCommand(new ToDo("Hello"));
        AddTaskCommand addTaskCommandDeadline = new AddTaskCommand(new Deadline("Hello",
                LocalDate.parse("18/12/2024", DateTimeFormatter.ofPattern("dd/MM/uuuu"))));
        AddTaskCommand addTaskCommandEvent = new AddTaskCommand(new EventTask("Hello",
                LocalDate.parse("18/12/2024", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalDate.parse("18/12/2025", DateTimeFormatter.ofPattern("dd/MM/uuuu"))));

        TaskList myTasks = new TaskList();
        addTaskCommandToDo.execute(myTasks);
        assertEquals(1, myTasks.size());
        addTaskCommandDeadline.execute(myTasks);
        assertEquals(2, myTasks.size());
        addTaskCommandEvent.execute(myTasks);
        assertEquals(3, myTasks.size());
    }
}
