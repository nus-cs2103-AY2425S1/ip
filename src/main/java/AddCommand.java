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
            Todo todo = new Todo(description);
            tasks.addTask(todo);
            ui.showTasks(tasks);
        } else if (taskType.equals("deadline")) {
            String[] parts = words[1].split(" /by ");
            String description = parts[0];
            LocalDateTime by = LocalDateTime.parse(parts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            Deadline deadline = new Deadline(description, by);
            tasks.addTask(deadline);
            ui.showTasks(tasks);
        } else if (taskType.equals("event")) {
            String[] parts = words[1].split(" /from ");
            String description = parts[0];
            String[] dateTimeParts = parts[1].split(" /to ");
            LocalDateTime from = LocalDateTime.parse(dateTimeParts[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            LocalDateTime to = LocalDateTime.parse(dateTimeParts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            Event event = new Event(description, from, to);
            tasks.addTask(event);
            ui.showTasks(tasks);
        }

        storage.save(tasks.getTasks());
    }
}