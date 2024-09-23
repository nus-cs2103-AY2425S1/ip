package meerkatpack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;
import taskpack.Todo;

public class TodoTest {
    @Test
    public void toParseableString_taskNameEat_tueat() {
        Todo todoTask = new Todo("eat", false);
        String outputName = todoTask.toParseableString();
        assertEquals("t,u,eat", outputName);
    }

    @Test
    public void testConstructor() {
        Todo todoTask1 = new Todo("sleep", false);
        assertFalse(todoTask1.isMarked());
        Todo todoTask2 = new Todo("wake up", true);
        assertTrue(todoTask2.isMarked());
    }
}
