package Tuesday.command;

import Tuesday.task.Task;
import Tuesday.task.ToDo;
import Tuesday.util.Storage;
import Tuesday.util.TuesdayException;
import Tuesday.util.Ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void command_execute_mark() {
        try {
            Command c = Parser.parse("todo readbook");
            c.execute(tasks, ui, storage);

            Command c2 = Parser.parse("mark 1");
            c2.execute(tasks, ui, storage);
            assertEquals("1", Task.taskArrayList.get(0).getDone1());
        } catch (TuesdayException e) {
            System.out.println("e");
        }
    }

    @Test
    public void command_execute_unmark() {
        try {
            Command c = Parser.parse("todo readbook");
            c.execute(tasks, ui, storage);

            Command c2 = Parser.parse("mark 1");
            c2.execute(tasks, ui, storage);

            Command c3 = Parser.parse("unmark 1");
            c3.execute(tasks, ui, storage);

            assertEquals("0", Task.taskArrayList.get(0).getDone1());
        } catch (TuesdayException e) {
            System.out.println("e");
        }
    }
    @Test
    void command_execute_todo_check_datafile() {
        ToDo todo = new ToDo("read book");
        Assertions.assertEquals("[T][ ] read book", todo.toString());
    }
}
