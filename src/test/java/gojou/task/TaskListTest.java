package gojou.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void testGetSizeMethod() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("1st task", false, Priority.NONE));
        tasks.add(new Task("2nd task", false, Priority.NONE));
        TaskList tasklist = new TaskList(tasks);

        assertEquals(2, tasklist.getSize());
    }

    @Test
    public void testShowTasksMethod() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("1st task", false, Priority.HIGH));
        tasks.add(new Task("2nd task", false, Priority.MEDIUM));
        TaskList tasklist = new TaskList(tasks);

        String expectedResult = """
                                    Testing...
                                    1. [HIGH][ ] 1st task
                                    2. [MEDIUM][ ] 2nd task""";

        assertEquals(expectedResult, tasklist.showTasks("Testing..."));
    }
}
