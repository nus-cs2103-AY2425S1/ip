package michael;

/**
 * Represents the command to search for tasks within the chatbot
 * that match a given keyword
 */
public class FindCommand {
    private TaskList tasks;
    private String keyword;

    public FindCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    public void check(String input) throws MichaelException {
        if (input.length() < 6) { // no string given to find
            throw new MichaelException("Enter valid string to find matching tasks.");
        }

        this.keyword = input.substring(5);
    }

    public String feedback() {
        return this.find();
    }

    private String find() {
        TaskList matchingTasks = new TaskList();

        // Iterate through tasks to check which tasks contain the keyword
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.getTaskName().contains(keyword)) {
                matchingTasks.add(t);
            }
        }

        String list = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < matchingTasks.size(); i++) {
            String elem = (i + 1) + ". " + matchingTasks.get(i) + "\n";
            list = list.concat(elem);
        }
        return list.substring(0, list.length() - 1); // substring to remove last line break
    }
}
