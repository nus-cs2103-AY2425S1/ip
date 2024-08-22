import java.util.ArrayList;

public class Storage {
    ArrayList<Task> TaskLists;
    
    public Storage() {
        this.TaskLists = new ArrayList<Task>();
    }
    
    public void storeTask(Task newTask) {
        this.TaskLists.add(newTask);
    }
    
    public String lastTask() {
        if (!this.TaskLists.isEmpty()) {
            if (this.TaskLists.get(this.TaskLists.size() - 1).getDescription().equals("bye"))
                return "Bye. Hope to see you again soon!";
            else {
                return this.TaskLists.get(this.TaskLists.size() - 1).getDescription();
            }
        }
        else {
            return "";
        }
    }
    
    public String listWord() {
        StringBuilder result = new StringBuilder();
            for (int i =0; i < this.TaskLists.size(); i++) {
                result.append(i + 1).append(". ")
                        .append(this.TaskLists.get(i).getStatusIcon())
                        .append(" ")
                        .append(this.TaskLists.get(i).getDescription()).append("\n");
            }
        return result.toString();
    }
    
    public String modifyOperation(int index, boolean markStatus) {
        int normalizedIndex = index -1;
        StringBuilder result = new StringBuilder();
        if (markStatus) {
            this.TaskLists.get(normalizedIndex).markAsDone();
        } else {
            this.TaskLists.get(normalizedIndex).markAsUndone();
        }
        result.append(this.TaskLists.get(normalizedIndex).getStatusIcon())
                .append(" ")  // Adding a space here
                .append(this.TaskLists.get(normalizedIndex).getDescription());
        return result.toString();
    }
    
}
