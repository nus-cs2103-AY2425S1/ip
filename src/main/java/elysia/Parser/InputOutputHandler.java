package elysia.Parser;

import elysia.commands.*;
import elysia.exceptions.ElysiaException;
import elysia.storage.FileReaderWriter;
import elysia.tasks.TaskList;
import elysia.ui.Message;

/**
 * Handles user input and output for the elysia.ui.Elysia application.
 * Manages the parsing of user commands and interactions with the task list.
 */
public class InputOutputHandler {
    TaskList taskList;
    FileReaderWriter fileReaderWriter;

    /**
     * Constructs an InputOutputHandler and initializes the task list and file reader/writer.
     * Loads any previously saved tasks from the file.
     */
    public InputOutputHandler() {
        taskList = new TaskList();
        fileReaderWriter = new FileReaderWriter(taskList);
        String msg = fileReaderWriter.readFile();
        if (!msg.isEmpty()) {
            Message.print(msg);
        }
    }

    /**
     * Parses user input and executes the corresponding command.
     * Supports commands for adding, deleting, marking, unmarking tasks, and more.
     *
     * @param input The user's command as a string.
     * @return {@code true} if the application should continue running; {@code false} if the application should exit.
     * @throws ElysiaException If the input command is unknown.
     * @throws StringIndexOutOfBoundsException If there is an error processing the input string.
     */
    public Command parseInput (String input) throws ElysiaException {
        String[] splitInput = input.split(" ", 2);
        String command = splitInput[0];
        switch(command) {
        case "bye":
            return new ByeCommand(taskList, fileReaderWriter);
        case "list":
            return new ListCommand(taskList, fileReaderWriter);
        case "mark":
            return new MarkCommand(taskList, fileReaderWriter, splitInput);
        case "unmark":
            return new UnmarkCommand(taskList, fileReaderWriter, splitInput);
        case "todo":
            return new TodoCommand(taskList, fileReaderWriter, splitInput);
        case "deadline":
            return new DeadlineCommand(taskList, fileReaderWriter, splitInput);
        case "event":
            return new EventCommand(taskList, fileReaderWriter, splitInput);
        case "delete":
            return new DeleteCommand(taskList, fileReaderWriter, splitInput);
        case "find":
            return new FindCommand(taskList, fileReaderWriter, splitInput);
        default:
            return new UnknownCommand(taskList, fileReaderWriter);
        }
    }
}
