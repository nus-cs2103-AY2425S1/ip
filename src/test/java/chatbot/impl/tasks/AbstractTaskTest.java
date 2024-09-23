package chatbot.impl.tasks;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import kat.tasks.Task;
import kat.tasks.impl.AbstractTask;
import kat.tasks.impl.DeadlineTask;
import kat.tasks.impl.EventTask;
import kat.tasks.impl.TodoTask;

public class AbstractTaskTest {

    @Test
    void testDeserializeValidTodoTask() {
        String input = "T | 0 | Buy groceries";
        Task task = AbstractTask.deserialize(input);
        assertInstanceOf(TodoTask.class, task);
    }

    @Test
    void testDeserializeValidDeadlineTask() {
        String input = "D | 0 | Submit report | 2024-12-02";
        Task task = AbstractTask.deserialize(input);
        assertInstanceOf(DeadlineTask.class, task);
    }

    @Test
    void testDeserializeValidEventTask() {
        String input = "E | 0 | Team meeting | 2024-12-02 | 2024-12-31";
        Task task = AbstractTask.deserialize(input);
        assertInstanceOf(EventTask.class, task);
    }

    @Test
    void testDeserializeInvalidFormat() {
        String input = "Invalid format";
        assertThrows(IllegalArgumentException.class, () -> AbstractTask.deserialize(input));
    }

    @Test
    void testDeserializeUnknownTaskType() {
        String input = "X | 0 | Some task | 2024-12-02";
        assertThrows(IllegalArgumentException.class, () -> AbstractTask.deserialize(input));
    }

}
