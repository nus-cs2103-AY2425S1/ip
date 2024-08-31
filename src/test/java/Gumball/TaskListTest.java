package Gumball;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void add_simpleToDo_Success() throws InputErrorException {
        ToDo temp = new ToDo("todo test");
        TaskList list = new TaskList();
        list.add(temp);
        assertEquals(list.tasks.get(0), temp);
    }

    @Test
    public void getTask_simpleToDo_Success() throws InputErrorException {
        ToDo temp = new ToDo("todo test");
        TaskList list = new TaskList();
        list.add(temp);
        assertEquals(list.getTask(1), temp);
    }

    @Test
    public void getTask_notEnoughtTasks_throwsException() throws InputErrorException {
        ToDo temp = new ToDo("todo test");
        TaskList list = new TaskList();
        list.add(temp);
        try {
            list.getTask(2);
            fail();
        } catch (InputErrorException e) {
            assertEquals("Sorry you do not have that many Tasks in your list", e.getMessage());
        }
    }

    @Test
    public void getTask_zeroInput_throwsException() throws InputErrorException {
        ToDo temp = new ToDo("todo test");
        TaskList list = new TaskList();
        list.add(temp);
        try {
            list.getTask(0);
            fail();
        } catch (InputErrorException e) {
            assertEquals("Invalid input task number cannot be zero or less", e.getMessage());
        }
    }

    @Test
    public void getSpecific_simpleToDo_Success() throws InputErrorException {
        ToDo temp = new ToDo("todo test");
        TaskList list = new TaskList();
        list.add(temp);
        assertEquals(list.getSpecific(1), "[T][ ] test");
    }

    @Test
    public void getSpecific_notEnoughtTasks_throwsException() throws InputErrorException {
        ToDo temp = new ToDo("todo test");
        TaskList list = new TaskList();
        list.add(temp);
        try {
            list.getSpecific(3);
            fail();
        } catch (InputErrorException e) {
            assertEquals("Sorry you do not have that many Tasks in your list", e.getMessage());
        }
    }
    @Test
    public void getSpecific_zeroInput_throwsException() throws InputErrorException {
        ToDo temp = new ToDo("todo test");
        TaskList list = new TaskList();
        list.add(temp);
        try {
            list.getSpecific(0);
            fail();
        } catch (InputErrorException e) {
            assertEquals("Invalid input task number cannot be zero or less", e.getMessage());
        }
    }
}
