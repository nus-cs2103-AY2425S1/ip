package meep.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    private TaskList taskList;
    private Task task1;
    private Task task2;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        task1 = new Task("Read book");
        task2 = new Task("Write code");
    }

    @Test
    public void addItem_singleTask_addedCorrectly() {
        taskList.addItem(task1);
        assertEquals(1, taskList.getSize());
        assertEquals("1. [ ] Read book\n", taskList.getList());
    }

    @Test
    public void deleteItem_existingTask_removedCorrectly() {
        taskList.addItem(task1);
        taskList.addItem(task2);
        taskList.deleteItem(0);
        assertEquals(1, taskList.getSize());
        assertEquals("1. [ ] Write code\n", taskList.getList());
    }

    @Test
    public void getTask_existingTask_correctTaskRetrieved() {
        taskList.addItem(task1);
        String expected = "[ ] Read book\n";
        assertEquals(expected, taskList.getTask(0));
    }

    @Test
    public void getLastTask_multipleTasks_lastTaskRetrieved() {
        taskList.addItem(task1);
        taskList.addItem(task2);
        String expected = "[ ] Write code";
        assertEquals(expected, taskList.getLastTask());
    }

    @Test
    public void getList_multipleTasks_correctListRetrieved() {
        taskList.addItem(task1);
        taskList.addItem(task2);
        String expected = "1. [ ] Read book\n2. [ ] Write code\n";
        assertEquals(expected, taskList.getList());
    }

    @Test
    public void getSaveFormatList_multipleTasks_correctFormatRetrieved() {
        taskList.addItem(task1);
        taskList.addItem(task2);
        String expected = "0|Read book\n0|Write code\n";  // Assuming this is the save format
        assertEquals(expected, taskList.getSaveFormatList());
    }

    @Test
    public void getSize_emptyList_zeroSize() {
        assertEquals(0, taskList.getSize());
    }

    @Test
    public void getSize_nonEmptyList_correctSize() {
        taskList.addItem(task1);
        taskList.addItem(task2);
        assertEquals(2, taskList.getSize());
    }

    @Test
    public void taskListCopyConstructor_copyCreated() {
        taskList.addItem(task1);
        TaskList copiedList = new TaskList(taskList);
        assertEquals(1, copiedList.getSize());
        assertEquals(taskList.getList(), copiedList.getList());
    }
}
