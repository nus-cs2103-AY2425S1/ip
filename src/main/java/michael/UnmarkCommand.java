package michael;

public class UnmarkCommand {
    private TaskList tasks;
    private int position;
    private String error = "Enter integer position of task on list to unmark. "
            + "Use command list to check the position of the required task.";

    public UnmarkCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    public void check(String input) throws MichaelException {
        if (input.length() < 8) { // no number given to unmark
            throw new MichaelException(error);
        }

        try {
            this.position = Integer.parseInt(input.substring(7)) - 1;
            this.unmark();
        } catch (NumberFormatException e) {
            throw new MichaelException(error);
        }
    }
    private void unmark() {
        Task task = tasks.get(position);
        task.undoTask();
    }

    public String feedback() {
        return "OK, I've marked this task as not done yet:\n" + "  " + tasks.get(position);
    }
}
