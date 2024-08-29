package features.task;

/**
 * Represents a todo task with a description and a completion status.
 */
public class TodoTask extends Task {

	/**
	 * Constructs a TodoTask with the specified name.
	 *
	 * @param name the name or description of the todo task
	 */
	public TodoTask(String name) {
		super(name);
	}

	/**
	 * Returns a string representation of the todo task.
	 * The format is "[T][status] name", where [status] is "x" if the task is marked,
	 * otherwise " ".
	 *
	 * @return a string representation of the todo task
	 */
	@Override
	public String toString() {
		return "[T]" + super.toString();
	}
}
