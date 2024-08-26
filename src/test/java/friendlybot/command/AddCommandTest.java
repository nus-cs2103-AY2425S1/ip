package friendlybot.command;

import friendlybot.FriendlyBotStub;
import friendlybot.task.Deadline;
import friendlybot.task.Event;
import friendlybot.task.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class AddCommandTest {
    @Test
    public void testAddEvent() {
        LocalDate from = LocalDate.of(2024,8,26);
        LocalDate to = LocalDate.of(2024,9,9);
        FriendlyBotStub friendlyBotStub = new FriendlyBotStub();
        Command cmd = new AddCommand("event", "test task", from, to);
        cmd.execute(friendlyBotStub.tasks, friendlyBotStub.ui, friendlyBotStub.storage);
        assertEquals(1, friendlyBotStub.tasks.getNumTasks());
        assertInstanceOf(Event.class, friendlyBotStub.tasks.getTask(1));
    }

    @Test
    public void testAddDeadline() {
        LocalDate by = LocalDate.of(2024,8,26);
        FriendlyBotStub friendlyBotStub = new FriendlyBotStub();
        Command cmd = new AddCommand("deadline", "test task", by);
        cmd.execute(friendlyBotStub.tasks, friendlyBotStub.ui, friendlyBotStub.storage);
        assertEquals(1, friendlyBotStub.tasks.getNumTasks());
        assertInstanceOf(Deadline.class, friendlyBotStub.tasks.getTask(1));
    }

    @Test
    public void testAddToDo() {
        FriendlyBotStub friendlyBotStub = new FriendlyBotStub();
        Command cmd = new AddCommand("todo", "test task");
        cmd.execute(friendlyBotStub.tasks, friendlyBotStub.ui, friendlyBotStub.storage);
        assertEquals(1, friendlyBotStub.tasks.getNumTasks());
        assertInstanceOf(ToDo.class, friendlyBotStub.tasks.getTask(1));
    }
}
