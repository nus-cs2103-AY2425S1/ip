package michael;

public class SortCommand {
    private TaskList tasks;

    public SortCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    public void sort() {
        tasks.sort();
    }

    public String feedback() {
        return "Tasks have been sorted.";
    }
}
