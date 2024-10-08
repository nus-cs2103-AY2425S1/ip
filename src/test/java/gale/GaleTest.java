package gale;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gale.exception.GaleException;

public class GaleTest {
    private static final String TEST_FILE_PATH = "src/main/java/gale/data/galeTest.txt";
    private Gale gale;
    private ByteArrayOutputStream outputCapture;

    @BeforeEach
    public void setUp() {
        outputCapture = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputCapture));
        gale = new Gale(TEST_FILE_PATH);
    }

    @AfterEach
    public void clearTestFile() {
        try {
            Files.write(Paths.get(TEST_FILE_PATH), new byte[0]);
        } catch (IOException e) {
            System.err.println("Failed to clear test file: " + e.getMessage());
        }
    }

    public void simulateUserInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @Test
    public void addToDoTask_validInput_successMessage() throws GaleException {
        simulateUserInput("todo Test task\nbye\n");
        gale.run();
        String output = outputCapture.toString();
        int taskCount = gale.getTaskList().size();
        assertTrue(output.contains("Whoosh! Task \"[T][ ] Test task\" added to my windy memory."));
        assertTrue(output.contains("Now you have " + taskCount
            + (taskCount == 1 ? " task" : " tasks") + " in the air."));
        assertTrue(output.contains("Anything else?"));
    }

    @Test
    public void userInput_invalidCommand_exceptionThrown() throws GaleException {
        simulateUserInput("invalid command\nbye\n");
        gale.run();
        String output = outputCapture.toString();
        assertTrue(output.contains("Whoosh! The wind blew away your command. "
            + "Please use 'todo', 'deadline' or 'event'."));
    }

    @Test
    public void deleteTask_taskNumberNotNumeric_exceptionThrown() throws GaleException {
        simulateUserInput("todo Test task\ndelete NAN\nbye\n");
        gale.run();
        String output = outputCapture.toString();
        assertTrue(output.contains("Swoosh! The wind thinks that's not a number!"));
    }

    @Test
    public void findTasks_noKeyword_exceptionThrown() throws GaleException {
        simulateUserInput("todo Test task\nfind\nbye\n");
        gale.run();
        String output = outputCapture.toString();
        assertTrue(output.contains("The wind blew away your keyword. Please use 'find [keyword]'."));
    }

    @Test
    public void markTask_taskNumberOutOfRange_exceptionThrown() throws GaleException {
        simulateUserInput("todo Test task\nmark 5\nbye\n");
        gale.run();
        String output = outputCapture.toString();
        assertTrue(output.contains("Oops! That task number is lost in the wind. Try again?"));
    }
}
