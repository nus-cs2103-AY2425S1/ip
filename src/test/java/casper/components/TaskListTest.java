package casper.components;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import casper.tasks.Deadline;
import casper.tasks.Event;
import casper.tasks.Task;
import casper.tasks.ToDo;


public class TaskListTest {
    @Test
    public void addTask_oneToDo_success() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Finish homework");
        taskList.addTask(task);
        assertEquals("1. [T][ ] Finish homework", taskList.toString());
    }

    @Test
    public void addTask_oneDeadline_success() {
        TaskList taskList = new TaskList();
        Task task = new Deadline("Submit applications",
                LocalDateTime.of(2024, 12, 12, 12, 12));
        taskList.addTask(task);
        assertEquals("1. [D][ ] Submit applications (by: 12-12-2024 12:12)", taskList.toString());
    }

    @Test
    public void addTask_oneEvent_success() {
        TaskList taskList = new TaskList();
        Task task = new Event("Arts Festival",
                LocalDateTime.of(2024, 12, 12, 12, 12),
                LocalDateTime.of(2024, 12, 12, 22, 12));
        taskList.addTask(task);
        assertEquals(
                "1. [E][ ] Arts Festival (from: 12-12-2024 12:12 to: 12-12-2024 22:12)",
                taskList.toString());
    }

    @Test
    public void addTask_multipleTasks_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("Finish homework"));
        taskList.addTask(new Deadline("Submit applications",
                LocalDateTime.of(2024, 12, 12, 12, 12)));
        taskList.addTask(new Event("Arts Festival",
                LocalDateTime.of(2024, 12, 12, 12, 12),
                LocalDateTime.of(2024, 12, 12, 22, 12)));
        assertEquals("""
                1. [T][ ] Finish homework
                2. [D][ ] Submit applications (by: 12-12-2024 12:12)
                3. [E][ ] Arts Festival (from: 12-12-2024 12:12 to: 12-12-2024 22:12)""", taskList.toString());
    }
}
