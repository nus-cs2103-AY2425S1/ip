package yapper.commands;

import java.util.HashSet;

import yapper.components.Parser;
import yapper.components.Storage;
import yapper.components.TaskList;
import yapper.components.Ui;
import yapper.exceptions.YapperException;

/**
 * A class representing the help command that list all available commands
 */
public class HelpCommand extends Command {
    private CommandList commandList;

    /**
     * Constructor for HelpCommand class
     * @param commandList command list of available commands
     */
    public HelpCommand(CommandList commandList) {
        super();
        this.commandList = commandList;
    }
    @Override
    public String execute(Parser parser, TaskList taskList, Storage storage) throws YapperException {
        StringBuilder listedOutCommands = new StringBuilder();
        HashSet<Command> seenCommands = new HashSet<>();

        for (String commandName : commandList.getAvailableCommands()) {
            Command command = this.commandList.getCommandToExecute(commandName);
            if (seenCommands.contains(command)) {
                continue;
            }
            listedOutCommands
                    .append(String.format("Command: %s\n", commandName))
                    .append(String.format("%s\n", command.commandDescription()))
                    .append(Ui.showLine());
            seenCommands.add(command);
        }
        return listedOutCommands.toString();
    }

    @Override
    public String commandDescription() {
        return "List out available commands, their names and descriptions";
    }

    @Override
    public String toString() {
        return "HelpCommand";
    }
}
