package spike.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import spike.commands.AddTaskCommand;
import spike.commands.ByeCommand;
import spike.commands.Command;
import spike.commands.ListCommand;
import spike.exceptions.SpikeException;
import spike.storage.Storage;
import spike.storage.TaskList;
import spike.tasks.ToDo;
import spike.ui.Ui;

public class ParserTest {

    @Test
    public void parseBye_byeInput_success() throws SpikeException {
        String input = "bye";
        assert(Parser.parse(input) instanceof ByeCommand);
    }

    @Test
    public void parseError_wrongInput_exceptionThrows() throws SpikeException {
        String input = "hello";
        Command error = Parser.parse(input);
        assertEquals("Please enter a valid command", error.getCommandType());
    }

    @Test
    public void parseList_success() throws SpikeException {
        String input = "list ";
        assert(Parser.parse(input) instanceof ListCommand);
    }

    @Test
    public void parseTodo_success() throws SpikeException {
        String input = "todo sleep";
        assert(Parser.parse(input) instanceof AddTaskCommand);
    }

    @Test
    public void parseDeadline_correctFormat_success() throws SpikeException {
        String input = "deadline do homework /by 2024-08-28T00:00";
        assert(Parser.parse(input) instanceof AddTaskCommand);
    }

    @Test
    public void parseDeadline_missingAll_exceptionThrown() throws SpikeException {
        String input = "deadline ";
        Command error = Parser.parse(input);
        assertEquals("The description of a deadline cannot be empty.", error.getCommandType());
    }

    @Test
    public void parseDeadline_incorrectDateFormat_exceptionThrown() throws SpikeException {
        String input = "deadline do homework /by 2024-08-28";
        Command error = Parser.parse(input);
        assertEquals("Please enter a deadline with a valid due date and time", error.getCommandType());
    }

    @Test
    public void parseDeadline_missingDescription_exceptionThrown() throws SpikeException {
        String input = "deadline  /by 2024-08-28T00:00";
        Command error = Parser.parse(input);
        assertEquals("Please enter a valid deadline description followed by "
                + "/by yyyy-MM-dd'T'HH:mm:ss <due date, time>", error.getCommandType());
    }

    @Test
    public void parseDeadline_missingDateTime_exceptionThrown() throws SpikeException {
        String input = "deadline do homework /by ";
        Command error = Parser.parse(input);
        assertEquals("Please enter a valid deadline in the right format: "
                + "deadline description /by yyyy-MM-dd'T'HH:mm:ss <due date, time>", error.getCommandType());
    }

    @Test
    public void parseEvent_correctFormat_success() throws SpikeException {
        String input = "event meeting /from 2024-08-28T00:00 /to 2024-08-29T00:00";
        assert(Parser.parse(input) instanceof AddTaskCommand);
    }

    @Test
    public void parseMarkTask_incorrectFormat_exceptionThrown() throws SpikeException {
        String input = "mark ";
        Command error = Parser.parse(input);
        assertEquals("Please enter a valid number", error.getCommandType());
    }

    @Test
    public void parseMarkTask_indexOutOfBounds_exceptionThrown() throws SpikeException {
        Storage storage = new Storage("data/test.txt");
        try {
            TaskList taskList = new TaskList();
            ToDo toDo = new ToDo("homework");
            taskList.addTask(toDo);
            Ui ui = new Ui();
            String input = "mark 5";
            Command command = Parser.parse(input);
            command.execute(taskList, ui, storage);
        } catch (SpikeException e) {
            assertEquals("Please enter a valid task number", e.getMessage());
        } finally {
            storage.clearFile();
        }
    }

    @Test
    public void parseUnmarkTask_incorrectFormat_exceptionThrown() throws SpikeException {
        String input = "unmark ";
        Command error = Parser.parse(input);
        assertEquals("Please enter a valid number", error.getCommandType());
    }

    @Test
    public void parseUnmarkTask_indexOutOfBounds_exceptionThrown() throws SpikeException {
        Storage storage = new Storage("data/test.txt");
        try {
            TaskList taskList = new TaskList();
            ToDo toDo = new ToDo("homework");
            taskList.addTask(toDo);
            Ui ui = new Ui();
            String input = "unmark 5";
            Command command = Parser.parse(input);
            command.execute(taskList, ui, storage);
        } catch (SpikeException e) {
            assertEquals("Please enter a valid task number", e.getMessage());
        } finally {
            storage.clearFile();
        }
    }

    @Test
    public void parseDelete_incorrectFormat_exceptionThrown() throws SpikeException {
        String input = "delete ";
        Command error = Parser.parse(input);
        assertEquals("Please enter a valid number", error.getCommandType());
    }

    @Test
    public void parseDelete_indexOutOfBounds_exceptionThrown() throws SpikeException {
        Storage storage = new Storage("data/test.txt");
        try {
            TaskList taskList = new TaskList();
            ToDo toDo = new ToDo("homework");
            taskList.addTask(toDo);
            Ui ui = new Ui();
            String input = "unmark 5";
            Command command = Parser.parse(input);
            command.execute(taskList, ui, storage);
        } catch (SpikeException e) {
            assertEquals("Please enter a valid task number", e.getMessage());
        } finally {
            storage.clearFile();
        }
    }
}
