package cheese;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import cheese.exception.CheeseException;
import cheese.task.Deadline;
import cheese.task.Event;
import cheese.task.Task;
import cheese.task.ToDo;

public class TaskListTest {
    @Test
    public void testAddTask() throws CheeseException {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Read book");
        taskList.add(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));
    }

    @Test
    public void testRemoveTask() throws CheeseException {
        TaskList taskList = new TaskList();
        Task task1 = new ToDo("Read book");
        Task task2 = new Deadline("Submit assignment", LocalDate.of(2024, 9, 15));
        taskList.add(task1);
        taskList.add(task2);
        assertEquals(2, taskList.size());

        Task removedTask = taskList.remove(0);
        assertEquals(task1, removedTask);
        assertEquals(1, taskList.size());
        assertEquals(task2, taskList.get(0));
    }

    @Test
    public void testSearch() throws CheeseException {
        TaskList taskList = new TaskList();
        Task task1 = new ToDo("Read book");
        Task task2 = new Deadline("Submit assignment", LocalDate.of(2024, 9, 15));
        Task task3 = new Event("Team meeting", LocalDate.of(2024, 9, 20), LocalDate.of(2024, 9, 21));
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        TaskList searchResult = taskList.search("assignment");
        assertEquals(1, searchResult.size());
        assertEquals(task2, searchResult.get(0));

        searchResult = taskList.search("meeting");
        assertEquals(1, searchResult.size());
        assertEquals(task3, searchResult.get(0));

        searchResult = taskList.search("nonexistent");
        assertEquals(0, searchResult.size());
    }

    @Test
    public void testSearchEmptyQuery() throws CheeseException {
        TaskList taskList = new TaskList();
        Task task1 = new ToDo("Read book");
        Task task2 = new Deadline("Submit assignment", LocalDate.of(2024, 9, 15));
        taskList.add(task1);
        taskList.add(task2);

        TaskList searchResult = taskList.search("");
        assertEquals(2, searchResult.size());
        assertEquals(task1, searchResult.get(0));
        assertEquals(task2, searchResult.get(1));
    }

    @Test
    public void testSearchNoMatch() throws CheeseException {
        TaskList taskList = new TaskList();
        Task task1 = new ToDo("Read book");
        taskList.add(task1);

        TaskList searchResult = taskList.search("assignment");
        assertEquals(0, searchResult.size());
    }

    @Test
    public void testConstructorWithExistingTasks() throws CheeseException {
        Task task1 = new ToDo("Read book");
        Task task2 = new Deadline("Submit assignment", LocalDate.of(2024, 9, 15));
        ArrayList<Task> existingTasks = new ArrayList<>();
        existingTasks.add(task1);
        existingTasks.add(task2);

        TaskList taskListWithTasks = new TaskList(existingTasks);
        assertEquals(2, taskListWithTasks.size());
        assertEquals(task1, taskListWithTasks.get(0));
        assertEquals(task2, taskListWithTasks.get(1));
    }
}
