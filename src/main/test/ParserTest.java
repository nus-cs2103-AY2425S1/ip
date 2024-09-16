package bing.parser;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import bing.command.*;

public class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    @Test
    public void testParseByeCommand() {
        Command command = parser.parse("bye");
        assertTrue(command instanceof ByeCommand);
    }

    @Test
    public void testParseDeadlineCommand() {
        String input = "deadline Task 1 /by 15/09/2024 1500";
        Command command = parser.parse(input);
        assertTrue(command instanceof DeadlineCommand);
        DeadlineCommand deadlineCommand = (DeadlineCommand) command;
        assertEquals("Task 1", deadlineCommand.getInfo());
        assertEquals(LocalDateTime.of(2024, 9, 15, 15, 0), deadlineCommand.getInfo());
    }

    @Test
    public void testParseInvalidDate() {
        String input = "deadline Task 1 /by invalid-date";
        Command command = parser.parse(input);
        assertTrue(command instanceof InvalidCommand);
    }
}
