package topaz.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import topaz.main.Storage;
import topaz.main.TaskList;
import topaz.task.Deadline;
import topaz.task.Event;
import topaz.task.Task;
import topaz.task.Todo;
import topaz.ui.Ui;
public class CreateCommandTest {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage("");
    }

    @Test
    void testAddTodo() {
        CreateCommand command = new CreateCommand("todo", "todo read book");
        command.execute(taskList, ui, storage);

        assertEquals(1, taskList.getSize());
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(new Todo("read book"));
        assertEquals(new TaskList(expected), taskList, "Error in create todo command");
    }

    @Test
    void testCreateDeadline() {
        CreateCommand command = new CreateCommand("deadline", "deadline submit assignment /by 2024-08-30 18:00");
        command.execute(taskList, ui, storage);

        assertEquals(1, taskList.getSize());
        ArrayList<Task> expected = new ArrayList<>();
        LocalDateTime by = LocalDateTime.of(2024, 8, 30, 18, 00);
        expected.add(new Deadline("submit assignment", by));
        assertEquals(new TaskList(expected), taskList, "Error in create deadline command");
    }

    @Test
    void testCreateEvent() {
        CreateCommand command = new CreateCommand("event",
                "event team meeting /from 2024-08-30 09:00 /to 2024-08-30 11:00");
        command.execute(taskList, ui, storage);
        assertEquals(1, taskList.getSize());
        ArrayList<Task> expected = new ArrayList<>();
        LocalDateTime from = LocalDateTime.of(2024, 8, 30, 9, 00);
        LocalDateTime to = LocalDateTime.of(2024, 8, 30, 11, 00);
        expected.add(new Event("team meeting", from, to));
        assertEquals(new TaskList(expected), taskList);
    }
}
