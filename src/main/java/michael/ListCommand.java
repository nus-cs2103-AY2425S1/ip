package michael;

/**
 * Represents the command to list out all tasks currently tracked by the chatbot
 */
public class ListCommand {
    private TaskList tasks;

    public ListCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Lists all tasks that are being tracked by the chatbot.
     *
     * @return All tasks in the chatbot.
     */
    public String feedback() {
        String list = "Here are the tasks in your list:\n";

        for (int i = 0; i < tasks.size(); i++) {
            String elem = (i + 1) + ". " + tasks.get(i) + "\n";
            list = list.concat(elem);
        }

        return list.substring(0, list.length() - 1); // substring to remove last line break
    }
}
