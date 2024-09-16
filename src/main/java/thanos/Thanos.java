package thanos;

import thanos.commands.Command;
import thanos.exceptions.InvalidCommandException;
import thanos.parser.Parser;
import thanos.storage.IStorage;
import thanos.storage.Storage;
import thanos.tasks.TaskList;

/**
 * Represents a task management application that handles user commands and manages tasks.
 */
public class Thanos {
    /**
     * The {@code TaskList} that manages all the tasks.
     */
    private final TaskList taskList;

    /**
     * Constructs a new {@code Thanos} instance with the specified file name for data storage.
     *
     * @param fileName The name of the file to load and save tasks.
     */
    public Thanos(String fileName) {
        IStorage storage = new Storage(fileName);
        this.taskList = new TaskList(storage);
    }

    /**
     * Processes the user input command and returns the corresponding response.
     *
     * @param userInput The input command provided by the user. It is parsed and executed to generate a response.
     * @return A {@code String} representing the response from executing the command.
     */
    public String getResponse(String userInput) {
        try {
            Command command = Parser.parse(userInput);
            return command.execute(taskList);
        } catch (InvalidCommandException e) {
            return e.getMessage();
        }
    }
}
