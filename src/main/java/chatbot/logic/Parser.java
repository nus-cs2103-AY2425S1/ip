package chatbot.logic;

import java.io.IOException;
import java.time.LocalDateTime;

import chatbot.command.Command;
import chatbot.command.DeadlineCommand;
import chatbot.command.EventCommand;
import chatbot.command.FindCommand;
import chatbot.command.ListCommand;
import chatbot.command.MarkCommand;
import chatbot.command.MessageCommand;
import chatbot.command.RemoveCommand;
import chatbot.command.SortCommand;
import chatbot.command.TodoCommand;
import chatbot.exception.DeadlineArgsException;
import chatbot.exception.EmptyArgsException;
import chatbot.exception.EventArgsException;
import chatbot.exception.InputException;
import chatbot.exception.InvalidCommandException;
import chatbot.exception.MissingDeadlineByException;
import chatbot.exception.MissingEventFromException;
import chatbot.exception.MissingEventToException;
import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.Todo;

/**
 * Represents the parser, converting strings into chatbot instructions
 * Either processes the user's inputs, or processes file inputs into instructions for the chatbot
 */
public class Parser {
    /**
     * Reads the encoded strings present in the data file, and processes it into a suitable Task
     *
     * @param taskString The encode string read from the file
     * @return A Task object that represents the encoded string
     * @throws IOException Exception thrown if string format is unknown
     */
    public static Task parseFileLine(String taskString) throws IOException {
        // TODO error handling for if data file is corrupted
        // splits the taskString into an array of token string based on the | character
        String[] tokens = taskString.split("\\|");
        boolean isDone = Integer.parseInt(tokens[1]) == 1;
        String title = tokens[2];
        return switch (tokens[0]) {
        case "T" -> new Todo(title, isDone);
        case "D" -> new Deadline(title, LocalDateTime.parse(tokens[3]), isDone);
        case "E" -> new Event(title, LocalDateTime.parse(tokens[3]), LocalDateTime.parse(tokens[4]), isDone);
        default -> throw new IOException();
        };
    }

    /**
     * Takes in user instructions, parses it into suitable instructions, and executes them
     *
     * @param s User's instruction string
     * @param taskList TaskList object as encapsulated by the Bobby class
     * @param storage Storage object as encapsulated by the Bobby class
     * @return A Command representing the command entered by the user
     * @throws InputException Exception thrown if instruction is not of the correct format
     */
    public static Command processInput(String s, TaskList taskList, Storage storage) throws InputException {
        // splits the command into 2 strings - The command string, and the arguments string
        String[] inputArr = s.split(" ", 2);
        String commandStr = inputArr[0];

        Command command;

        switch (commandStr) {
        case "bye" -> {
            command = new MessageCommand("Bye. Hope to see you again soon!");
        }
        case "list" -> {
            command = new ListCommand(taskList);
        }
        case "mark" -> {
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            command = new MarkCommand(taskList, Integer.parseInt(inputArr[1]), true);
        }
        case "unmark" -> {
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            command = new MarkCommand(taskList, Integer.parseInt(inputArr[1]), false);
        }
        case "find" -> {
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            command = new FindCommand(taskList, inputArr[1].trim());
        }
        case "delete" -> {
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            command = new RemoveCommand(taskList, Integer.parseInt(inputArr[1]));
        }
        case "todo" -> {
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            command = new TodoCommand(taskList, inputArr[1]);
        }
        case "deadline" -> {
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            if (!inputArr[1].contains("/by ")) {
                throw new MissingDeadlineByException();
            }
            String[] args = inputArr[1].split(" /by ", 2);
            if (args.length <= 1) {
                throw new DeadlineArgsException();
            }
            command = new DeadlineCommand(taskList, args[0], args[1]);
        }
        case "event" -> {
            // Error handling
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }
            if (!inputArr[1].contains("/from ")) {
                throw new MissingEventFromException();
            }
            if (!inputArr[1].contains(" /to")) {
                throw new MissingEventToException();
            }
            String[] args = inputArr[1].split(" /from ", 2);
            if (args.length <= 1) {
                throw new EventArgsException();
            }

            String name = args[0];
            String[] fromTo = args[1].split(" /to ", 2);
            if (fromTo.length <= 1) {
                throw new EventArgsException();
            }

            command = new EventCommand(taskList, name, fromTo[0], fromTo[1]);
        }
        case "sort" -> {
            // Error handling
            if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
                throw new EmptyArgsException();
            }

            String arg = inputArr[1].trim().toLowerCase();
            command = new SortCommand(taskList, arg);
        }
        default -> throw new InvalidCommandException();
        }

        storage.writeToFile(taskList);
        return command;
    }
}
