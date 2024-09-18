package choaticbot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
public class ToDosTest {

    private ToDos testObject = new ToDos("todoTest1");
    @Test
    public void testGetType() {
        assertEquals("T", testObject.getType());
    }

    @Test
    public void testToString() {
        assertEquals("[T] [ ] todoTest1", testObject.toString());
    }
}
