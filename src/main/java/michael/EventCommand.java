package michael;

public class EventCommand {
    private TaskList tasks;
    private Event newTask;

    public EventCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    public void add(String task) throws MichaelException {
        String[] parts = task.split("/");
        for (int i = 0; i < parts.length - 1; i++) {
            String curr = parts[i];
            parts[i] = curr.substring(0, curr.length() - 1);
        }

        this.newTask = new Event(parts[0], parts[1].substring(5), parts[2].substring(3));
        tasks.add(this.newTask);
    }

    public String feedback() {
        String message = "Got it. I've added this task:\n" + "  " + this.newTask.toString() + "\n"
                + "Now you have " + String.valueOf(tasks.size()) + " tasks in the list.";
        return message;
    }
}
