package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void displayTask_undoneDeadline_correctDisplay() {
        Deadline deadline = new Deadline("task", "1/2/2023 1400");
        assertEquals("[ ][D][ ] task (by: Feb 1 2023 14:00)\n", deadline.displayTask());
    }

    @Test
    public void displayTask_doneDeadline_correctDisplay() {
        Deadline deadline = new Deadline("task", "1/2/2023 1400");
        deadline.markAsDone();
        assertEquals("[ ][D][X] task (by: Feb 1 2023 14:00)\n", deadline.displayTask());
    }

    @Test
    public void displayTask_prioritisedDeadline_correctDisplay() {
        Deadline deadline = new Deadline("task", "1/2/2023 1400");
        deadline.setPriority(true);
        assertEquals("[!][D][ ] task (by: Feb 1 2023 14:00)\n", deadline.displayTask());
    }

    @Test
    public void displayTask_prioritisedAndDoneDeadline_correctDisplay() {
        Deadline deadline = new Deadline("task", "1/2/2023 1400");
        deadline.markAsDone();
        deadline.setPriority(true);
        assertEquals("[!][D][X] task (by: Feb 1 2023 14:00)\n", deadline.displayTask());
    }
}
