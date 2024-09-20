package michael;

public class DeadlineCommand {
    private TaskList tasks;
    private Deadline newTask;

    public DeadlineCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    public void check(String input) throws MichaelException {
        if (input.length() < 10) { // no deadline task given
            throw new MichaelException("Enter a valid task with a deadline.");
        }

        String task = input.substring(9);
        this.add(task);
    }

    private void add(String task) throws MichaelException {
        String[] parts = task.split("/");
        assert parts.length == 2 : "Format of deadline task is incorrect";

        for (int i = 0; i < parts.length - 1; i++) {
            String curr = parts[i];
            parts[i] = curr.substring(0, curr.length() - 1);
        }

        String deadline = parts[1].substring(3);
        if (deadline.split("-").length < 3) { // invalid format of date
            throw new MichaelException("Deadline should be in format YYYY-MM-DD");
        }

        this.newTask = new Deadline(parts[0], deadline);
        tasks.add(this.newTask);
    }

    public String feedback() {
        String message = "Got it. I've added this task:\n" + "  " + this.newTask.toString()
                + "\n" + "Now you have " + tasks.size() + " tasks in the list.";
        return message;
    }
}
