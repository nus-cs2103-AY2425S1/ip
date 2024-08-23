package citadel.commands;

import citadel.Task.Task;
import citadel.Task.TaskList;
import citadel.Task.Event;
import citadel.exception.CitadelException;
import citadel.exception.CitadelTaskNoInput;
import citadel.ui.TextUI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class handleEvent extends Command {
    public handleEvent(String input, TaskList tasks) {
        super(input, tasks);
    }

    @Override
    public void run() throws CitadelException {
        Task t;
        String[] words = this.input.split(" /from ");

        if (words.length < 2) {
            throw new CitadelTaskNoInput();
        }

        String task = words[0].substring(5).trim();
        String[] timeline = words[1].split(" /to ");

        if (timeline.length < 2) {
            throw new CitadelTaskNoInput();
        }

        String from = timeline[0].trim();
        String to = timeline[1].trim();

        if (task.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new CitadelTaskNoInput();
        }

        LocalDateTime fromFormatted = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        LocalDateTime toFormatted = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        if (fromFormatted.isAfter(toFormatted)) {
            System.out.println("The start time must be before the end time!");
        } else {
            t = new Event(task, fromFormatted, toFormatted);
            this.tasks.add(t);
            TextUI.printTask(t, tasks);
        }
    };
}
