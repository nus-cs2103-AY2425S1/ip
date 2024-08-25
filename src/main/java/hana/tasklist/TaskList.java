package hana.tasklist;

import hana.HanaException;
import hana.task.Task;

import java.util.ArrayList;

/**
 * Contains the task list
 * Has operations to manipulate tasks in list
 */
public class TaskList {
	private static final int MAX_TASKS = 100;
	private static ArrayList<Task> tasks;


	public TaskList() {
		tasks = new ArrayList<>();
	}

	public TaskList(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}

	/**
	 * Adds task to task list.
	 *
	 * @param task The task to be added.
	 * @throws HanaException if task list is full
	 */
	public void addTask(Task task) throws HanaException {
		if (tasks.size() < MAX_TASKS) {
			tasks.add(task);
		} else {
			throw new HanaException("Task list is full!");
		}
	}

	/**
	 * Mark task as done or not done.
	 *
	 * @param taskNumber The task number to be marked.
	 * @param isDone The status for the task to be marked as.
	 * @throws HanaException if task number is invalid.
	 */
	public void markTask(int taskNumber, boolean isDone) throws HanaException {
		if (taskNumber > 0 && taskNumber <= tasks.size()) {
			Task task = tasks.get(taskNumber - 1);
			task.setDone(isDone);
		} else {
			throw new HanaException("Invalid task number! Task number must be between 1 and " + tasks.size() + ".");
		}
	}

	/**
	 * Delete task from task list.
	 *
	 * @param taskNumber The task number delete.
	 * @throws HanaException if task number is invalid.
	 */
	public void deleteTask(int taskNumber) throws HanaException {
		if (taskNumber > 0 && taskNumber <= tasks.size()) {
			Task removedTask = tasks.remove(taskNumber - 1);
		} else {
			throw new HanaException("Invalid task number! Task number must be between 1 and " + tasks.size() + ".");
		}
	}

	/**
	 * Returns if task list is empty.
	 *
	 * @return True if task list is empty, false if it is not.
	 */
	public boolean isEmpty() {
		return tasks.isEmpty();
	}
}
