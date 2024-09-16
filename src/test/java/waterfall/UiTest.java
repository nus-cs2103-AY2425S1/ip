package waterfall;

import org.junit.jupiter.api.*;
import waterfall.task.Task;
import waterfall.task.TaskList;
import waterfall.task.ToDo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class UiTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Ui ui;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        ui = new Ui();
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }

    @Test
    void showWelcomeMessage_shouldDisplayCorrectMessage() {
        String res = Ui.showWelcomeMessage();
        assertEquals("Hualalalalala I'm Waterfall\n" + "What can I do for you?\n", res);
    }

    @Test
    void showByeMessage_shouldDisplayCorrectMessage() {
        String res = ui.showByeMessage();
        String expectedMessage = "Shhhhhhhhhhhh. Hope to see you again soon!\n";
        assertEquals(expectedMessage, res);
    }

    @Test
    void showLoadingError_shouldDisplayCorrectErrorMessage() {
        ui.showLoadingError();
        String expectedMessage = "Oops! Something went wrong in loading the database!";
        assertTrue(outputStreamCaptor.toString().contains(expectedMessage.trim()));
    }

    @Test
    void showLine_shouldDisplayCorrectLine() {
        String res = ui.showLine();
        String expectedMessage = "____________________________________________________________\n";
        assertEquals(expectedMessage, res);
    }

    @Test
    void readCommand_shouldReadUserInput() {
        String input = "test command";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        String command = ui.readCommand();
        assertEquals(input, command);
    }

    @Test
    void showError_shouldDisplayCorrectErrorMessage() {
        String errorMessage = "An error occurred";
        String res = ui.showError(errorMessage);
        String expectedMessage = "Oops Water falls: " + errorMessage;
        assertEquals(expectedMessage, res);
    }

    @Test
    void showAddMessage_shouldDisplayCorrectAddMessage() {
        Task task = new ToDo("Test task");
        String res = ui.showAddMessage(task);
        String expectedMessage = "Successfully added a task to the waterfallll:\n"
                + "[T][ ] Test task";
        assertEquals(expectedMessage, res);
    }

    @Test
    void showMarkMessage_shouldDisplayCorrectMarkMessage() {
        Task task = new ToDo("Test task");
        task.setDone(true);
        String res = ui.showMarkMessage(task);
        String expectedMessage = "Huluhuluhulu, I've marked this task as done: \n"
                + "[T][X] Test task";
        assertEquals(expectedMessage, res);
    }

    @Test
    void showUnmarkMessage_shouldDisplayCorrectUnmarkMessage() {
        Task task = new ToDo("Test task");
        String res = ui.showUnmarkMessage(task);
        String expectedMessage = "Hohohohohoho, I've marked this task as not done: \n"
                + "[T][ ] Test task";
        assertEquals(expectedMessage, res);
    }

    @Test
    void showDeleteMessage_shouldDisplayCorrectDeleteMessage() {
        Task task = new ToDo("Test task");
        String res = ui.showDeleteMessage(task);
        String expectedMessage = "Hehehehehehe, I've removed this task from the waterfall: \n"
                + "[T][ ] Test task";
        assertEquals(expectedMessage, res);
    }

    @Test
    void showTaskListMessage_shouldDisplayCorrectTaskList() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("Test task 1"));
        taskList.add(new ToDo("Test task 2"));

        String res = ui.showTaskListMessage(taskList);
        String expectedMessage = "Here's the tasks in your waterfall hualalala\n";
        assertTrue(res.contains(expectedMessage));
    }
}
