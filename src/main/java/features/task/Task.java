package features.task;

/**
 * Represents a task with a name, an ID, and a mark status.
 */
public class Task {
	private String name;
	private Integer id;
	private Boolean isMarked;

	/**
	 * Constructs a Task with the given name. The task is initially not marked.
	 * @param name the name of the task
	 */
	public Task(String name) {
		assert name != null : "Task name must not be null.";
		this.name = name;
		this.id = -1;
		this.isMarked = false;
	}

	/**
	 * Constructs a Task with the given name and mark status.
	 * @param name the name of the task
	 * @param isMarked the initial mark status of the task
	 */
	public Task(String name, boolean isMarked) {
		this(name);
		this.isMarked = isMarked;
	}

	/**
	 * Returns whether the task is marked.
	 * @return true if the task is marked, false otherwise
	 */
	public boolean getIsMarked() {
		return this.isMarked;
	}

	/**
	 * Sets the mark status of the task.
	 * @param isMarked the new mark status
	 */
	public void setIsMarked(boolean isMarked) { this.isMarked = isMarked; }

	/**
	 * Marks the task as completed.
	 */
	public void mark() {
		setIsMarked(true);
	}

	/**
	 * Unmarks the task, setting it as not completed.
	 */
	public void unmark() {
		setIsMarked(false);
	}

	/**
	 * Returns the ID of the task.
	 * @return the ID of the task
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Sets the ID of the task.
	 * @param id the new ID of the task
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the name of the task.
	 * @return the name of the task
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns a string representation of the task.
	 * @return a string representation of the task in the format "[x] name" or "[ ] name"
	 */
	@Override
	public String toString() {
		String check = this.isMarked ? "x" : " ";
		return "[" + check + "] " + this.name;
	}
}
