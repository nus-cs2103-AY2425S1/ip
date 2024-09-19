package max.task;

import static org.junit.jupiter.api.Assertions.*;

import max.exception.MaxException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskListTest {
    private TaskList taskList;
    private Task todo1, todo2, deadline1, deadline2, event1, event2;

    @BeforeEach
    void setUp() throws MaxException {
        taskList = new TaskList();

        todo1 = new Todo("Workout in gym");
        todo1.markDone();
        todo1.addTag("health");

        todo2 = new Todo("Refactor data structure for iP project");
        todo2.addTag("project");

        deadline1 = new Deadline("Submit CS2103 assignment",
                LocalDateTime.of(2024, 9, 15, 23, 59));
        deadline1.markDone();
        deadline1.addTag("urgent");

        deadline2 = new Deadline("Review pull requests for group project",
                LocalDateTime.of(2024, 9, 21, 18, 0));
        deadline2.addTag("project");

        event1 = new Event("Internship Fair", "/from 2pm /to 5pm");
        event1.markDone();
        event1.addTag("Work");

        event2 = new Event("Team meeting for 2103 Project", "/from 8pm /to 10pm");
        event2.addTag("Work");

        taskList.addTask(todo1);
        taskList.addTask(todo2);
        taskList.addTask(deadline1);
        taskList.addTask(deadline2);
        taskList.addTask(event1);
        taskList.addTask(event2);
    }

    @Test
    void testFindWithExactMatch() {
        ArrayList<Task> result = taskList.find("Workout in gym");
        assertEquals(1, result.size());
        assertEquals(todo1, result.get(0));
    }

    @Test
    void testFindWithPartialMatch() {
        ArrayList<Task> result = taskList.find("Refactor");
        assertEquals(1, result.size());
        assertEquals(todo2, result.get(0));

        result = taskList.find("meeting");
        assertEquals(1, result.size());
        assertEquals(event2, result.get(0));
    }

    @Test
    void testFindWithMultipleMatches() {
        ArrayList<Task> result = taskList.find("project");
        assertEquals(2, result.size());
        assertTrue(result.contains(todo2));
        assertTrue(result.contains(deadline2));
    }

    @Test
    void testFindWithNoMatches() {
        ArrayList<Task> result = taskList.find("Nonexistent task");
        assertTrue(result.isEmpty());
    }

    @Test
    void testSearchByTagWithSingleTag() {
        List<Task> result = taskList.searchByTag("health");
        assertEquals(1, result.size());
        assertEquals(todo1, result.get(0));

        result = taskList.searchByTag("urgent");
        assertEquals(1, result.size());
        assertEquals(deadline1, result.get(0));
    }

    @Test
    void testSearchByTagWithMultipleTags() {
        List<Task> result = taskList.searchByTag("Work");
        assertEquals(2, result.size());
        assertTrue(result.contains(event1));
        assertTrue(result.contains(event2));
    }

    @Test
    void testSearchByTagWithNoMatches() {
        List<Task> result = taskList.searchByTag("nonexistent");
        assertTrue(result.isEmpty());
    }

    @Test
    void testSearchByTagWithPartialMatches() {
        List<Task> result = taskList.searchByTag("proj");
        assertTrue(result.isEmpty());  // Assumes exact tag match is required
    }

    @Test
    void testFindAndSearchByTagIntegration() {
        // Find tasks that contain "assignment" and then search for tasks with tag "urgent"
        ArrayList<Task> foundTasks = taskList.find("assignment");
        assertEquals(1, foundTasks.size());
        assertEquals(deadline1, foundTasks.get(0));

        List<Task> taggedTasks = taskList.searchByTag("urgent");
        assertEquals(1, taggedTasks.size());
        assertEquals(deadline1, taggedTasks.get(0));
    }

    @Test
    void testEmptyTaskList() {
        TaskList emptyTaskList = new TaskList();

        ArrayList<Task> result = emptyTaskList.find("anything");
        assertTrue(result.isEmpty());

        List<Task> tagResult = emptyTaskList.searchByTag("anything");
        assertTrue(tagResult.isEmpty());
    }
}
