package mortalreminder.backend.tasklistmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mortalreminder.backend.TaskListStorage;
import mortalreminder.commands.CommandType;
import mortalreminder.errorhandling.MortalReminderException;
import mortalreminder.tasks.ToDoStub;

public class TaskEditorTest {
    TaskListStub taskListStub;
    String testIndexString;
    ToDoStub toDoStub;

    @BeforeEach
    public void setUp() throws MortalReminderException {
        TaskListStorage.clearListFile();
        taskListStub = new TaskListStub();
        toDoStub = new ToDoStub("Read Book");
        taskListStub.addTask(toDoStub);
        testIndexString = "1";
    }

    @AfterEach
    public void tearDown() throws MortalReminderException {
        TaskListStorage.clearListFile();
    }

    @Test
    public void executeMarkOrUnmark_successfulMark() {
        try {
            TaskEditor.executeMarkOrUnmark(testIndexString, taskListStub, CommandType.MARK);
            assertTrue(taskListStub.getTask(0).getIsDone());
        } catch (MortalReminderException e) {
            fail();
        }
    }

    @Test
    public void executeMarkOrUnmark_failedMark() throws MortalReminderException {
        try {
            taskListStub.getTask(0).markDone();
            TaskEditor.executeMarkOrUnmark(testIndexString, taskListStub, CommandType.MARK);
            fail();
        } catch (MortalReminderException e) {
            assertTrue(taskListStub.getTask(0).getIsDone());
            assertEquals(e.getMessage(), MortalReminderException.getAlreadyMarkedErrorMessage());
        }
    }

    @Test
    public void executeMarkOrUnmark_successfulUnmark() {
        try {
            taskListStub.getTask(0).markDone();
            TaskEditor.executeMarkOrUnmark(testIndexString, taskListStub, CommandType.UNMARK);
            assertFalse(taskListStub.getTask(0).getIsDone());
        } catch (MortalReminderException e) {
            fail();
        }
    }

    @Test
    public void executeMarkOrUnmark_failedUnmark() throws MortalReminderException {
        try {
            TaskEditor.executeMarkOrUnmark(testIndexString, taskListStub, CommandType.UNMARK);
            fail();
        } catch (MortalReminderException e) {
            assertFalse(taskListStub.getTask(0).getIsDone());
            assertEquals(e.getMessage(), MortalReminderException.getAlreadyNotMarkedErrorMessage());
        }
    }

    @Test
    public void executeMarkOrUnmark_invalidCommandType() {
        try {
            TaskEditor.executeMarkOrUnmark(testIndexString, taskListStub, CommandType.UNKNOWN);
        } catch (MortalReminderException e) {
            assertEquals(e.getMessage(), MortalReminderException.getUnreachableCodeErrorMessage());
        }
    }

    @Test
    public void executeMarkOrUnmark_invalidIndex() {
        try {
            testIndexString = "2";
            TaskEditor.executeMarkOrUnmark(testIndexString, taskListStub, CommandType.MARK);
            fail();
        } catch (MortalReminderException e) {
            assertEquals(e.getMessage(), "Task id is invalid!");
        }
    }

    @Test
    public void executeMarkOrUnmark_invalidNumberFormat() {
        try {
            testIndexString = "a";
            TaskEditor.executeMarkOrUnmark(testIndexString, taskListStub, CommandType.MARK);
            fail();
        } catch (MortalReminderException e) {
            assertEquals(e.getMessage(), MortalReminderException.getInvalidNumberFormatErrorMessage());
        }
    }
}
