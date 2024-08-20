import java.util.*;

public class TaskManagement {
	private List<Task> tasks;
	public int length;

	public TaskManagement() {
		this.tasks = new ArrayList<>();
		this.length = 0;
	}

	public void add(Task task) {
		int len = this.tasks.size();
		task.setId(len + 1);
		this.tasks.add(task);
		this.length++;
	}

	public Task remove(int id) {
		Task t = this.findTaskById(id).get(); 
		this.tasks = this.tasks.stream()
								.filter(task -> !task.getId().equals(id))
								.toList();
		this.length--;
		return t;
	}

	public String getPrintTasks() {
		StringBuilder res = new StringBuilder();
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

		switch (action) {
			case "mark":
				task.ifPresent(t -> t.mark());
				break;
			case "unmark":
				task.ifPresent(t -> t.unmark());
				break;
			default:
				break;
					
		}	
	}	

	public Optional<Task> findTaskById(int id) {
		return tasks.stream()
					.filter(task -> task.getId().equals(id))
					.findFirst();	
	}
}
