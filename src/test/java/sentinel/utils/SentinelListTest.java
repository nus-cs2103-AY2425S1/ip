package sentinel.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sentinel.task.Task;
import sentinel.task.ToDo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class SentinelListTest {
    private SentinelList sentinelList;
    private Task task1;
    private Task task2;

    @BeforeEach
    public void setUp() {
        sentinelList = new SentinelList();
        task1 = new ToDo("Task 1");
        task2 = new ToDo("Task 2");
        sentinelList.add(task1);
        sentinelList.add(task2);
    }

    @Test
    public void testSizeOneWhenSizeIsOne() {
        // Arrange
        SentinelList singleTaskList = new SentinelList();
        singleTaskList.add(new ToDo("Single Task"));

        // Act & Assert
        assertTrue(singleTaskList.sizeOne());
    }

    @Test
    public void testSizeOneWhenSizeIsNotOne() {
        // Act & Assert
        assertFalse(sentinelList.sizeOne());
    }

    @Test
    public void testGetListedString() {
        // Act
        String result = sentinelList.getListedString(0);

        // Assert
        assertEquals("[T][ ] Task 1", result);
    }

    @Test
    public void testToggleMark() {
        // Act
        sentinelList.toggleMark(0);
        // Assert
        assertTrue(task1.isDone());

        // Act
        sentinelList.toggleMark(0);
        // Assert
        assertFalse(task1.isDone());
    }

    @Test
    public void testToggleMarkWithTask() {
        // Act
        sentinelList.toggleMark(task2);
        // Assert
        assertTrue(task2.isDone());

        // Act
        sentinelList.toggleMark(task2);
        // Assert
        assertFalse(task2.isDone());
    }

    @Test
    public void testTaskIsDone() {
        // Act
        sentinelList.toggleMark(0);
        boolean isDone = sentinelList.taskIsDone(0);

        // Assert
        assertTrue(isDone);
    }

    @Test
    public void testGetWithValidIndex() {
        // Act
        Task result = sentinelList.get(1);

        // Assert
        assertEquals(task2, result);
    }

    @Test
    public void testGetWithInvalidIndex() {
        // Act
        Task result = sentinelList.get(-1);

        // Assert
        assertNull(result);
    }
}
