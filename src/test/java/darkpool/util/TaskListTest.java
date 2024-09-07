package darkpool.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import darkpool.task.Deadline;
import darkpool.task.Event;
import darkpool.task.Task;
import darkpool.task.Todo;


class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void testAddTask() {
        Task todo = new Todo("read a book", false);
        taskList.addTask(todo);
        assertEquals(1, taskList.getSize());
        assertEquals( "1. [T][ ] read a book\n", taskList.toString());
    }

    @Test
    void testDeleteTask() {
        Task todo = new Todo("read a book", false);
        taskList.addTask(todo);
        String deletedTask = taskList.deleteTask(0);
        assertEquals("[T][ ] read a book", deletedTask);
        assertEquals(0, taskList.getSize());
    }

    @Test
    void testMarkTask() {
        Task todo = new Todo("read a book", false);
        taskList.addTask(todo);
        String markedTask = taskList.markTask(0);
        assertEquals("[T][X] read a book", markedTask);
        assertEquals("1. [T][X] read a book\n", taskList.toString());
    }

    @Test
    void testUnmarkTask() {
        Task todo = new Todo("read a book", true);
        taskList.addTask(todo);
        String unmarkedTask = taskList.unmarkTask(0);
        assertEquals("[T][ ] read a book", unmarkedTask);
        assertEquals("1. [T][ ] read a book\n", taskList.toString());
    }

    @Test
    void testToStringEmptyList() {
        assertEquals("bozo you got no tasks", taskList.toString());
    }

    @Test
    void testToStringNonEmptyList() throws DarkpoolException {
        Task todo = new Todo("read a book", false);
        Task deadline = new Deadline("submit report", "30-08-2024 18:00", false);
        Task event = new Event("attend meeting", "30-08-2024 10:00", "30-08-2024 12:00",
                false);

        taskList.addTask(todo);
        taskList.addTask(deadline);
        taskList.addTask(event);

        String expectedOutput = """
                1. [T][ ] read a book
                2. [D][ ] submit report \
                (by:30-08-2024 18:00)
                3. [E][ ] attend meeting (from:30-08-2024 10:00 to:30-08-2024 12:00)
                """;

        assertEquals(expectedOutput, taskList.toString());
    }

    @Test
    void testToFileString() throws DarkpoolException {
        Task todo = new Todo("read a book", false);
        Task deadline = new Deadline("submit report", "30-08-2024 18:00", false);
        Task event = new Event("attend meeting", "30-08-2024 10:00", "30-08-2024 12:00",
                false);

        taskList.addTask(todo);
        taskList.addTask(deadline);
        taskList.addTask(event);

        String expectedFileString = """
                T | 0 | read a book
                D | 0 | submit report | 30-08-2024 18:00
                E | 0 | attend meeting | 30-08-2024 10:00 | 30-08-2024 12:00
                """;

        assertEquals(expectedFileString, taskList.toFileString());
    }

    @Test
    void testConstructorWithTasks() throws DarkpoolException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("read a book", false));
        tasks.add(new Deadline("submit report", "30-08-2024 18:00", false));

        TaskList taskListWithTasks = new TaskList(tasks);
        assertEquals(2, taskListWithTasks.getSize());

        String expectedOutput =
                "1. [T][ ] read a book\n2. [D][ ] submit report (by:30-08-2024 18:00)\n";
        assertEquals(expectedOutput, taskListWithTasks.toString());
    }
}
