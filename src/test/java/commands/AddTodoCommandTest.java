package commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import skibidi.SkibidiException;
import skibidi.Ui;
import storage.TaskStorage;
import java.io.IOException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddTodoCommandTest{
    private Ui ui;
    private TaskStorage storage;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() throws IOException {
        ui = new Ui();
        storage = new TaskStorage("data/test_tasks.txt");
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testExecuteWithValidInput() throws SkibidiException {
        AddTodoCommand command = new AddTodoCommand("todo submit report");
        command.execute(ui, storage);
        assertEquals("Erm...\n" +
                "SKIBIDI: Got it. I've added this task:\n" +
                "  [T][ ] submit report", outputStream.toString().trim());
    }

    @Test
    public void testExecuteWithInvalidFormat() {
        assertThrows(SkibidiException.class, () -> {
            new AddTodoCommand("todo");
        });
    }
}