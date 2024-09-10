package mira;

import mira.command.AddExpenseCommand;
import mira.command.Command;
import mira.command.DeadlineCommand;
import mira.command.DeleteCommand;
import mira.command.EventCommand;
import mira.command.ExitCommand;
import mira.command.FindCommand;
import mira.command.ListCommand;
import mira.command.ListExpenseCommand;
import mira.command.MarkCommand;
import mira.command.TodoCommand;
import mira.command.UnmarkCommand;

/**
 * Represents a Parser class to parse user input.
 */
public class Parser {
    /**
     * Parses the user's input command and returns the corresponding {@link Command} object.
     * <p>
     * The method splits the input string into the command and its arguments,
     * and then maps it to the appropriate command class, such as {@link MarkCommand},
     * {@link UnmarkCommand}, {@link DeadlineCommand}, etc.
     * </p>
     *
     * @param fullCommand The full command string entered by the user.
     * @return The {@link Command} object corresponding to the user's input.
     * @throws MiraException If the command is not recognized or if the command arguments
     *         are in an invalid format.
     */
    public static Command parse(String fullCommand) throws MiraException {
        String[] commandParts = fullCommand.split(" ", 2); // can only split one time
        String command = commandParts[0];
        String arguments = commandParts.length > 1 ? commandParts[1] : "";

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            if (arguments.equals("expenses")) {
                return new ListExpenseCommand();
            }
            return new ListCommand();
        case "mark":
            int markIdx = Integer.parseInt(arguments);
            return new MarkCommand(markIdx);
        case "unmark":
            int unmarkIdx = Integer.parseInt(arguments);
            return new UnmarkCommand(unmarkIdx);
        case "delete":
            int deleteIdx = Integer.parseInt(arguments);
            return new DeleteCommand(deleteIdx);
        case "todo":
            return new TodoCommand(arguments);
        case "deadline":
            String[] deadlineParts = arguments.split("/by", 2);
            if (deadlineParts.length != 2) {
                throw new MiraException("Invalid format. Use: deadline <description> /by <deadline>");
            }
            String deadlineDesc = deadlineParts[0].trim();
            String by = deadlineParts[1].trim();
            return new DeadlineCommand(deadlineDesc, by);
        case "event":
            String[] eventParts = arguments.split("/from|/to");
            if (eventParts.length != 3) {
                throw new MiraException("Invalid format. Use: event <description> /from <start> /to <end>");
            }
            String eventDesc = eventParts[0].trim();
            String from = eventParts[1].trim();
            String to = eventParts[2].trim();
            return new EventCommand(eventDesc, from, to);
        case "expense":
            String[] expenseParts = arguments.split("/category|/amount");
            if (expenseParts.length != 3) {
                throw new MiraException("Invalid format. Use: expense <description> /category <category> "
                        + "/amount <amount>");
            }
            String expenseDesc = expenseParts[0].trim();
            String category = expenseParts[1].trim();
            String amountStr = expenseParts[2].trim();
            double amount = Double.parseDouble(amountStr);
            return new AddExpenseCommand(category, amount, expenseDesc);
        case "find":
            return new FindCommand(arguments);
        default:
            throw new MiraException("I'm sorry, I don't understand that command.");
        }
    }
}
