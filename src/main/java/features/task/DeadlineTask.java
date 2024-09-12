package features.task;

import java.time.LocalDate;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
	private LocalDate deadline;

	/**
	 * Constructs a DeadlineTask with the given name and deadline.
	 *
	 * @param name the name of the task
	 * @param deadline the deadline of the task in ISO-8601 format (e.g., "2024-08-30")
	 */
	public DeadlineTask(String name, String deadline) {
		super(name);
		assert deadline != null : "Task deadline must not be null.";
		this.deadline = LocalDate.parse(deadline);
	}

	/**
	 * Returns a string representation of the DeadlineTask.
	 *
	 * @return a string representation of the DeadlineTask in the format "[D][x] name (by: deadline)"
	 */
	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + deadline + ")";
	}

	/**
	 * Returns the deadline of the task in ISO-8601 format.
	 *
	 * @return the deadline of the task
	 */
	public String getDeadline() {
		return this.deadline.toString();
	}
}
