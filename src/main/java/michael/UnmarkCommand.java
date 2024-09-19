package michael;

public class UnmarkCommand {
    private TaskList tasks;
    private int position;

    public UnmarkCommand(TaskList tasks, int position) {
        this.tasks = tasks;
        this.position = position;
    }

    public void unmark() {
        Task task = tasks.get(position);
        task.undoTask();
    }

    public String feedback() {
        return "OK, I've marked this task as not done yet:\n" + "  " + tasks.get(position);
    }
}
