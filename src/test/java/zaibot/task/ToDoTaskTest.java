package zaibot.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ToDoTaskTest {

    @Test
    public void toSaveString_success() {
        ToDoTask task = new ToDoTask("name");

        Assertions.assertEquals(task.toSaveString(),
                "T | INCOMPLETE | name");
    }
}
