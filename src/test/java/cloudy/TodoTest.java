package cloudy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testPrintTaskOnList_Marked() {
        Todo todo = new Todo("Go fishing", true);

        String result = todo.printTaskOnList();

        assertEquals("[T][X] Go fishing", result);
    }

    @Test
    public void testPrintTaskOnList_Unmarked() {
        Todo todo = new Todo("Go prawning", false);

        String result = todo.printTaskOnList();

        assertEquals("[T][ ] Go prawning", result);
    }

    @Test
    public void testToFileFormat_Marked() {
        Todo todo = new Todo("Go eat", true);

        String result = todo.toFileFormat();

        assertEquals("T | 1 | Go eat", result);
    }

    @Test
    public void testToFileFormat_Unmarked() {
        Todo todo = new Todo("Go shopping", false);

        String result = todo.toFileFormat();

        assertEquals("T | 0 | Go shopping", result);
    }


}
