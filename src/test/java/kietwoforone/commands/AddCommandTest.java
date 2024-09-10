package kietwoforone.commands;

import kietwoforone.tasks.Todo;
import kietwoforone.tasks.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {

    private Task task = new Todo("Read book");

    @Test
    public void addCommandTest() {
        assertEquals("Task added", new AddCommand(task).toString());
    }

}
