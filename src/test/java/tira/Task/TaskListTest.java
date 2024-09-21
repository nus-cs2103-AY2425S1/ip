package tira.task;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import tira.TiraException;

public class TaskListTest {
    @Test
    public void testAddEvent() throws TiraException {
        TaskList tasks = new TaskList();
        tasks.addEvent("event Project meeting /from 2001-01-01 /to 2002-02-02", "event Project meeting".split(" "));
        assertEquals(1, tasks.getTasks().size()); // checks if the taskList size is modified
        assertEquals("Project meeting", tasks.getTasks().get(0).getDescription()); // test for project description
        assertEquals(LocalDate.of(2001, 1, 1), ((Event) tasks.getTasks().get(0)).getStartDate());
        assertEquals(LocalDate.of(2002, 2, 2), ((Event) tasks.getTasks().get(0)).getEndDate());
    }

    @Test
    public void testMarkTask() throws TiraException {
        TaskList tasks = new TaskList();
        tasks.addToDo("todo Finish assignment", "todo Finish assignment".split(" "));
        tasks.markTask("mark 1".split(" "));
        assertTrue(tasks.getTasks().get(0).getIsDone());
    }

}
