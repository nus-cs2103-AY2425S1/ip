package bibi;

import java.io.IOException;

import bibi.task.TaskList;

/**
 * Represents the object that contains the command to the executed, and their relevant parameters.
 * Command to be executed has differing behaviour depending on the command.
 */
public class Command {
    private String cmd;
    private String args;

    /**
     * Constructs a new Command with specified command and relevant parameters.
     *
     * @param cmd The command to execute.
     * @param args The additional information needed to execute the command.
     */
    public Command(String cmd, String args) {
        this.cmd = cmd;
        this.args = args;
    }

    public String getCmd() {
        return cmd;
    }

    public String getArgs() {
        return args;
    }
    /**
     * Returns a boolean value indicating whether the to exit the program or not.
     *
     * @return isExit
     */
    public boolean isExit() {
        return cmd.equals("bye");
    }

    /**
     * Executes the command based on the command type.
     *
     * @param tasks The list of tasks in the save file.
     * @param ui The Ui instance that is handling the inputs and outputs of the console.
     * @param storage The Storage instance handling modification of the save file.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int index;
        // Preconfigured commands
        switch (cmd) {
        case "bye":
            // Exit
            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                return e.getMessage();
            }
            return "See you soon :3";
        case "list":
            return ui.processCommand(this, tasks, storage, -1);
        case "mark":
            if (!args.matches("\\d+")) {
                return "Please use \"mark <int>\"";
            } else if ((index = Integer.parseInt(args)) - 1 >= tasks.getTaskCount() || index <= 0) {
                return "Invalid task index";
            } else {
                return ui.processCommand(this, tasks, storage, index);
            }
        case "unmark":
            if (!args.matches("\\d+")) {
                return "Please use \"unmark <int>\"";
            } else if ((index = Integer.parseInt(args)) - 1 >= tasks.getTaskCount() || index <= 0) {
                return "Invalid task index";
            } else {
                return ui.processCommand(this, tasks, storage, -1);
            }
        case "todo":
            if (!args.matches(".+")) {
                return "Please use \"todo <description>\"";
            } else {
                return ui.processCommand(this, tasks, storage, -1);
            }
        case "deadline":
            if (!args.matches(".+ /by .+")) {
                return "Please use \"deadline <description> /by <deadline>\"";
            } else {
                return ui.processCommand(this, tasks, storage, -1);
            }
        case "event":
            if (!args.matches(".+ /from .+ /to .+")) {
                return "Please use \"event <description> /from <time> /to <time>\"";
            } else {
                return ui.processCommand(this, tasks, storage, -1);
            }
        case "remove":
            if (!args.matches("\\d+")) {
                return "Please use \"remove <index>\"";
            } else {
                return ui.processCommand(this, tasks, storage, Integer.parseInt(args));
            }
        case "find":
            // No pattern specified
            if (args.isEmpty()) {
                return "Please use \"find <pattern>\"";
            } else {
                return ui.processCommand(this, tasks, storage, -1);
            }
        default:
            return String.format("%s is an unknown command%n", cmd);
        }
    }
}
