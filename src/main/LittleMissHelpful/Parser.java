package main.LittleMissHelpful;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.LittleMissHelpful.Exception.InvalidCommandException;
import main.LittleMissHelpful.Exception.InvalidTaskFormatException;
import main.LittleMissHelpful.Exception.TaskNotFoundException;
import main.LittleMissHelpful.Task.Deadline;
import main.LittleMissHelpful.Task.Event;
import main.LittleMissHelpful.Task.Task;
import main.LittleMissHelpful.Task.Todo;
import main.LittleMissHelpful.TaskList;
import main.LittleMissHelpful.Ui;
import main.LittleMissHelpful.Storage;
import main.LittleMissHelpful.Parser;
import main.LittleMissHelpful.Command.Command;
import main.LittleMissHelpful.Command.ExitCommand;
import main.LittleMissHelpful.Command.ListCommand;
import main.LittleMissHelpful.Command.AddTodoCommand;
import main.LittleMissHelpful.Command.AddDeadlineCommand;





public class Parser {
    public static Command parse(String fullCommand) throws InvalidCommandException {
        String[] inputList = fullCommand.split(" ", 2);
        String command = inputList[0].toLowerCase();

        if (inputList.length < 2) {
            if (command.equals("bye")) {
                return new ExitCommand();
            } else if (command.equals("list")) {
                return new ListCommand();
            } else {
                throw new InvalidCommandException("Invalid command. Please provide a valid command.");
            }
        }

        String item = inputList[1];
        switch (command) {
            case "mark":
                return new MarkCommand(item);
            case "unmark":
                return new UnmarkCommand(item);
            case "delete":
                return new DeleteCommand(item);
            case "todo":
                return new AddTodoCommand(item);
            case "deadline":
                return new AddDeadlineCommand(item);
            case "event":
                return new AddEventCommand(item);
            default:
                throw new InvalidCommandException("Invalid command. Please provide a valid command.");
        }
    }
}
