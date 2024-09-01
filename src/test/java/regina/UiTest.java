package regina;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class UiTest {

    private Ui ui; // The regina.Ui instance we are testing
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        ui = new Ui();
        System.setOut(new PrintStream(outContent)); // Redirect system output
    }

    @Test
    public void testGreet() {
        ui.greet("regina.Regina");
        String expectedOutput = """
                Hey there! I'm regina.Regina
                I am a chatbot designed to help you track your activities.
                You can add tasks using the following formats:
                1. To add a To-Do task: todo <task_description>
                   Example: todo Finish homework
                2. To add a Deadline task: deadline <task_description> /by <deadline>
                   Example: deadline Submit report /by 2/12/2024 1800
                3. To add an Event task: event <task_description> /from <start_time> /to <end_time>
                   Example: event Team meeting /from 2/12/2024 1600 /to 2/12/2024 1800
                You can also:
                1. Mark a task as done: mark <task_number>
                   Example: mark 1
                2. Unmark a task: unmark <task_number>
                   Example: unmark 1
                3. Delete a task: delete <task_number>
                   Example: delete 1
                4. List tasks: type 'list' to see all your tasks
                5. Delete all current tasks: type 'clear'
                6. Find out current date and time: type 'now'
                7. List out all tasks occurring at a specified date and time: occurring <date_and_time>
                   Example: occurring 2/12/2024 1800
                8. For help: type 'help'
                What can I do for you?
                """;

        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    public void testPrintError() {
        ui.printError("This is an error message.");
        String expectedOutput = String.format(
                "    ********************************************************************\n"
                        + "    This is an error message.\n"
                        + "    ********************************************************************\n");

        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    public void testPrintMessage() {
        ui.printMessage("This is a test message.");
        String expectedOutput = "This is a test message.";

        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    public void testExitMessage() {
        ui.exit();
        String expectedOutput = String.format(
                "    ********************************************************************\n"
                        + "    Bye. Hope to see you again soon!\n"
                        + "    ********************************************************************\n");

        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }
}
