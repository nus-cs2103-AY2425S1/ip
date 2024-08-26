package tasks;
import java.io.*;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;
    private final String txtFileName = "./data/TaskList.txt";
    private final String datFileName = "./data/TaskList.dat";
    
    public TaskList() {
        this.taskList = new ArrayList<>();
        loadTasksFromFile();
    }

    public void addTask(Task task) {
        taskList.add(task);
        saveTasksToFile();
    }

    public void markAsDone(int index) {
        taskList.get(index).markAsDone();
        saveTasksToFile();
    }

    public void markAsUndone(int index) {
        taskList.get(index).unmarkAsDone();
        saveTasksToFile();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
        saveTasksToFile();
    }

    public int listLength() {
        return taskList.size();
    }

    @Override
    public String toString() {
        // suggested by Copilot
        if (taskList.isEmpty()) {
            return "You have no tasks in the list.\n";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            sb.append((i + 1)).append(". ").append(taskList.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    private void saveTasksToFile() {
        // Assisted by Copilot
        File dataDir = new File("./data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }   
        try (
            FileWriter writer = new FileWriter(txtFileName);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(datFileName))
        ) {
            for (Task task : taskList) {
                writer.write(task.toString() + "\n");
            }
            oos.writeObject(taskList);
        } catch (IOException e) {
            System.err.println("An error occurred while saving tasks to file: " + e.getMessage());
        }
    }

    private void loadTasksFromFile() {
        // Assisted by Copilot
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(datFileName))) {
            taskList.addAll((ArrayList<Task>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("An error occurred while loading tasks from file: " + e.getMessage());
        }
    }
}