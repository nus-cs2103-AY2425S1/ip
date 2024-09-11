package victor.tasklist;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import victor.tasks.Deadline;
import victor.tasks.Event;
import victor.tasks.Task;

public class TaskListTest {
    @Test
    public void testInit() {
        Path dataPath = Paths.get("data", "data.txt");
        TaskList taskList = new TaskList(dataPath);
        assertEquals(taskList.getTaskList(), new ArrayList<Task>());
    }

    @Test
    public void testAdd() {
        Path dataPath = Paths.get("data", "data.txt");
        Task deadline = new Deadline("test deadline", "2020-01-01");
        TaskList taskList = new TaskList(dataPath);
        String[] response = taskList.addTask(deadline);
        String[] expectedResponse = new String[] {"  ~  Cool! I added this task:",
            "  ~  {D}{ } test deadline (by: Jan 01 2020)",
            "  ~  You now have 1 task in your list."};
        assertAll("Verifying task list adding tasks works", ()
                -> assertArrayEquals(response, expectedResponse), ()
                -> assertEquals(taskList.getSize(), 1));
    }

    @Test
    public void testMarkDone() {
        Path dataPath = Paths.get("data", "data.txt");
        Task deadline = new Deadline("test deadline", "2020-01-01");
        TaskList taskList = new TaskList(dataPath);
        taskList.addTask(deadline);
        String[] response = taskList.markDone(1);
        String[] expectedResponse = new String[] {"  ~  You finished a task! Well done! I marked this task as done:",
            "  ~  {D}{X} test deadline (by: Jan 01 2020)"};
        assertAll("Verifying task list marking tasks as done works", ()
                -> assertArrayEquals(response, expectedResponse), ()
                -> assertEquals(taskList.getSize(), 1));
    }

    @Test
    public void testMarkUndone() {
        Path dataPath = Paths.get("data", "data.txt");
        Task deadline = new Deadline("test deadline", "2020-01-01");
        Task event = new Event("test event", "2024-02-02", "2024-02-03");
        TaskList taskList = new TaskList(dataPath);
        taskList.addTask(deadline);
        taskList.addTask(event);
        taskList.markDone(1);
        String[] response = taskList.unmarkDone(2);
        String[] expectedResponse = new String[] {"  ~  Oops, I guess you didn't finish the task! I marked this"
                + " task as undone:", "  ~  {E}{ } test event (from: Feb 02 2024 to: Feb 03 2024)"};
        assertAll("Verifying task list unmarking tasks as done works", ()
                -> assertArrayEquals(response, expectedResponse), ()
                -> assertEquals(taskList.getSize(), 2));
    }

    @Test
    public void testDelete() {
        Path dataPath = Paths.get("data", "data.txt");
        Task deadline = new Deadline("test deadline", "2020-01-01");
        Task event = new Event("test event", "2024-02-02", "2024-02-03");
        TaskList taskList = new TaskList(dataPath);
        taskList.addTask(deadline);
        taskList.addTask(event);
        taskList.markDone(1);
        String[] response = taskList.deleteTask(1);
        String[] expectedResponse = new String[] {"  ~  Deleting the task below now!",
            "  ~  {D}{X} test deadline (by: Jan 01 2020)", "  ~  You now have 1 task in your list."};
        assertAll("Verifying task list deleting tasks works", ()
                -> assertArrayEquals(response, expectedResponse), ()
                -> assertEquals(taskList.getSize(), 1));
    }

    @Test
    public void testInvalidNumberDelete() {
        Path dataPath = Paths.get("data", "data.txt");
        Task deadline = new Deadline("test deadline", "2020-01-01");
        Task event = new Event("test event", "2024-02-02", "2024-02-03");
        TaskList taskList = new TaskList(dataPath);
        taskList.addTask(deadline);
        taskList.addTask(event);
        taskList.markDone(1);
        String[] response = taskList.deleteTask(99);
        String[] expectedResponse = new String[] {"  ~  I don't think there's a task with that number!"};
        assertAll("Verifying task list deleting tasks works", ()
                -> assertArrayEquals(response, expectedResponse), ()
                -> assertEquals(taskList.getSize(), 2));
    }
}
