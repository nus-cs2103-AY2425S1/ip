package samson;

import samson.command.Command;
import samson.command.ExitCommand;
import samson.command.ListCommand;
import samson.command.MarkCommand;
import samson.command.UnmarkCommand;
import samson.command.DeleteCommand;
import samson.command.AddCommand;
import samson.command.FindCommand;


import samson.task.Deadline;
import samson.task.Event;
import samson.task.ToDo;

/**
 * The  class is responsible for interpreting user input and converting
 * it into executable commands. It parses the input string and returns the corresponding
 * <code> Command </code> object, which can be executed to perform the desired action.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding <code> Command </code> object.
     *
     * @param userInput The input string entered by the user.
     * @return The Command object that corresponds to the user's input.
     * @throws SamException If the input is invalid or incomplete.
     */
    public static Command parse(String userInput) throws SamException {
        String[] words = userInput.split(" ", 2);
        String commandWord = words[0];

        switch (commandWord) {

        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            if (words.length < 2) {
                throw new SamException("Please specify the task number to mark.");
            }
            try {
                int markIndex = Integer.parseInt(words[1].trim());
                return new MarkCommand(markIndex);
            } catch (NumberFormatException e) {
                throw new SamException("Please provide a valid task number to mark.");
            }
        case "unmark":
            if (words.length < 2) {
                throw new SamException("Please specify the task number to unmark.");
            }
            try {
                int unmarkIndex = Integer.parseInt(words[1].trim());
                return new UnmarkCommand(unmarkIndex);
            } catch (NumberFormatException e) {
                throw new SamException("Please provide a valid task number to unmark.");
            }
        case "delete":
            if (words.length < 2) {
                throw new SamException("Please specify the task number to delete.");
            }
            try {
                int deleteIndex = Integer.parseInt(words[1].trim());
                return new DeleteCommand(deleteIndex);
            } catch (NumberFormatException e) {
                throw new SamException("Please provide a valid task number to delete.");
            }
        case "todo":
            if (words.length < 2 || words[1].trim().isEmpty()) {
                throw new SamException("Please provide your description!!!! \n" +
                        "Example call: todo your_task");
            }
            return new AddCommand(new ToDo(words[1].trim()));
        case "deadline":
            if (words.length < 2 || words[1].trim().isEmpty()) {
                throw new SamException("Please provide the description and deadline of your task \n " +
                        "Example call: deadline your_task /by yyyy-MM-dd HHmm");
            }
            String[] deadlineParts = words[1].split(" /by ");
            if (deadlineParts.length < 2) {
                throw new SamException("Please provide the deadline using /by.");
            }
            String deadlineDescription = deadlineParts[0].trim();
            String by = deadlineParts[1].trim();
            return new AddCommand(new Deadline(deadlineDescription, by));
        case "event":
            if (words.length < 2 || words[1].trim().isEmpty()) {
                throw new SamException("The description, start, and end time of an event cannot be empty. \n " +
                        "Example call: event your_event /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
            }
            String[] eventParts = words[1].split(" /from | /to ");
            if (eventParts.length < 3) {
                throw new SamException("Please provide the event timings using /from and /to.");
            }
            String eventDescription = eventParts[0].trim();
            String from = eventParts[1].trim();
            String to = eventParts[2].trim();
            return new AddCommand(new Event(eventDescription, from, to));
        case "find":
            if (words.length < 2 || words[1].trim().isEmpty()) {
                throw new SamException("Please provide a keyword to search for. \n" +
                        "Example call: find your_task ");
            }
            return new FindCommand(words[1].trim());
        default:
            throw new SamException("I'm sorry, but I don't know what that means :-( \n " +
                    "Kindly provide the tasks starting with 'todo', 'event', 'deadline'!!");
        }
    }
}
