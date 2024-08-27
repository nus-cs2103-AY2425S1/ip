package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {
    @Test
    public void testDescription() {
        assertEquals("[D][ ] hi (by: Sunday)",new Deadline("hi", "Sunday").getString());
        assertEquals("[D][ ] bye (by: Dec 12 2020)",new Deadline("bye", "2020-12-12").getString());
    }

    @Test
    public void testStorageFormat() {
        assertEquals("0 deadline hi /by Sunday\n",new Deadline("hi", "Sunday").getStorageFormat());
        assertEquals("0 deadline bye /by 2020-12-12\n",new Deadline("bye", "2020-12-12").getStorageFormat());
    }

    @Test
    public void emptyTodo() throws Exception {
        try {
            assertEquals("",new Deadline("","").getStorageFormat());
        } catch (Exception e) {
            assertEquals("OOPS!!! The description of a deadline shouldn't be empty!\n",e.getMessage());
        }
    }
}
