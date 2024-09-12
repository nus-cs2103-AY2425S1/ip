package Tasks;

import Exceptions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventsTest {
    @Test
    public void eventPrintCheck() {
        try {
            assertEquals("[E][ ] read book (from: 4:00 pm to: 6:00 pm on: Apr 05 2020)", new Events("read book /from 16:00 /to 18:00 /on 2020-04-05").print());
        } catch (EmptyEventException e) {
            assertEquals("      OOPS!!! The description of a event cannot be empty leh.", e.getMessage());
        } catch (EmptyEventDateException e) {
            throw new RuntimeException(e);
        } catch (EmptyEventTimingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void invalidEventCheck1() {
        try {
            assertEquals("[E][ ] read book (from: 4:00 pm to: 6:00 pm on: Apr 05 2020", new Events("read book /from ").print());
        } catch (EmptyEventTimingException | EmptyEventException | EmptyEventDateException e) {
            assertEquals("     OOPS! Event start time not given leh. Pls provide in the following format: event project meeting /from 16:00 /to 18:00 /on yyyy-MM-dd or dd/MM/yyyy", e.getMessage());
        }
    }

    @Test
    public void invalidEventCheck2() {
        try {
            assertEquals("[E][ ] read book (from: 4:00 pm to: 6:00 pm on: Apr 05 2020", new Events("read book /from 16:00  ").print());
        } catch (EmptyEventTimingException | EmptyEventException | EmptyEventDateException e) {
            assertEquals("     OOPS! Event end time not given leh. Pls provide in the following format: event project meeting /from 16:00 /to 18:00 /on yyyy-MM-dd or dd/MM/yyyy", e.getMessage());
        }
    }

    @Test
    public void invalidEventCheck3() {
        try {
            assertEquals("[E][ ] read book (from: 4:00 pm to: 6:00 pm on: Apr 05 2020", new Events("read book /from 16:00 /to 18:00").print());
        } catch (EmptyEventDateException | EmptyEventException | EmptyEventTimingException e) {
            assertEquals("     OOPS! Event date not given leh. Pls provide in the following format: event project meeting /from 16:00 /to 18:00 /on yyyy-MM-dd or dd/MM/yyyy", e.getMessage());
        }
    }
}