package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest(){
        String t = new Todo("read book").toString();
        assertEquals("[T][ ] read book", t);
    }

    @Test
    public void toFileTest(){
        String t = new Todo("read book").toFile();
        assertEquals("T| |read book", t);
    }
}