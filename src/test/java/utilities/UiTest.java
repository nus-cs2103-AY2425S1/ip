package utilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Task;
import tasks.Todo;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    private Ui ui;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        ui = new Ui();
        System.setOut(new PrintStream(outputStream));  // Redirect System.out to outputStream for capturing output
    }

    @Test
    public void testShowWelcome() {
        ui.showWelcome();
        String actualOutput = outputStream.toString().replace("\r\n", "\n").trim();  // Normalize and trim
        String expectedOutput = "____________________________________________________________\n"
                + "Hello! I'm Bigmouth\nWhat can I do for you?\n"
                + "____________________________________________________________";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testShowTaskAdded() {
        Task task = new Todo("Finish assignment");
        ui.showTaskAdded(task, 1);
        String actualOutput = outputStream.toString().replace("\r\n", "\n").trim();  // Normalize and trim
        String expectedOutput = "____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + "  [T][ ] Finish assignment\n"
                + "Now you have 1 tasks in the list.\n"
                + "____________________________________________________________";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testShowGoodbyeMessage() {
        ui.showGoodbyeMessage();
        String actualOutput = outputStream.toString().replace("\r\n", "\n").trim();  // Normalize and trim
        String expectedOutput = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";
        assertEquals(expectedOutput, actualOutput);
    }
}
