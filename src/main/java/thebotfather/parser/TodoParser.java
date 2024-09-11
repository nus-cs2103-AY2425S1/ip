package thebotfather.parser;

import java.util.StringTokenizer;

import thebotfather.command.AddCommand;
import thebotfather.command.Command;
import thebotfather.task.Task;
import thebotfather.task.Todo;
import thebotfather.util.TheBotFatherException;
import thebotfather.util.Ui;

/**
 * The {@code TodoParser} class provides a method to parse user input tokens
 * to create a {@link Todo} task and return an {@link AddCommand} to add the task.
 */
public class TodoParser {

    /**
     * Parses the given {@link StringTokenizer} to create a {@link Todo} task.
     *
     * @param tokens the {@code StringTokenizer} containing the input to be parsed
     * @param ui     the {@code Ui} instance that handles interactions with the user
     * @return an {@code AddCommand} that contains the created {@code Todo} task
     * @throws TheBotFatherException if the input tokens are invalid or incomplete
     */
    public static Command parse(StringTokenizer tokens, Ui ui) throws TheBotFatherException {
        Task todo = Todo.makeTodo(tokens);
        return new AddCommand(todo, ui.getTodoPrint());
    }
}
