package tags;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import chatterboxexceptions.ChatterboxExceptions;
import tasks.Task;
import tasks.Todo;

public class TagTest {
    @Test
    public void getTagName() {
        Tag tag = new Tag("tag");
        assertEquals("tag", tag.getTagName());
    }

    @Test
    public void testToString() {
        Tag tag = new Tag("tag");
        assertEquals("tag", tag.toString());
    }

    @Test
    public void testEquals() {
        Tag tag = new Tag("tag");
        Tag tag2 = new Tag("tag");
        assertEquals(true, tag.equals(tag2));
    }

    @Test
    public void tagTodo() {
        try {
            Tag tag = new Tag("tag");
            Task task = new Todo("task");
            tag.tagTask(task);
            Set<Task> taggedTasks = new HashSet<>();
            taggedTasks.add(task);
            assertEquals(taggedTasks, tag.getTaggedTasks());
        } catch (ChatterboxExceptions.ChatterBoxNoInput e) {
            System.out.println("error");
        }
    }

    @Test
    public void getTaggedTasksString() {
        try {
            Tag tag = new Tag("tag");
            Task task = new Todo("task");
            tag.tagTask(task);
            assertEquals("task \n", tag.getTaggedTasksString());
        } catch (ChatterboxExceptions.ChatterBoxNoInput e) {
            System.out.println("error");
        }
    }
}
