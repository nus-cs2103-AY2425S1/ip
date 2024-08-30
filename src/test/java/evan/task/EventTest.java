package evan.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void encodeAsString_validInputs_success() {
        // from: STRING, to: STRING
        Event e1 = new Event("Event description", "today", "tomorrow");
        assertEquals("E | 0 | Event description | today | tomorrow", e1.encodeAsString());
        e1.markAsDone();
        assertEquals("E | 1 | Event description | today | tomorrow", e1.encodeAsString());

        // from: DATE, to: DATE
        Event e2 = new Event("Event description", "2020-10-05", "2021-09-05");
        assertEquals("E | 0 | Event description | 05 Oct 2020 | 05 Sep 2021", e2.encodeAsString());
        e2.markAsDone();
        assertEquals("E | 1 | Event description | 05 Oct 2020 | 05 Sep 2021", e2.encodeAsString());

        // from: DATETIME, to: DATETIME
        Event e3 = new Event("Event description", "2023-03-13 0103", "2023-03-14 2343");
        assertEquals("E | 0 | Event description | 13 Mar 2023, 01:03 am | 14 Mar 2023, 11:43 pm", e3.encodeAsString());
        e3.markAsDone();
        assertEquals("E | 1 | Event description | 13 Mar 2023, 01:03 am | 14 Mar 2023, 11:43 pm", e3.encodeAsString());

    }
}
