package yapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import yapper.components.ToDo;
import yapper.exceptions.YapperException;

public class ToDoTest {

    @Test
    public void testGetDesc() {
        assertEquals("| T |   | return book", new ToDo("return book").getDesc());
        assertEquals("| T |   | this", new ToDo("this").getDesc());
        assertEquals("| T |   | that", new ToDo("that").getDesc());
        assertEquals("| T |   | make thing", new ToDo("make thing").getDesc());
        assertEquals("| T |   | fight", new ToDo("fight").getDesc());
    }

    @Test
    public void testGetDesc_emptyString_exceptionThrown() {
        try {
            assertEquals("| T |   | ", new ToDo("").getDesc());
            System.out.println("Fail");
        } catch (YapperException e) {
            assertEquals("Please provide a description", e.getMessage());
        }
    }

    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] return book", new ToDo("return book").toString());
        assertEquals("[T][ ] this", new ToDo("this").toString());
        assertEquals("[T][ ] that", new ToDo("that").toString());
        assertEquals("[T][ ] make thing", new ToDo("make thing").toString());
        assertEquals("[T][ ] fight", new ToDo("fight").toString());
    }

    @Test
    public void testMarkAndUnMark() {
        ToDo toDoToTest = new ToDo("return book");
        // Mark todo
        toDoToTest.mark();
        assertEquals("[T][X] return book", toDoToTest.toString());
        assertEquals("| T | X | return book", toDoToTest.getDesc());
        // Unmark todo
        toDoToTest.unmark();
        assertEquals("[T][ ] return book", toDoToTest.toString());
        assertEquals("| T |   | return book", toDoToTest.getDesc());
    }
}
