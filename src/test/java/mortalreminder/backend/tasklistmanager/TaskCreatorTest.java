package mortalreminder.backend.tasklistmanager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mortalreminder.backend.TaskListStorage;
import mortalreminder.commands.CommandType;
import mortalreminder.errorhandling.MortalReminderException;
import mortalreminder.tasks.Deadline;
import mortalreminder.tasks.Event;
import mortalreminder.tasks.Task;
import mortalreminder.tasks.ToDoStub;

class TaskCreatorTest {
    TaskListStub testTaskListStub = new TaskListStub();
    Task testTask;
    String commandDetails;

    @BeforeEach
    void setUp() throws MortalReminderException {
        TaskListStorage.clearListFile();
    }

    @AfterEach
    void tearDown() throws MortalReminderException {
        TaskListStorage.clearListFile();
    }

    /* Some ideas of Edge Cases were caught by ChatGPT, code was done by myself.
     The setup idea was obtained from:
     https://stackoverflow.com/questions/63819472/how-can-i-use-the-beforeeach-method-for-testing-in-java */

    @Test
    void createTask_validToDo() {
        try {
            commandDetails = "Read Book";
            TaskCreator.createTask(commandDetails, testTaskListStub, CommandType.TODO);
            assertEquals(commandDetails, testTaskListStub.getTaskList().get(0).getDescription());
        } catch (MortalReminderException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void createTask_validDeadline() {
        try {
            commandDetails = "Return Book /by 19-08-2024 1900";
            TaskCreator.createTask(commandDetails, testTaskListStub, CommandType.DEADLINE);
            testTask = new Deadline(commandDetails);
            assertEquals(testTask.getDescription(), testTaskListStub.getTaskList().get(0).getDescription());
        } catch (MortalReminderException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void createTask_validEvent() {
        try {
            commandDetails = "project meeting /from 19-08-2024 1900 /to 19-08-2024 2000";
            TaskCreator.createTask(commandDetails, testTaskListStub, CommandType.EVENT);
            testTask = new Event(commandDetails);
            assertEquals(testTask.getDescription(), testTaskListStub.getTaskList().get(0).getDescription());
        } catch (MortalReminderException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void createTask_invalidTaskType() {
        try {
            TaskCreator.createTask("Blah blah", testTaskListStub, CommandType.UNKNOWN);
            fail();
        } catch (MortalReminderException e) {
            assertEquals(e.getMessage(), "This statement should be unreachable, code has an error!");
        }
    }

    @Test
    void createTask_invalidTaskDescription() {
        try {
            TaskCreator.createTask("", testTaskListStub, CommandType.TODO);
            fail();
        } catch (MortalReminderException e) {
            assertEquals(e.getMessage(), "Description cannot be empty!");
        }
    }

    @Test
    void createTask_invalidDeadlineDate() {
        try {
            TaskCreator.createTask("Return Book /by tmr", testTaskListStub, CommandType.DEADLINE);
            fail();
        } catch (MortalReminderException e) {
            assertEquals("Please enter a valid date in dd-MM-yyyy HHmm (24hr format)!", e.getMessage());
        }
    }

    @Test
    void createTask_invalidEventDate() {
        try {
            TaskCreator.createTask("Return Book /from tmr /to day after", testTaskListStub, CommandType.EVENT);
            fail();
        } catch (MortalReminderException e) {
            assertEquals("Please enter a valid date in dd-MM-yyyy HHmm (24hr format)!", e.getMessage());
        }
    }
}
