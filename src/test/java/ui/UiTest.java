package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Task;
import tasks.TaskList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {

    private static final String DIVIDER = "________________________________________\n";
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Ui ui;

    @BeforeEach
    void setUp() {
        ui = new Ui();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }

    @Test
    void testShowWelcome() {
        ui.showWelcome();
        String expectedOutput = DIVIDER + "Hello! I'm Downy.\nHow can I help?\n" + DIVIDER;
        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void testShowLine() {
        ui.showLine();
        assertEquals(DIVIDER.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void testShowExitMessage() {
        ui.showExitMessage();
        String expectedOutput = DIVIDER + "Bye! Yippee!";
        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void testShowErrorMessage() {
        Ui.showErrorMessage("An error occurred");
        String expectedOutput = DIVIDER + "Error: An error occurred\n" + DIVIDER;
        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void testShowMessage() {
        Ui.showMessage("This is a message");
        String expectedOutput = DIVIDER + "This is a message\n" + DIVIDER;
        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void testDisplayTasks() {
        TaskList taskList = new TaskList();
        taskList.addTodo("Read a book");
        taskList.addDeadline("Submit report", LocalDateTime.of(2024, 8, 30, 18, 0));

        ui.displayTasks(taskList);

        String expectedOutput = DIVIDER +
                "Here are the tasks in your list:\n" +
                "1. [T] [ ] Read a book\n" +
                "2. [D] [ ] Submit report (by: Aug 30 2024 1800)\n" +
                DIVIDER;

        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void testDisplayCompletedTask() {
        Task task = new Task("Read a book");
        ui.displayCompletedTask(task);
        String expectedOutput = DIVIDER +
                "Nice! You've completed this task:\n  " +
                task + "\n" +
                DIVIDER;

        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void testDisplayIncompleteTask() {
        Task task = new Task("Read a book");
        ui.displayIncompleteTask(task);
        String expectedOutput = DIVIDER +
                "Ok! This task is not complete:\n  " +
                task + "\n" +
                DIVIDER;

        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void testDisplayDeletedTask() {
        Task task = new Task("Read a book");
        ui.displayDeletedTask(task);
        String expectedOutput = DIVIDER +
                "Ok! This task has been removed:\n  " +
                task + "\n" +
                DIVIDER;

        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void testDisplayTaskAdded() {
        Task task = new Task("Read a book");
        ui.displayTaskAdded(task, 5);
        String expectedOutput = DIVIDER +
                "Okay! Added this task:\n  " +
                task + "\n" +
                "Now you have 5 tasks in this list\n" +
                DIVIDER;

        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void testDisplayHelp() {
        ui.displayHelp();
        String expectedOutput = DIVIDER +
                "Here are a list of valid commands:\n" +
                " - list\n" +
                " - mark <taskNumber>\n" +
                " - unmark <taskNumber>\n" +
                " - delete <taskNumber>\n" +
                " - todo <taskDescription>\n" +
                " - deadline <taskDescription> /by <dueDate>\n" +
                " - event <taskDescription> /from <startTime> /to <endTime>\n" +
                " - bye\n" +
                " - help\n" +
                DIVIDER;

        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }
}
