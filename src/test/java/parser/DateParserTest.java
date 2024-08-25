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
}
