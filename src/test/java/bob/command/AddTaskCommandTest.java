package bob.command;

import bob.tasks.ToDos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class AddTaskCommandTest {

    @Test
    public void TestIsRunning() {
        System.out.println("TESTING");
        AddTaskCommand addTaskCommand = new AddTaskCommand(new ToDos("Hello"));
        assertFalse(addTaskCommand.isRunning());
    }
}
