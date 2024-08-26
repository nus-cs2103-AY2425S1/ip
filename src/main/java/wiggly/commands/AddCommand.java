package wiggly.commands;

import java.time.LocalDate;

import wiggly.task.TaskList;
import wiggly.task.TaskType;

import wiggly.task.Task;
import wiggly.task.ToDo;
import wiggly.task.Deadline;
import wiggly.task.Event;

import wiggly.util.Storage;
import wiggly.util.Ui;

/**
 * A class representation of the add command
 */
public class AddCommand extends Command {

	private final TaskType taskType;
	private final String description;
	private final LocalDate by;
	private final LocalDate from;
	private final LocalDate to;

	/**
	 * Creates a {@code AddCommand} instance with the given taskType, description, and dates. If there is no date for
	 * that parameter, use {@code null} instead.
	 * @param taskType The type of task to instantiate
	 * @param description The description of the task
	 * @param by The due date of the task, if empty, use {@code null} as the parameter
	 * @param from The start date of the task, if empty, use {@code null} as the parameter
	 * @param to The end date of the task, if empty, use {@code null} as the parameter
	 */
	public AddCommand(TaskType taskType, String description, LocalDate by, LocalDate from, LocalDate to) {
		this.taskType = taskType;
		this.description = description;
		this.by = by;
		this.from = from;
		this.to = to;
	}

	/**
	 * Executes the add command by creating a {@code Task} based on {@code taskType} and
	 * adds it into the {@code taskList}
	 * @param taskList The tasklist to execute the command on
	 * @param ui The user interface for printing status
	 * @param storage The storage file to save and load from
	 */
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
			task = new Event(description, from, to);
			ui.printWrappedString(taskList.addTask(task));
			break;
		default:
			throw new RuntimeException("Unknown task type");
		}

		storage.save(taskList);
	}
}
