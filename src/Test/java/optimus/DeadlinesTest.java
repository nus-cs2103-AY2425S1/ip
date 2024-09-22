package optimus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlinesTest {

    private Deadline deadline;

    @BeforeEach
    public void setUp() throws OptimusException {
        deadline = new Deadline("Test deadline", "1/09/2024 12:00");
    }

    @Test
    public void testParseStringValidFormat1() throws OptimusException {
        String dateTimeInput = "15/09/2024 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, deadline.parseStringDeadline(dateTimeInput));
    }

    @Test
    public void testParseStringValidFormat2() throws OptimusException {
        String dateTimeInput = "2024-09-15 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, deadline.parseStringDeadline(dateTimeInput));
    }

    @Test
    public void testParseStringValidFormat3() throws OptimusException {
        String dateTimeInput = "15-09-2024 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, deadline.parseStringDeadline(dateTimeInput));
    }

    @Test
    public void testParseStringValidFormat4() throws OptimusException {
        String dateTimeInput = "2024/09/15 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, deadline.parseStringDeadline(dateTimeInput));
    }
}
