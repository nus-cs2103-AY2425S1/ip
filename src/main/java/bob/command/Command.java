package bob.command;

import java.util.ArrayList;

import bob.task.Task;

/**
 * Command executes tasks.
 */
public class Command {

    private String input;
    public Command(String input) {
        this.input = input;
    }

    /**
     * Returns a String representation of the text that users will see upon entering some input.
     *
     * @param record Existing records of all tasks
     * @return
     */
    public String execute(ArrayList<Task> record) {
        return "Please enter a command.";
    }
}
