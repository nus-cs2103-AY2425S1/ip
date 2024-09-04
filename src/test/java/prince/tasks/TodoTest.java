package prince.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import main.tasks.Todo;

public class TodoTest {
    @Test
    public void constructor_validInput_success() {
        assertEquals("test", new Todo("test").getDescription());
    }

    @Test
    public void toFileFormat_validInput_success() {
        assertEquals("T .. 0 .. test", new Todo("test").toFileFormat());
    }

    @Test
    public void toString_validInput_success() {
        assertEquals("[T][ ]  test", new Todo("test").toString());
    }
}
