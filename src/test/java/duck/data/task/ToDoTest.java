package duck.data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests the ToDo class.
 */
public class ToDoTest {

    private static final String INCOMPLETE_DESCRIPTION = "incomplete";
    private static final String COMPLETE_DESCRIPTION = "complete";

    private static final String EXPECTED_TO_STRING_INCOMPLETE_TODO = "[T][ ] " + INCOMPLETE_DESCRIPTION;

    private static final String EXPECTED_FILE_FORMAT_INCOMPLETE_TODO = "T | 0 | " + INCOMPLETE_DESCRIPTION;

    private static final String EXPECTED_TO_STRING_COMPLETE_TODO = "[T][X] " + COMPLETE_DESCRIPTION;

    private static final String EXPECTED_FILE_FORMAT_COMPLETE_TODO = "T | 1 | " + COMPLETE_DESCRIPTION;

    private ToDo incompleteToDo;
    private ToDo completeToDo;

    /**
     * Set up the to-dos for testing.
     */
    @BeforeEach
    public void setUp() {
        incompleteToDo = new ToDo(INCOMPLETE_DESCRIPTION);
        completeToDo = new ToDo(true, COMPLETE_DESCRIPTION);
    }

    /**
     * Test if the description of the to-do is correct.
     */
    @Test
    public void toFileFormat() {
        assertEquals(EXPECTED_FILE_FORMAT_INCOMPLETE_TODO, incompleteToDo.toFileFormat());
        assertEquals(EXPECTED_FILE_FORMAT_COMPLETE_TODO, completeToDo.toFileFormat());
    }

    /**
     * Test if the to-do is complete.
     */
    @Test
    public void testToString() {
        assertEquals(EXPECTED_TO_STRING_INCOMPLETE_TODO, incompleteToDo.toString());
        assertEquals(EXPECTED_TO_STRING_COMPLETE_TODO, completeToDo.toString());
    }
}
