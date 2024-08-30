import exceptions.InvalidTaskException;
import exceptions.NoTaskDescriptionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskTest {
    @Test
    public void createTaskTest1() {
        assertThrows(InvalidTaskException.class, () -> Task.createTask(""));
    }

    @Test
    public void createTaskTest2() {
        assertThrows(NoTaskDescriptionException.class, () -> Task.createTask("todo"));
    }

    @Test
    public void createTaskTest3() {
        assertThrows(NoTaskDescriptionException.class, () -> Task.createTask("event /from 10-10-2019 /to 11-10-2019"));
    }
}
