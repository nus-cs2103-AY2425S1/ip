package bimo.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import bimo.exception.BimoException;


public class DeadlineTest {
    @Test
    public void toString_emptyInput() throws BimoException {
        LocalDate deadLine = LocalDate.parse("2023-06-18");
        Task deadline = new Deadline("Leave work at mattar", deadLine);
        assertEquals("[D][ ] Leave work at mattar (by: 18 Jun 2023)", deadline.toString());
    }

    @Test
    public void getDateAsText_emptyInput() {
        LocalDate deadLine = LocalDate.parse("2023-06-18");
        Deadline deadline = new Deadline("Leave work at mattar", deadLine);
        assertEquals("|2023-06-18", deadline.getDateAsText());
    }
}
