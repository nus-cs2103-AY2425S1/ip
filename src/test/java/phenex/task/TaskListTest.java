package phenex.task;

import org.junit.jupiter.api.Test;
import phenex.exception.PhenexException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void invalid_index_phenexExceptionThrown() {
        TaskList taskList = new TaskList();
        ToDo toDoTask = new ToDo("task Test");
        taskList.addTask(toDoTask);

        try {
            taskList.markTaskCompleted(-1);
            fail();
        } catch (PhenexException e) {
            assertEquals("\t Invalid input, no such mission!", e.getMessage());
        }
    }

    @Test
    public void add_task_success() {
        TaskList taskList = new TaskList();
        ToDo toDo = new ToDo("test");
        taskList.addTask(toDo);

        assertEquals(1, taskList.getTasks().size());
    }

}