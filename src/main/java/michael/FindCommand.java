package michael;

public class FindCommand {
    private TaskList tasks;
    String keyword;

    public FindCommand(TaskList tasks, String keyword) {
        this.tasks = tasks;
        this.keyword = keyword;
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
