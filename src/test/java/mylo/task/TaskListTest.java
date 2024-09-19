package mylo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import mylo.data.DuplicatedTaskException;
import mylo.data.InsufficientInfoException;
import mylo.storage.StorageOperationException;
import mylo.utils.exceptions.IllegalValueException;




public class TaskListTest {

    private static class TodoStub extends Task {

        public TodoStub(String title) {
            super(title);
        }

        @Override
        public String storageFormat() {
            return "TODO | [ ] | Todo Task";
        }

        @Override
        public String toString() {
            return "[T][ ] Todo Task";
        }

        @Override
        public boolean equals(Object o) {
            return false;
        }
    }

    @Test
    public void addTask_todo_success() {
        Task todo = new TodoStub("Todo Task");
        ArrayList<Task> mockList = new ArrayList<>(List.of(new Task[]{todo}));
        TaskList test = new TaskList();
        try {
            test.addTask(" Todo Task", TaskType.TODO);
        } catch (InsufficientInfoException | StorageOperationException | IllegalValueException
                 | DuplicatedTaskException e) {
            fail();
        }
        assertEquals(new TaskList(mockList).toString(), test.toString());
    }
}
