package rizzler.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import rizzler.command.EventCommand;
import rizzler.command.ListCommand;
import rizzler.command.NullCommand;

public class ParserTest {
    private static final Parser testParser = new Parser();

    @Test
    public void parseInput_emptyInput_nullCommand() {
        assertInstanceOf(NullCommand.class, testParser.parseInput(""));
    }

    @Test
    public void parseInput_whitespaceInput_nullCommand() {
        assertInstanceOf(NullCommand.class, testParser.parseInput("            "));
    }

    @Test
    public void parseInput_todoWithoutDesc_nullCommand() {
        assertInstanceOf(NullCommand.class, testParser.parseInput("todo"));

        assertInstanceOf(NullCommand.class, testParser.parseInput("      todo"));

        assertInstanceOf(NullCommand.class, testParser.parseInput("todo       "));

        assertInstanceOf(NullCommand.class, testParser.parseInput("        todo       "));
    }

    @Test
    public void parseInput_todoWithoutDesc_nullCommandWithTodoErrorMessage() {
        String expectedOutput = """
                sorry dear, you're missing one or more arguments.\n
                Correct Usage:
                todo {task description}
                Examples:
                todo Feed Max tonight!
                todo Submit CS2103 individual proj""";

        assertEquals(expectedOutput, testParser.parseInput("todo").getTextInput());

        assertEquals(expectedOutput, testParser.parseInput("     todo").getTextInput());

        assertEquals(expectedOutput, testParser.parseInput("todo      ").getTextInput());

        assertEquals(expectedOutput, testParser.parseInput("       todo      ").getTextInput());
    }

    @Test
    public void parseInput_list_noTextInput() {
        assertEquals("", testParser.parseInput("list").getTextInput());
    }

    @Test
    public void parseInput_listWithWhitespace_listCommand() {
        assertInstanceOf(ListCommand.class, testParser.parseInput("list"));

        assertInstanceOf(ListCommand.class, testParser.parseInput("       list"));

        assertInstanceOf(ListCommand.class, testParser.parseInput("list        "));

        assertInstanceOf(ListCommand.class, testParser.parseInput("       list       "));
    }

    @Test
    public void parseInput_bye_noOutput() {
        assertEquals("", testParser.parseInput("bye").getTextInput());
    }

    @Test
    public void parseInput_properEvent_event() {
        assertInstanceOf(EventCommand.class,
                testParser.parseInput("event this is a test event /from 1999-09-11 /to 2000-05-29"));
    }

    @Test
    public void parseInput_eventWithoutFrom_nullCommandWithEventErrorMessage() {
        String expectedOutput = """
                sorry dear, you're missing one or more arguments.\n
                Correct Usage:
                event {task description} /from {eventStart} /to {eventEnd}
                Examples:
                event Molecular Biology Symposium /from 2024-10-02 /to 2024-10-05
                event dinner with jess /from 7pm /to 9pm""";

        assertEquals(expectedOutput, testParser.parseInput("event testEvent /to date").getTextInput());

        assertEquals(expectedOutput, testParser.parseInput("event testEvent").getTextInput());

        assertEquals(expectedOutput, testParser.parseInput("event").getTextInput());

        assertEquals(expectedOutput, testParser.parseInput("event testEvent/tofakeDate").getTextInput());

        assertEquals(expectedOutput, testParser.parseInput("event /to fakeDate").getTextInput());

        assertEquals(expectedOutput, testParser.parseInput("    event      testEvent    ").getTextInput());

        assertEquals(expectedOutput, testParser.parseInput("     event     testEvent     /to date").getTextInput());
    }
}
