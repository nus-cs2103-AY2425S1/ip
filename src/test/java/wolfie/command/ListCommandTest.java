package wolfie.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wolfie.task.Deadline;
import wolfie.task.TaskList;
import wolfie.task.Todo;
import wolfie.util.Storage;
import wolfie.util.Ui;

class ListCommandTest {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage("data/testTasks.txt");
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    // Not working yet
    @Test
    void testExecute() throws IOException {
        tasks.add(new Todo("Test task", false));
        LocalDateTime by = LocalDateTime.parse("2021-01-25 10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        tasks.add(new Deadline("Test deadline", by, false));
        ListCommand command = new ListCommand();
        command.execute(tasks, ui, storage); // Should print the list of tasks

        String output = outputStreamCaptor.toString().trim(); // Trim to remove any extra newlines
        String expectedOutput = """
                Here are the tasks in your list:
                 1. [T][ ] Test task
                 2. [D][ ] Test deadline (by: Jan 25 2021, 10:00 am)""";
        assertTrue(output.contains(expectedOutput));
    }
}
