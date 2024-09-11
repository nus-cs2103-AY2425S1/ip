package bob.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTaskTest {
    @Test
    public void getDate_success() {
        // getDate() should return null for TodoTask
        assertEquals(null, new TodoTask("eat lunch").getDate());
    }

    @Test
    public void getType_success() {
        // getType() should return "T" for TodoTask
        assertEquals("T", new TodoTask("eat lunch").getType());
    }

    @Test
    public void toString_success() {
        // toString() should return "[T][ ] eat lunch" for TodoTask
        assertEquals("[T][ ] eat lunch", new TodoTask("eat lunch").toString());
    }
}
