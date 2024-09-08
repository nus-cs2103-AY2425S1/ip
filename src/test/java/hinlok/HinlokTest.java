package hinlok; //same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import hinlok.exceptions.HinlokException;
import hinlok.tasks.TaskList;



public class HinlokTest {
    @Test
    public void testTodoCreation() throws HinlokException {
        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.addTodo("homework");
        assertEquals(tasks.getTaskByIndex(0).toString(), "[T][ ] homework");
    }

    @Test
    public void testDeadlineCreation() throws HinlokException {
        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.addDeadline("homework /by 2022-10-11");
        System.out.println(tasks.getTaskByIndex(0).toString());
        assertEquals(tasks.getTaskByIndex(0).toString(), "[D][ ] homework (by: Oct 11 2022)");
    }

    @Test
    public void testEventCreation() throws HinlokException {
        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.addEvent("homework /from 5pm /to 6pm");
        System.out.println(tasks.getTaskByIndex(0).toString());
        assertEquals(tasks.getTaskByIndex(0).toString(), "[E][ ] homework (from: 5pm to: 6pm)");
    }

    @Test
    public void testListAndMark() throws HinlokException {
        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.addEvent("homework /from 5pm /to 6pm");
        tasks.markTask(0);
        System.out.println(tasks.getTaskByIndex(0).toString());
        assertEquals(tasks.getTaskByIndex(0).toString(), "[E][X] homework (from: 5pm to: 6pm)");
    }

}
