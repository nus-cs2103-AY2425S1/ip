package yapper;
import yapper.exceptions.YapperException;
import yapper.resources.ToDo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void getDesc() {
        assertEquals("| T |   | return book", new ToDo("return book").getDesc());
        assertEquals("| T |   | this", new ToDo("this").getDesc());
        assertEquals("| T |   | that", new ToDo("that").getDesc());
        assertEquals("| T |   | make thing", new ToDo("make thing").getDesc());
        assertEquals("| T |   | fight", new ToDo("fight").getDesc());
    }

    @Test
    public void getDesc_emptyString_exceptionThrown() {
        try {
            assertEquals("| T |   | ", new ToDo("").getDesc());
            System.out.println("Fail");
        } catch (YapperException e) {
            assertEquals("Description cannot be empty", e.getMessage());
        }
    }
}
