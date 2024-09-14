package beechat.command;

import beechat.task.DeadlineTask;
import beechat.task.EventTask;
import beechat.task.TaskList;
import beechat.task.TodoTask;
import beechat.util.Storage;
import beechat.util.Ui;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddCommand extends Command {
    private final String fullCommand;

    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String[] words = fullCommand.split(" ", 2);
        String taskType = words[0];

        if (taskType.equals("todo")) {
            String description = words[1];
            TodoTask todo = new TodoTask(description);
            tasks.addTask(todo);
            ui.showTasks(tasks);
        } else if (taskType.equals("deadline")) {
            String[] parts = words[1].split(" /by ");
            String description = parts[0];
            LocalDateTime by = LocalDateTime.parse(parts[1], DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
            DeadlineTask deadline = new DeadlineTask(description, by);
            tasks.addTask(deadline);
            ui.showTasks(tasks);
        } else if (taskType.equals("event")) {
            String[] parts = words[1].split(" /from ");
            String description = parts[0];
            String[] dateTimeParts = parts[1].split(" /to ");
            LocalDateTime from = LocalDateTime.parse(dateTimeParts[0], DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
            LocalDateTime to = LocalDateTime.parse(dateTimeParts[1], DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
            EventTask event = new EventTask(description, from, to);
            tasks.addTask(event);
            ui.showTasks(tasks);
        }
        storage.saveTasks(tasks.getTasks());
    }
}
