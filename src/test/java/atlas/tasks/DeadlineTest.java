package atlas.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Deadline class to ensure correct functionality for deadline tasks.
 */
public class DeadlineTest {
    private Deadline deadline;

    /**
     * Initializes a Deadline object before each test.
     */
    @BeforeEach
    public void init() {
        this.deadline = new Deadline("finish assignment",
                LocalDateTime.of(2024, 6, 15, 17, 0));
    }

    /**
     * Tests the string representation of an unmarked deadline.
     */
    @Test
    public void toString_unmarkedDeadline() {
        assertEquals("[D] [ ] finish assignment (by: Jun 15 2024 5:00 pm)", this.deadline.toString());
    }

    /**
     * Tests the string representation of a marked deadline.
     */
    @Test
    public void toString_markedDeadline() {
        this.deadline.setIsDone();
        assertEquals("[D] [X] finish assignment (by: Jun 15 2024 5:00 pm)", this.deadline.toString());
    }

    /**
     * Tests the file string representation of an unmarked deadline.
     */
    @Test
    public void toFileString_unmarkedDeadline() {
        assertEquals("D | 0 | finish assignment | 2024-06-15 1700", this.deadline.toFileString());
    }

    /**
     * Tests the file string representation of a marked deadline.
     */
    @Test
    public void toFileString_markedDeadline() {
        this.deadline.setIsDone();
        assertEquals("D | 1 | finish assignment | 2024-06-15 1700", this.deadline.toFileString());
    }
}
