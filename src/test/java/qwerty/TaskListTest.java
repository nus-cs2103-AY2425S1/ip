package qwerty;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import qwerty.task.Deadline;
import qwerty.task.Task;
import qwerty.task.Todo;

public class TaskListTest {

    static class TodoStub extends Todo {

        public TodoStub(String description) {
            super(description);
        }

        @Override
        public List<String> getAllDetails() {
            String[] arr = {"T", " ", "todo"};
            return Arrays.stream(arr).toList();
        }
    }

    static class DeadlineStub extends Deadline {

        public DeadlineStub(String description, LocalDateTime by) {
            super(description, by);
        }

        @Override
        public List<String> getAllDetails() {
            String[] arr = {"D", "X", "deadline", "Aug 12 2036 0000"};
            return Arrays.stream(arr).toList();
        }
    }

    @Test
    public void generateSaveString_emptyList_emptyString() {
        assertEquals("", new TaskList().generateSaveString());
    }

    @Test
    public void generateSaveString_dummyTasks_success() {
        ArrayList<Task> list = new ArrayList<>();
        list.add(new TodoStub(""));
        list.add(new DeadlineStub("", null));
        TaskList tasks = new TaskList(list);
        String expected = """
                T| |todo
                D|X|deadline|Aug 12 2036 0000
                """;
        assertEquals(expected, tasks.generateSaveString());
    }
}
