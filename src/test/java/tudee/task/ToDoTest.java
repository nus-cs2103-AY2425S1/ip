package tudee.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ToDoTest {

    private ToDo toDo;

    @BeforeEach
    void setUp() {
        toDo = new ToDo("Play game");
    }

    @Test
    void testToString() {
        String expectedString = "[T][ ] Play game";
        assertEquals(expectedString, toDo.toString());
    }

    @Test
    void testToFileString() {
        String expectedFileString = "T | 0 | Play game";
        assertEquals(expectedFileString, toDo.toFileString());
    }
}
