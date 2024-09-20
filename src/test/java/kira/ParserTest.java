package kira;

import exceptions.EmptyException;
import exceptions.InvalidTaskException;
import exceptions.UnreadableException;
import tasks.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ParserTest {
    Parser parser = new Parser(new ListStub());
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        // Redirect System.out to capture the output
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        // Restore the original System.out
        System.setOut(originalOut);
    }

    @Test
    public void intepreteCommand_manySpaces_correctCommand() throws UnreadableException {
        Parser.CommandType command = parser.intepreteCommand("todo very very very long assignment");
        assertEquals(command, Parser.CommandType.TODO);
    }

    @Test
    public void intepreteCommand_sameWordAsCommand_correctCommand() throws UnreadableException {
        Parser.CommandType command = parser.intepreteCommand("event school event /from 1/1/2024 1100 /to 1/1/2024 1200");
        assertEquals(command, Parser.CommandType.EVENT);
    }

    @Test
    public void execute_manySpaces_correctTask() throws UnreadableException, EmptyException, InvalidTaskException {
        Task task = parser.execute(Parser.CommandType.TODO, "todo very very very long assignment");
        assertEquals(task.getInput(), "very very very long assignment");
    }
}