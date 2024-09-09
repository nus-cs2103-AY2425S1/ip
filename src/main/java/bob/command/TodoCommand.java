package bob.command;

import java.util.ArrayList;

import bob.task.Task;

/**
 * ListCommand class executes list command.
 */
public class TodoCommand extends Command {
    private String input;
    private ArrayList<Task> tempRecords;


    /**
     * Constructor to initalise TodoCommand
     *
     * @param input
     */
    public TodoCommand(String input) {
        super(input);
    }

    @Override
    public String execute(ArrayList<Task> records) {
        return "TodoCommand";
    }
}
