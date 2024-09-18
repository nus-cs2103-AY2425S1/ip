package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import chatterboxexceptions.ChatterboxExceptions;


public class EventTest {

    @Test
    public void eventDesc_normalString() {
        String expected = "event 1 ( from now to tmr )";
        try {
            assertEquals(expected, (new Event("event 1", "now", "tmr")).descNoTags());

        } catch (ChatterboxExceptions.ChatterBoxNoInput e) {
            System.out.println("input error");
        }
    }

    @Test
    public void eventDesc_dateTime() {
        LocalDateTime fromTest = LocalDateTime.of(2001, 1, 21, 21, 12);
        LocalDateTime toTest = LocalDateTime.of(2001, 2, 3, 0, 0);

        String expected = "event 1 ( from Jan 21 2001, 21:12 to Feb 03 2001, 00:00 )";
        try {
            assertEquals(expected, new Event("event 1", fromTest, toTest).descNoTags());
        } catch (ChatterboxExceptions.ChatterBoxNoInput e) {
            System.out.println("error input");
        }
    }
}
