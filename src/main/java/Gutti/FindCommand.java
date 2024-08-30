package Gutti;

import java.util.ArrayList;

/**
 * Represents a command that searches for tasks with descriptions containing a specified word.
 * <p>
 * This class extends {@code Command} and provides functionality to search through a list of tasks
 * and output tasks that match the search criteria.
 * </p>
 */
public class FindCommand extends Command{
    /** The word to match in the task descriptions. */
    public String wordToMatch;

    /**
     * Constructs a {@code FindCommand} with the specified word to match.
     *
     * @param wordToMatch The word to search for in task descriptions.
     */
    public FindCommand (String wordToMatch) {
        this.wordToMatch = wordToMatch;
    }

    /**
     * Executes the find command to search through the list of tasks for descriptions containing the specified word.
     * <p>
     * It iterates through the list of tasks, checks if the description of each task contains the search word,
     * and collects the indices of matching tasks. It then prints out the matching tasks with their indices.
     * If no tasks are found with the specified word, it prints a message indicating that no matching tasks were found.
     * </p>
     *
     * @param tasks The {@code TaskList} containing the list of tasks to search through.
     * @param ui The {@code Ui} instance for interacting with the user interface (not used in this method).
     * @param storage The {@code Storage} instance for saving and loading tasks (not used in this method).
     * @throws GuttiException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GuttiException {
        ArrayList<Task> taskList = tasks.getTasks();
        ArrayList<Integer> integerList = new ArrayList<>();

        for (int i = 0; i < taskList.size(); i++) {
            Task specificTask = taskList.get(i);
            if (specificTask.description.toLowerCase().contains(wordToMatch.toLowerCase())) {
                integerList.add(i);
            }
        }
        if (!integerList.isEmpty()) {
            System.out.println("____________________________________________________________");
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < integerList.size(); i++) {
                System.out.println((i + 1) + ". " + taskList.get(integerList.get(i)));
            }
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("Task with a description containing \"" + wordToMatch + "\" not found.");
        }
    }

    /**
     * Indicates whether the command will result in exiting the program.
     * <p>
     * This method returns {@code false} for the {@code FindCommand}, indicating that executing this command
     * does not terminate the program.
     * </p>
     *
     * @return {@code false} since the {@code FindCommand} does not result in program exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
