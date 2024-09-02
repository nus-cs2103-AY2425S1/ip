package friendlybot.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import friendlybot.FriendlyBotStub;
import friendlybot.task.Deadline;
import friendlybot.task.Event;
import friendlybot.task.ToDo;

/**
 * A JUnit test for AddCommand.
 */
public class AddCommandTest {
    /**
     * Tests adding a new Event.
     */
    @Test
    public void testAddEvent() {
        LocalDate from = LocalDate.of(2024, 8, 26);
        LocalDate to = LocalDate.of(2024, 9, 9);
        FriendlyBotStub friendlyBotStub = new FriendlyBotStub();
        Command cmd = new AddCommand("event", "test task", from, to);
        cmd.execute(friendlyBotStub.getTasks(), friendlyBotStub.getUi(), friendlyBotStub.getStorage());
        assertEquals(1, friendlyBotStub.getTasks().getNumTasks());
        assertInstanceOf(Event.class, friendlyBotStub.getTasks().getTask(1));
    }

    /**
     * Tests adding a new Deadline.
     */
    @Test
    public void testAddDeadline() {
        LocalDate by = LocalDate.of(2024, 8, 26);
        FriendlyBotStub friendlyBotStub = new FriendlyBotStub();
        Command cmd = new AddCommand("deadline", "test task", by);
        cmd.execute(friendlyBotStub.getTasks(), friendlyBotStub.getUi(), friendlyBotStub.getStorage());
        assertEquals(1, friendlyBotStub.getTasks().getNumTasks());
        assertInstanceOf(Deadline.class, friendlyBotStub.getTasks().getTask(1));
    }

    /**
     * Tests adding a new ToDo.
     */
    @Test
    public void testAddToDo() {
        FriendlyBotStub friendlyBotStub = new FriendlyBotStub();
        Command cmd = new AddCommand("todo", "test task");
        cmd.execute(friendlyBotStub.getTasks(), friendlyBotStub.getUi(), friendlyBotStub.getStorage());
        assertEquals(1, friendlyBotStub.getTasks().getNumTasks());
        assertInstanceOf(ToDo.class, friendlyBotStub.getTasks().getTask(1));
    }
}
