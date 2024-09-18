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
import victor.tasks.ToDo;

public class TaskListTest {
    @Test
    public void taskList_testInit_makeTaskList() {
        Path dataPath = Paths.get("data", "data.txt");
        TaskList taskList = new TaskList(dataPath);
        assertEquals(taskList.getTaskList(), new ArrayList<Task>());
    }

    @Test
    public void taskList_testAdd_addTaskToTaskList() {
        Path dataPath = Paths.get("data", "data.txt");
        Task deadline = new Deadline("test deadline", "2020-01-01");
        TaskList taskList = new TaskList(dataPath);
        String[] response = taskList.addTask(deadline);
        String[] expectedResponse = new String[] {"  ~  So cute! I added this task:",
            "  ~  {D}{ } test deadline (by: Jan 01 2020)",
            "  ~  You now have 1 task in your list."};
        assertAll("Verifying task list adding tasks works", ()
                -> assertArrayEquals(response, expectedResponse), ()
                -> assertEquals(taskList.getSize(), 1));
    }

    @Test
    public void taskList_markDone_correctMark() {
        Path dataPath = Paths.get("data", "data.txt");
        Task deadline = new Deadline("test deadline", "2020-01-01");
        TaskList taskList = new TaskList(dataPath);
        taskList.addTask(deadline);
        String[] response = taskList.markDone(1);
        String[] expectedResponse = new String[] {
            "  ~  You finished a task! You're amazing! I marked this task as done:",
            "  ~  {D}{X} test deadline (by: Jan 01 2020)"};
        assertAll("Verifying task list marking tasks as done works", ()
                -> assertArrayEquals(response, expectedResponse), ()
                -> assertEquals(taskList.getSize(), 1));
    }

    @Test
    public void taskList_markDoneEmptyList_incorrectInputResponse() {
        Path dataPath = Paths.get("data", "data.txt");
        TaskList taskList = new TaskList(dataPath);
        String[] response = taskList.markDone(1);
        String[] expectedResponse = new String[] {"  ~  Oh dear, I don't think there's a task with that number :("};
        assertAll("Checking marking empty list returns error message to user", ()
                -> assertArrayEquals(response, expectedResponse), ()
                -> assertEquals(taskList.getSize(), 0));
    }

    @Test
    public void taskList_markDoneMarkNegativeNumber_incorrectInputResponse() {
        Path dataPath = Paths.get("data", "data.txt");
        Task deadline = new Deadline("test deadline", "2020-01-01");
        Task todo = new ToDo("test dodo");
        TaskList taskList = new TaskList(dataPath);
        taskList.addTask(deadline);
        taskList.addTask(todo);
        String[] response = taskList.markDone(-9);
        String[] expectedResponse = new String[] {"  ~  Oh dear, I don't think there's a task with that number :("};
        assertAll("Checking marking empty list returns error message to user", ()
                -> assertArrayEquals(response, expectedResponse), ()
                -> assertEquals(taskList.getSize(), 2));
    }

    @Test
    public void taskList_markDoneMarkZero_incorrectInputResponse() {
        Path dataPath = Paths.get("data", "data.txt");
        Task deadline = new Deadline("test deadline", "2020-01-01");
        Task todo = new ToDo("test dodo");
        TaskList taskList = new TaskList(dataPath);
        taskList.addTask(deadline);
        taskList.addTask(todo);
        String[] response = taskList.markDone(0);
        String[] expectedResponse = new String[] {"  ~  Oh dear, I don't think there's a task with that number :("};
        assertAll("Checking marking empty list returns error message to user", ()
                -> assertArrayEquals(response, expectedResponse), ()
                -> assertEquals(taskList.getSize(), 2));
    }

    @Test
    public void taskList_testMarkUndone_correctUnmarkResponse() {
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
    public void taskList_testDelete_correctDeleteResponse() {
        Path dataPath = Paths.get("data", "data.txt");
        Task deadline = new Deadline("test deadline", "2020-01-01");
        Task event = new Event("test event", "2024-02-02", "2024-02-03");
        TaskList taskList = new TaskList(dataPath);
        taskList.addTask(deadline);
        taskList.addTask(event);
        taskList.markDone(1);
        String[] response = taskList.deleteTask(1);
        String[] expectedResponse = new String[] {"  ~  I see you're done with that task now!",
            "  ~  {D}{X} test deadline (by: Jan 01 2020)", "  ~  You now have 1 task in your list."};
        assertAll("Verifying task list deleting tasks works", ()
                -> assertArrayEquals(response, expectedResponse), ()
                -> assertEquals(taskList.getSize(), 1));
    }

    @Test
    public void taskList_testInvalidNumberDelete_deleteErrorResponse() {
        Path dataPath = Paths.get("data", "data.txt");
        Task deadline = new Deadline("test deadline", "2020-01-01");
        Task event = new Event("test event", "2024-02-02", "2024-02-03");
        TaskList taskList = new TaskList(dataPath);
        taskList.addTask(deadline);
        taskList.addTask(event);
        taskList.markDone(1);
        String[] response = taskList.deleteTask(99);
        String[] expectedResponse = new String[] {"  ~  Oh dear, I don't think there's a task with that number :("};
        assertAll("Verifying task list deleting tasks works", ()
                -> assertArrayEquals(response, expectedResponse), ()
                -> assertEquals(taskList.getSize(), 2));
    }
}
