import java.time.LocalDate;

public class AddCommand extends Command {

	private final TaskType taskType;
	private final String description;
	private final LocalDate by;
	private final LocalDate from;
	private final LocalDate to;

	public AddCommand(TaskType taskType, String description, LocalDate by, LocalDate from, LocalDate to) {
		this.taskType = taskType;
		this.description = description;
		this.by = by;
		this.from = from;
		this.to = to;
	}

	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) {

		Task task;

		switch (taskType) {
		case TODO:
			task = new ToDo(description);
			ui.printWrappedString(taskList.addTask(task));
			break;
		case DEADLINE:
			task = new Deadline(description, by);
			ui.printWrappedString(taskList.addTask(task));
			break;
		case EVENT:
			task = new Event(description, to, from);
			ui.printWrappedString(taskList.addTask(task));
			break;
		default:
			throw new RuntimeException("Unknown task type");
		}

		storage.save(taskList);
	}
}
