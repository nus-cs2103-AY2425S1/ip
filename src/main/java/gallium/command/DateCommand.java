package gallium.command;

import gallium.main.GalliumException;
import gallium.main.Storage;
import gallium.main.TaskList;
import gallium.main.Ui;

import gallium.task.Deadline;
import gallium.task.Event;
import gallium.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateCommand extends Command {
    private String message;

    public DateCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws GalliumException {
        try {
            LocalDate date = LocalDate.parse(message.split("date ")[1]);
            StringBuilder tasksStringBuilder = new StringBuilder();
            for (int i = 1; i < Task.count; i++) {
                Task task = taskList.get(i - 1);
                if (task.getDesc().startsWith("[D]") || task.getDesc().startsWith("deadline ")) {
                    Deadline deadline = (Deadline) task;
                    if (date.format(DateTimeFormatter.ofPattern("MMM d yyyy")).equals(deadline.getDate())) {
                        tasksStringBuilder.append("\n    " + deadline.toString());
                    }
                } else if (task.getDesc().startsWith("[E]") || task.getDesc().startsWith("event ")) {
                    Event event = (Event) task;
                    if (date.format(DateTimeFormatter.ofPattern("MMM d yyyy")).equals(event.getToDate())) {
                        tasksStringBuilder.append("\n    " + event.toString());
                    }
                }
            }
            String tasks = tasksStringBuilder.toString();
            ui.printMatchingDate(tasks);
        } catch (DateTimeParseException e) {
            ui.showWrongDateTimeFormat();
        }
    }
}
