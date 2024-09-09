package handsome.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import handsome.exception.InvalidSyntaxException;
import handsome.exception.ListIndexOutOfBoundException;

public class CommandTest {
    @Test
    public void toNumber_numberSmallerThanSize_success() throws ListIndexOutOfBoundException, InvalidSyntaxException {
        assertEquals(1, new DeleteCommand("delete 2").toNumber("1", 1));
    }

    @Test
    public void toNumber_numberLargerThanSize_exceptionThrown() throws InvalidSyntaxException {
        try {
            assertEquals(2, new DeleteCommand("delete 2").toNumber("2", 1));
            fail();
        } catch (ListIndexOutOfBoundException error) {
            assertEquals("Invalid List Index! List has 1 items.", error.getMessage());
        }
    }

    @Test
    public void toNumber_inputNotANumber_exceptionThrown() throws ListIndexOutOfBoundException {
        try {
            assertEquals(2, new DeleteCommand("delete hello").toNumber("hello", 1));
            fail();
        } catch (InvalidSyntaxException error) {
            assertEquals("Invalid Syntax! Type prompt to check for valid command", error.getMessage());
        }
    }
}
