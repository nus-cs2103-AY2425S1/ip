package bob.command;

import java.util.ArrayList;

import bob.task.Task;

/**
 * ListCommand class executes list command.
 */
public class DeadlineCommand extends Command {
    private String input;
    private ArrayList<Task> tempRecords;


    /**
     * Constructor to initalise DeadlineCommand
     *
     * @param input
     */
    public DeadlineCommand(String input) {
        super(input);
    }

    @Override
    public String execute(ArrayList<Task> records) {
        return "DeadlineCommand";
    }
}
