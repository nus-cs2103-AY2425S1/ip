package denim;

import denim.tasks.Task;
import denim.tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void addTaskAtIndex_validIndex_insertsTaskCorrectly() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("Task 1", false);
        Task task2 = new Todo("Task 2", false);
        Task task3 = new Todo("Task 3", false);

        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTaskAtIndex(1, task3);

        assertEquals(3, taskList.getTaskListSize());
        assertEquals(task1, taskList.getTask(0));
        assertEquals(task3, taskList.getTask(1));
        assertEquals(task2, taskList.getTask(2));
    }

    @Test
    public void printList_nonEmptyList_printsTasksCorrectly() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("Task 1", false);
        Task task2 = new Todo("Task 2", false);

        taskList.addTask(task1);
        taskList.addTask(task2);

        String output = taskList.printList();
        String expectedOutput = "1 " + task1 + "\n" + "2 " + task2;
        assertEquals(expectedOutput, output);
    }

    @Test
    public void printList_emptyList_printsEmptyMessage() {
        TaskList taskList = new TaskList();

        String output = taskList.printList();
        String expectedOutput = "Your Task List is Empty.\n";
        assertEquals(expectedOutput, output);
    }
}
