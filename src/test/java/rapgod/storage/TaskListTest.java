package rapgod.storage;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rapgod.tasks.Deadline;
import rapgod.tasks.Event;
import rapgod.tasks.Task;
import rapgod.tasks.ToDo;
import rapgod.utils.Parser;

import static org.junit.jupiter.api.Assertions.*;


class TaskListTest {

    private TaskList taskList;
    private List<Task> tasks;

    @BeforeEach
    void setUp() {
        tasks = new ArrayList<>();
        taskList = new TaskList(tasks);
    }

    @Test
    void testAddToDoTask_textStringInput_formattedStringOutput() {
        taskList.addToDoTask("Finish homework");
        assertEquals(1, taskList.getList().size());
        assertTrue(taskList.getList().get(0) instanceof ToDo);
        assertEquals("[T] [ ] Finish homework", taskList.getList().get(0).toString());
    }

    @Test
    void testMarkTaskByIndex_markDone_true() {
        Task task = new ToDo("Complete assignment");
        taskList.getList().add(task);
        taskList.markTaskByIndex(1);
        assertTrue(task.isMarkedDone());
    }

    @Test
    void testMarkTaskByIndex_markDone_throwsIndexOutOfBoundException() {
        Task task = new ToDo("Complete assignment");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.getList().add(task);
            taskList.markTaskByIndex(-1);
        });
    }

    @Test
    void testUnmarkTaskByIndex_markFalse_false() {
        Task task = new ToDo("Complete assignment");
        task.setIsDone(true);
        taskList.getList().add(task);
        taskList.unmarkTaskByIndex(1);
        assertFalse(task.isMarkedDone());
    }

    @Test
    void testUnmarkTaskByIndex_markDone_throwsIndexOutOfBoundException() {
        Task task = new ToDo("Complete assignment");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.getList().add(task);
            taskList.unmarkTaskByIndex(-1);
        });
    }

    @Test
    void testDeleteTaskByIndex_delete_taskDeleted() {
        Task task1 = new ToDo("Task 1");
        Task task2 = new ToDo("Task 2");
        taskList.getList().add(task1);
        taskList.getList().add(task2);
        taskList.deleteTaskByIndex(1);
        assertEquals(1, taskList.getList().size());
        assertEquals("Task 2", taskList.getList().get(0).getDescription());
    }

    @Test
    void testAddDeadlineTask_textStringInput_formattedStringOutput() {
        taskList.addDeadlineTask("Submit report", "30/09/2024");
        assertEquals(1, taskList.getList().size());
        assertTrue(taskList.getList().get(0) instanceof Deadline);
        assertEquals("[D] [ ] Submit report (by: Sep 30 2024)", taskList.getList().get(0).toString());
    }

    @Test
    void testAddEventTask_textStringInput_formattedStringOutput() {
        taskList.addEventTask("Attend conference", "01/10/2024", "03/10/2024");
        assertEquals(1, taskList.getList().size());
        assertTrue(taskList.getList().get(0) instanceof Event);
        assertEquals("[E] [ ] Attend conference (from: Oct 01 2024 to: Oct 03 2024)",
                        taskList.getList().get(0).toString());
    }
}
