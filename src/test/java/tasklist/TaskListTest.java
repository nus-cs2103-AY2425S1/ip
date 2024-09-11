package tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

public class TaskListTest {

    @Test
    public void test1() {
        List<Task> testList1 = new ArrayList<>();
        testList1.add(new Deadline("CS2103T", LocalDate.parse("2024-08-26"), 1));
        testList1.add(new ToDo("Swimming", 1));
        testList1.add(new Event("CS2102", LocalDate.parse("2025-01-15"), LocalDate.parse("2025-05-07"), 1));

        // Mock user input for the Scanner
        String userInput = "Next Input";
        Scanner scanner = new Scanner(new ByteArrayInputStream(userInput.getBytes()));

        // Capture System.out output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Act
        TaskList.find("CS", testList1, scanner);

        // Reset System.out to its original state
        System.setOut(originalOut);

        String expectedOutput =
                "____________________________________________________________" + System.lineSeparator()
                        + "Here are the matching tasks in your list:" + System.lineSeparator()
                        + "1.[D][ ] CS2103T (by: Aug 26 2024)" + System.lineSeparator()
                        + "2.[E][ ] CS2102 (from: Jan 15 2025 to: May 07 2025)" + System.lineSeparator()
                        + "____________________________________________________________" + System.lineSeparator();

        // Print to see actual output
        System.out.println("Actual Output: \n" + outContent.toString());
        System.out.println("Expected Output: \n" + expectedOutput);

        // Assert that the captured output matches the expected output
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void test2() {
        List<Task> testList2 = new ArrayList<>();
        testList2.add(new Deadline("CS2103T", LocalDate.parse("2024-08-26"), 1));
        testList2.add(new ToDo("Swimming", 1));
        testList2.add(new Event("CS2102", LocalDate.parse("2025-01-15"), LocalDate.parse("2025-05-07"), 1));

        // Mock user input for the Scanner
        String userInput = "Next Input";
        Scanner scanner = new Scanner(new ByteArrayInputStream(userInput.getBytes()));

        // Capture System.out output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        TaskList.activitiesOnThisDate(LocalDate.parse("2025-02-10"), testList2, scanner);

        // Reset System.out to its original state
        System.setOut(originalOut);

        String expectedOutput =
                "____________________________________________________________" + System.lineSeparator()
                        + "Here are the tasks that occur at this date: 2025-02-10" + System.lineSeparator()
                        + "1.[E][ ] CS2102 (from: Jan 15 2025 to: May 07 2025)" + System.lineSeparator()
                        + "____________________________________________________________" + System.lineSeparator();

        // Print to see actual output
        System.out.println("Actual Output: \n" + outContent.toString());
        System.out.println("Expected Output: \n" + expectedOutput);
        // Assert that the captured output matches the expected output
        assertEquals(expectedOutput, outContent.toString());
    }

    /*@Test
    public void test1() {
        List<Task> testList1 = new ArrayList<>();
        testList1.add(new Deadline("CS2103T", LocalDate.parse("2024-08-26")));
        testList1.add(new ToDo("Swimming"));
        testList1.add(new Event("CS2102", LocalDate.parse("2025-01-15"), LocalDate.parse("2025-05-07")));


        // Mock user input for the Scanner
        String userInput = "Next Input";
        Scanner scanner = new Scanner(new ByteArrayInputStream(userInput.getBytes()));

        // Capture System.out output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Act
        TaskList.find("CS", testList1, scanner);

        // Reset System.out to its original state
        System.setOut(originalOut);
        String expectedOutput =
                "____________________________________________________________\n"
                + "Here are the matching tasks in your list:\n"
                + "1.[D][ ] CS2103T (by: 2024-08-26)\n"
                + "2.[E][ ] CS2102 (from: 2025-01-15 to: 2025-05-07)\n"
                + "____________________________________________________________\n";

        System.out.println(outContent);
        assertTrue(outContent.toString().contains(expectedOutput));
    }*/
}
