package zaibot.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zaibot.Storage;
import zaibot.TaskList;
import zaibot.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ExitCommandTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void execute_success() {
        TaskList tasks = new TaskList();
        Storage storage = new Storage(tasks);
        ExitCommand exitCommand = new ExitCommand();
        try {
            exitCommand.execute(tasks, storage);
        } catch (Exception e) {
            Ui.displayError(e);
        }
        Assertions.assertEquals("See you whenever.", outputStreamCaptor.toString().trim());
    }

}
