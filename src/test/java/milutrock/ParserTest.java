package milutrock;

import org.junit.jupiter.api.Test;

import milutrock.exceptions.UnknownCommandException;
import milutrock.stubs.TaskListStub;
import milutrock.stubs.UiStub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    private TaskListStub taskListStub;
    private UiStub uiStub;
    private Parser parser;

    @Test
    public void testParseCommand() {
        this.taskListStub = new TaskListStub();
        this.uiStub = new UiStub();
        this.parser = new Parser(this.taskListStub, this.uiStub);

        runCommand("list", false);
        assertEquals(uiStub.lastFunctionCalled, UiStub.Function.PRINT_TASK_LIST);

        runCommand("mark 2", false);
        assertEquals(taskListStub.lastFunctionCalled, TaskListStub.Function.MARK_TASK_AS_DONE);
        assertEquals(taskListStub.integerInput, 1);
        assertEquals(uiStub.lastFunctionCalled, UiStub.Function.PRINT_MARK_MESSAGE);
        assertEquals(uiStub.integerInput, 1);

        runCommand("unmark 2", false);
        assertEquals(taskListStub.lastFunctionCalled, TaskListStub.Function.UNMARK_TASK_AS_DONE);
        assertEquals(taskListStub.integerInput, 1);
        assertEquals(uiStub.lastFunctionCalled, UiStub.Function.PRINT_UNMARK_MESSAGE);
        assertEquals(uiStub.integerInput, 1);

        runCommand("delete 2", false);
        assertEquals(taskListStub.lastFunctionCalled, TaskListStub.Function.REMOVE_TASK);
        assertEquals(taskListStub.integerInput, 1);
        assertEquals(uiStub.lastFunctionCalled, UiStub.Function.PRINT_DELETE_MESSAGE);
        assertEquals(uiStub.taskInput, this.taskListStub.dummyTask);

        runCommand("todo todo task", false);
        assertEquals(taskListStub.lastFunctionCalled, TaskListStub.Function.ADD_TASK);
        assertEquals(taskListStub.taskInput.toString(), "[T][ ] todo task");

        runCommand("deadline deadline task /by 1984-02-28 1600", false);
        assertEquals(taskListStub.lastFunctionCalled, TaskListStub.Function.ADD_TASK);
        assertEquals(taskListStub.taskInput.toString(), "[D][ ] deadline task (by: Feb 28 1984 1600)");

        runCommand("event event task /from from date /to to date", false);
        assertEquals(taskListStub.lastFunctionCalled, TaskListStub.Function.ADD_TASK);
        assertEquals(taskListStub.taskInput.toString(), "[E][ ] event task (from: from date to: to date)");

        runCommand("asdasdas", true);
    }

    private void runCommand(String command, boolean shouldThrowException) {
        uiStub.reset();
        taskListStub.reset();

        try {
            parser.parseCommand(command);

            if (shouldThrowException) {
                fail("Command did not throw exception");
            }
        } catch (UnknownCommandException e) {
            if (!shouldThrowException) {
                fail("Command failed unexpectedly");
            }
        }
    }
}
