package seedu.babygronk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import task.Task;
import task.TaskList;
import task.ToDo;

public class TaskListTest {

    private String[] tasks = new String[] {
        "[T][ ] play football",
        "[D][X] submit assignment (by: 2024-09-17)",
        "[E][ ] attend meeting (from: 2pm to: 4pm)"
    };
    private TaskList taskList = new TaskList(Arrays.stream(tasks));

    @Test
    void testAddTask() {
        Task newTask = new ToDo("study for exams");
        String result = taskList.add(newTask);
        assertEquals("added task: [T][ ] study for exams\n4 task(s) in the list rn\n", result);
        assertEquals(4, taskList.getTasks().count());
    }

    @Test
    void testDeleteTask() {
        String[] input = {"1"};
        String result = taskList.delete(input);
        assertTrue(result.contains("has been ejected"));
        assertEquals(2, taskList.getTasks().count());
    }

    @Test
    void testDeleteTaskInvalidIndex() {
        String[] input = {"4"};
        String result = taskList.delete(input);
        assertEquals("4 is invalid, please pick within the range 1 - 3\n", result);
    }

    @Test
    void testFindTask() {
        String result = taskList.find("submit");
        assertTrue(result.contains("submit assignment"));
    }
}
