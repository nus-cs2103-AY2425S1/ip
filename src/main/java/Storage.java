import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String file;

    public Storage(String file) {
        this.file = file;
    }

    public void saveTasks(ArrayList<Task> database) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(this.file))) {
            for (Task task : database) {
                writer.println(task.toFileFormat());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> database = new ArrayList<>();
        File file = new File(this.file);
        File directory = new File(file.getParent());
        if (!directory.exists()) {
            directory.mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(this.file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                Task task = null;
                try {
                    switch (parts[0]) {
                        case "T":
                            task = new Todo(parts[2]); // Create Todo task
                            break;
                        case "D":
                            task = new Deadline(parts[2], parts[3]); // Create Deadline task
                            break;
                        case "E":
                            task = new Event(parts[2], parts[3], parts[4]); // Create Event task
                            break;
                    }
                    if (task != null) {
                        task.setDone(parts[1].equals("1")); // Set the task's done status
                        database.add(task); // Add task to the database
                    }
                } catch (BuzzException e) {
                    System.out.println("ERROR!: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return database;
    }

}
