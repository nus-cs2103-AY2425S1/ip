package utility;

import tasktypes.Deadline;
import tasktypes.Event;
import tasktypes.Task;
import tasktypes.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 * The {@code Storage} class handles the creation, reading, writing,
 * and synchronization of task data to a file.
 */
public class Storage {
    
    /** The file path where the task data is stored. */
    private String filePath;
    
    /**
     * Constructs a {@code Storage} object with the specified file path.
     *
     * @param filePath the path to the file where task data will be stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    
    /**
     * Creates a new file if it does not already exist.
     * If the file already exists, no action is taken.
     */
    private void createFile() {
        try {
            File myObj = new File(this.filePath);
            myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    /**
     * Writes the specified text to the file.
     * If the file does not exist, it is created.
     *
     * @param textToAdd the text to write to the file
     */
    public void writeToFile(String textToAdd) {
        try {
            File dataFile = new File(this.filePath);
            if (!dataFile.exists()) {
                createFile();
            }
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
    
    /**
     * Loads tasks from the file and returns them as an {@code ArrayList} of {@code Task} objects.
     * If the file is not found, an empty list is returned.
     *
     * @return an {@code ArrayList} of tasks loaded from the file
     */
    public ArrayList<Task> load() {
        try {
            ArrayList<Task> taskList = new ArrayList<>();
            File file = new File(this.filePath);
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNext()) {
                String task = scanner.nextLine();
                String[] taskProcessed = task.split("\\|");
                switch (taskProcessed[0].trim()) {
                case "T":
                    taskList.add(new ToDo(taskProcessed[2], Integer.parseInt(taskProcessed[1].trim()) == 1));
                    break;
                case "E":
                    taskList.add(new Event(taskProcessed[2], LocalDate.parse(taskProcessed[3].trim()),  LocalDate.parse(taskProcessed[4].trim()),
                        Integer.parseInt(taskProcessed[1].trim()) == 1));
                    break;
                case "D":
                    taskList.add(new Deadline(taskProcessed[2],  LocalDate.parse(taskProcessed[3].trim()),
                    Integer.parseInt(taskProcessed[1].trim()) == 1));
                    break;
                }
            }
            return taskList;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return new ArrayList<>();
    }
    
    /**
     * Synchronizes the task list with the file by writing the updated list to the file.
     *
     * @param TaskLists an {@code ArrayList} of tasks to be synchronized with the file
     */
    public void synchronizeTasks(ArrayList<Task> TaskLists) {
        String updatedTaskList = TaskLists.stream().map(Task::storageFormat)
            .reduce("", (firstLine, secondLine) -> firstLine + secondLine);
        this.writeToFile(updatedTaskList);
    }
}
