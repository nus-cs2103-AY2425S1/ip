package friendlybot.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import friendlybot.FriendlyBotStub;
import friendlybot.task.Deadline;
import friendlybot.task.Event;
import friendlybot.task.TaskList;
import friendlybot.task.ToDo;

/**
 * A JUnit test for DateCommand.
 * Used GitHub CoPilot to assist with generating test cases for DateCommand.
 */
public class DateCommandTest {
    private FriendlyBotStub friendlyBotStub;
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        friendlyBotStub = new FriendlyBotStub();
        taskList = friendlyBotStub.getTasks();
        taskList.addTask(new ToDo("read book"));
        taskList.addTask(new Deadline("submit report", LocalDate.of(2023, 12, 31)));
        taskList.addTask(new Event("attend conference", LocalDate.of(2023, 12, 31),
                LocalDate.of(2024, 1, 2)));
    }

    /**
     * Tests executing DateCommand with tasks on the given date.
     */
    @Test
    public void testExecuteWithTasksOnDate() {
        Command dateCommand = new DateCommand(LocalDate.of(2023, 12, 31));
        String response = dateCommand.execute(taskList, friendlyBotStub.getUi(), friendlyBotStub.getStorage());

        assertTrue(response.contains("submit report"));
        assertTrue(response.contains("attend conference"));
    }

    /**
     * Tests executing DateCommand with no tasks on the given date.
     */
    @Test
    public void testExecuteWithNoTasksOnDate() {
        Command dateCommand = new DateCommand(LocalDate.of(2024, 1, 3));
        String response = dateCommand.execute(taskList, friendlyBotStub.getUi(), friendlyBotStub.getStorage());

        assertEquals("I couldn't find any tasks on this date!", response);
    }

    /**
     * Tests the empty constructor of DateCommand.
     */
    @Test
    public void testDateCommandEmptyConstructor() {
        DateCommand dateCommand = new DateCommand();
        assertNull(dateCommand.getDate());
    }
}
