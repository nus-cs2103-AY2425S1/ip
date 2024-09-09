package bob.command;

import java.util.ArrayList;

import bob.task.Task;

/**
 * ListCommand class executes list command.
 */
public class EventCommand extends Command {
    private String input;
    private ArrayList<Task> tempRecords;


    /**
     * Constructor to initalise EventCommand
     *
     * @param input
     */
    public EventCommand(String input) {
        super(input);
    }

    @Override
    public String execute(ArrayList<Task> records) {
        return "EventCommand";
    }
}
