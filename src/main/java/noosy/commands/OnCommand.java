package noosy.commands;

import noosy.storage.Storage;
import noosy.ui.Ui;
import noosy.task.Deadline;
import noosy.task.Event;
import noosy.task.Task;
import noosy.task.TaskList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OnCommand extends Command {

    private LocalDate date;

    public OnCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("You needa do this on " + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ":");
        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDate().equals(date)) {
                    System.out.println(deadline);
                    found = true;
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getStart().toLocalDate().equals(date)) {
                    System.out.println(event);
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("Hooray! Nothing to do on " + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
    }
}