package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {

    @Test
    public void testDescription() {
        assertEquals("[T][ ] hi",new Todo("hi").getString());
        assertEquals("[T][ ] bye",new Todo("bye").getString());
    }

    @Test
    public void testStorageFormat() {
        assertEquals("0 todo hi\n",new Todo("hi").getStorageFormat());
        Task todo = new Todo("hi");
        todo.mark();
        assertEquals("1 todo hi\n",todo.getStorageFormat());
    }

    @Test
    public void emptyTodo() throws Exception {
        try {
            assertEquals("",new Todo("").getStorageFormat());
        } catch (Exception e) {
            assertEquals("OOPS!!! The description of a todo shouldn't be empty!\n",e.getMessage());
        }
    }
}
