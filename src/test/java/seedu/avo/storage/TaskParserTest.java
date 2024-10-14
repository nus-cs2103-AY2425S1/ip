package seedu.avo.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.avo.exceptions.AvoException;
import seedu.avo.tasks.Deadline;
import seedu.avo.tasks.Event;
import seedu.avo.tasks.ToDo;

public class TaskParserTest {
    private final TaskParser parser;
    private final String name = "name";
    private final LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 12, 30);
    private final LocalDateTime endDate = LocalDateTime.of(2024, 1, 2, 12, 30);

    public TaskParserTest() {
        parser = new TaskParser();
    }
    @Test
    public void testInvalidString() {
        assertThrows(AvoException.class, () -> {
            parser.parse("invalid"); });
    }
    @Test
    public void testToDo() throws AvoException {
        assertEquals(new ToDo(name), parser.parse("T : 0 : name"));
    }
    @Test
    public void testDeadline() throws AvoException {
        assertEquals(new Deadline(name, startDate),
                parser.parse("D : 0 : name : Jan 1 2024 1230"));
    }
    @Test
    public void testUncompletedEvent() throws AvoException {
        assertEquals(new Event(name, startDate, endDate),
                parser.parse("E : 0 : name : Jan 1 2024 1230 : Jan 2 2024 1230"));
    }
    @Test
    public void testCompletedEvent() throws AvoException {
        Event event = new Event(name, startDate, endDate);
        event.complete();
        assertEquals(event,
                parser.parse("E : 1 : name : Jan 1 2024 1230 : Jan 2 2024 1230"));
    }
}
