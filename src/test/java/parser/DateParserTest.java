package parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class DateParserTest {
    @Test
    public void byStatement_correctFormat_success() throws Exception {
        assertEquals(DateParser.parseBy("/by 2023-01-20"),
                "2023-01-20T00:00");
        assertEquals(DateParser.parseBy("/by 2023-02-20 18:01"),
                "2023-02-20T18:01");
        assertEquals(DateParser.parseBy("/by 2023-11-20 22:03"),
                "2023-11-20T22:03");
    }

    @Test
    public void fromStatement_correctFormat_success() throws Exception {
        assertEquals(DateParser.parseFrom("/from 2023-01-20"),
                "2023-01-20T00:00");
        assertEquals(DateParser.parseFrom("/from 2024-01-20"),
                "2024-01-20T00:00");
        assertEquals(DateParser.parseFrom("/from 2023-11-20 22:03"),
                "2023-11-20T22:03");
    }

    @Test
    public void toStatement_correctFormat_success() throws Exception {
        assertEquals(DateParser.parseTo("/to 2023-01-20"),
                "2023-01-20T00:00");
        assertEquals(DateParser.parseTo("/to 2024-01-20"),
                "2024-01-20T00:00");
        assertEquals(DateParser.parseTo("/to 2023-11-20 22:03"),
                "2023-11-20T22:03");
    }

    @Test
    public void recordStatement_correctFormat_success() throws Exception {
        assertEquals(DateParser.parseRecordedDate("00:00 Jan 1 2023"),
                "2023-01-01T00:00");
        assertEquals(DateParser.parseRecordedDate("22:03 Nov 20 2023"),
                "2023-11-20T22:03");
    }
}
