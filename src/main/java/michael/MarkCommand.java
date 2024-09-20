package michael;

/**
 * Represents the command to mark an existing task as done
 */
public class MarkCommand {
    private TaskList tasks;
    private int position;
    private String error = "Enter integer position of task on list to mark. "
            + "Use command list to check the position of the required task.";

    public MarkCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Checks that a valid task is given to be marked and marks it as done if
     * the command is used correctly.
     *
     * @param input User's input to the chatbot.
     * @throws MichaelException If a non-number or a number out of range is given.
     */
    public void check(String input) throws MichaelException {
        if (input.length() < 6) { // no number given to mark
            throw new MichaelException(error);
        }

        try {
            this.position = Integer.parseInt(input.substring(5)) - 1;

            if (position < 0) {
                throw new MichaelException("Position of task to mark should be positive");
            }

            if (position >= tasks.size()) {
                throw new MichaelException("Position of task is out of range");
            }

            assert position >= 0 : "Position of task to mark should be positive";
            assert position < tasks.size() : "Position of task to mark cannot be out of bounds";

            this.mark();
        } catch (NumberFormatException e) {
            throw new MichaelException(error);
        }
    }
    private void mark() {
        Task task = tasks.get(position);
        task.doTask();
    }

    /**
     * Confirms that the task intended to be marked by the user has been marked.
     *
     * @return The task that was marked by the chatbot.
     */
    public String feedback() {
        return "Nice! I've marked this task as done:\n" + "  " + tasks.get(position);
    }
}
