package gale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GaleTest {
    private Gale gale;
    private ByteArrayOutputStream outputCapture;

    @BeforeEach
    public void setUp() {
        outputCapture = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputCapture));
        gale = new Gale("src/main/java/data/galeTest.txt");
    }

    public void simulateUserInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @Test
    public void testAddTask_success() throws GaleException {
        simulateUserInput("todo Test task\nbye\n");
        gale.run();
        String output = outputCapture.toString();
        assertTrue(output.contains("Whoosh! Task \"[T][ ] Test task\" added to my windy memory."));
        assertTrue(output.contains("Now you have 1 task in the air."));
    }

    @Test
    public void testInvalidCommand_exceptionThrown() throws GaleException {
        simulateUserInput("invalid command\nbye\n");
        gale.run();
        String output = outputCapture.toString();
        assertTrue(output.contains("Whoosh! The wind blew away your command. Please use 'todo', 'deadline' or 'event'."));
    }
}
