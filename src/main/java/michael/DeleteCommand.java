package michael;

/**
 * Represents the command to delete an existing task from the list
 */
public class DeleteCommand {
    private TaskList tasks;
    private int position;
    private Task deletedTask;
    private String error = "Enter integer position of task on list to delete. "
            + "Use command list to check the position of the required task.";

    public DeleteCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Checks that delete command is used correctly and deletes corresponding task
     * if valid position of task is given.
     *
     * @param input User's input to the chatbot.
     * @throws MichaelException If a non-number is given to be deleted or the number is not in the valid range.
     */
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

    /**
     * Returns the task deleted by the chatbot as feedback to the user.
     *
     * @return Confirmation of the deleted task.
     */
    public String feedback() {
        return "Noted. I've removed this task:\n" + "  " + this.deletedTask + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }
}
