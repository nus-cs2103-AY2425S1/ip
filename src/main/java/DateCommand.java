import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateCommand extends Command {
    private String Message;

    public DateCommand(String Message) {
        this.Message = Message;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws GalliumException {
        LocalDate date = LocalDate.parse(Message.split("date ")[1]);
        StringBuilder tasksStringBuilder = new StringBuilder();
        for (int i = 1; i < Task.count; i++) {
            Task task = taskList.get(i - 1);
            if (task.description.startsWith("[D]") || task.description.startsWith("deadline ")) {
                Deadline deadline = (Deadline) task;
                if (date.format(DateTimeFormatter.ofPattern("MMM d yyyy")).equals(deadline.date)) {
                    tasksStringBuilder.append("\n    " + deadline.toString());
                }
            } else if (task.description.startsWith("[E]") || task.description.startsWith("event ")) {
                Event event = (Event) task;
                if (date.format(DateTimeFormatter.ofPattern("MMM d yyyy")).equals(event.toDate)) {
                    tasksStringBuilder.append("\n    " + event.toString());
                }
            }
        }
        String tasks = tasksStringBuilder.toString();
        ui.printMatchingDate(tasks);
    }
}
