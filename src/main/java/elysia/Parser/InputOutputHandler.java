package elysia.Parser;

import elysia.commands.*;
import elysia.exceptions.ElysiaException;
import elysia.storage.FileReaderWriter;
import elysia.tasks.TaskList;

/**
 * Handles user input and output for the Elysia application.
 * This class is responsible for parsing user commands and interacting
 * with the task list and file system through various command objects.
 */
public class InputOutputHandler {
    TaskList taskList;
    FileReaderWriter fileReaderWriter;

    /**
     * Constructs an InputOutputHandler and initializes the task list.
     * This method sets up the {@code TaskList} but does not immediately
     * interact with the file storage.
     */
    public InputOutputHandler() {
        taskList = new TaskList();
    }

    /**
     * Reads tasks from the file and returns any saved tasks as a message.
     * Initializes the {@code FileReaderWriter} object and reads from the task file.
     *
     * @return a string message representing the tasks read from the file.
     */
    public String fileMessage() {
        fileReaderWriter = new FileReaderWriter(taskList);
        return fileReaderWriter.readFile();
    }

    /**
     * Parses the user input and returns the corresponding command.
     * This method processes the user input and maps it to the appropriate
     * command (such as adding, deleting, or marking tasks). If the command
     * is unrecognized, it returns an {@code UnknownCommand}.
     *
     * @param input the raw input string provided by the user.
     * @return a {@code Command} object corresponding to the parsed user input.
     * @throws ElysiaException if there is an error while parsing the command.
     */
    public Command parseInput(String input) throws ElysiaException {
        String[] splitInput = input.split(" ", 2);
        String command = splitInput[0];
        switch (command) {
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
