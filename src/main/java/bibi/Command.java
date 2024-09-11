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
     * @param processor The Ui instance that is handling the inputs and outputs of the console.
     * @param storage The Storage instance handling modification of the save file.
     */
    public String execute(TaskList tasks, Processor processor, Storage storage) {
        // Preconfigured commands
        switch (cmd) {
        case "bye":
            // Exit
            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                return e.getMessage();
            }
            return processor.getExitMessage();
        case "list", "mark", "unmark", "todo", "deadline", "event", "remove", "find":
            return processor.processCommand(this, tasks, storage);
        default:
            return String.format("%s is an unknown command%n", cmd);
        }
    }
}
