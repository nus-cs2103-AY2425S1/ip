package astor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import astor.exception.MarkTaskOutOfRangeException;
import astor.task.Task;
import astor.task.Todo;



public class TaskListTest {

    public class StubStorage extends Storage {
        @Override
        public void appendToFile(String textToAppend) {
        }

        @Override
        public void updateData(List<Task> tasks) {

        }
    }


    @Test
    public void markTaskDone_validIndex() {
        TaskList taskList = new TaskList();
        Storage storage = new StubStorage();
        try {
            taskList.addTask(new Todo("sample"), storage);
            assertEquals("Nice! I've marked this task as done:\n" + 1 + ". " + "[T] [X] sample",
                    taskList.markTaskDone(1, storage));
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    public void markTaskDone_invalidIndex() {
        TaskList taskList = new TaskList();
        Storage storage = new StubStorage();
        try {
            taskList.addTask(new Todo("sample"), storage);
            assertThrows(MarkTaskOutOfRangeException.class, () -> taskList.markTaskDone(2, storage));
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    public void markTaskDone_validIndexTaskDone() {
        TaskList taskList = new TaskList();
        Storage storage = new StubStorage();

        try {
            Task task = new Todo("sample");
            task.markDone();
            taskList.addTask(task, storage);
            assertEquals("This task is already done:\n" + 1 + ". " + task,
                    taskList.markTaskDone(1, storage));
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

}

