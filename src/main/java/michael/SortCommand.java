package michael;

/**
 * Represents the command to sort existing tasks lexicographically, ignoring case
 */
public class SortCommand {
    private TaskList tasks;

    public SortCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Calls sort method in the TaskList class to sort the tasks accordingly.
     */
    public void sort() {
        tasks.sort();
    }

    /**
     * Confirms that the tasks were sorted by the chatbot.
     *
     * @return Feedback to the user that tasks were sorted.
     */
    public String feedback() {
        return "Tasks have been sorted.";
    }
}
