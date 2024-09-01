package astor.task;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void toString_emptyUndone() {
        Todo todo = new Todo("");
        todo.markUndone();
        assertEquals("[T] [ ] ", todo.toString());
    }

    @Test
    public void toString_emptyDone() {
        Todo todo = new Todo("");
        todo.markDone();
        assertEquals("[T] [X] ", todo.toString());
    }

    @Test
    public void toString_oneWordUndone() {
        Todo todo = new Todo("read");
        todo.markUndone();
        assertEquals("[T] [ ] read", todo.toString());
    }

    @Test
    public void toString_oneWordDone() {
        Todo todo = new Todo("read");
        todo.markDone();
        assertEquals("[T] [X] read", todo.toString());
    }

    @Test
    public void toString_multipleWords() {
        Todo todo = new Todo("read and write");
        assertEquals("[T] [ ] read and write", todo.toString());
    }

    @Test
    public void toString_multipleWordsTrailingWhitespace() {
        Todo todo = new Todo(" read and write ");
        assertEquals("[T] [ ]  read and write ", todo.toString());
    }

    @Test
    public void dataDescription_done() {
        Todo todo = new Todo(" read and write ");
        todo.markDone();
        assertEquals("T | 1 |  read and write ", todo.dataDescription());
    }

    @Test
    public void dataDescription_undone() {
        Todo todo = new Todo(" read and write ");
        todo.markUndone();
        assertEquals("T | 0 |  read and write ", todo.dataDescription());
    }
}
