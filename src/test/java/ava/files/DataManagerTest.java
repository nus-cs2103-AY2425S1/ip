package ava.files;

import ava.task.Task;
import ava.task.tasks.Deadline;
import ava.task.tasks.Event;
import ava.task.tasks.TaskType;
import ava.task.tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataManagerTest {

    @Test
    void serialize() {
        // Test for TO-DO task
        Task todo = new Todo("Read book");
        assertEquals("T,0,Read book", DataManager.serialize(todo));

        // Test for DEADLINE task
        Task deadline = new Deadline("Submit assignment", "2023-10-10T23:59");
        assertEquals("D,0,Submit assignment,2023-10-10T23:59", DataManager.serialize(deadline));

        // Test for EVENT task
        Task event = new Event("Meeting", "2023-10-10T10:00", "2023-10-10T11:00");
        assertEquals("E,0,Meeting,2023-10-10T10:00,2023-10-10T11:00", DataManager.serialize(event));

    }

    @Test
    void deserialize() {
        // Test for TODO task
        String todoTask = "T,0,Read book";
        Task todo = DataManager.deserialize(todoTask);
        assertTrue(todo instanceof Todo);
        assertEquals("Read book", todo.getTitle());
        assertFalse(todo.isDone());

        // Test for DEADLINE task
        String deadlineTask = "D,1,Submit assignment,2023-10-10T23:59";
        Task deadline = DataManager.deserialize(deadlineTask);
        assertTrue(deadline instanceof Deadline);
        assertEquals("Submit assignment", deadline.getTitle());
        assertTrue(deadline.isDone());

        // Test for EVENT task
        String eventTask = "E,0,Meeting,2023-10-10T10:00,2023-10-10T11:00";
        Task event = DataManager.deserialize(eventTask);
        assertTrue(event instanceof Event);
        assertEquals("Meeting", event.getTitle());
        assertFalse(event.isDone());

        // Test for invalid task type
        String invalidTask = "X,0,Invalid task";
        assertThrows(IllegalArgumentException.class, () -> DataManager.deserialize(invalidTask));

        // Test for missing task identifier
        String emptyTask = "";
        assertThrows(IllegalArgumentException.class, () -> DataManager.deserialize(emptyTask));
    }

    @Test
    void getTaskType() {
        assertEquals(TaskType.TODO, DataManager.getTaskType('T'));
        assertEquals(TaskType.DEADLINE, DataManager.getTaskType('D'));
        assertEquals(TaskType.EVENT, DataManager.getTaskType('E'));
        assertThrows(IllegalArgumentException.class, () -> DataManager.getTaskType('X'));
    }

    @Test
    void isMarkedDone() {
        assertTrue(DataManager.isMarkedDone("1"));
        assertFalse(DataManager.isMarkedDone("0"));
        assertThrows(IllegalArgumentException.class, () -> DataManager.isMarkedDone("2"));
    }
}