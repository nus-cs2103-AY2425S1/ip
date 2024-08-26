package jag;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    // Creation of Task and TaskList objects
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
        Task Deadline = testTask.getTask(1);
        Task Event = testTask.getTask(2);

        assertTrue((todos instanceof Todo), "Expected a todo");
        assertTrue((Deadline instanceof Deadline), "Expected a Deadline");
        assertTrue((Event instanceof Event), "Expected an Event");

    }

    @Test
    public void write_test() throws IOException {
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