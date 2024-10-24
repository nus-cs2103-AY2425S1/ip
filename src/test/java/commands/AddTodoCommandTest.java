package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import skibidi.SkibidiException;
import skibidi.Ui;
import storage.TaskStorage;

public class AddTodoCommandTest {
    private Ui ui;
    private TaskStorage storage;

    @BeforeEach
    public void setUp() throws IOException {
        ui = new Ui();
        storage = new TaskStorage("data/test_tasks.txt");
    }

    @Test
    public void testExecuteWithValidInput() throws SkibidiException {
        AddTodoCommand command = new AddTodoCommand("todo submit report");
        String response = command.execute(ui, storage);
        assertEquals("Got it. I've added this task:\n"
                + "  [T][ ] submit report", response);
    }

    @Test
    public void testExecuteWithInvalidFormat() {
        assertThrows(SkibidiException.class, () -> {
            new AddTodoCommand("todo");
        });
    }
}
