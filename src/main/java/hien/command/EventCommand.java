package hien.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import hien.exception.HienException;
import hien.main.Storage;
import hien.main.TaskList;
import hien.task.Event;
import hien.ui.UI;


public class EventCommand extends Command {
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private String input;

    public EventCommand(String input, boolean isExit) {
        super(isExit);
        this.input = input;
    }

    private void addEvent(TaskList tasks, String input, Storage storage, UI ui) throws HienException {
        String[] parts = input.substring(5).split(" /from | /to ");
        if (parts.length == 3) {
            try {
                LocalDateTime startTime = LocalDateTime.parse(parts[1].trim(), INPUT_DATE_FORMAT);
                LocalDateTime endTime = LocalDateTime.parse(parts[2].trim(), INPUT_DATE_FORMAT);
                Event event = new Event(parts[0].trim(), startTime, endTime);
                tasks.addTask(event);
                storage.save(tasks);
                ui.showMessage(" Got it. I've added this task:");
                ui.showMessage("   " + event);
                ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
            } catch (DateTimeParseException e) {
                throw new HienException("☹ OOPS!!! The date format is incorrect. Please use: yyyy-MM-dd HHmm");
            }
        } else {
            throw new HienException("☹ OOPS!!! The event format is incorrect. "
                                    + "Please use: event <description> /from <yyyy-MM-dd HHmm> "
                                    + "/to <yyyy-MM-dd HHmm>");
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws HienException {
        addEvent(tasks, input, storage, ui);
    }
}
