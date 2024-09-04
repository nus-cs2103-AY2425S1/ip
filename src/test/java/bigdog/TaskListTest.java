package bigdog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        // Initialize the TaskList with some sample tasks
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(Todo.of("Task 1"));
        tasks.add(Deadline.of("Task 2 /by 10/09/2024"));
        taskList = new TaskList(tasks);
    }

    @Test
    void addTask_success() {
        // Add a new task and check if the task list size increases
        Task newTask = Event.of("Task 3 /from 12/09/2024 18:00 /to 12/09/2024 20:00");
        assertEquals("""
                Got it. I've added this task:
                [E][ ] Task 3 (from: 12 Sep 2024 18:00 to: 12 Sep 2024 20:00)
                Now you have 3 tasks in the list.
                """, taskList.add(newTask));
        assertEquals(3, taskList.get().size());
        assertTrue(taskList.get().contains(newTask));
    }

    @Test
    void deleteTask_success() {
        // Delete the first task and check if it's removed
        assertEquals("""
                Noted. I've removed this task:
                [T][ ] Task 1
                Now you have 1 tasks in the list.
                """, taskList.delete(1));
        assertEquals(1, taskList.get().size());
        assertNotEquals("Task 1", taskList.get().get(0).getDescription());
    }

    @Test
    void deleteTask_invalidIndex_throwsException() {
        // Attempt to delete a task with an invalid index and expect an exception
        assertThrows(BigdogException.class, () -> taskList.delete(0));
        assertThrows(BigdogException.class, () -> taskList.delete(3));
    }

    @Test
    void markTask_success() {
        // Mark the first task as done and check if it's marked
        assertEquals("""
                Nice! I've marked this task as done:
                [T][X] Task 1
                """, taskList.mark(1));
        assertTrue(taskList.get().get(0).isMarked());
    }

    @Test
    void markTask_invalidIndex_throwsException() {
        // Attempt to mark a task with an invalid index and expect an exception
        assertThrows(BigdogException.class, () -> taskList.mark(0));
        assertThrows(BigdogException.class, () -> taskList.mark(3));
    }

    @Test
    void unmarkTask_success() {
        // Mark and then unmark the first task, and check if it's unmarked
        taskList.mark(1);
        assertEquals("""
                OK, I've marked this task as not done yet:
                [T][ ] Task 1
                """, taskList.unmark(1));
        assertFalse(taskList.get().get(0).isMarked());
    }

    @Test
    void unmarkTask_invalidIndex_throwsException() {
        // Attempt to unmark a task with an invalid index and expect an exception
        assertThrows(BigdogException.class, () -> taskList.unmark(0));
        assertThrows(BigdogException.class, () -> taskList.unmark(3));
    }

    @Test
    void testToString() {
        assertEquals("""
                Here are the tasks in your list:
                1.[T][ ] Task 1
                2.[D][ ] Task 2 (by: 10 Sep 2024)
                """, taskList.toString());
    }
}