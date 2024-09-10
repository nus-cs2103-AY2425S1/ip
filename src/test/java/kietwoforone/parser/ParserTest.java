package kietwoforone.parser;

import kietwoforone.exceptions.KieTwoForOneException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    private String incompleteInput = "todo";
    private String invalidInput = "Blah";
    private String incompleteDeadline = "deadline read";
    private String incompleteEvent = "event read /to 2024-06-12 1800";

    @Test
    public void incompleteInput_exceptionThrown() {
        try {
            assertEquals("", Parser.parse(incompleteInput));
            fail();
        } catch (KieTwoForOneException e) {
            assertEquals("Your instruction is incomplete!", e.getMessage());
        }
    }

    @Test
    public void invalidInput_exceptionThrown() {
        try {
            assertEquals("", Parser.parse(invalidInput));
            fail();
        } catch (KieTwoForOneException e) {
            assertEquals("Invalid input!", e.getMessage());
        }
    }

    @Test
    public void incompleteDeadline_exceptionThrown() {
        try {
            assertEquals("", Parser.parse(incompleteDeadline));
            fail();
        } catch (KieTwoForOneException e) {
            assertEquals("Please input a deadline!", e.getMessage());
        }
    }

    @Test
    public void incompleteEvent_exceptionThrown() {
        try {
            assertEquals("", Parser.parse(incompleteEvent));
            fail();
        } catch (KieTwoForOneException e) {
            assertEquals("Please input a start and end time!", e.getMessage());
        }
    }

}
