package lama;

import lama.task.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UiTest {
    private static final String BAR = "____________________________________________________________";
    private Ui ui;
    private TaskList taskList;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
        this.ui = new Ui();
        this.taskList = new TaskList();
        taskList.add(new Todo("Read Book"));
    }

    @Test
    public void readCommandTest() {
        String input = "test" + System.lineSeparator();
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        this.ui = new Ui();
        String output = ui.readCommand();
        assertEquals("test", output);
    }

    @Test
    public void showWelcomeTest() {
        ui.showWelcome();
        String output = BAR + "\r\nHello! I'm lama.Lama\r\nWhat can I do for you?\r\n" + BAR + "\n\r\n";
        assertEquals(output, outputStream.toString());
    }

    @Test
    public void showAddCommandTest() {
        ui.showAddCommand(taskList);
        String output = BAR + "\r\nGot it. I've added this task:\r\n  " + taskList.get(taskList.size() - 1)
                + "\r\nNow you have 1 tasks in the list.\r\n" + BAR + "\n\r\n";
        assertEquals(output, outputStream.toString());
    }

    @Test
    public void showDeleteCommandHeaderTest() {
        ui.showDeleteCommandHeader();
        String output = BAR + "\r\nNoted. I've removed this task:\r\n";
        assertEquals(output, outputStream.toString());
    }

    @Test
    public void showDeleteCommandFooterTest() {
        ui.showDeleteCommandFooter(taskList);
        String output = "Now you have 1 tasks in the list.\r\n" + BAR + "\n\r\n";
        assertEquals(output, outputStream.toString());
    }

    @Test
    public void showExitCommandTest() {
        ui.showExitCommand();
        String output = BAR + "\r\nBye. Hope to see you again soon!\r\n" + BAR + "\r\n";
        assertEquals(output, outputStream.toString());
    }

    @Test
    public void showMarkCommandTest() {
        taskList.get(0).markAsDone();
        ui.showMarkCommand(taskList, 0);
        String output = BAR + "\r\nNice! I've marked this task as done:\r\n  " + taskList.get(0) + "\r\n"
                + BAR + "\n\r\n";
        assertEquals(output, outputStream.toString());
    }

    @Test
    public void showUnmarkCommandTest() {
        taskList.get(0).markAsUnDone();
        ui.showUnmarkCommand(taskList, 0);
        String output = BAR + "\r\nOK, I've marked this task as not done yet:\r\n  " + taskList.get(0) + "\r\n"
                + BAR + "\n\r\n";
        assertEquals(output, outputStream.toString());
    }

    @Test
    public void showListCommandTest() {
        ui.showListCommand(taskList);
        String output = BAR + "\r\nHere are the tasks in your list:\r\n1."
                + taskList.get(0) + "\r\n" + BAR + "\n\r\n";
        assertEquals(output, outputStream.toString());
    }

    @Test
    public void showLoadingErrorTest() {
        ui.showLoadingError();
        String output = BAR + "\r\nSorry, there's error loading the file!\r\nPlease Try Again!\r\n" + BAR + "\n\r\n";
        assertEquals(output, outputStream.toString());
    }

    @Test
    public void showErrorTest() {
        ui.showError("Error");
        String output = BAR + "\r\nError\r\n" + BAR + "\n\r\n";
        assertEquals(output, outputStream.toString());
    }

    @AfterEach
    public void reset() {
        System.setOut(standardOut);
    }

}
