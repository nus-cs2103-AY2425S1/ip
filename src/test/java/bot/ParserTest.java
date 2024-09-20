package bot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import bot.exceptions.InvalidDatetimeException;
import bot.exceptions.InvalidTaskDescriptionException;
import bot.tasks.Deadline;

public class ParserTest {
    @Test
    public void parseDeadlineTask_validInput_success() {
        try {
            assertEquals(
                    new Deadline("return book", LocalDate.of(2024, 1, 1)).toString(),
                    new Parser().parseDeadlineTask("return book /by 2024-01-01").toString()
            );
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseDeadlineTask_noDeadline_exceptionThrown() {
        InvalidTaskDescriptionException e = assertThrows(
                InvalidTaskDescriptionException.class, () -> new Parser().parseDeadlineTask("return book")
        );

        assertEquals(
                new InvalidTaskDescriptionException("return book").getMessage(),
                e.getMessage()
        );
    }

    @Test
    public void parseDeadlineTask_invalidDate_exceptionThrown() {
        InvalidDatetimeException e = assertThrows(
                InvalidDatetimeException.class, () -> new Parser().parseDeadlineTask("return book /by 2024-99-99")
        );
        assertEquals(
                "'2024-99-99' is not a valid date/time",
                e.getMessage()
        );
    }
}
