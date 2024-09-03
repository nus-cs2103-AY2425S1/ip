import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Tasks {
    private ArrayList<Task> list;
    private final TaskFileManager manager;

    public Tasks(Path saveLocation) {
        list = new ArrayList<>();
        manager = new TaskFileManager(saveLocation);
    }

    public void initialiseExistingTasks() throws IOException {
        list = new ArrayList<>(List.of(manager.readTasksFromFile()));
    }

    public void addTask(Task task) throws IOException {
        list.add(task);
        saveTasks();
    }

    public Task deleteTask(int taskNumber) throws IOException {
        if (list.isEmpty()) {
            throw new IllegalStateException("There are no tasks that can be chosen for deletion.");
        }

        if (taskNumber < 1 || taskNumber > list.size()) {
            throw new IllegalArgumentException(
                    String.format("Task does not exist. Number must be within the range 1 to %s.", list.size()));
        }

        Task removedTask = list.remove(taskNumber - 1);
        saveTasks();
        return removedTask;
    }

    public Task completeTask(int taskNumber) throws IOException {
        if (list.isEmpty()) {
            throw new IllegalStateException("There are no tasks that can be chosen to be marked as complete.");
        }

        if (taskNumber < 1 || taskNumber > list.size()) {
            throw new IllegalArgumentException(
                    String.format("Task does not exist. Number must be within the range 1 to %s.", list.size()));
        }

        Task t = list.get(taskNumber - 1);
        t.setComplete(true);
        saveTasks();
        return t;
    }

    public Task uncompleteTask(int taskNumber) throws IOException {
        if (list.isEmpty()) {
            throw new IllegalStateException("There are no tasks that can be chosen to be marked as incomplete.");
        }

        if (taskNumber < 1 || taskNumber > list.size()) {
            throw new IllegalArgumentException(
                    String.format("Task does not exist. Number must be within the range 1 to %s.", list.size()));
        }

        Task t = list.get(taskNumber - 1);
        t.setComplete(false);
        saveTasks();
        return t;
    }

    public int getSize() {
        return list.size();
    }

    @Override
    public String toString() {
        if (list.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            result.append(String.format("%d.%s%n", i + 1, list.get(i)));
        }

        // exclude the last newline character from getting printed
        return result.substring(0, result.length() - 2);
    }

    private void saveTasks() throws IOException{
        manager.saveTasksToFile(list.toArray(new Task[0]));
    }
}
