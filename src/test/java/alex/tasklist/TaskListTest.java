package alex.tasklist;

import alex.task.Deadline;
import alex.task.Task;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void handleTodo_success() {
        ArrayList<Task> testList = new ArrayList<>();
        TaskList tl = new TaskList(testList);
        tl.handleTodo("todo homework");
        assertEquals(1, testList.size());
        assertEquals("[T][ ] homework", testList.get(0).toString());
    }

    @Test
    public void handleTodo_missingDescription_tryAgainMessagePrinted() {
        ArrayList<Task> testList = new ArrayList<>();
        TaskList tl = new TaskList(testList);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream)); // Redirect System.out
        tl.handleTodo("todo ");
        System.setOut(originalOut);
        String expectedOutput = "You missed out some details. Try again\n";
        assertEquals(expectedOutput, outputStream.toString());
        assertEquals(0, testList.size());
    }

    @Test
    public void handleDate_validDate_success() {
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(new Deadline("read book", LocalDate.parse("2020-03-03")));
        TaskList tl = new TaskList(testList);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream)); // Redirect System.out
        tl.handleDate("tasks on 2020-03-03");
        System.setOut(originalOut);
        String expectedOutput = tl.LINE + "\n[D][ ] read book // by: Mar 3 2020\n" + tl.LINE +"\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void handleDate_invalidDate_catchMessagePrinted() {
        ArrayList<Task> testList = new ArrayList<>();
        TaskList tl = new TaskList(testList);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream)); // Redirect System.out
        tl.handleDate("tasks on 2020-03-33");
        System.setOut(originalOut);
        String expectedOutput = "Invalid date(s) entered. Use this format: YYYY-MM-DD\n";
        assertEquals(expectedOutput, outputStream.toString());
    }
}
