package Johnson.task;

import Johnson.utils.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testTaskCreationWithName() {
        Task task = new Task("Test Task") {};
        assertEquals("Test Task", task.getTaskName());
        assertFalse(task.getTaskStatus());
    }

    @Test
    void testTaskCreationWithNameAndStatus() {
        Task task = new Task("Test Task", true) {};
        assertEquals("Test Task", task.getTaskName());
        assertTrue(task.getTaskStatus());
    }

    @Test
    void testCompleteTask() {
        Task task = new Task("Test Task") {};
        task.completeTask();
        assertTrue(task.getTaskStatus());
    }

    @Test
    void testUncompleteTask() {
        Task task = new Task("Test Task", true) {};
        task.uncompleteTask();
        assertFalse(task.getTaskStatus());
    }

    @Test
    void testSetTaskName() {
        Task task = new Task("Test Task") {};
        task.setTaskName("New Task Name");
        assertEquals("New Task Name", task.getTaskName());
    }

    @Test
    void testSetTaskStatus() {
        Task task = new Task("Test Task") {};
        task.setTaskStatus(true);
        assertTrue(task.getTaskStatus());
    }

    @Test
    void testAddTag() {
        Task task = new Task("Test Task") {};
        task.addTag("newTag");
        assertTrue(task.hasTag("newTag"));
    }

    @Test
    void testHasTag() {
        Task task = new Task("Test Task", "tag1", "tag2") {};
        assertTrue(task.hasTag("tag1"));
        assertTrue(task.hasTag("tag2"));
        assertFalse(task.hasTag("tag3"));
    }

    @Test
    void testGetTags() {
        Task task = new Task("Test Task", "tag1", "tag2") {};
        ArrayList<Tag> tags = task.getTags();
        assertEquals(2, tags.size());
        assertEquals("tag1", tags.get(0).getTag().toString());
        assertEquals("tag2", tags.get(1).getTag().toString());
    }

    @Test
    void testToString() {
        Task task = new Task("Test Task", "tag1", "tag2") {};
        assertEquals("[ ] Test Task [tag1, tag2]", task.toString());
        task.completeTask();
        assertEquals("[X] Test Task [tag1, tag2]", task.toString());
    }
}