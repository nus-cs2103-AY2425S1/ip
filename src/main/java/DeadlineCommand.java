import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineCommand implements Command{
    private final String description;
    private final LocalDateTime deadlineDate;

    DeadlineCommand(String args) throws MichaelScottException {
        String[] deadlineParts = args.split(" /by ");
        if (deadlineParts.length != 2) {
            throw new MichaelScottException("Please provide both the task description and deadline (Here is an example: deadline homework /by 2024-03-02 12:00).");
        }
        this.description = deadlineParts[0].trim();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.deadlineDate = LocalDateTime.parse(deadlineParts[1], formatter);
        } catch (java.time.format.DateTimeParseException e) {
            throw new MichaelScottException("Invalid date format. Please use the format YYYY-MM-DD HH:MM.");
        }
    }

    @Override
    public String execute(TaskList tasks) {
        Deadline DeadlineTask = new Deadline(this.description, this.deadlineDate);
        tasks.addTask(DeadlineTask);
        return "Got it. I've added this task:\n" + DeadlineTask.toString() +
                "\nNow you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.";
    }
}
