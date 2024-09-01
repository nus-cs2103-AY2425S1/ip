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

public class AddDeadlineCommandTest {
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
        AddDeadlineCommand command = new AddDeadlineCommand("deadline submit report /by 2023-10-10 23:59");
        command.execute(ui, storage);
        assertEquals("Erm...\n" +
                "SKIBIDI: Got it. I've added this task:\n" +
                "  [D][ ] submit report (by: Oct 10 2023 23:59)\n", outputStream.toString());
    }

    @Test
    public void testExecuteWithInvalidFormat() {
        assertThrows(SkibidiException.class, () -> {
            new AddDeadlineCommand("deadline submit report");
        });
    }

    @Test
    public void testExecuteWithInvalidDateFormat() throws SkibidiException {
        AddDeadlineCommand command = new AddDeadlineCommand("deadline submit report /by invalid-date");
        command.execute(ui, storage);
        assertEquals("Erm...\n" +
                "SKIBIDI: Invalid date format. Please use yyyy-mm-dd hh:mm.\n" + "\n", outputStream.toString());
    }
}