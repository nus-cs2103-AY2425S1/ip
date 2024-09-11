package moimoi.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import moimoi.util.exception.InvalidCommandException;
import moimoi.util.exception.MissingArgumentException;
import moimoi.util.exception.MoiMoiException;

public class ParserTest {

    @Test
    public void parse_validInput_success() {
        try {
            Parser.parse("todo borrow book");
            Parser.parse("deadline return book /by 2024-08-22 17:00");
            Parser.parse("event project meeting /from 2024-08-22 18:00 /to 2024-08-22 18:30");
            Parser.parse("period cry /for 24");
            Parser.parse("delete 1");
            Parser.parse("mark 2");
            Parser.parse("unmark 3");
            Parser.parse("LIST");
            Parser.parse("schedule 2024-08-22");
            Parser.parse("find happiness");
            Parser.parse("Bye");
        } catch (MoiMoiException e) {
            fail();
        }
    }

    @Test
    public void parse_invalidInput_invalidCommandExceptionThrown() {

        String expected = new InvalidCommandException().getMessage();

        try {
            Parser.parse("");
            fail();
        } catch (MoiMoiException e) {
            assertEquals(expected, e.getMessage());
        }

        try {
            Parser.parse("cry");
            fail();
        } catch (MoiMoiException e) {
            assertEquals(expected, e.getMessage());
        }

        try {
            Parser.parse("even project meeting /from 2024-08-22 18:00 /to 2024-08-22 18:30");
            fail();
        } catch (MoiMoiException e) {
            assertEquals(expected, e.getMessage());
        }

    }

    @Test
    public void parse_invalidInput_missingArgumentExceptionThrown() {

        try {
            Parser.parse("deadline");
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new MissingArgumentException().getMessage(), e.getMessage());
        }

        try {
            Parser.parse("todo ");
            fail();
        } catch (MoiMoiException e) {
            assertEquals(new MissingArgumentException().getMessage(), e.getMessage());
        }

    }

}
