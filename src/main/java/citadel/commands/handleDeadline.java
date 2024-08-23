package citadel.commands;

import citadel.Task.Task;
import citadel.Task.TaskList;
import citadel.Task.Deadline;
import citadel.exception.CitadelTaskNoInput;
import citadel.exception.CitadelException;
import citadel.ui.TextUI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class handleDeadline extends Command {
    public handleDeadline(String input, TaskList tasks) {
        super(input, tasks);
    }

    @Override
    public void run() throws CitadelException {
        Task t;
        String[] words = input.split(" /by ");

        if (words.length < 2) {
            throw new CitadelTaskNoInput();
        }
        String task = words[0].substring(9).trim();
        String deadline = words[1].trim();

        if (task.isEmpty() || deadline.isEmpty()) {
            throw new CitadelTaskNoInput();
        }

        LocalDateTime deadlineFormatted = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        t = new Deadline(task, deadlineFormatted);
        this.tasks.add(t);
        TextUI.printTask(t, tasks);
    }
}