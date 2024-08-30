package colress;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import colress.task.Deadline;

public class TaskListTest {
    @Test
    public void retrieveTask_noItems_success() {
        TaskList taskList = new TaskList();
        assertEquals("", taskList.retrieveTasks());
    }

    @Test
    public void retrieveTask_noItemsOnDate_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Deadline("foo", LocalDate.parse("2024-08-08")));
        assertEquals("", taskList.retrieveTasks(LocalDate.parse("2024-08-09")));
    }

    @Test
    public void retrieveTask_itemsOnDate_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Deadline("foo", LocalDate.parse("2024-08-08")));
        assertEquals("Here is your list:\n1. [ ][D] foo (Aug 08 2024)",
                taskList.retrieveTasks(LocalDate.parse("2024-08-08")));
    }
}
