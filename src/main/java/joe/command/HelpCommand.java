package joe.command;

import java.util.ArrayList;
import java.util.List;

import joe.Commands;
import joe.JoeException;
import joe.Storage;
import joe.Ui;
import joe.task.TaskList;

/**
 * The {@code HelpCommand} class extends the {@code Command} class and provides
 * a mechanism for displaying help information for available commands in the application.
 * If a specific command is provided as an argument, it displays usage information for that command;
 * otherwise, it lists all available commands and their usage.
 */
public class HelpCommand extends Command {
    private Commands queryCommand;
    /**
     * Constructs a {@code HelpCommand} object with the specified command to query for help.
     *
     * @param queryCommand the command to query for help; if empty, displays help for all commands
     * @throws JoeException if the provided command is not recognized
     */
    public HelpCommand(String queryCommand) {
        if (queryCommand.isEmpty()) {
            this.queryCommand = null;
        } else {
            try {
                this.queryCommand = Commands.valueOf(queryCommand.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new JoeException(String.format("'%s' is an unknown command", queryCommand));
            }
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JoeException {
        ArrayList<Class<? extends Command>> commandList;
        if (queryCommand != null) {
            Class<? extends Command> c = CommandFactory.getCommandClass(queryCommand);
            commandList = new ArrayList<>(List.of(c));
        } else {
            commandList = CommandFactory.getAllCommandClasses();
        }
        String s;
        for (Class<? extends Command> c : commandList) {
            try {
                s = (String) c.getDeclaredMethod("getHelp").invoke(null);
                System.out.println("\t" + s);
            } catch (Exception e) {
                System.out.println("currently no help for " + c.getName());
            }
        }
    }

    public static String getHelp() {
        return "To access help, try: help {command}";
    }
}
