package bob.command;

import java.util.ArrayList;

import bob.task.Task;

/**
 * ListCommand class executes list command.
 */
public class FindCommand extends Command {
    private String input;
    private ArrayList<Task> tempRecords;


    /**
     * Constructor to initalise FindCommand
     *
     * @param input
     */
    public FindCommand(String input) {
        super(input);
    }

    @Override
    public String execute(ArrayList<Task> records) {
        return "FindCommand";
    }
}
