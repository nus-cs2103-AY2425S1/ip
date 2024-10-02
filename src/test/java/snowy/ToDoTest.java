package snowy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toString_normalName_success() {
        assertEquals("[T][ ] Read Book", new ToDo("Read Book").toString());
    }

    @Test
    public void toFileStorage_normalName_success() {
        assertEquals("T|0|Read Book", new ToDo("Read Book").toFileStorage());
    }
}
