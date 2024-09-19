package winner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WinnerTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final TaskList taskList = new TaskList();

    @BeforeEach
    void setUp() {
        // Redirect System.out to capture output for verification
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testGreetUser() {
        // Simulate "hi" input and verify greeting
        simulateUserInput("hi\nbye\n");
        WinnerTaskBotCli.winnerTaskBot(taskList);
        assertTrue(outputStreamCaptor.toString().contains("hi"));
    }

    @Test
    void testAddToDo() {
        // Simulate adding a ToDo task
        simulateUserInput("todo read book\nbye\n");
        WinnerTaskBotCli.winnerTaskBot(taskList);
        assertTrue(outputStreamCaptor.toString().contains("[T] [ ] read book"));
        assertEquals(1, taskList.getNoOfTasks());
    }

    @Test
    void testAddDeadline() {
        // Simulate adding a Deadline task
        simulateUserInput("deadline submit assignment by 25/12/2024 at 2359\nbye\n");
        WinnerTaskBotCli.winnerTaskBot(taskList);
        String expectedOutput = "[D] [ ] submit assignment (by: Wednesday, 25/12/2024 at 2359)";
        assertTrue(outputStreamCaptor.toString().contains(expectedOutput));
        assertEquals(1, taskList.getNoOfTasks());
    }

    @Test
    void testAddEvent() {
        // Simulate adding an Event task
        simulateUserInput("event project meeting from 01/12/2024 at 0900 to 01/12/2024 at 1200\nbye\n");
        WinnerTaskBotCli.winnerTaskBot(taskList);
        String expectedOutput = "[E] [ ] project meeting (01/12/2024 at 0900 - 01/12/2024 at 1200)";
        assertTrue(outputStreamCaptor.toString().contains(expectedOutput));
        assertEquals(1, taskList.getNoOfTasks());
    }

    @Test
    void testMarkTaskAsDone() {
        // Simulate adding and marking a task as done
        simulateUserInput("todo read book\nmark 1\nbye\n");
        WinnerTaskBotCli.winnerTaskBot(taskList);
        assertTrue(outputStreamCaptor.toString().contains("[T] [X] read book"));
        assertEquals(1, taskList.getNoOfTasks());
        assertTrue(taskList.getTasks().get(0).isDone);
    }

    @Test
    void testInvalidCommand() {
        // Simulate an invalid command and check the error message
        simulateUserInput("invalid command\nbye\n");
        WinnerTaskBotCli.winnerTaskBot(taskList);
        assertTrue(outputStreamCaptor.toString().contains("Sorry, I do not know what that means"));
    }

    @Test
    void testListTasks() {
        // Simulate adding tasks and listing them
        simulateUserInput("todo read book\ntodo write code\nlist\nbye\n");
        WinnerTaskBotCli.winnerTaskBot(taskList);
        assertTrue(outputStreamCaptor.toString().contains("1. [T] [ ] read book"));
        assertTrue(outputStreamCaptor.toString().contains("2. [T] [ ] write code"));
        assertEquals(2, taskList.getNoOfTasks());
    }

    @Test
    void testSaveAndLoadTasks() {
        // Simulate adding tasks, saving, reloading, and verifying the tasks
        simulateUserInput("todo read book\nbye\n");
        WinnerTaskBotCli.winnerTaskBot(taskList);
        Storage.saveTasks(taskList.getTasks());

        TaskList reloadedTaskList = new TaskList();
        Storage.loadTasks(reloadedTaskList.getTasks());

        assertEquals(1, reloadedTaskList.getNoOfTasks());
        assertEquals("read book", reloadedTaskList.getTasks().get(0).description);
    }

    private void simulateUserInput(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
}
