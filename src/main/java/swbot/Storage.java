package swbot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * A class that takes care of loading and saving the tasks to the database of the chatbot
 * so that it can help remember what the user has added to the list in the form of an
 * output file.
 */
public class Storage {
    private String file;

    /**
     * Creates a storage object that stores the file to be written and read upon by the chatbot
     *
     * @param file file path that tells the program where to store the output task list
     */
    public Storage(String file) {
        this.file = file;
    }

    /**
     * Saves the tasks in the database to the output file and keeps track of it
     *
     * @param database current list of tasks stored
     */
    public void saveTasks(ArrayList<Task> database) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(this.file))) {
            for (Task task : database) {
                writer.println(task.toFileFormat());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads the task into a database and then returns the new database
     *
     * @return the new list of tasks after loading
     */
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
                            task = new Todo(parts[2]);
                            assert task.description != null : "task description cannot be empty";
                            break;
                        case "D":
                            task = new Deadline(parts[2], parts[3]);
                            assert task.description != null : "task description cannot be empty";
                            break;
                        case "E":
                            task = new Event(parts[2], parts[3], parts[4]);
                            assert task.description != null : "task description cannot be empty";
                            break;
                        default:
                            task = task;
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
