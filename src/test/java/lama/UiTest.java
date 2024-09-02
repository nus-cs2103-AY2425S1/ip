package lama;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lama.task.Todo;

/**
 * Test class for Ui class.
 * Contains unit test case for Ui class.
 */
public class UiTest {
    private static final String BAR = "____________________________________________________________";
    private Ui ui;
    private TaskList taskList;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    /**
     * Sets up the test environment before each test case.
     */
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
        this.ui = new Ui();
        this.taskList = new TaskList();
        taskList.add(new Todo("Read Book"));
    }

    /**
     * Tests the readCommand method of Ui.
     * Verifies that the method correctly reads input from the user.
     */
    @Test
    public void readCommandTest() {
        String input = "test" + System.lineSeparator();
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        this.ui = new Ui();
        String output = ui.readCommand();
        assertEquals("test", output);
    }

    /**
     * Tests the showWelcome method of Ui.
     * Verifies that the method correctly displays the welcome message.
     */
    @Test
    public void showWelcomeTest() {
        ui.showWelcome();
        String output = BAR + "\r\nHello! I'm lama.Lama\r\nWhat can I do for you?\r\n" + BAR + "\n\r\n";
        assertEquals(output, outputStream.toString());
    }

    /**
     * Tests the showAddCommand method of Ui.
     * Verifies that the method correctly displays the message.
     */
    @Test
    public void showAddCommandTest() {
        ui.showAddCommand(taskList);
        String output = BAR + "\r\nGot it. I've added this task:\r\n  " + taskList.get(taskList.size() - 1)
                + "\r\nNow you have 1 tasks in the list.\r\n" + BAR + "\n\r\n";
        assertEquals(output, outputStream.toString());
    }

    /**
     * Tests the showDeleteCommandHeader method of Ui.
     * Verifies that the method correctly displays the header message.
     */
    @Test
    public void showDeleteCommandHeaderTest() {
        ui.showDeleteCommandHeader();
        String output = BAR + "\r\nNoted. I've removed this task:\r\n";
        assertEquals(output, outputStream.toString());
    }

    /**
     * Tests the showDeleteCommandHeader method of Ui.
     * Verifies that the method correctly displays the footer message.
     */
    @Test
    public void showDeleteCommandFooterTest() {
        ui.showDeleteCommandFooter(taskList);
        String output = "Now you have 1 tasks in the list.\r\n" + BAR + "\n\r\n";
        assertEquals(output, outputStream.toString());
    }

    /**
     * Tests the showExitCommand method of Ui.
     * Verifies that the method correctly displays the message.
     */
    @Test
    public void showExitCommandTest() {
        ui.showExitCommand();
        String output = BAR + "\r\nBye. Hope to see you again soon!\r\n" + BAR + "\r\n";
        assertEquals(output, outputStream.toString());
    }

    /**
     * Tests the showMarkCommand method of Ui.
     * Verifies that the method correctly displays the message.
     */
    @Test
    public void showMarkCommandTest() {
        taskList.get(0).markAsDone();
        ui.showMarkCommand(taskList, 0);
        String output = BAR + "\r\nNice! I've marked this task as done:\r\n  " + taskList.get(0) + "\r\n"
                + BAR + "\n\r\n";
        assertEquals(output, outputStream.toString());
    }

    /**
     * Tests the showUnmarkCommand method of Ui.
     * Verifies that the method correctly displays the message.
     */
    @Test
    public void showUnmarkCommandTest() {
        taskList.get(0).markAsUnDone();
        ui.showUnmarkCommand(taskList, 0);
        String output = BAR + "\r\nOK, I've marked this task as not done yet:\r\n  " + taskList.get(0) + "\r\n"
                + BAR + "\n\r\n";
        assertEquals(output, outputStream.toString());
    }

    /**
     * Tests the showListCommand method of Ui.
     * Verifies that the method correctly displays the message.
     */
    @Test
    public void showListCommandTest() {
        ui.showListCommand(taskList);
        String output = BAR + "\r\nHere are the tasks in your list:\r\n1."
                + taskList.get(0) + "\r\n" + BAR + "\n\r\n";
        assertEquals(output, outputStream.toString());
    }

    /**
     * Tests the showLoading method of Ui.
     * Verifies that the method correctly displays the message.
     */
    @Test
    public void showLoadingErrorTest() {
        ui.showLoadingError();
        String output = BAR + "\r\nSorry, there's error loading the file!\r\nPlease Try Again!\r\n" + BAR + "\n\r\n";
        assertEquals(output, outputStream.toString());
    }

    /**
     * Tests the showError method of Ui.
     * Verifies that the method correctly displays the message.
     */
    @Test
    public void showErrorTest() {
        ui.showError("Error");
        String output = BAR + "\r\nError\r\n" + BAR + "\n\r\n";
        assertEquals(output, outputStream.toString());
    }

    /**
     * Tests the run method of FindCommand to ensure it correctly finds and displays tasks
     */
    @Test
    public void showFindCommandTest() {
        ui.showFindCommand(taskList);
        String output = BAR + "\r\nHere are the matching tasks in your list:\r\n"
                + "1.[T][ ] Read Book\r\n" + BAR + "\n\r\n";

        assertEquals(output, outputStream.toString());
    }

    /**
     * Tests the run method of FindCommand when there is no keyword found in the task list.
     */
    @Test
    public void showFindCommandNoneTest() {
        ui.showFindCommand(new TaskList());
        String output = BAR + "\r\nNo matching tasks found!\r\n" + BAR + "\n\r\n";

        assertEquals(output, outputStream.toString());
    }

    /**
     * Sets up the test environment before each test case.
     */
    @AfterEach
    public void reset() {
        System.setOut(standardOut);
    }

}
