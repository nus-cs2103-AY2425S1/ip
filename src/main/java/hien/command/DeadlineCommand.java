package hien.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import hien.exception.HienException;
import hien.main.Storage;
import hien.main.TaskList;
import hien.task.Deadline;
import hien.ui.UI;

public class DeadlineCommand extends Command {
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private String input;

    public DeadlineCommand(String input, boolean isExit) {
        super(isExit);
        this.input = input;
    }

    private String addDeadline(TaskList tasks, String input, Storage storage, UI ui) throws HienException {
        String[] parts = input.substring(8).split(" /by ");
        String msg = "";
        if (parts.length == 2) {
            try {
                LocalDateTime dateTime = LocalDateTime.parse(parts[1].trim(), INPUT_DATE_FORMAT);
                Deadline deadline = new Deadline(parts[0].trim(), dateTime);
                tasks.addTask(deadline);
                storage.save(tasks);
                msg += ui.showMessage(" Got it. I've added this task:");
                msg += ui.showMessage("   " + deadline);
                msg += ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
                return msg;
            } catch (DateTimeParseException e) {
                throw new HienException("☹ OOPS!!! The date format is incorrect. Please use: yyyy-MM-dd HHmm");
            }
        } else {
            throw new HienException(" ☹ OOPS!!! The deadline format is incorrect. "
                                    + "Please use: deadline <description> /by <yyyy-MM-dd HHmm>");
        }
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws HienException {
        return addDeadline(tasks, input, storage, ui);
    }
}
