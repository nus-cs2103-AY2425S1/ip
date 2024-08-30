package atlas.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    private ArrayList<Task> initialTaskList = new ArrayList<>();

    @AfterEach
    public void cleanup() {
        this.initialTaskList = new ArrayList<>();
    }

    @Test
    public void constructor_zeroArguments_emptyArrayList() {
        TaskList tasks = new TaskList();
        assertTrue(tasks.isEmpty());
        assertEquals(tasks.size(), 0);
        assertEquals(tasks.listAllTasks(), "");
    }

    @Test
    public void constructor_taskListArgument_arrayListWithTasks() {
        this.initialTaskList.add(new Todo("return book"));
        this.initialTaskList.add(new Deadline("finish assignment",
                LocalDateTime.of(2024, 6, 15, 17, 0)));

        TaskList tasks = new TaskList(this.initialTaskList);
        assertFalse(tasks.isEmpty());
        assertEquals(this.initialTaskList.size(), tasks.size());
        String expectedListString = "1: [T] [ ] return book\n"
                + "2: [D] [ ] finish assignment (by: Jun 15 2024 5:00 pm)";
        assertEquals(expectedListString, tasks.listAllTasks());
    }
}
