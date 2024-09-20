package johncena.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import johncena.tasks.Task;

/**
 * The {@code OnCommand} class implements the {@code Command} interface and provides
 * the functionality to execute the "on" command, which lists all tasks on a specified date.
 */
public class OnCommand implements Command {
    private ArrayList<Task> tasks;
    private LocalDate targetDate;

    /**
     * Constructs a new {@code OnCommand} with the specified task list and date.
     *
     * @param tasks the list of tasks
     * @param date the date to filter tasks by
     * @throws DateTimeParseException if the date string is in an incorrect format
     */
    public OnCommand(ArrayList<Task> tasks, String date) throws DateTimeParseException {
        this.tasks = tasks;
        // Parse the date string into a LocalDate object using the specified format
        this.targetDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Executes the "on" command. Lists all tasks on the specified date.
     */
    @Override
    public String execute() {

        StringBuilder sb = new StringBuilder();
        //sb.append("____________________________________________________________\n");
        sb.append(" Here are the tasks on ").append(targetDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))).append(":\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.occursOn(targetDate)) {
                sb.append(" ").append(i + 1).append(".").append(task).append("\n");
            }
        }
        //sb.append("____________________________________________________________\n");
        return sb.toString();

//        System.out.println("____________________________________________________________");
//        System.out.println(" Here are the tasks on " + targetDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
//                + ":");
//        for (int i = 0; i < tasks.size(); i++) {
//            Task task = tasks.get(i);
//            if (task.occursOn(targetDate)) {
//                System.out.println(" " + (i + 1) + "." + task);
//            }
//        }
//        System.out.println("____________________________________________________________");

    }
}
