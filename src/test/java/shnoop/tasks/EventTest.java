package shnoop.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import shnoop.exceptions.EmptyDescriptionException;
import shnoop.exceptions.ImproperFileTypeException;


public class EventTest {

    @Test
    public void equals_event_success() throws EmptyDescriptionException, ImproperFileTypeException {
        Event e = new Event("12345#^!&#@&@ndadjsdh",
                new Task("why").parseDate("2020-06-08"), "2033-33-33");
        assertEquals(true, e.equals(e));

        Event eDate = new Event("12345#^!&#@&@ndadjsdh", "Jun 8 2020", "2033-33-33");
        assertEquals(e, eDate);
        assertEquals(true, e.equals(eDate));

        Event eFalse = new Event("12345#^!&#@&@ndadjsdh d", "Jun 8 2023", "2033-33-33");
        assertEquals(false, e.equals(eFalse));

        assertEquals(false, e.equals(new Event("", "", "")));
    }

}
