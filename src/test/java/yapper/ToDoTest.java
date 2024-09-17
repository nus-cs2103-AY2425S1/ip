package yapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toStringTest() {
        ToDo todo = new ToDo("buy bread");
        todo.setIsDone(true);
        String expected = "[T][X] buy bread";
        String actual = todo.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void toFileTest() {
        ToDo todo = new ToDo("buy bread");
        todo.setIsDone(true);
        String expected = "T D--buy bread";
        String actual = todo.toFile();
        assertEquals(expected, actual);
    }
}
