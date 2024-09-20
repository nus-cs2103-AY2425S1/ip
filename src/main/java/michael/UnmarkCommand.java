package michael;

/**
 * Represents the command to unmark an existing task
 */
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

            if (position < 0) {
                throw new MichaelException("Position of task to unmark should be positive");
            }

            if (position >= tasks.size()) {
                throw new MichaelException("Position of task to unmark is out of bounds");
            }

            assert position >= 0 : "Position of task to unmark should be positive";
            assert position < tasks.size() : "Position of task to unmark cannot be out of bounds";

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
