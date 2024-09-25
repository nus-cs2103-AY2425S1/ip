package bigdog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class BigdogTest {

    /**
     * A dummy test to verify that the testing framework is working correctly.
     * This test is used as a placeholder to ensure that the testing setup is functional.
     */
    @Test
    public void buildTest() {
        assertEquals(2, 2);
    }

    /**
     * Tests the add method of the TaskList class.
     * This test verifies that a Todo task can be added to a task list
     * and the correct message is returned.
     */
    @Test
    public void testList() {
        assertEquals("Got it. I've added this task:\n[T][ ] read a book\nNow you have 1 tasks in the list.\n", (
                new TaskList(new ArrayList<Task>())).add(Todo.of("read a book")));
    }

}
