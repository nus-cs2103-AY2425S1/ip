package edith;

import edith.exception.InvalidTaskNumberException;
import edith.exception.MissingEventDurationException;
import edith.exception.MissingTaskNameException;
import edith.task.ToDoTask;
import org.junit.jupiter.api.Test;

import static edith.ToDoList.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoListTest {

    @Test
    public void testAddTask() {
        ToDoTask task = new ToDoTask("read book");
        ToDoList list = new ToDoList();
        list.add(task);
        assertEquals(" 1.[T][ ] read book\n", list.toString());
    }

    @Test
    public void testDeleteTask() {
        ToDoTask task = new ToDoTask("read book");
        ToDoTask task1 = new ToDoTask("watch lecture");
        ToDoList list = new ToDoList();
        list.add(task);
        list.add(task1);
        try {
            list.delete(1);
            assertEquals(" 1.[T][ ] watch lecture\n", list.toString());
        } catch (InvalidTaskNumberException e) {
            fail();
        }
    }

}
