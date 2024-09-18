package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void displayTask_undoneDeadline() {
        Deadline deadline = new Deadline("task", "1/2/2023 1400");
        assertEquals("[D][ ] task (by: Feb 1 2023 14:00)\n", deadline.displayTask());
    }

    @Test
    public void displayTask_doneDeadline() {
        Deadline deadline = new Deadline("task", "1/2/2023 1400");
        deadline.markAsDone();
        assertEquals("[D][X] task (by: Feb 1 2023 14:00)\n", deadline.displayTask());
    }
}
