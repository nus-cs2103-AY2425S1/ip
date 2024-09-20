package diomon;

import diomon.parser.Parser;
import org.junit.jupiter.api.Test;
import diomon.task.Deadline;
import diomon.task.Event;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static diomon.parser.Parser.DATEFORMATTER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parseStoredDeadlineTest1(){
        assertEquals(Parser.parseStoredDeadline(new String[]{Deadline.TYPEICON,
                        " ",
                        "deadlineTask",
                        "01-09-2024"}),
                new Deadline(false,
                        "deadlineTask",
                        LocalDate.parse("01-09-2024", DATEFORMATTER)));
    }

    @Test
    public void parseStoredDeadlineTest2(){
        assertEquals(Parser.parseStoredDeadline(new String[]{Deadline.TYPEICON,
                        Deadline.COMPLETEICON,
                        "deadlineTask",
                        "01-09-2024"}),
                new Deadline(true,
                        "deadlineTask",
                        LocalDate.parse("01-09-2024", DATEFORMATTER)));
    }

    @Test
    public void parseStoredDeadlineTest3(){
        DateTimeParseException e = assertThrows(DateTimeParseException.class,
                () -> Parser.parseStoredDeadline(new String[]{Deadline.TYPEICON,
                        Deadline.COMPLETEICON,
                        "deadlineTask",
                        "01-09-202"}));
        assertEquals("Text '01-09-202' could not be parsed at index 6",e.getMessage());
    }

    @Test
    public void parseStoredDeadlineTest4(){
        RuntimeException e = assertThrows(RuntimeException.class,
                () -> Parser.parseStoredDeadline(new String[]{Deadline.TYPEICON,
                        "Y",
                        "deadlineTask",
                        "01-09-2024"}));
        assertEquals("CompletionStatus seem to be wrong",e.getMessage());
    }

    @Test
    public void parseStoredDeadlineTest5(){
        RuntimeException e = assertThrows(RuntimeException.class,
                () -> Parser.parseStoredDeadline(new String[]{Deadline.TYPEICON,
                        "X", "deadlineTask",
                        "01-09-2024",
                        "aaa"}));
        assertEquals("Error loading deadline task, data stored is wrong",e.getMessage());
    }

    @Test
    public void parseStoredEventTest1(){
        assertEquals(Parser.parseStoredEvent(new String[]{Event.TYPEICON,
                        " ",
                        "eventTask",
                        "01-09-2024",
                        "02-09-2024"}),
            new Event(false,
                    "eventTask",
                    LocalDate.parse("01-09-2024", DATEFORMATTER),
                    LocalDate.parse("02-09-2024", DATEFORMATTER)));
    }

    @Test
    public void parseStoredEventTest2(){
        assertEquals(Parser.parseStoredEvent(new String[]{Event.TYPEICON, "X", "eventTask", "01-09-2024", "02-09-2024"}),
                new Event(true,
                        "eventTask",
                        LocalDate.parse("01-09-2024", DATEFORMATTER),
                        LocalDate.parse("02-09-2024", DATEFORMATTER)));
    }

    @Test
    public void parseStoredEventTest3(){
        RuntimeException e = assertThrows(RuntimeException.class,
                () -> Parser.parseStoredEvent(new String[]{Event.TYPEICON,
                        "X",
                        "eventTask",
                        "01-09-2024",
                        "02-9-2024"}));
        assertEquals("Date dont seem to exist... Are you ok?",e.getMessage());
    }

    @Test
    public void parseStoredEventTest4(){
        RuntimeException e = assertThrows(RuntimeException.class,
                () -> Parser.parseStoredEvent(new String[]{Event.TYPEICON,
                        "Y",
                        "eventTask",
                        "01-09-2024",
                        "02-09-2024"}));
        assertEquals("CompletionStatus seem to be wrong",e.getMessage());
    }

    @Test
    public void parseStoredEventTest5(){
        RuntimeException e = assertThrows(RuntimeException.class,
                () -> Parser.parseStoredEvent(new String[]{Event.TYPEICON,
                        "X",
                        "eventTask",
                        "01-09-2024",
                        "02-09-2024",
                        "ss"}));
        assertEquals("Error loading event task, data stored is wrong",e.getMessage());
    }

    @Test
    public void parseStoredEventTest6(){
        RuntimeException e = assertThrows(RuntimeException.class,
                () -> Parser.parseStoredEvent(new String[]{Event.TYPEICON,
                        " ",
                        "eventTask",
                        "05-09-2024",
                        "02-09-2024"}));
        assertEquals("Date dont seem to exist... Are you ok?",e.getMessage());
    }



}
