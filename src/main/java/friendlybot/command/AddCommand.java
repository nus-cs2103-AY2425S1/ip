package friendlybot.command;

import friendlybot.Storage;
import friendlybot.Ui;
import friendlybot.task.Deadline;
import friendlybot.task.Event;
import friendlybot.task.Task;
import friendlybot.task.TaskList;
import friendlybot.task.ToDo;

import java.time.LocalDate;

/**
 * AddCommand is a Command for adding new Tasks (ToDo, Deadline, Event)
 */
public class AddCommand extends Command {
    private String eventType;
    private String taskDescription;
    private LocalDate by;
    private LocalDate from;
    private LocalDate to;

    /**
     * A constructor for AddCommand, used for adding ToDo tasks.
     *
     * @param eventType Type of Task to be added.
     * @param taskDescription Description of Task to be added.
     */
    public AddCommand(String eventType, String taskDescription) {
        this.eventType = eventType;
        this.taskDescription = taskDescription;
    }

    /**
     * A constructor for AddCommand, used for adding Deadline tasks.
     *
     * @param eventType Type of Task to be added.
     * @param taskDescription Description of Task to be added.
     * @param by Deadline of task.
     */
    public AddCommand(String eventType, String taskDescription, LocalDate by) {
        this.eventType = eventType;
        this.taskDescription = taskDescription;
        this.by = by;
    }

    /**
     * A constructor for AddCommand, used for adding Event tasks.
     *
     * @param eventType Type of Task to be added.
     * @param taskDescription Description of Task to be added.
     * @param from Start date of task.
     * @param to End date of task.
     */
    public AddCommand(String eventType, String taskDescription, LocalDate from, LocalDate to) {
        this.eventType = eventType;
        this.taskDescription = taskDescription;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the AddCommand and adds the respective task to the task list.
     *
     * @param tasks An instance of TaskList where the new task is added to.
     * @param ui An instance of Ui (User Interface) that handles the interactions between FriendlyBot and user.
     * @param storage An instance of Storage that loads tasks and saves tasks in a file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask;
        if (eventType.equals("todo")) {
            newTask = new ToDo(taskDescription);
        } else if (eventType.equals("deadline")) {
            newTask = new Deadline(taskDescription, by);
        } else {
            if (to.isBefore(from)) {
                Ui.print("Uh oh, I can't create an event that is due before it even started!");
                return;
            }
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
