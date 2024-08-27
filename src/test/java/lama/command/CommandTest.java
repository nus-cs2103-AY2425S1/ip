package lama.command;

import lama.task.Task;
import lama.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {
    @Test
    public void isExitTest() {

        Task todo = new Todo("Read Book");

        Command command = new AddCommand(todo);

        assertFalse(command.isExit());
    }
}
