import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import commands.Command;
import exceptions.InvalidDateException;
import exceptions.InvalidTaskException;
import io.Parser;
import storage.Storage;
import task.TaskList;

public class CommandTest {

    private static final String STORAGE_PATH = "src/main/resource/data/blitz.txt";

    @Test
    public void executeTodoWithoutDescription() throws InvalidTaskException {
        try {
            TaskList taskList = new TaskList(Storage.createStorage(STORAGE_PATH));
            Command addTaskCommand = Parser.inputToCommand("todo");
            assertEquals("Wah, no description then I record what?", addTaskCommand.execute(taskList));
        } catch (InvalidDateException e) {
            // This block will never run, date from storage always has the right format
        }
    }

    @Test
    public void executeEventWithoutDescription() throws InvalidTaskException {
        try {
            TaskList taskList = new TaskList(Storage.createStorage(STORAGE_PATH));
            Command addTaskCommand = Parser.inputToCommand("event /from 10/10/2019 /to 11/10/2019");
            assertEquals("Wah, no description then I record what?", addTaskCommand.execute(taskList));
        } catch (InvalidDateException e) {
            // This block will never run, date from storage always has the right format
        }
    }

    @Test
    public void executeDeleteNonExistentIndex() throws InvalidTaskException {
        try {
            TaskList taskList = new TaskList(Storage.createStorage(STORAGE_PATH));
            Command deleteCommand = Parser.inputToCommand("delete 10000");
            assertEquals("No valid index was given!!", deleteCommand.execute(taskList));
        } catch (InvalidDateException e) {
            // This block will never run, date from storage always has the right format
        }
    }

    @Test
    public void executeFilterByTagsWithNonExistentTag() throws InvalidTaskException {
        try {
            TaskList taskList = new TaskList(Storage.createStorage(STORAGE_PATH));
            Command fitlerByTagsCommand = Parser.inputToCommand("find -t urgent");
            assertEquals("You are finding a tag that doesn't exist!!", fitlerByTagsCommand.execute(taskList));
        } catch (InvalidDateException e) {
            // This block will never run, date from storage always has the right format
        }
    }

    @Test
    public void executeExit() throws InvalidTaskException {
        try {
            TaskList taskList = new TaskList(Storage.createStorage(STORAGE_PATH));
            Command exitCommand = Parser.inputToCommand("bye");
            assertEquals("Till we meet again, GOODBYE", exitCommand.execute(taskList));
        } catch (InvalidDateException e) {
            // This block will never run, date from storage always has the right format
        }
    }

    @Test
    public void executeInvalidTask() {
        assertThrows(InvalidTaskException.class , () -> Parser.inputToCommand("invalidTask"));
    }
}
