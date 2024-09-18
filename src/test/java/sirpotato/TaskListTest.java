package sirpotato;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

public class TaskListTest {

    @Test
    public void delete_validIndex_taskRemoved() {
        
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Task 1"));
        tasks.add(new Todo("Task 2"));
        tasks.add(new Todo("Task 3"));
        TaskList toDoList = new TaskList(tasks);

        toDoList.delete(1, tasks); 

        // Ensure the right item was deleted and the array is modified correctly.
        assertEquals(2, toDoList.getList().size());
        assertEquals("Task 3", toDoList.getList().get(1).displayDescription());
    }

    @Test
    public void sortByDescription_tasksSortedCorrectly() {

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Walk dog"));
        tasks.add(new Todo("Read book"));
        tasks.add(new Todo("Ace Exam"));
        
        TaskList taskList = new TaskList(tasks);

        TaskList sortedList = taskList.sortBy("description");

        assertEquals("Ace Exam", sortedList.getTask(0).displayDescription());
        assertEquals("Read book", sortedList.getTask(1).displayDescription());
        assertEquals("Walk dog", sortedList.getTask(2).displayDescription());
    }
}
