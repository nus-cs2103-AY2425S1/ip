package screwllum.tasks;

import java.util.List;

public class TaskManager {
    private List<Task> taskList;
    
    public TaskManager(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task task, List<Task> taskList) {
        taskList.add(task);
        System.out.println("Understood, I have added to your list:\n" + task.toString());
        System.out.println("You have " + taskList.size() + " tasks");
    }
    
    public void delete(int index) {
        try {
            taskList.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            
        }
    }
}
