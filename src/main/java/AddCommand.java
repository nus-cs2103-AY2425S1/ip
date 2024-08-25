import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddCommand extends Command {
    char type;
    public AddCommand(char type) {
        this.type = type;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        if (this.type == 'T') {
            // Tasks
            String description = ui.getDescription(this.type);
            Todo newTodo = new Todo(description);
            tasks.addTask(newTodo);

            // Storage
            try {
                storage.write(tasks);
            } catch (IOException io) {
                System.out.println("Error in writing history" + io.getMessage());
            }

            // UI response
            ui.addedResponse(this.type, newTodo, tasks);

        } else if (this.type == 'D') {
            // Tasks
            String description = ui.getDescription(this.type);
            String by = ui.getBy();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime d1 = LocalDateTime.parse(by, formatter);
            Deadline newDeadline = new Deadline(description, d1);
            tasks.addTask(newDeadline);

            // Storage
            try {
                storage.write(tasks);
            } catch (IOException io) {
                System.out.println("Error in writing history" + io.getMessage());
            }

            // Ui response
            ui.addedResponse(this.type, newDeadline, tasks);

        } else {
            // Tasks
            String description = ui.getDescription(this.type);
            String from = ui.getFrom();
            String to = ui.getTo();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime d1 = LocalDateTime.parse(from, formatter);
            LocalDateTime d2 = LocalDateTime.parse(to, formatter);
            Event newEvent = new Event(description, d1, d2);
            tasks.addTask(newEvent);

            // Storage
            try {
                storage.write(tasks);
            } catch (IOException io) {
                System.out.println("Error in writing history" + io.getMessage());
            }

            //Ui response
            ui.addedResponse(this.type, newEvent, tasks);

        }
    }

    @Override
    public Boolean isExit() {
        return false;
    }

}
