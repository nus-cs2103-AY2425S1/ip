package tuesday.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tuesday.task.Deadline;
import tuesday.task.Task;
import tuesday.task.ToDo;
import tuesday.util.Storage;
import tuesday.util.TuesdayException;
import tuesday.util.Ui;

public class CommandTest {
    private Task tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage("src/main/data/tuesday.txt");
        tasks = new Task(storage);
        ui = new Ui();
    }

    @Test
    public void commandExecuteMark() {
        try {
            Command c = Parser.parse("todo readbook");
            c.execute(tasks, ui, storage);

            Command c2 = Parser.parse("mark 1");
            c2.execute(tasks, ui, storage);
            assertEquals("1", Task.getTaskArrayList().get(0).getDone1());
        } catch (TuesdayException e) {
            System.out.println("e");
        }
    }

    @Test
    public void commandExecuteUnmark() {
        try {
            Command c = Parser.parse("todo readbook");
            c.execute(tasks, ui, storage);

            Command c2 = Parser.parse("mark 1");
            c2.execute(tasks, ui, storage);

            Command c3 = Parser.parse("unmark 1");
            c3.execute(tasks, ui, storage);

            assertEquals("0", Task.getTaskArrayList().get(0).getDone1());
        } catch (TuesdayException e) {
            System.out.println("e");
        }
    }

    @Test
    public void commandExecuteCheckDatafileForTodo() {
        ToDo todo = new ToDo("read book");
        Assertions.assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void commandExecuteCheckDatafileForDeadline() {
        Deadline deadline = new Deadline("finish reading book", "5pm");
        Assertions.assertEquals("[D][ ] finish reading book (by: 5pm)", deadline.toString());
    }
}
