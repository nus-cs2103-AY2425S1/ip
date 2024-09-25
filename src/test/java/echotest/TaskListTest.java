package echotest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import echo.EchoException;
import echo.TaskList;
import echo.task.Todo;


/**
 * Unit test class for TaskList.
 */
public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(); // Initialize a fresh TaskList before each test
    }

    @Test
    public void addTodo_taskAddedSuccessfully() {
        Todo todo = taskList.addTodo("Read a book");
        assertEquals(1, taskList.size());
        assertEquals("Read a book", todo.getTaskDes());
    }


    @Test
    public void addDeadline_invalidDateFormat_throwsEchoException() {
        assertThrows(EchoException.class, () -> taskList.addDeadline("Submit report /by invalid-date"),
                "Expected to throw EchoException due to invalid date format");
    }

    @Test
    public void deleteTask_validTaskNumber_taskDeletedSuccessfully() throws EchoException {
        taskList.addTodo("Do laundry");
        taskList.addTodo("Clean room");
        assertEquals(2, taskList.size());

        taskList.deleteTask(1);
        assertEquals(1, taskList.size());
        assertEquals("Clean room", taskList.getTask(1).getTaskDes());
    }

    @Test
    public void deleteTask_invalidTaskNumber_throwsEchoException() {
        assertThrows(EchoException.class, () -> taskList.deleteTask(100),
                "Expected to throw EchoException due to invalid task number");
    }

    @Test
    public void markTask_validTaskNumber_taskMarkedAsDone() throws EchoException {
        taskList.addTodo("Finish homework");
        taskList.markTask(1);
        assertEquals("1", taskList.getTask(1).getIsDone());
    }

    @Test
    public void unmarkTask_validTaskNumber_taskUnmarkedAsUndone() throws EchoException {
        taskList.addTodo("Finish homework");
        taskList.markTask(1);
        taskList.unmarkTask(1);
        assertEquals("0", taskList.getTask(1).getIsDone());
    }

    @Test
    public void listToString_returnsCorrectStringRepresentation() {
        taskList.addTodo("Buy groceries");
        taskList.addTodo("Complete assignment");

        String expectedString = "T|0|Buy groceries| \n" + "T|0|Complete assignment| \n";
        assertEquals(expectedString, taskList.listToString());
    }

    @Test
    public void find_tasksContainingKeyword_returnCorrectTasks() {
        taskList.addTodo("Buy groceries");
        taskList.addTodo("Complete assignment");
        taskList.addTodo("Buy gifts");

        String foundTasks = taskList.find("Buy");
        assertTrue(foundTasks.contains("Buy groceries"));
        assertTrue(foundTasks.contains("Buy gifts"));
        assertFalse(foundTasks.contains("Complete assignment"));
    }

    @Test
    public void size_returnsCorrectTaskCount() {
        taskList.addTodo("Buy groceries");
        taskList.addTodo("Complete assignment");

        assertEquals(2, taskList.size());
    }
}
