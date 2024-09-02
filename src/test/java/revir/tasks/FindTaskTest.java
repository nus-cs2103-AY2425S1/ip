package revir.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import revir.system.Storage;
import revir.user.Ui;

public class FindTaskTest {
    Storage storageMock = mock(Storage.class);
    Ui uiMock = mock(Ui.class);

    Todo task1 = new Todo("task1");
    Todo task2 = new Todo("task2");
    Todo task3 = new Todo("task3");

    private TaskList generateTaskList() {
        TaskList taskList = new TaskList(storageMock, uiMock);
        try {

            taskList.add(task1);
            taskList.add(task2);
            taskList.add(task3);
        } catch (IOException e) {
            assert (false);
        }
        return taskList;
    }

    @Test
    public void testSingleResult() {
        TaskList taskList = generateTaskList();
        assertEquals(taskList.find("1"), task1.toString());
        assertEquals(taskList.find("2"), task2.toString());
        assertEquals(taskList.find("3"), task3.toString());
    }

    @Test
    public void testNoResult() {
        TaskList taskList = generateTaskList();
        assertEquals(taskList.find("0"), "");
    }

    @Test
    public void testMultipleResults() {
        TaskList taskList = generateTaskList();
        assertEquals(taskList.find("task"), task1.toString() + "\n" 
            + task2.toString() + "\n" + task3.toString());
    }
}