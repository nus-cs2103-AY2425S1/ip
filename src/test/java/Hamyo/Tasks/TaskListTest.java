package Hamyo.Tasks;

import org.junit.jupiter.api.Test;
import Hamyo.Misc.HamyoException;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void testAddTask () throws HamyoException {
        TaskList taskList = new TaskList();

        // Test Case 1: Empty Array.
        assertTrue(taskList.isEmpty());

        // Test Case 2: Array of size 1 [T, ].
        taskList.addTask(TaskType.TODO, " apple");
        assertEquals(1, taskList.size());
        assertEquals("[T] [ ] apple", taskList.get(0).toString());

        // Test Case 3: Array of size 2 [T, D, ].
        taskList.addTask(TaskType.DEADLINE, " banana /by 2002-09-18");
        assertEquals(2, taskList.size());
        assertEquals("[T] [ ] apple", taskList.get(0).toString());
        assertEquals("[D] [ ] banana (by: Sep 18 2002)", taskList.get(1).toString());

        // Test Case 4: Array of size 3 [T, D, E, ].
        taskList.addTask(TaskType.EVENT, " chiku /from 2002-09-18 23:59 /to 2002-10-18 00:01");
        assertEquals(3, taskList.size());
        assertEquals("[T] [ ] apple", taskList.get(0).toString());
        assertEquals("[D] [ ] banana (by: Sep 18 2002)", taskList.get(1).toString());
        assertEquals("[E] [ ] chiku (from: Sep 18 2002 23:59HRS to: Oct 18 2002 00:01HRS)", taskList.get(2).toString());
    }

    @Test
    public void testDeleteTask () throws HamyoException {
        TaskList taskList = new TaskList();
        taskList.addTask(TaskType.TODO, " apple");
        taskList.addTask(TaskType.DEADLINE, " banana /by 2002-09-18");
        taskList.addTask(TaskType.EVENT, " chiku /from 2002-09-18 23:59 /to 2002-10-18 00:01");

        // Test Case 1: Array of size 3 [T, D, E, ].
        assertEquals(3, taskList.size());
        assertEquals("[T] [ ] apple", taskList.get(0).toString());
        assertEquals("[D] [ ] banana (by: Sep 18 2002)", taskList.get(1).toString());
        assertEquals("[E] [ ] chiku (from: Sep 18 2002 23:59HRS to: Oct 18 2002 00:01HRS)", taskList.get(2).toString());

        // Test Case 2: Array of size 2 [D, E, ].
        taskList.deleteTask(" 1");
        assertEquals(2, taskList.size());
        assertEquals("[D] [ ] banana (by: Sep 18 2002)", taskList.get(0).toString());
        assertEquals("[E] [ ] chiku (from: Sep 18 2002 23:59HRS to: Oct 18 2002 00:01HRS)", taskList.get(1).toString());

        // Test Case 3: Array of size 2 [E, ].
        taskList.deleteTask(" 1");
        assertEquals(1, taskList.size());
        assertEquals("[E] [ ] chiku (from: Sep 18 2002 23:59HRS to: Oct 18 2002 00:01HRS)", taskList.get(0).toString());

        // Test Case 4: Empty Array.
        taskList.deleteTask(" 1");
        assertTrue(taskList.isEmpty());
    }

    @Test
    public void testMarkTask() throws HamyoException {
        TaskList taskList = new TaskList();
        taskList.addTask(TaskType.TODO, " apple");

        // Test Case 1: Check new task is unmarked.
        assertEquals("[T] [ ] apple", taskList.get(0).toString());

        // Test Case 2: Mark unmarked task.
        taskList.markTask(" 1");
        assertEquals("[T] [X] apple", taskList.get(0).toString());

        // Test Case 3: Mark marked task.
        try {
        taskList.markTask(" 1");
        } catch (Exception e) {
        assertEquals("This task was already marked as completed!", e.getMessage());
        }
        assertEquals("[T] [X] apple", taskList.get(0).toString());
    }

    @Test
    public void testUnmarkTask() throws HamyoException {
        TaskList taskList = new TaskList();
        taskList.addTask(TaskType.TODO, " apple");

        // Test Case 1: Check new task is unmarked.
        assertEquals("[T] [ ] apple", taskList.get(0).toString());

        // Test Case 2: Mark unmarked task.
        taskList.markTask(" 1");
        assertEquals("[T] [X] apple", taskList.get(0).toString());

        // Test Case 3: Unmark marked task.
        taskList.unmarkTask(" 1");
        assertEquals("[T] [ ] apple", taskList.get(0).toString());

        // Test Case 4: Unmark unmarked task.
        try {
        taskList.unmarkTask(" 1");
        } catch (Exception e) {
        assertEquals("This task was already marked as incomplete!", e.getMessage());
        }
        assertEquals("[T] [ ] apple", taskList.get(0).toString());
    }
}
