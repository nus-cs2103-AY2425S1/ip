package bob.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bob.data.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

public class ByeTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStreamCaptor));

        // Initialize the task list, Ui, Storage, and the Bye command
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage("test.txt");
    }

    @Test
    public void bye_success() {
        new Bye().execute(taskList, ui, storage);
        assertEquals("Bye. Hope to see you again soon!", outputStreamCaptor.toString().trim());
    }
}
