package michael;

/**
 * Represents the command to add an event to the chatbot
 */
public class EventCommand {
    private TaskList tasks;
    private Event newTask;

    public EventCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    public void check(String input) throws MichaelException {
        if (input.length() < 7) { // no event given
            throw new MichaelException("Enter a valid event with format: [task] /from [start] /to [end].");
        }

        String task = input.substring(6);
        this.add(task);
    }

    private void add(String task) throws MichaelException {
        String[] parts = task.split("/");

        if (parts.length != 3) {
            throw new MichaelException("Follow format: [task] /from [start] /to [end]");
        }

        for (int i = 0; i < parts.length - 1; i++) {
            String curr = parts[i];
            parts[i] = curr.trim();
        }

        this.newTask = new Event(parts[0], parts[1].substring(5), parts[2].substring(3));
        tasks.add(this.newTask);
    }

    public String feedback() {
        String message = "Got it. I've added this task:\n" + "  " + this.newTask.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
        return message;
    }
}
