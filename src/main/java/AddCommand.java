import java.time.LocalDate;

public class AddCommand extends Command {
    private String eventType;
    private String taskDescription;
    private LocalDate by;
    private LocalDate from;
    private LocalDate to;

    public AddCommand(String eventType, String taskDescription) {
        this.eventType = eventType;
        this.taskDescription = taskDescription;
    }

    public AddCommand(String eventType, String taskDescription, LocalDate by) {
        this.eventType = eventType;
        this.taskDescription = taskDescription;
        this.by = by;
    }

    public AddCommand(String eventType, String taskDescription, LocalDate from, LocalDate to) {
        this.eventType = eventType;
        this.taskDescription = taskDescription;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask;
        if (eventType.equals("todo")) {
            newTask = new ToDo(taskDescription);
        } else if (eventType.equals("deadline")) {
            newTask = new Deadline(taskDescription, by);
        } else {
            newTask = new Event(taskDescription, from, to);
        }
        tasks.addTask(newTask);
        Ui.print("Got it. I've added this task:");
        Ui.print("  " + newTask.toString());
        int numTasks = tasks.getNumTasks();
        if (numTasks == 1) {
            Ui.print("Now you have " + numTasks + " task in the list.");
        } else {
            Ui.print("Now you have " + numTasks + " tasks in the list.");
        }
    }
}
