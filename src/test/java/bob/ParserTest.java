package bob;

import java.time.LocalDateTime;

public class ParserTest {
    public void parseDateTimeTest() {
        assertEquals(Parser.parseDateTime("02/12/2019 1800"), LocalDateTime.of(2019, 12, 2, 18, 0));
        assertEquals(Parser.parseDateTime("2/12/2019 1800"), LocalDateTime.of(2019, 12, 2, 18, 0));
        assertEquals(Parser.parseDateTime("02/2/2019 1800"),  LocalDateTime.of(2019, 2, 2, 18, 0));
        assertEquals(Parser.parseDateTime("02/12/2019 1000"), LocalDateTime.of(2019, 12, 2, 10, 0));
    }
}
