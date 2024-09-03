package choaticbot.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {

    ToDos testObject = new ToDos("todoTest1");
    @Test
    public void testGetType() {
        assertEquals("T", testObject.getType());
    }

    @Test
    public void testToString() {
        assertEquals("[T] [ ] todoTest1", testObject.toString());
    }
}
