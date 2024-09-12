package garfield.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TodoTest {

    private Todo todo;

    @BeforeEach
    void setUp() {
        todo = new Todo("Write unit tests");
    }

    @Test
    void constructor_validDescription_taskInitialized() {
        assertNotNull(todo);
        assertEquals("Write unit tests", todo.getTaskDescription());
        assertEquals("[T][ ] Write unit tests", todo.toString());
    }

    @Test
    void markAsDone_taskMarkedDone_statusUpdated() {
        todo.markAsDone();
        assertEquals("[T][X] Write unit tests", todo.toString());
    }

    @Test
    void markAsUndone_taskPreviouslyMarkedDone_statusReverted() {
        todo.markAsDone(); // Mark as done first
        todo.markAsUndone();
        assertEquals("[T][ ] Write unit tests", todo.toString());
    }

    @Test
    void toSaveRepresentation_taskNotDone_correctSaveFormat() {
        assertEquals("T | 0 | Write unit tests", todo.toSaveRepresentation());
    }

    @Test
    void toSaveRepresentation_taskDone_correctSaveFormat() {
        todo.markAsDone();
        assertEquals("T | 1 | Write unit tests", todo.toSaveRepresentation());
    }
}

