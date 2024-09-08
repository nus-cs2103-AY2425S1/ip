package drbrown.utils;

import static java.lang.Integer.parseInt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import drbrown.command.AddCommand;
import drbrown.command.Command;
import drbrown.command.DeleteCommand;
import drbrown.command.ExitCommand;
import drbrown.command.FindCommand;
import drbrown.command.ListCommand;
import drbrown.command.MarkCommand;
import drbrown.command.UnmarkCommand;
import drbrown.task.Deadline;
import drbrown.task.Event;
import drbrown.task.Task;
import drbrown.task.Todo;

/**
 * Parses user input and returns the appropriate command for execution.
 * Handles parsing for various commands such as adding tasks, marking tasks,
 * unmarking tasks, listing tasks, deleting tasks, and exiting the application.
 */
public class Parser {

    private static final DateTimeFormatter FILE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Parses the user input and returns a corresponding command.
     *
     * @param userInput The input provided by the user.
     * @return A Command object representing the user's intended action.
     * @throws DrBrownException If the input is invalid or an error occurs while parsing.
     */
    public static Command parse(String userInput) throws DrBrownException {
        String[] inputSplit = userInput.split(" ", 2);
        switch (inputSplit[0]) {
        case "todo":
            try {
                if (inputSplit[1].trim().isEmpty()) {
                    throw new DrBrownException("Great Scott! You can't add a to-do without a "
                            + "description!\n\nUse the format: todo {description}");
                }
                assert inputSplit.length == 2 : "Missing description";
                Task todo = new Todo(false, inputSplit[1].trim());
                return new AddCommand(todo);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DrBrownException("Great Scott! You can't add a to-do without a description!"
                        + "\n\nUse the format: todo {description}");
            }
        case "deadline":
            try {
                if (inputSplit.length == 1) {
                    throw new DrBrownException("Great Scott! You can't add a deadline without a "
                            + "description and date!\nUse the format: deadline {description} /by {date}");
                }
                String[] deadlineSplit = inputSplit[1].split("/by");
                if (deadlineSplit[0].isEmpty()) {
                    throw new DrBrownException("Hello? Hello? Anybody home? Looks like something's missing "
                            + "here!\nUse the format: deadline {description} /by {date}");
                }
                assert deadlineSplit.length == 2 : "Missing date time";
                Task deadline = new Deadline(false, deadlineSplit[0].trim(),
                        LocalDateTime.parse(deadlineSplit[1].trim(), FILE_DATE_TIME_FORMATTER));
                return new AddCommand(deadline);
            } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                throw new DrBrownException("Looks like your Uncle Joey didn't make parole again... "
                        + "and you missed the date! Let's fix that deadline!\nUse the format: deadline "
                        + "{description} /by {MMM dd yyyy HH:mm}");
            }
        case "event":
            try {
                if (inputSplit.length == 1) {
                    throw new DrBrownException("Great Scott! You can't add an event without a description "
                            + "and from and to date!\nUse the format: "
                            + "event {description} /from {date} /to {date}");
                }
                if (!userInput.contains("/from") || !userInput.contains("/to")
                        || userInput.indexOf("/from") > userInput.indexOf("/to")) {
                    throw new DrBrownException("Looks like your Uncle Joey didn't make parole again... "
                            + "and you missed the date! Let's fix that event!\nUse the format: "
                            + "event {description} /from {date} /to {date}");
                }
                String[] eventSplit = inputSplit[1].split("/from | /to");
                assert eventSplit.length == 3 : "Missing date time";
                Task event = new Event(false, eventSplit[0].trim(),
                        LocalDateTime.parse(eventSplit[1].trim(), FILE_DATE_TIME_FORMATTER),
                        LocalDateTime.parse(eventSplit[2].trim(), FILE_DATE_TIME_FORMATTER));
                return new AddCommand(event);
            } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                throw new DrBrownException("Looks like your Uncle Joey didn't make parole again... "
                        + "and you missed the date! Let's fix that event!\nUse the format: "
                        + "event {description} /from {MMM dd yyyy HH:mm} /to {MMM dd yyyy HH:mm}");
            }
        case "mark":
            try {
                if (inputSplit.length == 1) {
                    throw new DrBrownException("Great Scott! You can't complete a task without a count!\n"
                            + "Use the format: mark {count}");
                }
                assert inputSplit.length == 2 : "Missing index";
                int itemMarkIndex = parseInt(inputSplit[1]) - 1;
                return new MarkCommand(itemMarkIndex);
            } catch (NumberFormatException e) {
                throw new DrBrownException("That's not a number! Without the right input, "
                        + "we're never going to get this DeLorean off the ground!");
            }
        case "unmark":
            try {
                if (inputSplit.length == 1) {
                    throw new DrBrownException("Great Scott! You can't go back in time without a count!\n"
                            + "Use the format: unmark {count}");
                }
                assert inputSplit.length == 2 : "Missing index";
                int itemUnmarkIndex = parseInt(inputSplit[1]) - 1;
                return new UnmarkCommand(itemUnmarkIndex);
            } catch (NumberFormatException e) {
                throw new DrBrownException("That's not a number! Without the right input, we're never going to get "
                        + "this DeLorean off the ground!");
            }
        case "list":
            if (inputSplit.length != 1) {
                throw new DrBrownException("Whoa, hold on! You've written more letters than necessary! "
                        + "It's like trying to fit a flux capacitor into a toaster – it just doesn't belong!");
            }
            return new ListCommand();
        case "delete":
            try {
                if (inputSplit.length == 1) {
                    throw new DrBrownException("You can't erase something from history without a count!\n"
                            + "Use the format: delete {count}");
                }
                assert inputSplit.length == 2 : "Missing index";
                int itemDeleteIndex = parseInt(inputSplit[1]) - 1;
                return new DeleteCommand(itemDeleteIndex);
            } catch (NumberFormatException e) {
                throw new DrBrownException("That's not a number! Without the right input, we're never going to get "
                        + "this DeLorean off the ground!");
            }
        case "bye":
            if (inputSplit.length != 1) {
                throw new DrBrownException("Whoa, hold on! You've written more letters than necessary! "
                        + "It's like trying to fit a flux capacitor into a toaster – it just doesn't belong!");
            }
            return new ExitCommand();
        case "find":
            if (inputSplit.length == 1) {
                throw new DrBrownException("Whoa, hold on! You've written too few letters than necessary! "
                        + "It's like trying to fit a flux capacitor into a toaster – it just doesn't belong!");
            }
            assert inputSplit.length == 2 : "Missing keyword";
            return new FindCommand(inputSplit[1]);
        default:
            throw new DrBrownException("I'm from the future, and even I don't know what that means.");
        }
    }
}
