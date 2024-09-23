package ava.task.tasks;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;




class TaskTypeTest {

    @Test
    void values() {
        TaskType[] expected = {TaskType.TODO, TaskType.DEADLINE, TaskType.EVENT};
        TaskType[] actual = TaskType.values();
        assertArrayEquals(expected, actual);
    }

    @Test
    void valueOf() {
        assertEquals(TaskType.TODO, TaskType.valueOf("TODO"));
        assertEquals(TaskType.DEADLINE, TaskType.valueOf("DEADLINE"));
        assertEquals(TaskType.EVENT, TaskType.valueOf("EVENT"));
    }
}
