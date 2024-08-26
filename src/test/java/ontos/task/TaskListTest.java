package ontos.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        taskList.addTask(Task.toDo("Buy groceries"));
        taskList.addTask(Task.deadline("Submit report", LocalDate.parse("2024-09-01")));
        taskList.addTask(Task.event("Team meeting", LocalDate.parse("2024-09-15"), LocalDate.parse("2024-09-16")));
    }

    @Test
    void completeTaskAt_sampleList_taskMarked() {
        taskList.completeTaskAt(1);
        assertEquals(Task.toDo("Buy groceries", true), taskList.getTaskAt(1));
    }

    @Test
    void completeTaskAt_invalidIndex_exceptionThrown() {
        try {
            taskList.completeTaskAt(0);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index -1 out of bounds for length 3", e.getMessage());
        }
    }

    @Test
    void uncompleteTaskAt_sampleList_taskUnmarked() {
        taskList.completeTaskAt(1);
        taskList.uncompleteTaskAt(1);
        assertEquals(Task.toDo("Buy groceries"), taskList.getTaskAt(1));
    }

    @Test
    void removeTaskAt_sampleList_taskRemoved() {
        Task removedTask = taskList.removeTaskAt(1);
        assertEquals(2, taskList.getSize());
        assertEquals(Task.toDo("Buy groceries"), removedTask);
    }

    @Test
    void toString_sampleList_listPrinted() {
        String expected = " 1. [T][ ] Buy groceries\n" +
                          " 2. [D][ ] Submit report (by: 2024-09-01)\n" +
                          " 3. [E][ ] Team meeting (from: 2024-09-15 to: 2024-09-16)";
        assertEquals(expected, taskList.toString());
    }

}