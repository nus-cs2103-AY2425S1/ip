package features.task;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import config.Config;
import data.TaskDAO;

/**
 * Manages tasks using a TaskDAO for data access.
 * Provides methods to add, remove, and handle tasks, as well as retrieve and print task lists.
 */
public class TaskManagement {
	public int length;
	private TaskDAO taskDAO;

	/**
	 * Constructs a TaskManagement instance with the specified TaskDAO.
	 *
	 * @param taskDAO the TaskDAO to be used for task operations
	 */
	public TaskManagement(TaskDAO taskDAO) {
		assert taskDAO != null : "TaskDAO must not be null.";
		this.taskDAO = taskDAO;
		this.length = taskDAO.getAllTasks().size();
	}

	/**
	 * Returns a list of all tasks managed by the TaskDAO.
	 *
	 * @return a list of all tasks
	 */
	public List<Task> getAllTasks() {
		return taskDAO.getAllTasks();
	}

	/**
	 * Adds a new task and increments the task count.
	 *
	 * @param task the task to be added
	 */
	public void add(Task task) {
		assert task != null : "Task must not be null.";
		length++;
		taskDAO.addTask(task);
	}

	/**
	 * Removes a task by its ID and decrements the task count.
	 *
	 * @param id the ID of the task to be removed
	 * @return the removed task
	 */
	public Task remove(int id) {
		length--;
		return taskDAO.deleteTask(id);
	}

	/**
	 * Returns a formatted string of all tasks, suitable for printing.
	 *
	 * @return a string representing all tasks
	 */
	public String getPrintTasks() {
		List<Task> tasks = taskDAO.getAllTasks();

		return getPrintTasks(tasks);
	}

	/**
	 * Returns a formatted string of all tasks, suitable for printing.
	 *
	 * @param tasks the tasks to be printed
	 * @return a string representing all tasks
	 */
	public String getPrintTasks(List<Task> tasks) {
		StringBuilder res = new StringBuilder();

		AtomicInteger sizeTracker = new AtomicInteger();
		tasks.stream()
				.map(t -> Config.INDENTATION + (tasks.indexOf(t) + 1) + ". " + t)
				.forEach(t -> {
					res.append(t);
					if (sizeTracker.get() != length - 1) {
						res.append("\n");
					}
					sizeTracker.getAndIncrement();
				});

		return res.toString();
	}

	/**
	 * Handles marking or unmarking a task based on the action and task ID.
	 *
	 * @param action the action to be performed ("mark" or "unmark")
	 * @param id the ID of the task to be handled
	 */
	public void handleItem(String action, int id) {
		Optional<Task> task = this.findTaskById(id);
		System.out.println(task);
		switch (action) {
			case "mark" -> task.ifPresent((t) -> {
				t.mark();
				taskDAO.updateTask(t);
			});
			case "unmark" -> task.ifPresent(t -> {
				t.unmark();
				taskDAO.updateTask(t);
			});
			default -> {
			}
		}
	}

	/**
	 * Finds a task by its ID.
	 *
	 * @param id the ID of the task to be found
	 * @return an Optional containing the task if found, otherwise an empty Optional
	 */
	public Optional<Task> findTaskById(int id) {
		return taskDAO.findTaskById(id);
	}
}
