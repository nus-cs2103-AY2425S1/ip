package jag;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UiTest {
    @Test
    public void addedResponse_test() {
        // Stream created to hold and capture the print statement
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        // Creation of Ui, Task and TaskList objects
        Ui testUi = new Ui();

        Task testTodo = new Todo("read book");

        String b = "2020-10-10 1800";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime by = LocalDateTime.parse(b, formatter);
        Task testDeadline = new Deadline("read magazine", by);

        String f = "2019-10-10 1800";
        String t = "2019-10-11 1900";
        LocalDateTime from = LocalDateTime.parse(f, formatter);
        LocalDateTime to = LocalDateTime.parse(t, formatter);
        Task testEvent = new Event("read newspaper", from, to);

        TaskList tasks = new TaskList();


        // Simulating adding todos
        tasks.addTask(testTodo);
        testUi.addedResponse('T', testTodo, tasks);
        String actualOutput = outputStream.toString().trim();
        String exepectedOutput = """
                ----------
                Got it. I've added this task: 
                [T][] read book
                Now you have 1 tasks in the list
                ----------
                """;


        // Testing for Todos
        assertEquals(exepectedOutput, actualOutput);


        // Simulating adding Deadline
        tasks.addTask(testDeadline);
        testUi.addedResponse('D', testDeadline, tasks);
        actualOutput = outputStream.toString().trim();
        exepectedOutput = """
                ----------
                Got it. I've added this task: 
                [D][] read magazine by Oct 10 2020, 18 00 00
                Now you have 2 tasks in the list
                ----------
                """;

        // Testing for Deadline
        assertEquals(exepectedOutput, actualOutput);


        // Simulating adding Event
        tasks.addTask(testEvent);
        testUi.addedResponse('E', testEvent, tasks);
        actualOutput = outputStream.toString().trim();
        exepectedOutput = """
                ----------
                Got it. I've added this task: 
                [E][] read newspaper from Oct 10 2019, 18 00 00 to Oct 11 2019, 19 00 00
                Now you have 3 tasks in the list
                ----------
                """;

        // Testing for Events
        assertEquals(exepectedOutput, actualOutput);


        // Restore original System.out
        System.setOut(System.out);
    }
}