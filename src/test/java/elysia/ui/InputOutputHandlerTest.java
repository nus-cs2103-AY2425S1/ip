package elysia.ui;

import elysia.tasks.Deadline;
import elysia.tasks.Event;
import elysia.tasks.Todo;
import elysia.tasks.TaskListStub;
import elysia.tasks.TaskList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * Tests for the {@link InputOutputHandler} class.
 * This class contains unit tests to verify the behavior of the {@link InputOutputHandler} methods,
 * including parsing input commands and interacting with stubs of {@link TaskList} and {@link FileReaderWriter}.
 */
public class InputOutputHandlerTest {
    InputOutputHandler inputOutputHandler;
    TaskListStub taskListStub;
    FileReaderWriterStub fileReaderWriterStub;

    /**
     * Sets up the test environment before each test case.
     * Initializes {@link TaskListStub} and {@link FileReaderWriterStub} instances
     * and assigns them to the {@link InputOutputHandler} instance.
     */
    @BeforeEach
    void setUp() {
        taskListStub = new TaskListStub();
        fileReaderWriterStub = new FileReaderWriterStub(taskListStub);
        inputOutputHandler = new InputOutputHandler() {
            {
                taskList = taskListStub;
                fileReaderWriter = fileReaderWriterStub;
            }
        };
    }

    /**
     * Tests the behavior when the "bye" command is parsed.
     * Verifies that the file creation and saving methods are called.
     *
     * @throws ElysiaException if an exception occurs during parsing.
     */
    @Test
    void testParseInputBye() throws ElysiaException {
        assertFalse(inputOutputHandler.parseInput("bye"));
        assertTrue(fileReaderWriterStub.createFileCalled);
        assertTrue(fileReaderWriterStub.writeFileCalled);
    }

