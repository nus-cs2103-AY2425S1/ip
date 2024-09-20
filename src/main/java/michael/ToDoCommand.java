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

    public String feedback() {
        String message = "Got it. I've added this task:\n" + "  " + this.newTask.toString()
                + "\n" + "Now you have " + tasks.size() + " tasks in the list.";
        return message;
    }
}
