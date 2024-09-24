package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void getWriteFormatTest() {
        Todo todo = new Todo("complete 2103 week 4 tasks");

        //test case 1
        todo.getWriteFormat();
        assertEquals("T , 0 , complete 2103 week 4 tasks", todo.getWriteFormat());

        //test case 2
        todo.setDone(true);
        assertEquals("T , 1 , complete 2103 week 4 tasks", todo.getWriteFormat());
    }

    @Test
    public void toStringTest() {
        //test case 1
        Todo todo = new Todo("Complete CS2103 week 5 tasks");
        assertEquals(todo.toString(), "[T][ ] Complete CS2103 week 5 tasks");
        todo.setDone(true);
        assertEquals(todo.toString(), "[T][X] Complete CS2103 week 5 tasks");
    }
}
