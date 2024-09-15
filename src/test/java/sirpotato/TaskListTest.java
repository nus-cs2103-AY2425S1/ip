package sirpotato;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

public class TaskListTest {

    @Test
    public void delete_validIndex_taskRemoved() {
        
        //Create any random tasklist, the tasks are irrelevant here
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
    public void delete_invalidIndex_throwsException() {

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Task 1"));
        tasks.add(new Todo("Task 2"));
        tasks.add(new Todo("Task 3"));
        TaskList toDoList = new TaskList(tasks);

        // Since the arraylist is only of size 3, using the item number as 10
        // should throw an error
        assertThrows(DukeException.class, () -> {
            toDoList.delete(10, tasks); 
        });
    }
}
