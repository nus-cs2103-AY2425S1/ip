package torne.command;

import torne.command.argument.CommandArgument;
import torne.exception.TorneException;
import torne.exception.TorneExecutionException;
import torne.ui.ChatOutput;

import java.util.Map;
import java.util.Objects;

public class Help extends Command {
    private String helpMessage;

    protected Help() {
        super("help", "Show help information.");
    }

    /**
     * Shows the help message. Yes this is very not OOP I know.
     *
     * @param commands List of commands.
     */
    public void showHelp(CommandList commands) throws TorneExecutionException {
        if (helpMessage != null) {
            // has already been generated
            output.writeText(helpMessage);
            return;
        }

        StringBuilder helpMessageBuilder = new StringBuilder();
        helpMessageBuilder.append("usage: [command] [<default-arg>] [<args>]\n");
        helpMessageBuilder.append("The following is a list of Torne commands:\n");

        for (var entry : commands.getCommands().entrySet()) {
            String name = entry.getKey();
            Command cmd = entry.getValue();
            helpMessageBuilder.append("\n").append(ChatOutput.INDENT);
            helpMessageBuilder.append(cmd.getName()).append(" - ").append(cmd.description);
            helpMessageBuilder.append("\n").append(ChatOutput.INDENT);

            // TODO needs to be better lol

            for (CommandArgument arg : cmd.getArgs().getArgList()) {
                if (Objects.equals(arg.getName(), "")) {
                    // default arg
                    helpMessageBuilder.append(String.format(" [<%s>]", arg.getDescriptionShort()));
                    continue;
                }
                helpMessageBuilder.append(String.format(" [/%s <%s>]", arg.getName(), arg.getDescriptionShort()));
            }
        }
        // memoize
        helpMessage = helpMessageBuilder.toString();
        output.writeText(helpMessage);
    }

    @Override
    public void execute(Map<String, String> arguments) throws TorneException {

    }
}
