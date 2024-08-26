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
import wolfie.util.Storage;
import wolfie.util.Ui;

class OnCommandTest {
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

    @Test
    void testExecute() throws IOException {
        LocalDateTime by = LocalDateTime.parse("2021-01-25 10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        tasks.add(new Deadline("Test deadline", by, false));
        OnCommand command = new OnCommand("2021-01-25");
        command.execute(tasks, ui, storage);

        String output = outputStreamCaptor.toString().trim(); // Trim to remove any extra newlines
        String expectedOutput = "[D][ ] Test deadline (by: Jan 25 2021, 10:00 am)";
        // Only the deadline on Jan 25 should be printed
        assertTrue(output.contains(expectedOutput));
    }
}
