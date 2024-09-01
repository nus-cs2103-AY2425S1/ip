package carly.utils;

import carly.exception.CarlyNoTaskDescription;
import carly.exception.CarlyUnknownIInputException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import carly.exception.CarlyException;

public class ParserTest {
    @Test
    public void testGetCommand_success() throws CarlyException {
        Parser p = new Parser("deadline code review /by 2019-10-15");
        assertEquals(Parser.Command.DEADLINE, p.getCommand());
    }

    @Test
    public void testGetCommand_exceptionThrown() {
        Parser p;
        CarlyException thrown;

        p = new Parser("");
        thrown = assertThrows(CarlyUnknownIInputException.class, p::getCommand,
                "Expecting getCommand() to throw, but it didn't");
        assertEquals("No input detected. Please type in something!!", thrown.getMessage());

        p = new Parser("deadlinee code review /by 2019-10-15");
        thrown = assertThrows(CarlyUnknownIInputException.class, p::getCommand,
                "Expecting getCommand() to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("Please ensure that you enter a valid command."));
    }

    @Test
    public void testGetDetailsAfterCommand_success() throws CarlyException {
        Parser p = new Parser("deadline code review /by 2019-10-15");
        assertEquals("code review /by 2019-10-15", p.getDetailsAfterCommand(Parser.Command.DEADLINE));

        Parser p1 = new Parser("event meeting /from 7pm /to 9pm");
        assertEquals("meeting /from 7pm /to 9pm", p1.getDetailsAfterCommand(Parser.Command.EVENT));
    }

    @Test
    public void testGetDetailsAfterCommand_exceptionThrown() {
        CarlyNoTaskDescription thrown;
        final String errorMessage = "Oops. You forgot to enter a task description. Please reenter your message.";

        Parser p = new Parser("deadline");
        thrown = assertThrows(CarlyNoTaskDescription.class, () -> p.getDetailsAfterCommand(Parser.Command.DEADLINE),
                "Expecting getDetailsAfterCommand() to throw, but it didn't");
        assertEquals(errorMessage, thrown.getMessage());

        Parser p1 = new Parser("deadline ");
        thrown = assertThrows(CarlyNoTaskDescription.class, () -> p1.getDetailsAfterCommand(Parser.Command.DEADLINE),
                "Expecting getDetailsAfterCommand() to throw, but it didn't");
        assertEquals(errorMessage, thrown.getMessage());
    }
}
