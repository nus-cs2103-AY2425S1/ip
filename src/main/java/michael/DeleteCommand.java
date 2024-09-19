package michael;

public class DeleteCommand {
    private TaskList tasks;
    private int position;
    private Task deletedTask;

    public DeleteCommand(TaskList tasks, int position) {
        this.tasks = tasks;
        this.position = position;
    }

    public void delete() {
        this.deletedTask = tasks.get(position);
        tasks.delete(position);
    }

    public String feedback() {
        return "Noted. I've removed this task:\n" + "  " + this.deletedTask + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }
}
