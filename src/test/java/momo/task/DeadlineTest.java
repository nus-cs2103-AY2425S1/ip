package momo.task;
import momo.task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    void toStringTest() {
        // Check if task list string is formatted properly
        assertEquals("[D][ ] return book (Apr 5 2021)", new Deadline("return book", LocalDate.parse("2021-04-05"), false).toString(), "Deadline string should be formatted properly for task list display");

        assertEquals("[D][X] return book (Apr 5 2021)", new Deadline("return book", LocalDate.parse("2021-04-05"), true).toString(), "Deadline string should be formatted properly for task list display");

    }

    @Test
    void toFileStringTest() {
        // Check if file string is formatted properly
        assertEquals("D|1|return book|2021-04-05", new Deadline("return book", LocalDate.parse("2021-04-05"), false).toFileString(), "Deadline string should be formatted properly for file storage");

    }

}
