package michael;

/**
 * Represents the command to add a deadline task to the chatbot
 */
public class DeadlineCommand {
    private TaskList tasks;
    private Deadline newTask;

    public DeadlineCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Checks the format of the given task and adds the new task to the chatbot
     * if the format matches what is expected.
     *
     * @param input User input to the chatbot.
     * @throws MichaelException If task is written in an invalid format.
     */
    public void check(String input) throws MichaelException {
        if (input.length() < 10) { // no deadline task given
            throw new MichaelException("Enter a task with a deadline as follows: [task] /by [YYYY-MM-DD].");
        }

        String task = input.substring(9);
        this.add(task);
    }

    private void add(String task) throws MichaelException {
        String[] parts = task.split("/");

        if (parts.length != 2) {
            throw new MichaelException("Follow format: [task] /by [YYYY-MM-DD]");
        }

        for (int i = 0; i < parts.length - 1; i++) {
            String curr = parts[i];
            parts[i] = curr.trim();
        }

        String deadline = parts[1].substring(3);
        if (deadline.split("-").length < 3) { // invalid format of date
            throw new MichaelException("Deadline should be in format YYYY-MM-DD");
        }

        this.newTask = new Deadline(parts[0], deadline);
        tasks.add(this.newTask);
    }

    /**
     * Returns the task added by the user as feedback back to them.
     *
     * @return Confirmation of the task added by the user.
     */
    public String feedback() {
        String message = "Got it. I've added this task:\n" + "  " + this.newTask.toString()
                + "\n" + "Now you have " + tasks.size() + " tasks in the list.";
        return message;
    }
}
