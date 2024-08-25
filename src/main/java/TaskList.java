import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


class TaskList {
    private ArrayList<Task> tasks;
    private Path filePath;

    TaskList(Path filePath) {
        this.tasks = new ArrayList<Task>();
        this.filePath = filePath;
        // check if file exists
        if (Files.exists(filePath)) {
            loadFromFile();
        } 
    }

    // add
    void add(Task task) {
        tasks.add(task);
        saveToFile();
    }

    // delete
    String remove(int index) throws IndexOutOfBoundsException {
        if (index < 1 || index > tasks.size()) {
            throw new IndexOutOfBoundsException(
                    "Invalid task index. Expected index between 1 and " + tasks.size());
        }
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        saveToFile();
        return "Task deleted: " + task.toString();
    }

    // save to file
    void saveToFile() {
        try {
            // Create directories if they don't exist
            Files.createDirectories(this.filePath.getParent());
            
            // Create file if it doesn't exist
            if (!Files.exists(this.filePath)) {
                Files.createFile(this.filePath);
            }
            
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.filePath.toFile()))) {
                oos.writeObject(this.tasks);
                // System.out.println("ArrayList saved to file successfully.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath.toString()))) {
            this.tasks = (ArrayList<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    // list
    String list() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            str.append(tasks.get(i).toString() + '\n');
        }
        if (!str.isEmpty()) str.deleteCharAt(str.length() - 1);
        return str.toString();
    }

    String setCompleted(int index, boolean status) throws IndexOutOfBoundsException {
        if (index < 1 || index > tasks.size()) {
            throw new IndexOutOfBoundsException(
                    "Invalid task index. Expected index between 1 and " + tasks.size());
        }
        Task task = tasks.get(index);
        task.setCompleted(status);
        saveToFile();
        return "Task marked as" + (status ? "completed" : "incomplete") + ": " + task.toString();
    }

}
