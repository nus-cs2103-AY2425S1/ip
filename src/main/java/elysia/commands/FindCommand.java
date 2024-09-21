package elysia.commands;

import elysia.exceptions.ArgumentFormatException;
import elysia.exceptions.ElysiaException;
import elysia.exceptions.EmptyArgumentException;
import elysia.storage.FileReaderWriter;
import elysia.tasks.TaskList;

import java.util.Objects;

/**
 * Represents a command to search for tasks in the task list based on a keyword.
 * Extends the {@code Command} class and handles the parsing and validation of search arguments.
 */
public class FindCommand extends Command {
    private String[] args;

    /**
     * Constructs a {@code FindCommand} with the specified task list, file reader/writer, and command arguments.
     *
     * @param taskList the task list to search through for matching tasks.
     * @param fileReaderWriter the file reader/writer for saving or loading task data.
     * @param args the command arguments, where the first argument is the command type and the second is the search keyword.
     */
    public FindCommand(TaskList taskList, FileReaderWriter fileReaderWriter, String[] args) {
        super(taskList, fileReaderWriter);
        this.args = args;
    }

    /**
     * Executes the {@code FindCommand} to search for tasks matching the provided keyword.
     * Validates the command arguments, performs the search, and returns the matching tasks.
     *
     * @return a string indicating the results of the search, including matching tasks or a message if no tasks are found.
     * @throws ElysiaException if the command arguments are not formatted correctly or are empty.
     */
    @Override
    public String execute() throws ElysiaException {
        String output = "";

        if (args.length == 1) {
            throw new EmptyArgumentException(args[0]);
        } else if (args.length != 2) {
            throw new ArgumentFormatException(args[0]);
        }

        TaskList searchResults = taskList.searchByKeyword(args[1]);
        if (Objects.equals(searchResults.getSizeAsString(), "0")) {
            output = "I couldn't find anything...";
        } else {
            output = "Here's the tasks matching your search string! " +
                    "I hope you found what you're looking for!\n";
            output += searchResults.toString();
        }

        return output;
    }
}
