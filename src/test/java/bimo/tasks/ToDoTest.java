package bimo.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bimo.exception.BimoException;


public class ToDoTest {
    @Test
    public void toString_emptyInput() throws BimoException {
        Task todo = new ToDo("Attend lesson");
        assertEquals("[T][ ] Attend lesson", todo.toString());
    }

}
