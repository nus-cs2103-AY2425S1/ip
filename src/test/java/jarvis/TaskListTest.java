package jarvis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void add_invalidArgument_exceptionThrown() {
        TaskList tasklist = new TaskList();
        try {
            tasklist.add("hello");
        } catch (Exception e){
            assertEquals(e.getClass(), new IllegalArgumentException().getClass());
        }
    }

    @Test
    void handleDelete() {
        TaskList tasklist = new TaskList();
        tasklist.add("todo hello task!");
        tasklist.handleDelete(1);

        assertEquals(tasklist.getNumTasks(), 0);

    }
}