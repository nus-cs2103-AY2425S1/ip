package alfred.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskListTest {

    private TaskList taskList;
    private Task task1;
    private Task task2;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        task1 = new TaskStub("Read book", false);
        task2 = new TaskStub("Write code", false);
    }

    @Test
    void addTask() {
        taskList.addTask(task1);
        assertEquals(1, taskList.getTasksCount());
        assertEquals(task1, taskList.getTasks().get(0));
    }

    @Test
    void getTasks() {
        taskList.addTask(task1);
        taskList.addTask(task2);

        List<Task> tasks = taskList.getTasks();
        assertEquals(2, tasks.size());
        assertTrue(tasks.contains(task1));
        assertTrue(tasks.contains(task2));
    }

    @Test
    void getTasksCount() {
        assertEquals(0, taskList.getTasksCount());

        taskList.addTask(task1);
        taskList.addTask(task2);

        assertEquals(2, taskList.getTasksCount());
    }

    @Test
    void deleteTask_valid() {
        taskList.addTask(task1);
        taskList.addTask(task2);

        taskList.deleteTask(1);
        assertEquals(1, taskList.getTasksCount());
        assertEquals(task2, taskList.getTasks().get(0));
    }

    @Test
    void deleteTask_outOfBounds() {
        taskList.addTask(task1);
        taskList.addTask(task2);

        // No tasks should be deleted if command invalid
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(3));
        assertEquals(2, taskList.getTasksCount());
        assertEquals(task1, taskList.getTasks().get(0));
        assertEquals(task2, taskList.getTasks().get(1));
    }

    @Test
    void markTask_valid() {
        taskList.addTask(task1);
        taskList.markTask(1);
        assertTrue(task1.isDone);
    }

    @Test
    void markTask_outOfBounds() {
        taskList.addTask(task1);

        assertThrows(IndexOutOfBoundsException.class, () -> taskList.markTask(3));
        assertFalse(task1.isDone);
    }

    @Test
    void addTagToTask_valid() {
        taskList.addTask(task1);
        String tag = "urgent";

        taskList.tagTask(1, tag);
        assertEquals(tag, task1.getTagsAsString());
    }

    @Test
    void addTagToTask_multipleTags() {
        taskList.addTask(task1);
        taskList.tagTask(1, "important");
        taskList.tagTask(1, "urgent");

        assertEquals("important urgent", task1.getTagsAsString());
    }

    @Test
    void untagTask_valid() {
        taskList.addTask(task1);
        taskList.tagTask(1, "important");
        taskList.tagTask(1, "urgent");

        taskList.untagTask(1, "important");

        assertEquals("urgent", task1.getTagsAsString());
    }

    @Test
    void untagTask_tagNotFound() {
        taskList.addTask(task1);
        taskList.tagTask(1, "important");

        // Attempt to untag a tag that does not exist
        taskList.untagTask(1, "urgent");

        // The existing tag "important" should still be there
        assertEquals("important", task1.getTagsAsString());
    }

    @Test
    void getTagsAsString_noTags() {
        taskList.addTask(task1);

        assertEquals("No Tags", task1.getTagsAsString());
    }
}
