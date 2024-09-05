package jag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class StorageTest {
    // Creation of Task and TaskList objects
    private UiCLI testUi = new UiCLI();

    private Task testTodo = new Todo("read book");

    private String b = "2020-10-10 1800";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private LocalDateTime by = LocalDateTime.parse(b, formatter);
    private Task testDeadline = new Deadline("read magazine", by);

    private String f = "2019-10-10 1800";
    private String t = "2019-10-11 1900";
    private LocalDateTime from = LocalDateTime.parse(f, formatter);
    private LocalDateTime to = LocalDateTime.parse(t, formatter);
    private Task testEvent = new Event("read newspaper", from, to);



    @Test
    public void load_test() throws IOException {
        // Simulating adding todos
        TaskList tasks = new TaskList();
        tasks.addTask(this.testTodo);
        tasks.addTask(this.testDeadline);
        tasks.addTask(this.testEvent);
        Storage testStorage = new Storage("./data/jag.txt");
        testStorage.write(tasks);
        TaskList testTask = testStorage.load();
        Task todos = testTask.getTask(0);
        Task deadline = testTask.getTask(1);
        Task event = testTask.getTask(2);

        assertTrue((todos instanceof Todo), "Expected a todo");
        assertTrue((deadline instanceof Deadline), "Expected a Deadline");
        assertTrue((event instanceof Event), "Expected an Event");

    }

    @Test
    public void write_test_exceptionThrown() throws IOException {
        // Simulating adding todos
        TaskList tasks = new TaskList();
        Storage testStorage = new Storage("./data/jag.txt");

        try {
            testStorage.write(tasks);
        } catch (IOException e) {
            assertEquals(e.getMessage(), "No tasks to save :(");
        }
    }
}
