package rapgod.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


class TaskTest {

    @Test
    void testSetIsDone_setDone_true() {
        Task task = new Task("Complete JUnit tests");
        task.setIsDone(true);
        assertTrue(task.isMarkedDone(), "Task should be marked as done.");
    }

    @Test
    void testToString_simpleString_formattedString() {
        Task taskDone = new Task("Complete JUnit tests");
        taskDone.setIsDone(true);

        Task taskNotDone = new Task("Write documentation");

        assertEquals("[X] Complete JUnit tests", taskDone.toString(), "toString should show [X] for done tasks.");
        assertEquals("[ ] Write documentation", taskNotDone.toString(), "toString should show [ ] for not done tasks.");
    }
}
