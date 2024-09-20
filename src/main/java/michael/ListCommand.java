package michael;

public class ListCommand {
    private TaskList tasks;

    public ListCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    public String feedback() {
        String list = "Here are the tasks in your list:\n";

        for (int i = 0; i < tasks.size(); i++) {
            String elem = (i + 1) + ". " + tasks.get(i) + "\n";
            list = list.concat(elem);
        }

        return list.substring(0, list.length() - 1); // substring to remove last line break
    }
}
