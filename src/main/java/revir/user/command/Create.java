package revir.user.command;

import java.io.IOException;
import java.time.LocalDateTime;

import revir.tasks.Deadline;
import revir.tasks.Event;
import revir.tasks.Task;
import revir.tasks.TaskList;
import revir.tasks.Todo;
import revir.user.ui.Ui;

/**
 * Represents a command to create a new task and add it to the task list.
 */
public class Create extends Command {
    private Task task;

    /**
     * Constructs a new Create object with the given task description.
     *
     * @param taskDescription the description of the task
     */
    public Create(String taskDescription) {
        super(false);
        this.task = new Todo(taskDescription);
    }

    /**
     * Constructs a new Create object with the given task description and deadline.
     *
     * @param taskDescription the description of the task
     * @param deadline the deadline of the task
     */
    public Create(String taskDescription, LocalDateTime deadline) {
        super(false);
        this.task = new Deadline(taskDescription, deadline);
    }

    /**
     * Constructs a new Create object with the given task description, start time, and end time.
     *
     * @param taskDescription the description of the task
     * @param start the start time of the task
     * @param end the end time of the task
     */
    public Create(String taskDescription, LocalDateTime start, LocalDateTime end) {
        super(false);
        this.task = new Event(taskDescription, start, end);
    }

    /**
     * Executes the command to add a task to the task list.
     *
     * @param ui The user interface to display the result.
     * @param taskList The task list to add the task to.
     * @throws IOException If there is an error adding the task.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) throws IOException {
        taskList.add(this.task);
        ui.showResult("Task added: " + this.task);
    }
}
