package impl.ui;

import impl.interfaces.Deadlines;
import impl.interfaces.Events;
import impl.interfaces.ToDos;
import impl.storage.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestParser {
    TaskList list = new TaskList();
    Parser parser = new Parser(list);
    @Test
    public void check_command_working() throws Exception {
        // Command checking for todo
        assertEquals(Parser.Command.Todo, Parser.checkCommand("todo Task1",list.size()));

        // Command checking for Deadline
        assertEquals(Parser.Command.Deadline, Parser.checkCommand("deadline Task2 /by Sunday",list.size()));

        // Command checking for Event
        assertEquals(Parser.Command.Event, Parser.checkCommand("event Task3 /from Sunday /to Monday",list.size()));

        // Command checking for List
        assertEquals(Parser.Command.List, Parser.checkCommand("list",list.size()));

        // Command checking for Mark
        assertEquals(Parser.Command.Mark, Parser.checkCommand("mark 1",1));

        // Command checking for Unmark
        assertEquals(Parser.Command.Unmark, Parser.checkCommand("unmark 1",1));

        // Command checking for Delete
        assertEquals(Parser.Command.Delete, Parser.checkCommand("delete 1",1));
    }
    @Test
    public void check_handle_input_working() throws Exception {
        TaskList check = new TaskList();
        parser.handleInput("todo Task1");
        check.add(new ToDos("Task1"));
        // Checking handling input for todo
        assertEquals(check.saveTasks(), list.saveTasks());

        parser.handleInput("deadline Task2 /by Sunday");
        check.add(new Deadlines("Task2", "Sunday"));
        // Command checking for Deadline
        assertEquals(check.saveTasks(), list.saveTasks());

        // Command checking for Event
        parser.handleInput("event Task3 /from Sunday /to Monday");
        check.add(new Events("Task3", "Monday", "Sunday"));
        // Command checking for Events
        assertEquals(check.saveTasks(), list.saveTasks());
    }


}
