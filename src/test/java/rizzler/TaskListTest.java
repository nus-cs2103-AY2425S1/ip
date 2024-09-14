package rizzler;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Array;
import java.util.ArrayList;

public class TaskListTest {
    @Test
    public void addTaskToList_success() throws Exception {
        String testOutput = new TaskList(new ArrayList<>()).add(new Todo("test 1"));
        assertEquals(testOutput,
                "Gotcha! I've added the new task for you:\n"
                        + "[T][ ] test 1\n"
                        + "Now you've gyat 1 tasks in the list.\n");
    }

    @Test
    public void deleteTaskFromList_success() throws Exception {
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(new Todo("test 2"));
        String testOutput = new TaskList(testList).delete(0);
        assertEquals(testOutput,
                "I have fanum taxed this task for you:\n"
                        + "[T][ ] test 2\n"
                        + "Now you've gyat 0 tasks in the list.\n");
    }

    @Test
    public void deleteTaskFromList_noItemsInList() throws Exception {
        try {
            new TaskList(new ArrayList<>()).delete(0);
            fail(); // this should never go through
        } catch (RizzlerException e) {
            assertEquals(e.getMessage(), "No tasks to fanum tax");
        }
    }

    @Test
    public void deleteTaskFromList_indexOutOfBounds() throws Exception {
        try {
            ArrayList<Task> testList = new ArrayList<>();
            testList.add(new Todo("test 3"));
            new TaskList(testList).delete(1);
        } catch (RizzlerException e) {
            assertEquals(e.getMessage(),
                    "Stop capping and put a task number that is actually in the list");
        }
    }

    @Test
    public void listTasks_success() {
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(new Todo("test 4"));
        testList.add(new Todo("test 5"));
        String output = new TaskList(testList).list();
        assertEquals(output,
                "You've gyat these tasks in your list\n"
                        + "1. [T][ ] test 4\n"
                        + "2. [T][ ] test 5\n");
    }

    @Test
    public void listTasks_noTasksToList() {
        String output = new TaskList(new ArrayList<>()).list();
        assertEquals(output,
                "No tasks here yet\n");
    }

    @Test
    public void markTaskInList_success() throws Exception {
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(new Todo("test 6"));
        String output = new TaskList(testList).mark(0);
        assertEquals(output,
                "Skibidi Ohio! You finished your task:\n"
                        + "[T][X] test 6\n");
    }

    @Test
    public void markTaskInList_noTasksInList() {
        try {
            new TaskList(new ArrayList<>()).mark(0);
            fail();
        } catch (RizzlerException e) {
            assertEquals(e.getMessage(),
                    "No tasks to mark");
        }
    }

    @Test
    public void markTaskInList_indexNotInList() {
        try {
            ArrayList<Task> testList = new ArrayList<>();
            testList.add(new Todo("test 7"));
            new TaskList(testList).mark(1);
        } catch (RizzlerException e) {
            assertEquals(e.getMessage(),
                    "Stop capping and put a task number that is actually in the list");
        }
    }

    @Test
    public void unmarkTaskInList_success() throws Exception {
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(new Todo("test 8"));
        TaskList testTasks = new TaskList(testList);
        testTasks.mark(0);
        String output = testTasks.unmark(0);
        assertEquals(output,
                "Womp womp. Better do it later:\n"
                        + "[T][ ] test 8\n");
    }

    @Test
    public void unmarkTaskInList_noTasksInList() {
        try {
            new TaskList(new ArrayList<>()).unmark(0);
            fail();
        } catch (RizzlerException e) {
            assertEquals(e.getMessage(),
                    "No tasks to unmark");
        }
    }

    @Test
    public void unmarkTaskInList_indexNotInList() {
        try {
            ArrayList<Task> testList = new ArrayList<>();
            testList.add(new Todo("test 9"));
            TaskList testTasks = new TaskList(testList);
            testTasks.mark(0);
            new TaskList(testList).unmark(1);
        } catch (RizzlerException e) {
            assertEquals(e.getMessage(),
                    "Stop capping and put a task number that is actually in the list");
        }
    }

    @Test
    public void findTaskInList_success() throws Exception {
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(new Todo("test 10"));
        TaskList testTasks = new TaskList(testList);
        String output = testTasks.find("test");
        assertEquals(output,
                "I found these tasks matching your keyword\n"
                        + "1. [T][ ] test 10\n");
    }

    @Test
    public void findTaskInList_noTasksInList() {
        try {
            new TaskList(new ArrayList<>()).find("test");
        } catch (RizzlerException e) {
            assertEquals(e.getMessage(),
                    "No tasks here to find");
        }
    }

    @Test
    public void sortTasks_success() throws Exception {
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(new Todo("test 12"));
        testList.add(new Todo("test 11"));
        TaskList testTasks = new TaskList(testList);
        String output = testTasks.sort();
        assertEquals(output, "I have sorted the tasks for you\n");
        //check if sorting really worked
        assertEquals(testTasks.list(),
                "You've gyat these tasks in your list\n"
                        + "1. [T][ ] test 11\n"
                        + "2. [T][ ] test 12\n");
    }

    @Test
    public void sortTasks_noTasksInList() {
        try {
            new TaskList(new ArrayList<>()).sort();
        } catch (RizzlerException e) {
            assertEquals(e.getMessage(),
                    "No tasks here to sort");
        }
    }
}
