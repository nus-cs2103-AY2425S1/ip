package friendlybot.command;

import java.time.LocalDate;

import friendlybot.Storage;
import friendlybot.Ui;
import friendlybot.task.Deadline;
import friendlybot.task.Event;
import friendlybot.task.Task;
import friendlybot.task.TaskList;
import friendlybot.task.ToDo;

/**
 * AddCommand is a Command for adding new Tasks (ToDo, Deadline, Event)
 */
public class AddCommand extends Command {
    private String taskType;
    private String taskDescription;
    private LocalDate by;
    private LocalDate from;
    private LocalDate to;

    /**
     * A constructor for AddCommand, used for adding ToDo tasks.
     *
     * @param taskType Type of Task to be added.
     * @param taskDescription Description of Task to be added.
     */
    public AddCommand(String taskType, String taskDescription) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
    }

    /**
     * A constructor for AddCommand, used for adding Deadline tasks.
     *
     * @param taskType Type of Task to be added.
     * @param taskDescription Description of Task to be added.
     * @param by Deadline of task.
     */
    public AddCommand(String taskType, String taskDescription, LocalDate by) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.by = by;
    }

    /**
     * A constructor for AddCommand, used for adding Event tasks.
     *
     * @param taskType Type of Task to be added.
     * @param taskDescription Description of Task to be added.
     * @param from Start date of task.
     * @param to End date of task.
     */
    public AddCommand(String taskType, String taskDescription, LocalDate from, LocalDate to) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.from = from;
        this.to = to;
    }

    /**
     * An empty constructor for AddCommand, used to display the format of the AddCommand.
     */
    public AddCommand() {
    }

    public String getTaskType() {
        return this.taskType;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public LocalDate getDeadlineDate() {
        return this.by;
    }

    public LocalDate getEventFromDate() {
        return this.from;
    }

    public LocalDate getEventToDate() {
        return this.to;
    }

    /**
     * Executes the AddCommand and adds the respective task to the task list.
     *
     * @param tasks An instance of TaskList where the new task is added to.
     * @param ui An instance of Ui (User Interface) that handles the interactions between FriendlyBot and user.
     * @param storage An instance of Storage that loads tasks and saves tasks in a file.
     *
     * @return Returns a response String from FriendlyBot to the User.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder sb = new StringBuilder();
        Task newTask;
        if (taskType.equals("todo")) {
            newTask = new ToDo(taskDescription);
        } else if (taskType.equals("deadline")) {
            newTask = new Deadline(taskDescription, by);
        } else {
            if (to.isBefore(from)) {
                return "Uh oh, I can't create an event that is due before it even started!";
            }
            newTask = new Event(taskDescription, from, to);
        }
        tasks.addTask(newTask);
        Ui.print("Got it. I've added this task:");
        sb.append("Got it. I've added this task:\n");
        Ui.print("  " + newTask.toString());
        sb.append("  ").append(newTask.toString()).append("\n");
        int numTasks = tasks.getNumTasks();
        if (numTasks == 1) {
            Ui.print("Now you have " + numTasks + " task in the list.");
            sb.append("Now you have ").append(numTasks).append(" task in the list.");
        } else {
            Ui.print("Now you have " + numTasks + " tasks in the list.");
            sb.append("Now you have ").append(numTasks).append(" tasks in the list.");
        }
        storage.writeToFile(tasks.formatTasksToSave());
        return sb.toString();
    }

    @Override
    public String toString() {
        return """
                todo <task_description> - Adds a new ToDo task.
                deadline <task_description> /by <date> - Adds a new Deadline task.
                event <task_description> /from <date> /to <date> - Adds a new Event task.""";
    }
}
