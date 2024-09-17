// src/test/java/Johnson/command/CommandTest.java
package Johnson.command;

import Johnson.task.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void testSetTaskList() {
        Command.setTaskList(taskList);
        assertNotNull(Command.taskList, "Task list should not be null");
    }

    @Test
    void testExecuteCommand() {
        Command command = new ConcreteCommand();
        Command.setTaskList(taskList);
        String result = command.executeCommand();
        assertEquals("Command executed", result, "The command should execute successfully");
    }

    // Concrete subclass for testing
    static class ConcreteCommand extends Command {
        @Override
        public String executeCommand() {
            return "Command executed";
        }
    }
}