    /**
     * Tests the behavior when the "list" command is parsed.
     * Verifies that the {@code toString} method of {@link TaskListStub} is called.
     *
     * @throws ElysiaException if an exception occurs during parsing.
     */
    @Test
    void testParseInputList() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("list"));
        assertTrue(taskListStub.isToStringCalled);
    }

    /**
     * Tests marking a task with a correct task number.
     * Verifies that the task number is set correctly and the {@code printTask} method is called.
     *
     * @throws ElysiaException if an exception occurs during parsing.
     */
    @Test
    void parseInput_markCorrectTask_success() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("mark 1"));
        assertEquals(1, taskListStub.taskNumber);
        assertTrue(taskListStub.isPrintTaskCalled);
    }

    /**
     * Tests marking a task with a wrong task number.
     * Verifies that no task is printed and an exception is handled.
     *
     * @throws ElysiaException if an exception occurs during parsing.
     */
    @Test
    void parseInput_markWrongTask_exceptionCaught() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("mark -1"));
        assertFalse(taskListStub.isPrintTaskCalled);
    }

    /**
     * Tests unmarking a task with a correct task number.
     * Verifies that the task number is set correctly and the {@code printTask} method is called.
     *
     * @throws ElysiaException if an exception occurs during parsing.
     */
    @Test
    void parseInput_unmarkCorrectTask_success() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput(("unmark 1")));
        assertEquals(1, taskListStub.taskNumber);
        assertTrue(taskListStub.isPrintTaskCalled);
    }

    /**
     * Tests unmarking a task with a wrong task number.
     * Verifies that no task is printed and an exception is handled.
     *
     * @throws ElysiaException if an exception occurs during parsing.
     */
    @Test
    void parseInput_unmarkWrongTask_exceptionCaught() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("mark -1"));
        assertFalse(taskListStub.isPrintTaskCalled);
    }

    /**
     * Tests the behavior when an empty "todo" command is parsed.
     * Verifies that no task is added and no size check is performed.
     *
     * @throws ElysiaException if an exception occurs during parsing.
     */
    @Test
    void parseInput_todoEmptyInput_nothingDone() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("todo"));
        assertFalse(taskListStub.isAddTaskCalled);
        assertFalse(taskListStub.isSizeCalled);
    }

    /**
     * Tests the behavior when a valid "todo" command is parsed.
     * Verifies that a task is added, the size is checked, and the correct task type is used.
     *
     * @throws ElysiaException if an exception occurs during parsing.
     */
    @Test
    void parseInput_todoCorrectFormat_correctTaskName() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("todo task"));
        assertTrue(taskListStub.isAddTaskCalled);
        assertTrue(taskListStub.isSizeCalled);
        assertInstanceOf(Todo.class, taskListStub.task);
        assertEquals("[T][ ] task",taskListStub.task.toString());
    }

    /**
     * Tests the behavior when an empty "deadline" command is parsed.
     * Verifies that no task is added and no size check is performed.
     *
     * @throws ElysiaException if an exception occurs during parsing.
     */
    @Test
    void parseInput_deadlineEmptyInput_nothingDone() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("deadline"));
        assertFalse(taskListStub.isAddTaskCalled);
        assertFalse(taskListStub.isSizeCalled);
    }

    /**
     * Tests the behavior when a valid "deadline" command with correct date format is parsed.
     * Verifies that a task is added, the size is checked, and the correct task type is used with the formatted date.
     *
     * @throws ElysiaException if an exception occurs during parsing.
     */
    @Test
    void parseInput_deadlineCorrectFormat_correctTaskNameAndDate() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("deadline task /by 2024-03-13"));
        assertTrue(taskListStub.isAddTaskCalled);
        assertTrue(taskListStub.isSizeCalled);
        assertInstanceOf(Deadline.class, taskListStub.task);
        assertEquals("[D][ ] task  (by: Mar 13 2024)",taskListStub.task.toString());
    }

    /**
     * Tests the behavior when a valid "deadline" command with incorrect date format is parsed.
     * Verifies that a task is added, the size is checked, and the task is created with the original date string.
     *
     * @throws ElysiaException if an exception occurs during parsing.
     */
    @Test
    void parseInput_deadlineWrongDateFormat_correctTaskNameAndDatE() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("deadline task /by now"));
        assertTrue(taskListStub.isAddTaskCalled);
        assertTrue(taskListStub.isSizeCalled);
        assertInstanceOf(Deadline.class, taskListStub.task);
        assertEquals("[D][ ] task  (by: now)",taskListStub.task.toString());
    }

    /**
     * Tests the behavior when an empty "event" command is parsed.
     * Verifies that no task is added and no size check is performed.
     *
     * @throws ElysiaException if an exception occurs during parsing.
     */
    @Test
    void parseInput_eventEmptyInput_nothingDone() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("event"));
        assertFalse(taskListStub.isAddTaskCalled);
        assertFalse(taskListStub.isSizeCalled);
    }

    /**
     * Tests the behavior when a valid "event" command is parsed.
     * Verifies that a task is added, the size is checked, and the correct task type is used with the specified time range.
     *
     * @throws ElysiaException if an exception occurs during parsing.
     */
    @Test
    void parseInput_eventCorrectFormat_correctTaskNameAndTime() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("event task /from 1 /to 2"));
        assertTrue(taskListStub.isAddTaskCalled);
        assertTrue(taskListStub.isSizeCalled);
        assertInstanceOf(Event.class, taskListStub.task);
        assertEquals("[E][ ] task  (from: 1  to: 2)",taskListStub.task.toString());
    }

    /**
     * Tests the behavior when an empty "delete" command is parsed.
     * Verifies that no task is deleted and no size check is performed.
     *
     * @throws ElysiaException if an exception occurs during parsing.
     */
    @Test
    void parseInput_deleteNothing_nothingDone() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("delete"));
        assertFalse(taskListStub.isDeleteTaskCalled);
    }

    /**
     * Tests the behavior when a valid "delete" command is parsed.
     * Verifies that a task is deleted and the size is checked.
     *
     * @throws ElysiaException if an exception occurs during parsing.
     */
    @Test
    void parseInput_deleteTask_success() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("delete 1"));
        assertTrue(taskListStub.isDeleteTaskCalled);
        assertTrue(taskListStub.isSizeCalled);
    }

    /**
     * Tests the behavior when a "delete" command with a wrong index is parsed.
     * Verifies that a task is deleted and an exception is handled.
     *
     * @throws ElysiaException if an exception occurs during parsing.
     */
    @Test
    void parseInput_deleteWrongTask_exceptionCaught() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("delete -1"));
        assertTrue(taskListStub.isDeleteTaskCalled);
        assertFalse(taskListStub.isSizeCalled);
    }
}
