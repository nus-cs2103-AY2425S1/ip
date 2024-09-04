package mylo.command;

import mylo.storage.StorageOperationException;
import mylo.task.TaskList;
import mylo.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class ListCommand extends Command{
    private LocalDateTime dateTime;
    public ListCommand(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public ListCommand() {
        super();
    }
    @Override
    public void execute(TaskList list, Ui ui) {
        String message = list.toString();

        if (dateTime != null) {
            message = list.tasksOnDate(dateTime).toString();
        }

        ui.show(message);
    }
}
