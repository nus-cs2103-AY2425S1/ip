package gray;

import gray.command.Command;
import gray.command_factory.*;

import java.util.List;

public class Parser {

    private static List<CommandFactory> commandFactoryList = List.of(
            new ByeCommandFactory(),
            new ListCommandFactory(),
            new AddTodoCommandFactory(),
            new AddDeadlineCommandFactory(),
            new AddEventCommandFactory(),
            new MarkCommandFactory(),
            new DeleteCommandFactory()
    );

    public static Command parse(String text) throws GrayException {
        for (CommandFactory commandFactory : commandFactoryList) {
            Command command = commandFactory.parse(text);
            if (command != null) return command;
        }
        throw new GrayException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
