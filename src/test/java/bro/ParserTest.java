package bro;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseDateTest() {
        assertEquals(LocalDateTime.parse("2020-11-26 2359",
                     DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                     new Parser(new Ui()).parseDate("2020-11-26 2359"));
    }
}
