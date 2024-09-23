package ava.task.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



class TodoTest {

    @Test
    void testToString1() {
        Todo todo = new Todo("Test Task", true);
        String expected = "Todo: ✅ Done    | Test Task";
        assertEquals(expected, todo.toString());
    }

    @Test
    void testToString2() {
        Todo todo = new Todo("Test Task", false);
        String expected = "Todo: ❌ Pending | Test Task";
        assertEquals(expected, todo.toString());
    }

    @Test
    void serialize1() {
        Todo todo = new Todo("Test Task", true);
        String expected = "T,1,Test Task";
        assertEquals(expected, todo.serialize());

    }

    @Test
    void serialize2() {
        Todo todo = new Todo("Test Task", false);
        String expected = "T,0,Test Task";
        assertEquals(expected, todo.serialize());
    }

    @Test
    void serialize3() {
        Todo todo = new Todo("Test Task");
        String expected = "T,0,Test Task";
        assertEquals(expected, todo.serialize());
    }

}
