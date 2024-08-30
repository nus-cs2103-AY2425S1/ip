package Bwead;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ParserTest {
    @Test
    public void testGetEventEndTimeSuccess() throws Exception {
        assertEquals(LocalTime.of(18,00),
                new Parser("event project meeting /from 2019-10-15 1700 /to 2019-10-15 1800").getEventEndTime());
        assertEquals(LocalTime.of(9,00),
                new Parser("event project meeting /from 2019-10-15 1700 /to 2020-09-16 0900").getEventEndTime());
        assertEquals(LocalTime.of(10,38),
                new Parser("event project meeting /from 2019-10-15 1700 /to 2019-10-15 1038").getEventEndTime());
    }

    /*@Test
    public void testGetEventEndTimeException() {
        try {
            assertEquals(LocalTime.of(18, 00),
                    new Parser("event project meeting /from 2019-10-15 1700 /to 2019-10-15 180").getEventEndTime());
            assertEquals(LocalTime.of(9, 00),
                    new Parser("event project meeting /from 2019-10-15 1700 /to 2020-09-16 0900!").getEventEndTime());
            assertEquals(LocalTime.of(10, 38),
                    new Parser("event project meeting /from 2019-10-15 1700 /to 2019-10-15 10380").getEventEndTime());
        } catch (Exception e) {
        }
    }*/
}
