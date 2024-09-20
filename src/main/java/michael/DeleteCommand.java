package michael;

public class DeleteCommand {
    private TaskList tasks;
    private int position;
    private Task deletedTask;
    private String error = "Enter integer position of task on list to delete. "
            + "Use command list to check the position of the required task.";

    public DeleteCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    public void check(String input) throws MichaelException {
        if (input.length() < 8) { // no number given to delete
            throw new MichaelException(error);
        }

        try {
            this.position = Integer.parseInt(input.substring(7)) - 1;

            if (position < 0) {
                throw new MichaelException("Position of task to delete should be positive");
            }

            if (position >= tasks.size()) {
                throw new MichaelException("Position of task to delete is out of bounds");
            }

            assert position >= 0 : "Position of task to delete should be positive";
            assert position < tasks.size() : "Position of task to delete cannot be out of bounds";

            this.delete();
        } catch (NumberFormatException e) {
            throw new MichaelException(error);
        }
    }

    private void delete() {
        this.deletedTask = tasks.get(position);
        tasks.delete(position);
    }

    public String feedback() {
        return "Noted. I've removed this task:\n" + "  " + this.deletedTask + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }
}
