package chatbot;

import chatbot.exception.InvalidIndexException;
import chatbot.task.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void get() {
        ArrayList<Task> input = new ArrayList<>();
        TaskStub task = new TaskStub();
        input.add(task);
        TaskList tl = new TaskList(input);
        try {
            assertEquals(tl.get(0), task);
        } catch(InvalidIndexException e) {
            fail();
        }
    }

    @Test
    public void get_invalidIndex() {
        ArrayList<Task> input = new ArrayList<>();
        TaskStub task = new TaskStub();
        input.add(task);
        TaskList tl = new TaskList(input);
        assertThrows(InvalidIndexException.class,
                () -> {
                    tl.get(-1);
                });
    }

    @Test
    public void mark_invalidIndex() {
        ArrayList<Task> input = new ArrayList<>();
        TaskStub task = new TaskStub();
        input.add(task);
        TaskList tl = new TaskList(input);
        assertThrows(InvalidIndexException.class,
                () -> {
                    tl.mark(-1, true);
                });
    }

    @Test
    public void remove() {
        ArrayList<Task> input = new ArrayList<>();
        TaskStub task = new TaskStub();
        input.add(task);
        TaskList tl = new TaskList(input);
        try {
            tl.remove(0);
        } catch (InvalidIndexException e) {
            fail();
        }
        assertThrows(InvalidIndexException.class,
                () -> {
                    tl.get(0);
                });
    }
}
