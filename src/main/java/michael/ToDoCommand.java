package michael;

/**
 * Represents the command to add a simple task
 */
public class ToDoCommand {
    private TaskList tasks;
    private ToDo newTask;

    public ToDoCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Checks the task to be added and adds it if valid.
     *
     * @param input User's input to the chatbot.
     * @throws MichaelException If no task is given to be added.
     */
    public void check(String input) throws MichaelException {
        if (input.length() < 6) { // no task given
            throw new MichaelException("Enter a task to be done.");
        }
        this.add(input.substring(5).trim());
    }

    private void add(String task) {
        this.newTask = new ToDo(task);
        tasks.add(this.newTask);
    }

    /**
     * Returns the task added to the chatbot.
     *
     * @return Confirmation of the task added.
     */
    public String feedback() {
        String message = "Got it. I've added this task:\n" + "  " + this.newTask.toString()
                + "\n" + "Now you have " + tasks.size() + " tasks in the list.";
        return message;
    }
}
