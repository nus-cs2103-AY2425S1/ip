package choaticbot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import choaticbot.exceptions.WrongInputFormatException;
import org.junit.jupiter.api.Test;
public class ToDosTest {

    private ToDos testObject = new ToDos("todoTest1");

    @Test
    public void testConstructor() {
        assertEquals("todoTest1", testObject.getName());
        assertFalse(testObject.isComplete());
    }

    @Test
    public void testGetType() {
        assertEquals("T", testObject.getType());
    }

    @Test
    public void testToString() {
        assertEquals("[T] [ ] todoTest1", testObject.toString());
    }

    @Test
    public void testUpdate() throws WrongInputFormatException {
        testObject.update("todoTest2");

        assertEquals("todoTest2", testObject.getName());
    }

    @Test
    public void testGetAdditionalInfo() {
        assertEquals("", testObject.getAdditionalInfo());
    }
}
