package utility;

import tasktypes.Task;
import exception.AlphaException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    
    public TaskList(ArrayList<Task> taskList) throws AlphaException {
        if (taskList.isEmpty()) {
            throw new AlphaException("No tasks currently!");
        }
        this.tasks = taskList;
    }
    
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }
    
    public void storeTask(Task newTask) {
        this.tasks.add(newTask);
    }
    
    public Task lastTask() {
        if (!this.tasks.isEmpty()) {
            if (this.tasks.get(this.tasks.size() - 1).getDescription().equals("bye")) {
                return null;
            } else {
                return this.tasks.get(this.tasks.size() - 1);
            }
        }
        else {
            return null;
        }
    }
    
    public String listWord() {
        StringBuilder result = new StringBuilder();
        for (int i =0; i < this.tasks.size(); i++) {
            result.append(i + 1).append(". ")
                .append(this.tasks.get(i).toString()).append("\n");
        }
        return result.toString();
    }
    
    public String modifyOperation(int index, boolean markStatus) {
        int normalizedIndex = index -1;
        StringBuilder result = new StringBuilder();
        if (markStatus) {
            this.tasks.get(normalizedIndex).markAsDone();
        } else {
            this.tasks.get(normalizedIndex).markAsUndone();
        }
        result.append(this.tasks.get(normalizedIndex).toString());
        return result.toString();
    }
    
    public String deleteOperation(int index) {
        int normalizedIndex = index -1;
        StringBuilder result = new StringBuilder();
        result.append(this.tasks.get(normalizedIndex).toString());
        this.tasks.remove(normalizedIndex);
        return result.toString();
    }
    
    public String getLength() {
        return "Now you have " + String.valueOf(this.tasks.size())+ " tasks in the list.";
    }
    
    public ArrayList<Task> getTaskLists() {
        return this.tasks;
    }
    
}
