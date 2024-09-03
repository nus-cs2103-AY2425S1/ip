package drbrown.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    private String description;
    private String validTime;
    private String invalidTime;
    private DateTimeFormatter formatter;
    @BeforeEach
    void setUp() {
        description = "Assignment";
        validTime = "03-09-2024 10:10";
        invalidTime = "03-09-24 10:10";
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    }
    @Test
    public void testSuccessfulCreationDeadline() {
        Deadline deadline = new Deadline(false, this.description, LocalDateTime.parse(this.validTime, formatter));
        assertEquals("[D][ ] Assignment (by: Sep 03 2024 10:10)", deadline.toString());
    }

    @Test
    public void testInvalidDateTimeDeadline() {
        try {
            Deadline deadline = new Deadline(false, this.description, LocalDateTime.parse(this.invalidTime, formatter));
            fail("Expected DateTimeParseException to be thrown");
        } catch (DateTimeParseException ignored) {
        }
    }

    @Test
    void testToFileStringDeadline() {
        Deadline deadline = new Deadline(false, this.description, LocalDateTime.parse(this.validTime, formatter));
        assertEquals("D | false | Assignment | 2024-09-03 1010", deadline.toFileString());
    }

    @Test
    void testToUIStringDeadline() {
        Deadline deadline = new Deadline(false, this.description, LocalDateTime.parse(this.validTime, formatter));
        assertEquals("Last night, Darth Vader came down from Planet Vulcan and told me that if you don't meet this deadline... he'd melt your brain! So, better get moving!\n", deadline.toUIString());
    }

    @Test
    public void testSuccessfulCreationMarkDoneDeadline() {
        Deadline deadline = new Deadline(true, this.description, LocalDateTime.parse(this.validTime, formatter));
        assertEquals("[D][X] Assignment (by: Sep 03 2024 10:10)", deadline.toString());
    }

}
