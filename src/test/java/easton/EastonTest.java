package easton;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import easton.exception.DateTimeFormatException;
import easton.exception.EmptyDescriptionException;
import easton.exception.InvalidFormatException;


public class EastonTest {

    @Test
    public void createToDo_emptyDescription_exceptionThrown() {
        String input = " todo ";
        assertThrows(EmptyDescriptionException.class, () -> {
            Easton.createToDo(input);
        });
    }

    @Test
    public void createToDo_withDescription_success() {
        String input = "todo hello world";
        assertDoesNotThrow(() -> {
            Easton.createToDo(input);
        });
    }

    @Test
    public void createDeadline_emptyDescription_exceptionThrown() {
        String input = " deadline ";
        assertThrows(EmptyDescriptionException.class, () -> {
            Easton.createDeadline(input);
        });
    }

    @Test
    public void createDeadline_withoutByLabel_exceptionThrown() {
        String input = " deadline read book";
        assertThrows(InvalidFormatException.class, () -> {
            Easton.createDeadline(input);
        });
    }

    @Test
    public void createDeadline_incorrectDateTimeFormat_exceptionThrown() {
        String input = " deadline read book /by 23-06-2001 2300";
        assertThrows(DateTimeFormatException.class, () -> {
            Easton.createDeadline(input);
        });
    }

    @Test
    public void createDeadline_validInput_success() {
        String input = " deadline read book /by 23/06/2001 23:00";
        assertDoesNotThrow(() -> {
            Easton.createDeadline(input);
        });
    }
}
