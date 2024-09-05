package bob;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import bob.command.AddTaskCommand;
import bob.command.ByeCommand;
import bob.command.Command;
import bob.command.DeleteCommand;
import bob.command.FindCommand;
import bob.command.ListCommand;
import bob.command.MarkCommand;
import bob.command.UnmarkCommand;
import bob.exceptions.EmptyArgumentException;
import bob.exceptions.InvalidInputException;
import bob.exceptions.InvalidTaskNumberException;
import bob.exceptions.MissingArgumentException;
import bob.tasks.Deadline;
import bob.tasks.EventTask;
import bob.tasks.ToDo;


/**
 * Handles the parsing of string into commands and task objects
 */
public class Parser {

    /**
     * Parses the given user input into an appropriate command
     *
     * @param userCommand String of the user command entered
     * @return Command object based on the input from the user
     * @throws EmptyArgumentException If any of the required argument is empty
     * @throws MissingArgumentException If user does not include a required argument in the command
     * @throws InvalidInputException If user inputs an unrecognized command
     * @throws InvalidTaskNumberException If user inputs an invalid Task Number
     */
    public static Command parseCommand(String userCommand)
            throws EmptyArgumentException, MissingArgumentException, InvalidInputException, InvalidTaskNumberException {
        Scanner scanner = new Scanner(userCommand);
        String input = scanner.next();
        Command command = switch (input) {
        case "bye" -> new ByeCommand();
        case "list" -> new ListCommand();
        case "mark" -> new MarkCommand(scanner.nextInt() - 1);
        case "unmark" -> new UnmarkCommand(scanner.nextInt() - 1);
        case "todo" -> new AddTaskCommand(Parser.newToDo(scanner.nextLine().trim()));
        case "deadline" -> new AddTaskCommand(Parser.newDeadline(scanner.nextLine().trim()));
        case "event" -> new AddTaskCommand(Parser.newEvent(scanner.nextLine().trim()));
        case "delete" -> new DeleteCommand(scanner.nextInt() - 1);
        case "find" -> new FindCommand(scanner.nextLine().trim());
        default -> throw new InvalidInputException();
        };
        return command;
    }

    /**
     * Parses the given string into a To Do object
     *
     * @param input String from the user input
     * @return To Do object
     * @throws EmptyArgumentException If the "description" field is empty
     */
    public static ToDo newToDo(String input) throws EmptyArgumentException {
        if (input.isEmpty()) {
            throw new EmptyArgumentException("description", "todo");
        }
        return new ToDo(input);
    }

    /**
     * Parses the given string into a Deadline object
     *
     * @param input String from the user input
     * @return Deadline object
     * @throws EmptyArgumentException If the "description" or by field is empty
     * @throws MissingArgumentException If the "by" field is not specified
     * @throws DateTimeParseException If the inputted date is in the wrong format or invalid
     */
    public static Deadline newDeadline(String input)
            throws EmptyArgumentException, MissingArgumentException, DateTimeParseException {

        if (!input.matches("^\\S.+")) {
            throw new EmptyArgumentException("description", "deadline");
        } else if (!input.matches("^.*/by.*$")) {
            throw new MissingArgumentException("by", "deadline");
        } else if (!input.matches("^\\S.+/by.*$")) {
            throw new EmptyArgumentException("description", "deadline");
        } else if (!input.matches("^\\S.+ /by \\S.+$")) {
            throw new EmptyArgumentException("by", "deadline");
        }

        String[] inputs = input.split("/by", 2);
        String[] dateTime = inputs[1].trim().split(" ");
        if (dateTime.length == 2) {
            return new Deadline(inputs[0].trim(),
                    LocalDate.parse(dateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                    LocalTime.parse(dateTime[1], DateTimeFormatter.ofPattern("HHmm")));
        }
        return new Deadline(inputs[0].trim(), LocalDate.parse(dateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")));
    }

    /**
     * Parses the given string into an EventTask object
     *
     * @param input String from the user input
     * @return EventTask object
     * @throws EmptyArgumentException If the "description", "from" or "to" field is empty
     * @throws MissingArgumentException If the "from" or "to" field is not specified
     * @throws DateTimeParseException If the inputted date is in the wrong format or invalid
     */
    public static EventTask newEvent(String input)
            throws EmptyArgumentException, MissingArgumentException, DateTimeParseException {

        if (!input.matches("^\\S.+")) {
            throw new EmptyArgumentException("description", "event");
        } else if (!input.matches("^.*/from.*$")) {
            throw new MissingArgumentException("from", "event");
        } else if (!input.matches("^\\S.+/from.*$")) {
            throw new EmptyArgumentException("description", "event");
        } else if (!input.matches("^.*/from.*/to.*$")) {
            throw new MissingArgumentException("to", "event");
        } else if (!input.matches("^\\S.+ /from \\S.+/to.*$")) {
            throw new EmptyArgumentException("from", "event");
        } else if (!input.matches("^\\S.+ /from \\S.+/to \\S.+$")) {
            throw new EmptyArgumentException("to", "event");
        }

        String[] inputs = input.split("/from", 2);
        String[] dates = inputs[1].split("/to", 2);
        String[] startDateTime = dates[0].trim().split(" ");
        String[] endDateTime = dates[1].trim().split(" ");

        if (startDateTime.length == 1 && endDateTime.length == 1) {
            return new EventTask(inputs[0].trim(),
                    LocalDate.parse(startDateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                    LocalDate.parse(endDateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")));
        } else if (startDateTime.length == 1 && endDateTime.length == 2) {
            return new EventTask(inputs[0].trim(),
                    LocalDate.parse(startDateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                    LocalDate.parse(endDateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                    LocalTime.parse(endDateTime[1], DateTimeFormatter.ofPattern("HHmm")));
        } else if (startDateTime.length == 2 && endDateTime.length == 1) {
            return new EventTask(inputs[0].trim(),
                    LocalDate.parse(startDateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                    LocalTime.parse(startDateTime[1], DateTimeFormatter.ofPattern("HHmm")),
                    LocalDate.parse(endDateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")));
        }
        return new EventTask(inputs[0].trim(),
                LocalDate.parse(startDateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalTime.parse(startDateTime[1], DateTimeFormatter.ofPattern("HHmm")),
                LocalDate.parse(endDateTime[0], DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalTime.parse(endDateTime[1], DateTimeFormatter.ofPattern("HHmm")));
    }
}
