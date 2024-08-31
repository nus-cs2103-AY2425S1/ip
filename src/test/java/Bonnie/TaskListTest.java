package Bonnie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void removeTask_removeNonexistentTask_exceptionThrown() {
        try {
            TaskList.addTask(new Todo("Eat Dinner"));
            TaskList.removeTask(2);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("You cannot delete a task that does not exist!", e.getMessage());
        }
    }

    @Test
    public void markTaskAsDone_remarkingDoneTaskAsDone_success() {
        TaskList.addTask(new Todo("Eat Dinner"));
        TaskList.markTaskAsDone(1);
        TaskList.markTaskAsDone(1);
        assertEquals(true, TaskList.getTasks().get(0).getStatus());
    }
}
