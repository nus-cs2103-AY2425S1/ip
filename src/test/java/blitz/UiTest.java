package blitz;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import task.Todo;

public class UiTest {
    @Test
    public void getStringForAddedTask_taskListSizeAndTaskObject_returnString() {
        int size = 1;
        Todo task = new Todo("123", "T", false);

        String expected = "Got it. I've added this task:\n"
                + "[T][ ] " + task + "\n"
                + "Now you have " + size + " tasks in the list.";
        String response = new Ui().getStringForTaskAdded(size, task);
        assertEquals(expected, response);
    }

    @Test
    public void getStringForDeletedTask_taskObject_returnString() {
        Todo task = new Todo("123", "T", false);

        String expected = "Noted. I've removed this task:\n"
                + "[T][ ] " + task;
        String response = new Ui().getStringForTaskDeleted(task);
        assertEquals(expected, response);
    }

    @Test
    public void getStringForMarkedTask_taskObject_returnString() {
        Todo task = new Todo("123", "T", false);

        String expected = "Nice! I've marked this task as done:\n"
                + "[T][X] " + task;
        String response = new Ui().getStringForTaskMarked(task);
        assertEquals(expected, response);
    }

    @Test
    public void getStringForUnmarkedTask_taskObject_returnString() {
        Todo task = new Todo("123", "T", false);

        String expected = "Ok, I've marked this task as not done yet:\n"
                + "[T][ ] " + task;
        String response = new Ui().getStringForTaskUnmarked(task);
        assertEquals(expected, response);
    }

    @Test
    public void getStringForMatchedTask_matchedTaskList_returnString() {
        Todo task1 = new Todo("123", "T", false);
        Todo task2 = new Todo("345", "T", true);
        TaskList tasklist = new TaskList(new ArrayList<>(List.of(task1, task2)));

        String expected = "Here are the matching tasks in your list:\n"
                + "1. [T][ ] " + task1 + "\n"
                + "2. [T][X] " + task2 + "\n";
        String response = new Ui().getStringForTasksMatched(tasklist);
        assertEquals(expected, response);
    }

    @Test
    public void getStringForAllTask_taskList_returnString() {
        Todo task1 = new Todo("123", "T", false);
        Todo task2 = new Todo("345", "T", true);
        TaskList tasklist = new TaskList(new ArrayList<>(List.of(task1, task2)));

        String expected = "Here are the tasks in your list:\n"
                + "1. [T][ ] " + task1 + "\n"
                + "2. [T][X] " + task2 + "\n";
        String response = new Ui().getStringForAllTasks(tasklist);
        assertEquals(expected, response);
    }
}
