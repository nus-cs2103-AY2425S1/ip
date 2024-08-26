import java.util.ArrayList;
import java.io.File;
import java.util.stream.Collector;

public class Storage {
    ArrayList<Task> TaskLists;
    
    public Storage() {
        this.TaskLists = FileUtility.fileContents();
    }
    
    private void updateMemory() {
        String updatedTaskList = this.TaskLists.stream().map(Task::storageFormat).
                reduce("", (firstLine, secondLine) -> firstLine + secondLine);
        FileUtility.writeToFile(updatedTaskList);
    }
    
    public void storeTask(Task newTask) {
        this.TaskLists.add(newTask);
        updateMemory();
    }
    
    public Task lastTask() {
        if (!this.TaskLists.isEmpty()) {
            if (this.TaskLists.get(this.TaskLists.size() - 1).getDescription().equals("bye"))
                return null;
            else {
                return this.TaskLists.get(this.TaskLists.size() - 1);
            }
        }
        else {
            return null;
        }
    }
    
    public String listWord() {
        StringBuilder result = new StringBuilder();
            for (int i =0; i < this.TaskLists.size(); i++) {
                result.append(i + 1).append(". ")
                    .append(this.TaskLists.get(i).toString()).append("\n");
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
        updateMemory();
        result.append(this.TaskLists.get(normalizedIndex).toString());
        return result.toString();
    }
    
    public String deleteOperation(int index) {
        int normalizedIndex = index -1;
        StringBuilder result = new StringBuilder();
        result.append(this.TaskLists.get(normalizedIndex).toString());
        this.TaskLists.remove(normalizedIndex);
        updateMemory();
        return result.toString();
    }
    
    public String getLength() {
        return "Now you have " + String.valueOf(this.TaskLists.size())+ " tasks in the list.";
    }
    
}
