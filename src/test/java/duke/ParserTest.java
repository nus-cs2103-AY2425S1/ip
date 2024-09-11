package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void testParseEventCommand_withValidInput_success() {
        Parser parser = new Parser();
        String command = "event Meeting /from 2024-09-01 10:00 /to 2024-09-01 12:00";
        Event actualEvent = parser.parseEventCommand(command);
        Event expectedEvent = new Event("Meeting",
                LocalDateTime.parse("2024-09-01T10:00"),
                LocalDateTime.parse("2024-09-01T12:00"));
        assertEquals(expectedEvent.getDescription(), actualEvent.getDescription());
        assertEquals(expectedEvent.getStart(), actualEvent.getStart());
        assertEquals(expectedEvent.getEnd(), actualEvent.getEnd());
    }

    @Test
    public void testParseEventCommand_missingFrom_exceptionThrown() {
        try {
            Parser parser = new Parser();
            String command = "event Meeting /to 2024-09-01 12:00";
            Event actualEvent = parser.parseEventCommand(command);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Event command must contain '/from' followed by a start date-time.",
                    e.getMessage());
        }
    }

    @Test
    public void testParseEventCommand_missingTo_exceptionThrown() {
        try {
            Parser parser = new Parser();
            String command = "event Meeting /from 2024-09-01 10:00";
            Event actualEvent = parser.parseEventCommand(command);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Event command must contain '/to' followed by an end date-time.",
                    e.getMessage());
        }
    }

    @Test
    public void testParseEventCommand_emptyDescription_exceptionThrown() {
        try {
            Parser parser = new Parser();
            String command = "event /from 2024-09-01 10:00 /to 2024-09-0 T12:00";
            Event actualEvent = parser.parseEventCommand(command);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Description for 'event' cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void testParseEventCommand_emptyCommand_exceptionThrown() {
        try {
            Parser parser = new Parser();
            String command = "";
            Event actualEvent = parser.parseEventCommand(command);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Event command must contain '/from' followed by a start date-time.",
                    e.getMessage());
        }
    }

}
