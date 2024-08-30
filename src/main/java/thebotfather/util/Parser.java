package thebotfather.util;

import thebotfather.command.*;
import thebotfather.task.Deadline;
import thebotfather.task.Event;
import thebotfather.task.Task;
import thebotfather.task.Todo;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Parser {
    public static Command parse(String completeLine, Ui ui) throws TheBotFatherException {
        StringTokenizer tokens;
        String command = "";
        try {
            tokens = new StringTokenizer(completeLine);
            command = tokens.nextToken();
        } catch (NoSuchElementException e) {
            throw new TheBotFatherException("OOPS!!! I'm sorry, but I don't know what that means :-(.\n" +
                    "\tUse \"bye\" if you want to exit the program");
        }
        switch (CommandList.findCommand(command)) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new PrintTaskListCommand();
            case MARK:
                try {
                    return new MarkCommand(String.valueOf(tokens.nextToken()), true);
                } catch (NoSuchElementException e) {
                    throw new TheBotFatherException("Skill issue: Atleast enter a number.\n" +
                            "\tTo mark a task as done enter \"mark <index>\"");
                }
            case UNMARK:
                try {
                    return new MarkCommand(String.valueOf(tokens.nextToken()), false);
                } catch (NoSuchElementException e) {
                    throw new TheBotFatherException("Skill issue: Atleast enter a number.\n" +
                            "\tTo unmark a task enter \"unmark <index>\"");
                }
            case DELETE:
                try {
                    return new DeleteCommand(String.valueOf(tokens.nextToken()));
                } catch (NoSuchElementException e) {
                    throw new TheBotFatherException("Skill issue: Atleast enter a number.\n" +
                            "\tTo unmark a task enter \"unmark <index>\"");
                }
            case TODO:
                Task todo = Todo.makeTodo(tokens);
                ui.printAddedTodo(todo);
                return new AddCommand(todo);
            case EVENT:
                Task event = Event.makeEvent(tokens);
                ui.printAddedEvent(event);
                return new AddCommand(event);
            case DEADLINE:
                Task deadline = Deadline.makeDeadline(tokens);
                ui.printAddedDeadline(deadline);
                return new AddCommand(deadline);
            default:
                throw new TheBotFatherException("OOPS!!! I'm sorry, but I don't know what that means :-(.\n" +
                        "\tUse \"bye\" if you want to exit the program");
        }
    }
}
