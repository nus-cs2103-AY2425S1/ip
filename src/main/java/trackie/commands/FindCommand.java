package trackie.commands;

import java.util.ArrayList;

import trackie.storage.Storage;
import trackie.storage.TaskList;
import trackie.tasks.Task;
import trackie.ui.TrackieException;

/**
 * Represents a command to find relevant tasks based on a query String.
 */
public class FindCommand extends Command {
    private String[] arguments;
    private int ptr = 1;
    private StringBuilder retriever = new StringBuilder();

    public FindCommand(String[] arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the Find Command.
     *
     * @param tasklist the TaskList to operate on
     * @param storage the storage system to interact with
     * @return a String representation of all the commands that match the query.
     */
    @Override
    public String execute(TaskList tasklist, Storage storage) {
        try {
            if (arguments.length == 1) {
                throw new TrackieException("Please provide a valid query to find!");
            }

            if (tasklist.isEmpty()) {
                throw new TrackieException("Nothing to find =/");
            }

            while (ptr < arguments.length) {
                retriever.append(arguments[ptr]).append(" ");
                ptr++;
            }

            String query = retriever.substring(0, retriever.length() - 1);

            ArrayList<Task> results = tasklist.findTasks(query);
            if (results.isEmpty()) {
                return "No tasks matched your query.";
            }

            StringBuilder listOfMatchingTasks = new StringBuilder();
            for (Task t : results) {
                listOfMatchingTasks.append(t.toString()).append(System.lineSeparator());
            }
           return String.format("%d matching task(s) found:\n\n" +
                   listOfMatchingTasks.toString(), results.size());


        } catch (TrackieException e) {
            return e.getMessage();
        }
    }
}
