package tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

public class TaskListTest {

    @Test
    public void testIfListDisplaysCorrectOutput() {
        List<Task> testList1 = new ArrayList<>();
        testList1.add(new Deadline("CS2103T", LocalDate.parse("2024-08-26"), 1));
        testList1.add(new ToDo("Swimming", 1));
        testList1.add(new Event("CS2102", LocalDate.parse("2025-01-15"), LocalDate.parse("2025-05-07"), 1));

        // Capture System.out output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Act
        TaskList.printTasksContainingKeyword("CS", testList1);

        // Reset System.out to its original state
        System.setOut(originalOut);

        String expectedOutput =
                "____________________________________________________________" + System.lineSeparator()
                        + "Here are the matching tasks in your list:" + System.lineSeparator()
                        + "1.[D][ ] CS2103T (by: Aug 26 2024)" + System.lineSeparator()
                        + "2.[E][ ] CS2102 (from: Jan 15 2025 to: May 07 2025)" + System.lineSeparator()
                        + "____________________________________________________________" + System.lineSeparator();

        // Print to see actual output
        System.out.println("Actual Output: \n" + outContent);
        System.out.println("Expected Output: \n" + expectedOutput);

        // Assert that the captured output matches the expected output
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testForCommandOnPrintsCorrectTasksOnTheDate() {
        List<Task> testList2 = new ArrayList<>();
        testList2.add(new Deadline("CS2103T", LocalDate.parse("2024-08-26"), 1));
        testList2.add(new ToDo("Swimming", 1));
        testList2.add(new Event("CS2102", LocalDate.parse("2025-01-15"),
                LocalDate.parse("2025-05-07"), 1));

        // Capture System.out output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        TaskList.printTasksOnRelevantDate(LocalDate.parse("2025-02-10"), testList2);

        // Reset System.out to its original state
        System.setOut(originalOut);

        String expectedOutput =
                "____________________________________________________________" + System.lineSeparator()
                        + "Here are the tasks that occur at this date: 2025-02-10" + System.lineSeparator()
                        + "2.[E][ ] CS2102 (from: Jan 15 2025 to: May 07 2025)" + System.lineSeparator()
                        + "____________________________________________________________" + System.lineSeparator();

        // Print to see actual output
        System.out.println("Actual Output: \n" + outContent);
        System.out.println("Expected Output: \n" + expectedOutput);
        // Assert that the captured output matches the expected output
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testTaskPriorityIsUpdatedCorrectly() {
        List<Task> testList3 = new ArrayList<>();
        testList3.add(new Deadline("CS2103T", LocalDate.parse("2024-08-26"), 1));
        testList3.add(new ToDo("Swimming", 1));
        testList3.add(new Event("CS2102", LocalDate.parse("2025-01-15"),
                LocalDate.parse("2025-05-07"), 1));
        String actualOutput = TaskList.changePriorityForSpecificTask("cp 2 4", testList3);
        String expectedOutput = "Got it. I've changed the priority of this task:\n"
                + "[T][ ] Swimming\n"
                + "from LOW to CRITICAL\n";

        System.out.println("Actual Output: \n" + actualOutput);
        System.out.println("Expected Output: \n" + expectedOutput);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testFindTasksWithPartialKeywordMatch() {
        List<Task> testList4 = new ArrayList<>();
        testList4.add(new Deadline("CS2103T", LocalDate.parse("2024-08-26"), 1));
        testList4.add(new ToDo("Swimming", 1));
        testList4.add(new Event("CS2102", LocalDate.parse("2025-01-15"),
                LocalDate.parse("2025-05-07"), 1));
        testList4.add(new Deadline("CS2101", LocalDate.parse("2024-10-22"), 1));
        String actualOutput = TaskList.printTasksContainingKeyword("3", testList4);
        String expectedOutput = "Here are the matching tasks in your list:\n"
                + "1.[D][ ] CS2103T (by: Aug 26 2024)\n";
        System.out.println("Actual Output: \n" + actualOutput);
        System.out.println("Expected Output: \n" + expectedOutput);
        assertEquals(expectedOutput, actualOutput);
    }
}
