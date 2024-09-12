package mortalreminder.backend.tasklistmanager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mortalreminder.backend.TaskListStorage;
import mortalreminder.commands.CommandType;
import mortalreminder.errorhandling.MortalReminderException;

class TaskCreatorTest {

    @BeforeEach
    void setUp() throws MortalReminderException {
        TaskListStorage.clearListFile();
    }

    @AfterEach
    void tearDown() throws MortalReminderException {
        TaskListStorage.clearListFile();
    }

    // Some ideas of Edge Cases were caught by ChatGPT, code was done by myself.

    @Test
    void createTask_validToDo() {
        try {
            TaskListStub stub = new TaskListStub();
            TaskCreator.createTask("Read Book", stub, CommandType.TODO);
            assertEquals("Read Book", stub.getTaskList().get(0).getDescription());
        } catch (MortalReminderException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void createTask_validDeadline() {
        try {
            TaskListStub stub = new TaskListStub();
            TaskCreator.createTask("Return Book /by 19-08-2024 1900", stub, CommandType.DEADLINE);
            assertEquals("Return Book (by: 19 Aug 2024 7:00 pm)", stub.getTaskList().get(0).getDescription());
        } catch (MortalReminderException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void createTask_validEvent() {
        try {
            TaskListStub stub = new TaskListStub();
            TaskCreator.createTask("project meeting /from 19-08-2024 1900 /to 19-08-2024 2000",
                    stub, CommandType.EVENT);
            assertEquals("project meeting  (from: 19 Aug 2024 7:00 pm, to: 19 Aug 2024 8:00 pm)",
                    stub.getTaskList().get(0).getDescription());
        } catch (MortalReminderException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void createTask_invalidTaskType() {
        try {
            TaskListStub stub = new TaskListStub();
            TaskCreator.createTask("Blah blah", stub, CommandType.UNKNOWN);
            fail();
        } catch (MortalReminderException e) {
            assertEquals(e.getMessage(), "This statement should be unreachable, code has an error!");
        }
    }

    @Test
    void createTask_invalidTaskDescription() {
        try {
            TaskListStub stub = new TaskListStub();
            TaskCreator.createTask("", stub, CommandType.TODO);
            fail();
        } catch (MortalReminderException e) {
            assertEquals(e.getMessage(), "Description cannot be empty!");
        }
    }

    @Test
    void createTask_invalidDeadlineDate() {
        try {
            TaskListStub stub = new TaskListStub();
            TaskCreator.createTask("Return Book /by tmr", stub, CommandType.DEADLINE);
            fail();
        } catch (MortalReminderException e) {
            assertEquals("Please enter a valid date in dd-MM-yyy HHmm (24hr format)!", e.getMessage());
        }
    }

    @Test
    void createTask_invalidEventDate() {
        try {
            TaskListStub stub = new TaskListStub();
            TaskCreator.createTask("Return Book /from tmr /to day after", stub, CommandType.EVENT);
            fail();
        } catch (MortalReminderException e) {
            assertEquals("Please enter a valid date in dd-MM-yyy HHmm (24hr format)!", e.getMessage());
        }
    }
}
