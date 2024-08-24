import ip.derrick.*;
import ip.derrick.EmptyListException;
import ip.derrick.InvalidDescriptionException;
import ip.derrick.MissingItemException;
import ip.derrick.Task;
import ip.derrick.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    private TaskList tasks;

    @BeforeEach
    public void setUp() {
        ArrayList<Task> emptyList = new ArrayList<>();
        this.tasks = new TaskList(emptyList);
    }

    @Test
    public void deleteFromEmptyList_ShouldThrowEmptyListException() {
        EmptyListException exception = assertThrows(EmptyListException.class, () -> {
            tasks.delete("delete 1");
        });

        assertEquals("You are trying to delete from an empty list.", exception.getMessage());
    }

    @Test
    public void markAnEmptyList() {
        MissingItemException exception = assertThrows(MissingItemException.class, () -> {
            tasks.markItem("mark 1");
        });

        assertEquals("Item does not exist in the list. Please try again.", exception.getMessage());
    }

    @Test
    public void missingDescription() {
        InvalidDescriptionException exception = assertThrows(ip.derrick.InvalidDescriptionException.class, () -> {
            tasks.addTodo("todo");
        });

        assertEquals("You have not added any description for the todo task. Please try again.", exception.getMessage());
    }
}
