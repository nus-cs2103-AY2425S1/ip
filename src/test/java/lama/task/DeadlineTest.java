package lama.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void ConstructorTest() {
        String description = "Return Book";
        LocalDate by = LocalDate.of(2025, 12, 12);
        Task deadline = new Deadline(description, by);

        String output = "[D][ ] Return Book (by: Dec 12 2025)";

        assertEquals(output, deadline.toString());
    }

    @Test
    public void toFileTest() {
        String description = "Return Book";
        LocalDate by = LocalDate.of(2025, 12, 12);
        Task deadline = new Deadline(description, by);

        String output = "D | 0 | Return Book | 2025-12-12";

        assertEquals(output, deadline.toFile());
    }
}
