package johncena.commands;

import johncena.tasks.Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class OnCommand implements Command {
    private ArrayList<Task> tasks;
    private LocalDate targetDate;

    public OnCommand(ArrayList<Task> tasks, String date) throws DateTimeParseException {
        this.tasks = tasks;
        // Parse the date string into a LocalDate object using the specified format
        this.targetDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public void execute() {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks on " + targetDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ":");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.occursOn(targetDate)) {
                System.out.println(" " + (i + 1) + "." + task);
            }
        }
        System.out.println("____________________________________________________________");

    }
}
