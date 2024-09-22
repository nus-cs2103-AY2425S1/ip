package seedu.babygronk;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import parser.EmptyInputException;
import parser.Instruction;
import parser.InvalidCommands;
import parser.InvalidInputException;
import parser.Parser;

public class ParserTest {

    @Test
    public void parseInput() throws EmptyInputException {
        String input = "  hello   world ";
        String expected = "hello world";
        assertEquals(expected, Parser.parseInput(input));
    }

    @Test
    public void parseEmptyInput() throws EmptyInputException {
        Exception exception = assertThrows(EmptyInputException.class, () -> {
            Parser.parseInput("");
        });
        assertEquals("What bro? You're too fanum taxed\n", exception.getMessage());
    }

    @Test
    public void parseNull() throws EmptyInputException {
        Exception exception = assertThrows(EmptyInputException.class, () -> {
            Parser.parseInput(null);
        });
        assertEquals("What bro? You're too fanum taxed\n", exception.getMessage());
    }

    @Test
    public void parseTodo() throws InvalidInputException {
        Instruction instruction = new Parser().parseInstruction("todo read a book");
        assertEquals("todo", instruction.getInstruction());
        assertArrayEquals(new String[] {"read a book"}, instruction.getArgs());
    }

    @Test
    public void testParseInstruction_validDeadline() throws InvalidInputException {
        Instruction instruction = new Parser().parseInstruction("deadline submit report /by tomorrow");
        assertEquals("deadline", instruction.getInstruction());
        assertArrayEquals(new String[] {"submit report", "tomorrow"}, instruction.getArgs());
    }

    @Test
    public void testParseInstruction_invalidDelete() {
        Exception exception = assertThrows(InvalidInputException.class, () -> {
            new Parser().parseInstruction("delete");
        });
        assertEquals(InvalidCommands.INVALID_DELETE_CMD.getErrorMessage(), exception.getMessage());
    }
}
