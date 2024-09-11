package thebotfather.parser;

import java.util.StringTokenizer;

import thebotfather.command.AddCommand;
import thebotfather.command.Command;
import thebotfather.task.Event;
import thebotfather.task.Task;
import thebotfather.util.TheBotFatherException;
import thebotfather.util.Ui;

/**
 * The {@code EventParser} class provides a method to parse user input tokens
 * to create an {@link Event} task and return an {@link AddCommand} to add the task.
 */
public class EventParser {

    /**
     * Parses the given {@link StringTokenizer} to create an {@link Event} task.
     *
     * @param tokens the {@code StringTokenizer} containing the input to be parsed
     * @param ui the {@code Ui} instance that handles interactions with the user
     * @return an {@code AddCommand} that contains the created {@code Event} task
     * @throws TheBotFatherException if the input tokens are invalid or incomplete
     */
    public static Command parse(StringTokenizer tokens, Ui ui) throws TheBotFatherException {
        Task event = Event.makeEvent(tokens);
        return new AddCommand(event, ui.getEventPrint());
    }
}
