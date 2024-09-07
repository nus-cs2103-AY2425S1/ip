package wolfie.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage("data/testTasks.txt");
    }

    @Test
    void testExecute() {
        LocalDateTime by = LocalDateTime.parse("2021-01-25 10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        tasks.add(new Deadline("Test deadline", by, false));
        OnCommand command = new OnCommand("2021-01-25");
        String result = command.execute(tasks, ui, storage);
        String expectedOutput = """
                Here are the tasks on Jan 25 2021:
                   [D][ ] Test deadline (by: Jan 25 2021, 10:00 am)
                 """;
        // Only the deadline on Jan 25 should be printed
        assertEquals(result, expectedOutput);
    }
}
