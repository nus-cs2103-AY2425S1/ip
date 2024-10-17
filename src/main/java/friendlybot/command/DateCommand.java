package friendlybot.command;

import java.time.LocalDate;
import java.util.List;

import friendlybot.Storage;
import friendlybot.Ui;
import friendlybot.task.Task;
import friendlybot.task.TaskList;

/**
 * DateCommand is a Command that prints a list of tasks that happen on a given date.
 */
public class DateCommand extends Command {
    private LocalDate date;

    /**
     * A constructor for DateCommand.
     *
     * @param date Date to scan for tasks that happen on that date.
     */
    public DateCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * An empty constructor for DateCommand, used to display the command format.
     */
    public DateCommand() {};

    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Prints a list of tasks that happen on the given date upon execution.
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
        List<Task> taskList = tasks.getTasksOnDate(date);
        int numTasks = taskList.size();
        if (numTasks == 0) {
            Ui.print("I couldn't find any tasks on this date!");
            return "I couldn't find any tasks on this date!";
        }
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < numTasks; i++) {
            Task task = taskList.get(i);
            sb.append(i + 1).append(".").append(task.toString());
            if (i != numTasks - 1) {
                sb.append("\n");
            }
        }
        Ui.print(sb.toString());
        return sb.toString();
    }

    @Override
    public String toString() {
        return "date <date> - Finds deadline and event tasks that fall on the provided date.";
    }
}
