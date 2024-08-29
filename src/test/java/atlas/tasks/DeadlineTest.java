package atlas.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    public Deadline deadline;

    @BeforeEach
    public void init() {
        this.deadline = new Deadline("finish assignment",
                LocalDateTime.of(2024, 6, 15, 17, 0));
    }

    @Test
    public void toString_unmarkedDeadline() {
        assertEquals("[D] [ ] finish assignment (by: Jun 15 2024 5:00 pm)", this.deadline.toString());
    }

    @Test
    public void toString_markedDeadline() {
        this.deadline.setIsDone();
        assertEquals("[D] [X] finish assignment (by: Jun 15 2024 5:00 pm)", this.deadline.toString());
    }

    @Test
    public void toFileString_unmarkedDeadline() {
        assertEquals("D | 0 | finish assignment | 2024-06-15 1700", this.deadline.toFileString());
    }

    @Test
    public void toFileString_markedDeadline() {
        this.deadline.setIsDone();
        assertEquals("D | 1 | finish assignment | 2024-06-15 1700", this.deadline.toFileString());
    }
}
