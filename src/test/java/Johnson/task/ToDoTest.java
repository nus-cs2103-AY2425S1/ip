package Johnson.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ToDoTest {

    @Test
    void testToDoCreationWithName() {
        ToDo todo = new ToDo("Test ToDo");
        assertEquals("Test ToDo", todo.getTaskName());
        assertFalse(todo.getTaskStatus());
    }

    @Test
    void testToDoCreationWithNameAndTags() {
        ToDo todo = new ToDo("Test ToDo", "tag1", "tag2");
        assertEquals("Test ToDo", todo.getTaskName());
        assertFalse(todo.getTaskStatus());
        assertTrue(todo.hasTag("tag1"));
        assertTrue(todo.hasTag("tag2"));
    }

    @Test
    void testCompleteToDo() {
        ToDo todo = new ToDo("Test ToDo");
        todo.completeTask();
        assertTrue(todo.getTaskStatus());
    }

    @Test
    void testUncompleteToDo() {
        ToDo todo = new ToDo("Test ToDo");
        todo.completeTask();
        todo.uncompleteTask();
        assertFalse(todo.getTaskStatus());
    }

    @Test
    void testSetToDoName() {
        ToDo todo = new ToDo("Test ToDo");
        todo.setTaskName("New ToDo Name");
        assertEquals("New ToDo Name", todo.getTaskName());
    }

    @Test
    void testAddTag() {
        ToDo todo = new ToDo("Test ToDo");
        todo.addTag("newTag");
        assertTrue(todo.hasTag("newTag"));
    }

    @Test
    void testToString() {
        ToDo todo = new ToDo("Test ToDo", "tag1", "tag2");
        assertEquals("[T][ ] Test ToDo [tag1, tag2]", todo.toString());
        todo.completeTask();
        assertEquals("[T][X] Test ToDo [tag1, tag2]", todo.toString());
    }
}