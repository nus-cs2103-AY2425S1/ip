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
        ui.showWelcomeMessage();
        String expectedMessage = ("     Hualalalalala I'm Waterfall\n" + "     What can I do for you?\n");
        assertTrue(outputStreamCaptor.toString().contains(expectedMessage.trim()));
    }

    @Test
    void showByeMessage_shouldDisplayCorrectMessage() {
        ui.showByeMessage();
        String expectedMessage = "    Shhhhhhhhhhhh. Hope to see you again soon!\n";
        assertTrue(outputStreamCaptor.toString().contains(expectedMessage.trim()));
    }

    @Test
    void showLoadingError_shouldDisplayCorrectErrorMessage() {
        ui.showLoadingError();
        String expectedMessage = "    Oops! Something went wrong in loading the database!";
        assertTrue(outputStreamCaptor.toString().contains(expectedMessage.trim()));
    }

    @Test
    void showLine_shouldDisplayCorrectLine() {
        ui.showLine();
        String expectedMessage = "    ____________________________________________________________\n";
        assertTrue(outputStreamCaptor.toString().contains(expectedMessage.trim()));
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
        ui.showError(errorMessage);
        String expectedMessage = "     Oops Water falls: " + errorMessage;
        assertTrue(outputStreamCaptor.toString().contains(expectedMessage.trim()));
    }

    @Test
    void showAddMessage_shouldDisplayCorrectAddMessage() {
        Task task = new ToDo("Test task");
        ui.showAddMessage(task);
        String expectedMessage1 = "      Successfully added a task to the waterfallll:";
        String expectedMessage2 = "      [T][ ] Test task";
        assertTrue(outputStreamCaptor.toString().contains(expectedMessage1.trim()));
        assertTrue(outputStreamCaptor.toString().contains(expectedMessage2.trim()));
    }

    @Test
    void showMarkMessage_shouldDisplayCorrectMarkMessage() {
        Task task = new ToDo("Test task");
        task.setDone(true);
        ui.showMarkMessage(task);
        String expectedMessage1 = "      Huluhuluhulu, I've marked this task as done: ";
        String expectedMessage2 = "      [T][X] Test task";
        assertTrue(outputStreamCaptor.toString().contains(expectedMessage1.trim()));
        assertTrue(outputStreamCaptor.toString().contains(expectedMessage2.trim()));
    }

    @Test
    void showUnmarkMessage_shouldDisplayCorrectUnmarkMessage() {
        Task task = new ToDo("Test task");
        ui.showUnmarkMessage(task);
        String expectedMessage1 = "      Hohohohohoho, I've marked this task as not done: ";
        String expectedMessage2 = "      [T][ ] Test task";
        assertTrue(outputStreamCaptor.toString().contains(expectedMessage1.trim()));
        assertTrue(outputStreamCaptor.toString().contains(expectedMessage2.trim()));

    }

    @Test
    void showDeleteMessage_shouldDisplayCorrectDeleteMessage() {
        Task task = new ToDo("Test task");
        ui.showDeleteMessage(task);
        String expectedMessage1 = "      Hehehehehehe, I've removed this task from the waterfall: ";
        String expectedMessage2 = "      [T][ ] Test task";
        assertTrue(outputStreamCaptor.toString().contains(expectedMessage1.trim()));
        assertTrue(outputStreamCaptor.toString().contains(expectedMessage2.trim()));
    }

    @Test
    void showTaskListMessage_shouldDisplayCorrectTaskList() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("Test task 1"));
        taskList.add(new ToDo("Test task 2"));

        ui.showTaskListMessage(taskList);
        String expectedMessage = "     Here's the tasks in your waterfall hualalala\n";
        assertTrue(outputStreamCaptor.toString().contains(expectedMessage.trim()));
    }
}
