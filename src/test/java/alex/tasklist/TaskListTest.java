package alex.tasklist;

import alex.task.Deadline;
import alex.task.Task;
import alex.task.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * List of tests to check whether the tasklist class is running correctly
 */
public class TaskListTest {
    @Test
    public void handleMark_success() {
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(new Todo("shopping"));
        TaskList tl = new TaskList(testList);
        String expectedOutput = "You deserve a pat on the back! I've marked this task as done: \n" +
                "[T][X] shopping";
        assertEquals(expectedOutput, tl.handleMark("mark 1"));
    }

    @Test
    public void handleMark_invalidIndex_catchMessagePrinted() {
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(new Todo("shopping"));
        TaskList tl = new TaskList(testList);
        String expectedOutput = "It seems that task does not exist. Please try again.";
        assertEquals(expectedOutput, tl.handleMark("mark 2"));
    }

    @Test
    public void handleTodo_success() {
        ArrayList<Task> testList = new ArrayList<>();
        TaskList tl = new TaskList(testList);
        tl.handleTodo("todo homework");
        assertEquals(1, testList.size());
        assertEquals("[T][ ] homework", testList.get(0).toString());
    }

    @Test
    public void handleTodo_missingDescription_catchMessagePrinted() {
        ArrayList<Task> testList = new ArrayList<>();
        TaskList tl = new TaskList(testList);
        String expectedOutput = "You missed out some details. Try again";
        assertEquals(expectedOutput, tl.handleTodo("todo "));
        assertEquals(0, testList.size());
    }

    @Test
    public void handleDelete_success() {
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(new Todo("shopping"));
        TaskList tl = new TaskList(testList);
        String expectedOutputPreDelete = "Here are the tasks in your list: \n" +
                "1. [T][ ] shopping";
        String expectedOutputPostDelete = "Here are the tasks in your list: ";
        assertEquals(expectedOutputPreDelete, tl.handleList());
        tl.handleDelete("delete 1");
        assertEquals(expectedOutputPostDelete, tl.handleList());
    }

    @Test
    public void handleDelete_invalidIndex_catchMessagePrinted() {
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(new Todo("shopping"));
        TaskList tl = new TaskList(testList);
        String expectedOutput = "It seems that task does not exist. Please try again.";
        assertEquals(expectedOutput, tl.handleDelete("delete 3"));
    }
    @Test
    public void handleDate_validDate_success() {
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(new Deadline("read book", LocalDate.parse("2020-03-03")));
        TaskList tl = new TaskList(testList);
        String expectedOutput = "[D][ ] read book // by: Mar 3 2020";
        assertEquals(expectedOutput, tl.handleDate("tasks on 2020-03-03"));
        assertEquals(1, testList.size());
    }

    @Test
    public void handleDate_invalidDate_catchMessagePrinted() {
        ArrayList<Task> testList = new ArrayList<>();
        TaskList tl = new TaskList(testList);
        String expectedOutput = "Invalid date(s) entered. Use this format: YYYY-MM-DD";
        assertEquals(expectedOutput, tl.handleDate("tasks on 2020-03-33"));
    }

    @Test
    public void handleFind_matchFound() {
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(new Deadline("read book", LocalDate.parse("2020-03-03")));
        testList.add(new Todo("shopping"));
        TaskList tl = new TaskList(testList);
        String expectedOutput = "Tasks found matching the keyword: book\n" +
                "1. [D][ ] read book // by: Mar 3 2020";
        assertEquals(expectedOutput, tl.handleFind("find book"));
        assertEquals(2, testList.size());
    }

    @Test
    public void handleFind_noMatch_catchMessagePrinted() {
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(new Deadline("read book", LocalDate.parse("2020-03-03")));
        testList.add(new Todo("shopping"));
        TaskList tl = new TaskList(testList);
        String expectedOutput = "No tasks found matching the keyword: cat";
        assertEquals(expectedOutput, tl.handleFind("find cat"));
        assertEquals(2, testList.size());
    }

}
