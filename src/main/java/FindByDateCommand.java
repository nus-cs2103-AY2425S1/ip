import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FindByDateCommand extends Command{
	private String input;

	public FindByDateCommand(String input) {
		this.input = input;
	}

	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws HanaException {
		try {
			String[] parts = input.split(" ", 2);
			if (parts.length < 2) {
				throw new HanaException("Please specify a date.");
			}
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
			LocalDate date = LocalDate.parse(parts[1].trim(), formatter);

			ui.printLine();
			ui.printMessage("Tasks occurring on " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
			boolean found = false;

			for (Task task : taskList.getTasks()) {
				if (task instanceof Deadline) {
					LocalDateTime taskDate = ((Deadline) task).getDeadline();
					if (taskDate.toLocalDate().equals(date)) {
						ui.printMessage(task.toString());
						found = true;
					}
				} else if (task instanceof Event) {
					LocalDateTime taskDateFrom = ((Event) task).getFrom();
					LocalDateTime taskDateTo = ((Event) task).getTo();
					if ((taskDateFrom.toLocalDate().equals(date) || taskDateFrom.toLocalDate().isBefore(date)) &&
							(taskDateTo.toLocalDate().equals(date) || taskDateTo.toLocalDate().isAfter(date))) {
						ui.printMessage(task.toString());
						found = true;
					}
				}
			}
			if (!found) {
				ui.printMessage("No tasks found for this date.");
			}
			ui.printLine();
		} catch (DateTimeParseException e) {
			throw new HanaException("Please enter the date in the correct format: [d/M/yyyy]");
		}
	}
}
