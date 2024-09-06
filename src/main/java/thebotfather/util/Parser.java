package thebotfather.util;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import thebotfather.command.AddCommand;
import thebotfather.command.Command;
import thebotfather.command.CommandList;
import thebotfather.command.DeleteCommand;
import thebotfather.command.ExitCommand;
import thebotfather.command.FindCommand;
import thebotfather.command.MarkCommand;
import thebotfather.command.PrintTaskListCommand;
import thebotfather.task.Deadline;
import thebotfather.task.Event;
import thebotfather.task.Task;
import thebotfather.task.Todo;

/**
 * The Parser class is responsible for interpreting user input and converting it into a Command.
 * It parses the string input to determine which command the user wants to execute
 * and returns the corresponding Command object.
 */
public class Parser {

    /**
     * Parses the user's input and returns the corresponding Command object.
     *
     * @param completeLine The full line of input entered by the user.
     * @param ui           The Ui object used to interact with the user interface.
     * @return The Command object corresponding to the user's input.
     * @throws TheBotFatherException If the input is invalid or does not correspond to any known command.
     */
    public static Command parse(String completeLine, Ui ui) throws TheBotFatherException {

        StringTokenizer tokens;
        String command;

        try {
            tokens = new StringTokenizer(completeLine);
            command = tokens.nextToken();
        } catch (NoSuchElementException e) {
            throw new TheBotFatherException("OOPS!!! I'm sorry, but I don't know what that means :-(.\n"
                    + "Use \"bye\" if you want to exit the program");
        }

        switch (CommandList.findCommand(command)) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new PrintTaskListCommand();
        case MARK:
            try {
                String index = tokens.nextToken();
                if (tokens.hasMoreTokens()) {
                    throw new TheBotFatherException("Just one number please :/\n"
                            + "To mark a task as done enter \"mark <index>\"");
                }
                return new MarkCommand(String.valueOf(index), true);
            } catch (NoSuchElementException e) {
                throw new TheBotFatherException("Skill issue: Atleast enter a number.\n"
                        + "To mark a task as done enter \"mark <index>\"");
            }
        case UNMARK:
            try {
                String index = tokens.nextToken();
                if (tokens.hasMoreTokens()) {
                    throw new TheBotFatherException("Just one number please :/\n"
                            + "To unmark a task enter \"mark <index>\"");
                }
                return new MarkCommand(String.valueOf(index), false);
            } catch (NoSuchElementException e) {
                throw new TheBotFatherException("Skill issue: Atleast enter a number.\n"
                        + "To unmark a task enter \"unmark <index>\"");
            }
        case DELETE:
            try {
                String index = tokens.nextToken();
                if (tokens.hasMoreTokens()) {
                    throw new TheBotFatherException("Just one number please :/\n"
                            + "To delete a task as done enter \"delete <index>\"");
                }
                return new DeleteCommand(String.valueOf(index));
            } catch (NoSuchElementException e) {
                throw new TheBotFatherException("Skill issue: Atleast enter a number.\n"
                        + "To delete a task enter \"delete <index>\"");
            }
        case FIND:
            return new FindCommand(tokens);
        case TODO:
            Task todo = Todo.makeTodo(tokens);
            return new AddCommand(todo, ui.getTodoPrint());
        case EVENT:
            Task event = Event.makeEvent(tokens);
            return new AddCommand(event, ui.getEventPrint());
        case DEADLINE:
            Task deadline = Deadline.makeDeadline(tokens);
            return new AddCommand(deadline, ui.getDeadlinePrint());
        default:
            throw new TheBotFatherException("OOPS!!! I'm sorry, but I don't know what that means :-(.\n"
                    + "Use \"bye\" if you want to exit the program");
        }
    }
}
