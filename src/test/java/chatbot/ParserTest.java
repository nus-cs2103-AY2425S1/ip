package chatbot;

import org.junit.jupiter.api.Test;
import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests Parser class.
 */
public class ParserTest {

    /**
     * Tests if date time string is parsed correctly.
     */
    @Test
    public void parseStringToDateTime_formatting_success() {
        LocalDateTime actualDateTime = LocalDateTime.parse("2024-06-22T16:00");

        assertEquals(actualDateTime, Parser.parseStringToDateTime("2024-06-22 1600"));
        assertEquals(actualDateTime, Parser.parseStringToDateTime("2024/06/22 1600"));
        assertEquals(actualDateTime, Parser.parseStringToDateTime("2024-06-22 04:00 pm"));
        assertEquals(actualDateTime, Parser.parseStringToDateTime("2024/06/22 04:00 PM"));
    }

    /**
     * Tests for exception thrown when common inputs in the wrong formats
     * are passed to the function.
     */
    @Test
    public void parseStringToDateTime_exceptionThrown() {
        // Tests for double digits for month
        try {
            Parser.parseStringToDateTime("2024-6-22 1600");
        } catch (DateTimeParseException e) {
            assertEquals(
                    "Text '2024-6-22 1600' could not be parsed, unparsed text found at index 0",
                    e.getMessage());
        }

        // Tests for time provided
        try {
            Parser.parseStringToDateTime("2024-06-22");
        } catch (DateTimeParseException e) {
            assertEquals(
                    "Text '2024-06-22' could not be parsed, unparsed text found at index 0",
                    e.getMessage());
        }
    }

    /**
     * Tests for correct parsing of java LocalDateTime object into String.
     */
    @Test
    public void parseDateTimeToString_formatting_success() {
        LocalDateTime actualDateTime = LocalDateTime.parse("2024-06-22T16:00");

        assertEquals(
                Parser.parseDateTimeToString(actualDateTime), "2024-06-22 04:00 pm");
    }

    /**
     * Tests for correct reading of local file into TaskList
     * with only uncompleted tasks.
     */
    @Test
    public void parseFromTxtTaskList_tasksIncomplete() {
        TaskList taskList = new TaskList();

        Scanner sc1 = new Scanner("[T][ ] task 1");
        Parser.parseFromTxtTaskList(sc1, taskList);
        assertEquals(new Todo("task 1"), taskList.removeTask(1));
        sc1.close();

        Scanner sc2 = new Scanner("[D][ ] task 2 (by: 2024-06-22 1600)");
        Parser.parseFromTxtTaskList(sc2, taskList);
        assertEquals(
                new Deadline("task 2", "2024-06-22 1600"),
                taskList.removeTask(1));
        sc2.close();

        Scanner sc3 = new Scanner("[E][ ] task 3 (from: 2024-06-22 1600 to: 2024-06-22 1800)");
        Parser.parseFromTxtTaskList(sc3, taskList);
        assertEquals(
                new Event("task 3", "2024-06-22 1600", "2024-06-22 1800"),
                taskList.removeTask(1));
        sc3.close();
    }

    /**
     * Tests for correct reading of source file into TaskList
     * with only completed tasks.
     */
    @Test
    public void parseFromTxtTaskList_tasksCompleted() {
        TaskList taskList = new TaskList();

        Scanner sc1 = new Scanner("[T][X] task 1");
        Parser.parseFromTxtTaskList(sc1, taskList);
        Todo todo = new Todo("task 1");
        todo.markAsDone();
        assertEquals(todo, taskList.removeTask(1));
        sc1.close();

        Scanner sc2 = new Scanner("[D][X] task 2 (by: 2024-06-22 1600)");
        Parser.parseFromTxtTaskList(sc2, taskList);
        Deadline deadline = new Deadline("task 2", "2024-06-22 1600");
        deadline.markAsDone();
        assertEquals(deadline, taskList.removeTask(1));
        sc2.close();

        Scanner sc3 = new Scanner("[E][X] task 3 (from: 2024-06-22 1600 to: 2024-06-22 1800)");
        Parser.parseFromTxtTaskList(sc3, taskList);
        Event event = new Event("task 3", "2024-06-22 1600", "2024-06-22 1800");
        event.markAsDone();
        assertEquals(event, taskList.removeTask(1));
        sc3.close();
    }

    /**
     * Tests for correct recognition and handling of
     * empty strings and strings with invalid format
     * in source file.
     */
    @Test
    public void parseFromTxtTaskList_invalid_empty_input() {
        TaskList taskList = new TaskList();
        // Create a stream to hold the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        // Redirect System.out to the new stream
        System.setOut(new PrintStream(outContent));

        Scanner sc1 = new Scanner("[][X] invalid input");
        Parser.parseFromTxtTaskList(sc1, taskList);

        assertEquals(0, taskList.getTotalNumOfTasks());
        assertEquals("this line is in an invalid format\n", outContent.toString());

        outContent.reset();

        Scanner sc2 = new Scanner("        ");
        Parser.parseFromTxtTaskList(sc2, taskList);

        assertEquals(0, taskList.getTotalNumOfTasks());
        assertEquals("this line is in an invalid format\n", outContent.toString());

        // Restore original System.out
        System.setOut(originalOut);
    }
}
