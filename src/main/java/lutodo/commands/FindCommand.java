package lutodo.commands;

import lutodo.storage.Storage;
import lutodo.tasklist.TaskList;

/**
 * Represents a search command to search for tasks.
 */
public class FindCommand extends Command{

    private String toSearch;

    /**
     * Constructs a FindCommand object with the key word to search for.
     *
     * @param toSearch The key word to search for.
     */
    public FindCommand(String toSearch) {
        this.toSearch = toSearch;
    }

    /**
     * Performs searching tasks and print matching tasks to users.
     *
     * @param tasks The TaskList to search in.
     * @param storage The Storage object.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        if (tasks.isEmpty()) {
            System.out.println("You don't have any task now ~(∠・ω< )⌒★");
            return;
        }
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            if (tasks.get(i - 1).getDescription().contains(toSearch)) {
                System.out.println(i + "." + tasks.get(i - 1));
            }
        }
    }


    /**
     * Returns false since this command is not exit command.
     *
     * @return Whether this command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
