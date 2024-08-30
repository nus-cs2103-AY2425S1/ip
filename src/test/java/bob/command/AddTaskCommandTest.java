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

public class AddTaskCommandTest {

    @Test
    public void IsRunningTest() {
        AddTaskCommand addTaskCommandToDo = new AddTaskCommand(new ToDos("Hello"));
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
    public void AddTaskTest() {
        AddTaskCommand addTaskCommandToDo = new AddTaskCommand(new ToDos("Hello"));
        AddTaskCommand addTaskCommandDeadline = new AddTaskCommand(new Deadline("Hello",
                LocalDate.parse("18/12/2024", DateTimeFormatter.ofPattern("dd/MM/uuuu"))));
        AddTaskCommand addTaskCommandEvent = new AddTaskCommand(new EventTask("Hello",
                LocalDate.parse("18/12/2024", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalDate.parse("18/12/2025", DateTimeFormatter.ofPattern("dd/MM/uuuu"))));

        TaskList myTasks = new TaskList();
        addTaskCommandToDo.execute(myTasks);
        assertEquals(myTasks.size(), 1);
        addTaskCommandDeadline.execute(myTasks);
        assertEquals(myTasks.size(), 2);
        addTaskCommandEvent.execute(myTasks);
        assertEquals(myTasks.size(), 3);
    }
}
