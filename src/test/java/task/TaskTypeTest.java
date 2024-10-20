package task;

import darwin.exception.IllegalTaskTypeException;
import darwin.task.TaskType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskTypeTest {

    @Test
    public void fromName_writtenCorrectly() throws IllegalTaskTypeException {
        assertEquals(TaskType.TODO, TaskType.fromName("todo"));
        assertEquals(TaskType.DEADLINE, TaskType.fromName("deadline"));
        assertEquals(TaskType.EVENT, TaskType.fromName("event"));
    }

    @Test
    public void fromName_exceptionThrown() {
        IllegalTaskTypeException e = assertThrows(
                IllegalTaskTypeException.class,
                () -> TaskType.fromName("error")
        );
        assertEquals("error is not a valid task type", e.getMessage());
    }

    @Test
    public void fromSymbol_writtenCorrectly() throws IllegalTaskTypeException {
        assertEquals(TaskType.TODO, TaskType.fromSymbol("T"));
        assertEquals(TaskType.DEADLINE, TaskType.fromSymbol("D"));
        assertEquals(TaskType.EVENT, TaskType.fromSymbol("E"));
    }

    @Test
    public void fromSymbol_exceptionThrown() {
        IllegalTaskTypeException e = assertThrows(
                IllegalTaskTypeException.class,
                () -> TaskType.fromSymbol("0")
        );
        assertEquals("0 is not a valid task type", e.getMessage());
    }
}
