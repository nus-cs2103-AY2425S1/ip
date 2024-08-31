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
        String expectedOutput = String.format(
                "    ********************************************************************\n"
                        + "    Hey there! I'm regina.Regina\n"
                        + "    I am a chatbot designed to help you track your activities.\n"
                        + "    You can add tasks using the following formats:\n"
                        + "    1. To add a To-Do task: todo <task_description>\n"
                        + "       Example: todo Finish homework\n"
                        + "    2. To add a Deadline task: deadline <task_description> /by <deadline>\n"
                        + "       Example: deadline Submit report /by 2/12/2024 1800\n"
                        + "    3. To add an Event task: event <task_description> /from <start_time> /to <end_time>\n"
                        + "       Example: event Team meeting /from 2/12/2024 1600 /to 2/12/2024 1800\n"
                        + "    You can also:\n"
                        + "    1. Mark a task as done: mark <task_number>\n"
                        + "       Example: mark 1\n"
                        + "    2. Unmark a task: unmark <task_number>\n"
                        + "       Example: unmark 1\n"
                        + "    3. Delete a task: delete <task_number>\n"
                        + "       Example: delete 1\n"
                        + "    4. List tasks: type 'list' to see all your tasks\n"
                        + "    5. Delete all current tasks: type 'clear'\n"
                        + "    6. Find out current date and time: type 'now'\n"
                        + "    7. List out all tasks occurring at a specified date and time:"
                        + " occurring <date_and_time>\n"
                        + "       Example: occurring 2/12/2024 1800\n"
                        + "    8. For help: type 'help'\n"
                        + "    What can I do for you?\n"
                        + "    ********************************************************************\n");

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
        String expectedOutput = String.format(
                "    ********************************************************************\n"
                        + "    This is a test message.\n"
                        + "    ********************************************************************\n");

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
