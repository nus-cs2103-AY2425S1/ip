package wolfie.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wolfie.exception.WolfieException;
import wolfie.task.Deadline;
import wolfie.task.TaskList;
import wolfie.task.Todo;
import wolfie.util.Storage;
import wolfie.util.Ui;

class ListCommandTest {
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
    void testExecute() throws IOException, WolfieException {
        tasks.add(new Todo("Test task", false));
        LocalDateTime by = LocalDateTime.parse("2021-01-25 10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        tasks.add(new Deadline("Test deadline", by, false));
        ListCommand command = new ListCommand();
        String result = command.execute(tasks, ui, storage); // Capture the result of the execute method

        String expectedOutput = """
                Here are the tasks in your list:
                [T][ ] Test task
                [D][ ] Test deadline (by: Jan 25 2021, 10:00 am)""";
        assertEquals(result, expectedOutput);
    }
}
