package Storage;

import Task.Deadline;
import Task.Event;
import Task.TaskList;
import Task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Storage {
    private String filePath;

    // Constructor to initialize filePath
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // Method to set the file path
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    // Method to get the current file path
    public String getFilePath() {
        return filePath;
    }

    public void clearSave() {
        try (FileWriter saveFile = new FileWriter(filePath)) {
            saveFile.write("");
        } catch (IOException e) {
            System.out.println("There was an error clearing your saved data. Details can be found below.");
            System.out.println(e.getMessage());
        }
    }

    // Method to save data to the file
    public void saveData() {
        try (FileWriter saveFile = new FileWriter(filePath)) {
            int numTask = TaskList.mainTaskList.getNumTasks();
            for (int i = 0; i < numTask; i++) {
                saveFile.write(TaskList.mainTaskList.getTaskFileFormat(i));
                if (i != numTask - 1) {
                    saveFile.write("\n");
                }
            }
        } catch (IOException e) {
            System.out.println("There was an error saving your data. Details can be found below.");
            System.out.println(e.getMessage());
        }
    }

    // Method to load data from the file
    public void loadData() {
        try (Scanner reader = new Scanner(new File(filePath))) {
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String taskType = data.substring(0, 1);
                String[] taskDetails = data.split(" \\| ");
                switch (taskType) {
                    case "E" -> Event.load(taskDetails);
                    case "D" -> Deadline.load(taskDetails);
                    case "T" -> ToDo.load(taskDetails);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while loading the data.");
            System.out.println(e.getMessage());
        }
    }
}
