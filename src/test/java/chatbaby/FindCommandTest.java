package chatbaby;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class FindCommandTest {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage("./data/test.txt");

        // Add some tasks for testing
        tasks.addTask(new ToDo("Play Pingpong"));
        tasks.addTask(new ToDo("Write report"));
        tasks.addTask(new Deadline("Submit assignment", "2024-08-31 23:59"));
        tasks.addTask(new Event("Play Basketball", "2024-09-01 09:00", "2024-09-01 17:00"));
    }

    @Test
    public void testFindCommand_success() throws ChatBabyException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        Command command = new FindCommand("find report");

        String expectedOutput = "Here are the tasks in your list:\n1. [T][ ] Write report\n";
            try {
                command.execute(tasks, ui, storage);

            } catch (ChatBabyException e) {
                throw new RuntimeException(e);
            }
        assertEquals(expectedOutput.replace("\r\n", "\n"),
                outputStream.toString().replace("\r\n", "\n"));
    }

    @Test
    public void testFindCommand_noMatch() throws ChatBabyException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        Command command = new FindCommand("find sing");

        String expectedOutput = "There is no task that matches.\n";

        try {
            command.execute(tasks, ui, storage);
        } catch (ChatBabyException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expectedOutput.replace("\r\n", "\n"),
                outputStream.toString().replace("\r\n", "\n"));
    }

    @Test
    public void testFindCommand_emptyKeyword() {
        assertThrows(ChatBabyException.class, () -> {
            Command command = new FindCommand("find");
            command.execute(tasks, ui, storage);
        });
    }
}
