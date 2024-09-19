package fridayproject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

/*
 * Represents the storage of the program.
 * Storage deals with loading and saving tasks to a file.
 * The file path is specified by the user.
 */
public class Storage {
    private String filePath;

    /*
     * Constructor for Storage class.
     * @param filePath
     */
    public Storage(String filePath) {
        // Assertions to ensure that the file path is not null
        assert this.filePath != null : "File path should not be null";
        this.filePath = filePath;
    }

    /*
     * Saves the tasks to a file.
     * @param tasks
     * @throws IOException
     */
    public void saveTasksToFile(ArrayList<Tasks> tasks) throws IOException {

        // Assertions to ensure that the tasks are not null
        assert tasks != null : "Tasks should not be null";

        FileWriter fileWriter = new FileWriter(filePath);
        for (Tasks task : tasks) {
            fileWriter.write(task.toFileString() + "\n");
        }
        fileWriter.close();
    }

    /*
     * Loads the tasks from a file.
     * @return The list of loaded tasks.
     * @throws IOException
     */
     public ArrayList<Tasks> loadTasks() throws IOException {
        File file = new File(filePath);
        ArrayList<Tasks> tasks = new ArrayList<>();
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return tasks;
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String taskString = scanner.nextLine().trim();

            if (taskString.isEmpty()) {
                continue;
            }

            String[] parts = taskString.split("\\s*\\|\\s*");

            try {
                switch (parts[0]) {
                case "T":
                    assert parts.length == 3 : "Length should be 3";

                    if (parts.length == 3) {
                        Tasks todo = new Todo(parts[2]);
                        if(parts[1].equals("1")) {
                            todo.markAsDone();
                        }
                        tasks.add(todo);
                    } 
                    break;
                case "D":
                    assert parts.length == 4 : "Length should be 4";

                    if (parts.length == 4) {
                        Tasks deadline = new Deadline(parts[2], parts[3]);
                        if (parts[1].equals("1")) {
                            deadline.markAsDone();
                        }
                        tasks.add(deadline);
                    } 
                    break;
                case "E":
                    assert parts.length == 5 : "Length should be 5";
                    
                    if (parts.length == 5) {
                        Tasks event = new Event(parts[2], parts[3], parts[4]);
                        if (parts[1].equals("1")) {
                            event.markAsDone();
                        }
                        tasks.add(event);
                    } 
                    break;
                default:
                    System.out.println("Error loading task: " + taskString);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error loading task: " + taskString);
            }
        }
        scanner.close();
        return tasks;
    }
}
