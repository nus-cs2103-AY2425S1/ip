package bimo.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testStringConversion() {
        LocalDate startDate = LocalDate.parse("2022-12-19");
        LocalDate endDate = LocalDate.parse("2023-06-18");
        Event event = new Event("Go to work everyday from 8am to 5pm", startDate, endDate);
        assertEquals("[E][ ] Go to work everyday from 8am to 5pm (from: 19 Dec 2022 to: 18 Jun 2023)",
                event.toString());
    }

    @Test
    public void testTaskConversionToText() {
        LocalDate deadLine = LocalDate.parse("2023-06-18");
        Deadline deadline = new Deadline("Leave work at mattar", deadLine);
        assertEquals("|2023-06-18", deadline.getDateAsText());
    }
}
