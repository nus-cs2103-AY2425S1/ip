package elysia.ui;

import elysia.tasks.Deadline;
import elysia.tasks.Event;
import elysia.tasks.Todo;
import elysia.tasks.TaskListStub;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;


public class InputOutputHandlerTest {
    InputOutputHandler inputOutputHandler;
    TaskListStub taskListStub;
    FileReaderWriterStub fileReaderWriterStub;

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

    @Test
    void testParseInputBye() throws ElysiaException {
        assertFalse(inputOutputHandler.parseInput("bye"));
        assertTrue(fileReaderWriterStub.createFileCalled);
        assertTrue(fileReaderWriterStub.writeFileCalled);
    }

    @Test
    void testParseInputList() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("list"));
        assertTrue(taskListStub.isToStringCalled);
    }

    @Test
    void parseInput_markCorrectTask_success() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("mark 1"));
        assertEquals(1, taskListStub.taskNumber);
        assertTrue(taskListStub.isPrintTaskCalled);
    }

    @Test
    void parseInput_markWrongTask_exceptionCaught() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("mark -1"));
        assertFalse(taskListStub.isPrintTaskCalled);
    }

    @Test
    void parseInput_unmarkCorrectTask_success() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput(("unmark 1")));
        assertEquals(1, taskListStub.taskNumber);
        assertTrue(taskListStub.isPrintTaskCalled);
    }

    @Test
    void parseInput_unmarkWrongTask_exceptionCaught() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("mark -1"));
        assertFalse(taskListStub.isPrintTaskCalled);
    }

    @Test
    void parseInput_todoEmptyInput_nothingDone() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("todo"));
        assertFalse(taskListStub.isAddTaskCalled);
        assertFalse(taskListStub.isSizeCalled);
    }

    @Test
    void parseInput_todoCorrectFormat_correctTaskName() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("todo task"));
        assertTrue(taskListStub.isAddTaskCalled);
        assertTrue(taskListStub.isSizeCalled);
        assertInstanceOf(Todo.class, taskListStub.task);
        assertEquals("[T][ ] task",taskListStub.task.toString());
    }

    @Test
    void parseInput_deadlineEmptyInput_nothingDone() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("deadline"));
        assertFalse(taskListStub.isAddTaskCalled);
        assertFalse(taskListStub.isSizeCalled);
    }

    @Test
    void parseInput_deadlineCorrectFormat_correctTaskNameAndDate() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("deadline task /by 2024-03-13"));
        assertTrue(taskListStub.isAddTaskCalled);
        assertTrue(taskListStub.isSizeCalled);
        assertInstanceOf(Deadline.class, taskListStub.task);
        assertEquals("[D][ ] task  (by: Mar 13 2024)",taskListStub.task.toString());
    }

    @Test
    void parseInput_deadlineWrongDateFormat_correctTaskNameAndDatE() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("deadline task /by now"));
        assertTrue(taskListStub.isAddTaskCalled);
        assertTrue(taskListStub.isSizeCalled);
        assertInstanceOf(Deadline.class, taskListStub.task);
        assertEquals("[D][ ] task  (by: now)",taskListStub.task.toString());
    }

    @Test
    void parseInput_eventEmptyInput_nothingDone() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("event"));
        assertFalse(taskListStub.isAddTaskCalled);
        assertFalse(taskListStub.isSizeCalled);
    }

    @Test
    void parseInput_eventCorrectFormat_correctTaskNameAndTime() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("event task /from 1 /to 2"));
        assertTrue(taskListStub.isAddTaskCalled);
        assertTrue(taskListStub.isSizeCalled);
        assertInstanceOf(Event.class, taskListStub.task);
        assertEquals("[E][ ] task  (from: 1  to: 2)",taskListStub.task.toString());
    }

    @Test
    void parseInput_deleteNothing_nothingDone() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("delete"));
        assertFalse(taskListStub.isDeleteTaskCalled);
    }

    @Test
    void parseInput_deleteTask_success() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("delete 1"));
        assertTrue(taskListStub.isDeleteTaskCalled);
        assertTrue(taskListStub.isSizeCalled);
    }

    @Test
    void parseInput_deleteWrongTask_exceptionCaught() throws ElysiaException {
        assertTrue(inputOutputHandler.parseInput("delete -1"));
        assertTrue(taskListStub.isDeleteTaskCalled);
        assertFalse(taskListStub.isSizeCalled);
    }
}
