package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import skibidi.Ui;
import storage.TaskStorage;

public class ExitCommandTest {
    private Ui ui;
    private TaskStorage storage;

    @BeforeEach
    public void setUp() throws IOException {
        ui = new Ui();
        storage = new TaskStorage("data/test_tasks.txt");
    }

    @Test
    public void testExecute() {
        ExitCommand command = new ExitCommand();
        String response = command.execute(ui, storage);
        assertEquals("Bye. Hope to see you again soon!", response);
    }
}
