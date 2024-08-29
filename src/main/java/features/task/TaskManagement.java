package features.task;

import java.util.*;
import config.Config;
import data.TaskDAO;

public class TaskManagement {
	public int length;
	private TaskDAO taskDAO;

	public TaskManagement(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
		this.length = taskDAO.getAllTasks().size();
	}

	public void add(Task task) {
		length++;
		taskDAO.addTask(task);
	}

	public Task remove(int id) {
		length--;
		return taskDAO.deleteTask(id);
	}

	public String getPrintTasks() {
		StringBuilder res = new StringBuilder();
		List<Task> tasks = taskDAO.getAllTasks();

		for (int i = 0; i < tasks.size(); i++ ) {
			Task t = tasks.get(i);
			res.append(Config.INDENTATION + (i+1) + ". " + t);
			if (i != tasks.size() - 1) {
				res.append("\n");	
			}
		}
		return res.toString();	
	}

	public void handleItem(String action, int id) {
		Optional<Task> task = this.findTaskById(id);
		System.out.println(task);
		switch (action) {
			case "mark":
				task.ifPresent((t) -> {
					t.mark();
					taskDAO.updateTask(t);
				});
				break;
			case "unmark":
				task.ifPresent(t -> {
					t.unmark();
					taskDAO.updateTask(t);
				});
				break;
			default:
				break;
					
		}	
	}	

	public Optional<Task> findTaskById(int id) {
		return taskDAO.findTaskById(id);
	}
}
