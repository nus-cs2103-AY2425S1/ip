package commands;

import bob.*;
import tasks.Deadline;
import tasks.Task;
import utilities.Printer;

/**
 * Represents a command to add a deadline task to the task list.
 * This command specifies a task with a name and a deadline date.
 */
public class DeadlineCommand extends Command {
    public static String[] params = new String[] {
        "deadline", "/by"
    };
    public static int paramCount = 2;
    private String name;
    private String by;

    /**
     * Constructs a {@code DeadlineCommand} with the specified task name and deadline date.
     *
     * @param name the name of the task.
     * @param by the deadline date for the task.
     */
    public DeadlineCommand(String name, String by) {
        this.name = name;
        this.by = by;
    }

    /**
     * Executes the deadline command by adding the task to the task list.
     * Displays a message indicating that the task has been added and shows the updated task list size.
     *
     * @param tasks the {@code TaskList} to which the deadline task will be added.
     * @param ui the {@code Ui} to interact with the user.
     * @param storage the {@code Storage} for saving or loading tasks (not used in this method).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, ExecutionStack execStack) {
        Deadline deadline = tasks.deadline(this.name, this.by);
        String tasksRemaining = String.format("Now you have %d tasks in the list.", tasks.getSize());
        execStack.push(this);
        return Printer.format(new String[] { "Got it. I've added this task:",
            " " + deadline.toString(),
             tasksRemaining });
    }

    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) {
        Task removedTask = tasks.pop();
        String response = "Removed task [" + removedTask.toString() + "].";
        return response;
    }


        /**
         * Compares this {@code DeadlineCommand} with another object for equality.
         * Two {@code DeadlineCommand} objects are considered equal if they have the same task name and deadline date.
         *
         * @param obj the object to be compared with this {@code DeadlineCommand}.
         * @return {@code true} if the specified object is equal to this {@code DeadlineCommand}; {@code false} otherwise.
         */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeadlineCommand temp) {
            boolean hasSameName = this.name.equals(temp.name);
            boolean hasSameByDate = this.by.equals(temp.by);
            return hasSameName && hasSameByDate;
        }
        return false;
    }
}
