package michael;

public class ToDoCommand {
    private TaskList tasks;
    private ToDo newTask;

    public ToDoCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    public void add(String task) {
        this.newTask = new ToDo(task);
        tasks.add(this.newTask);
    }

    public String feedback() {
        String message = "Got it. I've added this task:\n" + "  " + this.newTask.toString()
                + "\n" + "Now you have " + String.valueOf(tasks.size()) + " tasks in the list.";
        return message;
    }
}
