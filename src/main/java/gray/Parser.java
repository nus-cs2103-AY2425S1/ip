package gray;

import java.util.List;

import gray.command.Command;
import gray.command.factory.AddDeadlineCommandFactory;
import gray.command.factory.AddEventCommandFactory;
import gray.command.factory.AddTodoCommandFactory;
import gray.command.factory.ByeCommandFactory;
import gray.command.factory.CommandFactory;
import gray.command.factory.DeleteCommandFactory;
import gray.command.factory.FindCommandFactory;
import gray.command.factory.HelpCommandFactory;
import gray.command.factory.ListCommandFactory;
import gray.command.factory.MarkCommandFactory;

/**
 * The parser that parses text to commands for the chatbot.
 */
public class Parser {

    private static List<CommandFactory> commandFactoryList = List.of(
            new ByeCommandFactory(),
            new ListCommandFactory(),
            new AddTodoCommandFactory(),
            new AddDeadlineCommandFactory(),
            new AddEventCommandFactory(),
            new MarkCommandFactory(),
            new DeleteCommandFactory(),
            new FindCommandFactory(),
            new HelpCommandFactory()
    );

    /**
     * Attempts to parse text from the list of command factories,
     * and returns the associated command.
     *
     * @param text
     * @return Command
     * @throws GrayException
     */
    public static Command parse(String text) throws GrayException {
        for (CommandFactory commandFactory : commandFactoryList) {
            Command command = commandFactory.parse(text);
            if (command != null) {
                return command;
            }
        }
        throw new GrayException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
