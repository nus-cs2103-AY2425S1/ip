package bot;

import bot.exceptions.InvalidTaskDescriptionException;
import bot.tasks.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

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
                InvalidTaskDescriptionException.class,
                () -> new Parser().parseDeadlineTask("return book")
        );

        assertEquals(
                new InvalidTaskDescriptionException("return book").getMessage(),
                e.getMessage()
        );
    }

    @Test
    public void parseDeadlineTask_invalidDate_exceptionThrown() {
        DateTimeParseException e = assertThrows(
                DateTimeParseException.class,
                () -> new Parser().parseDeadlineTask("return book /by 2024-99-99")
        );
        assertEquals(
                "Text '2024-99-99' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 99",
                e.getMessage()
        );
    }
}
