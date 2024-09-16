package tayoo;

import org.junit.jupiter.api.Test;
import tayoo.tasks.Task;
import tayoo.tasks.ToDo;
import tayoo.tasks.Deadline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TasklistTest {

    @Test
    public void tasklist_constructor_success() {
        ArrayList<Task> testEmptyTaskArraylist = new ArrayList<>();
        Task[] testTaskArray = { new ToDo("Read book"),
                new Deadline("Return Book", "Sunday")};
        List<Task> testTaskList = Arrays.asList(testTaskArray);
        ArrayList<Task> dummyTaskArraylist = new ArrayList<Task>(testTaskList);

        Tasklist testEmptyTasklist = new Tasklist(testEmptyTaskArraylist);
        Tasklist testDummyTasklist = new Tasklist(dummyTaskArraylist);

        //Empty Tasklist should have size an ArrayList<Task> size of 0
        assertEquals(0, testEmptyTasklist.getTaskList().size());

        //dummyTaskArraylist should have a Todo in index 0 and a Deadline at index 1 inside it
        assertEquals("[T][ ] Read book", testDummyTasklist.getTaskStr(0));
        assertEquals("[D][ ] Return Book (by: Sunday)", testDummyTasklist.getTaskStr(1));
    }

    @Test
    public void tasklist_addTask_success() {
        ArrayList<Task> testEmptyTaskArraylist = new ArrayList<>();
        Task[] testTaskArray = { new ToDo("Read book"),
                new Deadline("Return Book", "Sunday")};
        List<Task> testTaskList = Arrays.asList(testTaskArray);
        ArrayList<Task> dummyTaskArraylist = new ArrayList<Task>(testTaskList);

        Tasklist testEmptyTasklist = new Tasklist(testEmptyTaskArraylist);
        Tasklist testDummyTasklist = new Tasklist(dummyTaskArraylist);

        //adding a task to an empty list should increase size by one and add task to tasklist
        assertTrue(testEmptyTasklist.addTask(new ToDo("Feed fish")));
        assertEquals(1, testEmptyTasklist.getSize());
        assertEquals("[T][ ] Feed fish", testEmptyTasklist.getTaskStr(0));

        //adding a task to a list should append it to the end of the list
        assertTrue(testDummyTasklist.addTask(new ToDo("Feed cat")));
        assertEquals(3, testDummyTasklist.getSize());
        assertEquals("[T][ ] Feed cat", testDummyTasklist.getTaskStr(2));
    }

    @Test
    public void tasklist_deleteTask_success() {
        Task[] testTaskArray = { new ToDo("Read book"),
                new Deadline("Return Book", "Sunday")};
        List<Task> testTaskList = Arrays.asList(testTaskArray);
        ArrayList<Task> dummyTaskArraylist = new ArrayList<Task>(testTaskList);

        Tasklist testDummyTasklist = new Tasklist(dummyTaskArraylist);

        assertInstanceOf(ToDo.class, testDummyTasklist.deleteTask(0));
        assertEquals("[D][ ] Return Book (by: Sunday)", testDummyTasklist.getTaskStr(0));
        assertEquals(1, testDummyTasklist.getSize());
    }

}
