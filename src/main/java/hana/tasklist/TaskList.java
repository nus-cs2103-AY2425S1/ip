package hana.tasklist;

import hana.HanaException;
import hana.task.Task;

import java.util.ArrayList;

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

	public void addTask(Task task) throws HanaException {
		if (tasks.size() < MAX_TASKS) {
			tasks.add(task);
		} else {
			throw new HanaException("Task list is full!");
		}
	}

	public void markTask(int taskNumber, boolean isDone) throws HanaException {
		if (taskNumber > 0 && taskNumber <= tasks.size()) {
			Task task = tasks.get(taskNumber - 1);
			task.setDone(isDone);
		} else {
			throw new HanaException("Invalid task number! Task number must be between 1 and " + tasks.size() + ".");
		}
	}

	public void deleteTask(int taskNumber) throws HanaException {
		if (taskNumber > 0 && taskNumber <= tasks.size()) {
			Task removedTask = tasks.remove(taskNumber - 1);
		} else {
			throw new HanaException("Invalid task number! Task number must be between 1 and " + tasks.size() + ".");
		}
	}

	public boolean isEmpty() {
		return tasks.isEmpty();
	}

    /**
     * Finds tasks containing the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks containing the keyword.
     */
    public ArrayList<Task> findTasksByKeyword(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                result.add(task);
            }
        }
        return result;
    }
}
