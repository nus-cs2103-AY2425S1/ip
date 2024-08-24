import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command{
	private String description;
	private LocalDateTime from;
	private LocalDateTime to;


	public EventCommand(String input) throws HanaException {
		try {
			String[] parts = input.substring(5).split(" /from | /to ");
			if (parts.length < 3) {
				throw new HanaException("Event task must have a description, start time, and end time.");
			}
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
			this.from = LocalDateTime.parse(parts[1].trim(), formatter);
			this.to = LocalDateTime.parse(parts[2].trim(), formatter);
			this.description = parts[0].trim();
		} catch (DateTimeParseException e) {
			throw new HanaException("Please provide a valid deadline command in the format: " +
					"event [description] /from [d/M/yyyy HHmm] /to [d/M/yyyy HHmm]");
		}
	}

	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws HanaException {
		Task task = new Event(description, from, to);
		taskList.addTask(task);
		ui.printAdd(task, taskList.getTasks().size());
		storage.save();
	}
}
