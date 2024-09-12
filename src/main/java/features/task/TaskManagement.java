package features.task;

import java.util.*;
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
		StringBuilder res = new StringBuilder();
		List<Task> tasks = taskDAO.getAllTasks();

		for (int i = 0; i < tasks.size(); i++ ) {
			Task t = tasks.get(i);
			res.append(Config.INDENTATION).append(i + 1).append(". ").append(t);
			if (i != tasks.size() - 1) {
				res.append("\n");
			}
		}
		return res.toString();
	}

	public String getPrintTasks(List<Task> tasks) {
		StringBuilder res = new StringBuilder();

		for (int i = 0; i < tasks.size(); i++ ) {
			Task t = tasks.get(i);
			res.append(Config.INDENTATION).append(i + 1).append(". ").append(t);
			if (i != tasks.size() - 1) {
				res.append("\n");
			}
		}
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
