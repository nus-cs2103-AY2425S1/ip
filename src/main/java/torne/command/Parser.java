package torne.command;

import torne.exception.TorneException;
import torne.exception.TorneInvalidCommandException;
import torne.storage.Storage;
import torne.task.TaskHandler;
import torne.ui.ChatOutput;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    private static final Command EMPTY_COMMAND = new EmptyCommand();
    private final Map<String, Command> commands;
    private final CommandList commandList;

    /**
     * Creates the {@link Parser} with the default {@link TaskHandler}, {@link ChatOutput}, and {@link Storage}
     * that will be used for all commands called by this Parser.
     *
     * @param taskHandler Default {@link TaskHandler}.
     * @param output Default {@link ChatOutput}.
     * @param storage Default {@link Storage}.
     */
    public Parser(TaskHandler taskHandler, ChatOutput output, Storage storage) {
        commandList = new CommandList();
        commands = commandList.getCommands();

        for (Map.Entry<String, Command> pair : commands.entrySet()) {
            pair.getValue().init(taskHandler, output, storage);
        }
    }

    /**
     * Parses the string command and arguments that the user entered and returns a
     * command of type {@link torne.command.Command}.
     * <ul>
     *      <li>If the input is invalid, e.g. the command does not exist, a {@link torne.exception.TorneException}
     *      would be thrown.
     *      <li>If the input is blank, an {@link EmptyCommand} gets returned.
     * </ul>
     * Invalid inputs have their own handling processes.
     *
     * @param input String input line by the user.
     * @throws TorneException When there is an error with the input.
     */
    public Command parse(String input) throws TorneException {
        // empty command - do nothing
        if (input.isBlank()) {
            return EMPTY_COMMAND;
        }

        // Split input by spaces
        String[] parts = input.split("\\s+");

        if (parts.length == 0) {
            // invalid - return empty command
            return EMPTY_COMMAND;
        }

        // get command - first no-space phrase
        String command = parts[0];

        // Command existence check:
        // Check if input is a command, if not, give error message
        if (!commands.containsKey(command)) {
            // Command is not found in COMMANDS
            throw new TorneInvalidCommandException(String.format("Command %s is not valid.", command));
        }

        // now get available arguments based on command
        List<String> availableArgs = commands.get(command).getArgStringList();

        // Now handle the arguments, I "love" REGEX
        // args will be a string, string map
        // This HashMap gets passed to whatever command is found
        HashMap<String, String> argMap = new HashMap<>();

        // remove command from input
        String argsStr = input.substring(command.length());

        // Split string to get arguments + their argument values
        String[] args = argsStr.split("\s(?=/)|$");

        // add a default arg
        if (args.length > 0 && !args[0].isEmpty() && args[0].charAt(0) != '/') {
            // the arg array is always nonEmpty, so, to determine the existence of a default arg,
            // we check if the first arg is nonEmpty does not start with a '\'
            argMap.put("", args[0].trim());
        }

        // go through the args list and add the other args
        for (int i = 1; i < args.length; i++) {
            String argument = args[i];
            // split on first whitespace
            String[] argParts = args[i].split("\\s+", 2);

            // TODO: what to do when only an argument flag provided - presumably that can be expected behavior...
            // For now, raise an error
            if (argParts.length == 1) {
                throw new TorneInvalidCommandException("No value entered for flag " + argParts[0]);
            }

            if (!availableArgs.contains(argParts[0].substring(1))) {
                // argument flag is invalid for the given command
                throw new TorneInvalidCommandException(
                        String.format("Argument flag %s is invalid for command %s", argParts[0], command));
            }

            // add to argMap
            argMap.put(argParts[0].substring(1), argParts[1].trim());

        }

        // TODO TEMP for help - need to exec differently
        if (command.equals("help")) {
            Help c = (Help) commands.get(command);
            // this is so not ok
            c.showHelp(commandList);
        }

        // NOW ON TO ACTUALLY MATCHING THE FUNCS
        Command c = commands.get(command);
        c.execute(argMap);

        return c;
    }
}
