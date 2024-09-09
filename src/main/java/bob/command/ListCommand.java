package bob.command;

import java.util.ArrayList;

import bob.task.Task;

/**
 * ListCommand class executes list command.
 */
public class ListCommand extends Command {
    private String input;
    private ArrayList<Task> tempRecords;


    /**
     * Constructor to initalise ListCommand
     *
     * @param input
     */
    public ListCommand(String input) {
        super(input);
    }

    @Override
    public String execute(ArrayList<Task> records) {
        return "ListCommand";
    }
}
