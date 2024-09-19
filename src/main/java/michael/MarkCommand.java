package michael;

public class MarkCommand {
    private TaskList tasks;
    private int position;


    public MarkCommand(TaskList tasks, int position) {
        this.tasks = tasks;
        this.position = position;
    }

    public void mark() {
        Task task = tasks.get(position);
        task.doTask();
        this.feedback();
    }

    public String feedback() {
        return "Nice! I've marked this task as done:\n" + "  " + tasks.get(position);
    }
}
