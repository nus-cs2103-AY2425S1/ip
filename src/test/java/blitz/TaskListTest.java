package blitz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import task.Todo;

public class TaskListTest {
    @Test
    public void checkListIsEmpty_emptyTaskList_returnTrue() {
        assertTrue(new TaskList(new ArrayList<>()).isEmpty());
    }

    @Test
    public void checkListIsEmpty_nonemptyTaskList_returnFalse() {
        assertFalse(new TaskList(new ArrayList<>(List.of(new Todo("", "", false)))).isEmpty());
    }

    @Test
    public void checkListSize_emptyTaskList_returnZero() {
        assertEquals(0, new TaskList(new ArrayList<>()).getSize());
    }

    @Test
    public void checkListSize_taskListOfSizeOne_returnOne() {
        assertEquals(1, new TaskList(new ArrayList<>(List.of(new Todo("", "", false)))).getSize());
    }

    @Test
    public void addTaskToTaskList_newTask_taskAddedInTaskList() {
        Todo todo = new Todo("", "", false);
        TaskList list = new TaskList(new ArrayList<>());
        list.addTask(todo);

        assertEquals(new TaskList(new ArrayList<>(List.of(todo))), list);
    }

    @Test
    public void deleteTaskFromTaskList_none_taskDeletedFromTaskList() {
        Todo todo = new Todo("", "", false);
        TaskList list = new TaskList(new ArrayList<>(List.of(todo)));
        list.deleteTask(0);

        assertEquals(new TaskList(new ArrayList<>()), list);
    }

    @Test
    public void getTaskFromTaskList_taskIndex_correctTaskReturn() {
        Todo todo = new Todo("", "", false);
        TaskList list = new TaskList(new ArrayList<>(List.of(todo)));

        assertEquals(todo, list.getTask(0));
    }

    @Test
    public void getAllTaskFromTaskList_none_arrayListOfAllTasksInTaskListReturn() {
        Todo todo = new Todo("", "", false);
        Todo todo2 = new Todo("", "", true);
        TaskList list = new TaskList(new ArrayList<>(List.of(todo, todo2)));

        assertEquals(new ArrayList<>(List.of(todo, todo2)), list.getAllTask());
    }

    @Test
    public void equalityOfTaskList_anotherTaskListWithSameContent_returnTrue() {
        TaskList list = new TaskList(new ArrayList<>());
        assertEquals(new TaskList(new ArrayList<>()), list);

        Todo todo = new Todo("", "", false);
        list.addTask(todo);
        assertEquals(new TaskList(new ArrayList<>(List.of(todo))), list);
    }

    @Test
    public void equalityOfTaskList_anotherTaskListWithDifferentContent_returnFalse() {
        Todo todo = new Todo("", "", false);
        TaskList list = new TaskList(new ArrayList<>());
        assertNotEquals(new TaskList(new ArrayList<>(List.of(todo))), list);

        list.addTask(todo);
        assertNotEquals(new TaskList(new ArrayList<>()), list);
    }
}
