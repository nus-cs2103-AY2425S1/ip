package michael;

public class MarkCommand {
    private TaskList tasks;
    private int position;
    private String error = "Enter integer position of task on list to mark. "
            + "Use command list to check the position of the required task.";

    public MarkCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    public void check(String input) throws MichaelException {
        if (input.length() < 6) { // no number given to mark
            throw new MichaelException(error);
        }

        try {
            this.position = Integer.parseInt(input.substring(5)) - 1;
            this.mark();
        } catch (NumberFormatException e) {
            throw new MichaelException(error);
        }
    }
    private void mark() {
        Task task = tasks.get(position);
        task.doTask();
        this.feedback();
    }

    public String feedback() {
        return "Nice! I've marked this task as done:\n" + "  " + tasks.get(position);
    }
}
