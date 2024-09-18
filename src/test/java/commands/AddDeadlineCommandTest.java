package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import skibidi.SkibidiException;
import skibidi.Ui;
import storage.TaskStorage;

public class AddDeadlineCommandTest {
    private Ui ui;
    private TaskStorage storage;

    @BeforeEach
    public void setUp() throws IOException {
        ui = new Ui();
        storage = new TaskStorage("data/test_tasks.txt");
    }

    @Test
    public void testExecuteWithValidInput() throws SkibidiException {
        AddDeadlineCommand command = new AddDeadlineCommand("deadline submit report /by 2023-10-10 23:59");
        String response = command.execute(ui, storage);
        assertEquals("Got it. I've added this task:\n"
                + "  [D][ ] submit report (by: Oct 10 2023 23:59)", response);
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
        String response = command.execute(ui, storage);
        assertEquals("Invalid date format. Please use yyyy-mm-dd hh:mm.", response);
    }
}
