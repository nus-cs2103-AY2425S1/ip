package screwllum.tasks;

import java.util.List;

public class TaskManager {
    private List<Task> taskList;
    
    public TaskManager(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void execute(List<String> tokens) {
        switch (tokens.get(0)) {
        case "toggle":
            taskList.get(Integer.parseInt(tokens.get(1))).toggleStatus();
            break;
        case "delete":
            taskList.remove(Integer.parseInt(tokens.get(1)));
            break;
        case "todo":
            taskList.add(new ToDo(tokens.get(1)));
            break;
        case "deadline":
            taskList.add(new Deadline(tokens.get(1), tokens.get(2)));
            break;
        case "event":
            taskList.add(new Event(tokens.get(1), tokens.get(2), tokens.get(3)));
            break;
        }
    }
    
    public List<Task> getTaskList() {
        return taskList;
    }
}
