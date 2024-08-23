package matcha.task;

import matcha.exception.MatchaException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

public class DeadlineTest {

    @Test
    public void toString_returnsCorrectResult() throws MatchaException {
        Deadline deadline = new Deadline("return book", LocalDateTime.parse("2024-12-12 1212",
                Task.getInputFormat()));
        deadline.markDone();
        assertEquals("\t[D][X] return book (by: Dec 12 2024 12:12 pm)", deadline.toString());
    }

    @Test
    public void toSaveString_returnsCorrectResult() throws MatchaException {
        Deadline deadline = new Deadline("return book", LocalDateTime.parse("2024-12-12 1212",
                Task.getInputFormat()));
        assertEquals("D | 0 | return book | 2024-12-12T12:12", deadline.toSaveString());
    }
}
