package mryapper.storagemanager;

import mryapper.exception.InvalidFileDataException;
import mryapper.task.TaskList;
import mryapper.task.Task;
import mryapper.task.Deadline;
import mryapper.task.Todo;
import mryapper.task.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class StorageManager {
    private final String filePath;

    public StorageManager(String filePath) {
        this.filePath = filePath;
    }

    public TaskList retrieveData() throws IOException {
        File taskData = new File(filePath);
        try {
            if (!taskData.exists()) {
                throw new InvalidFileDataException(" It seems that there isn't a data file!");
            }
            ArrayList<Task> taskList = new ArrayList<>(100);
            Scanner dataFileReader = new Scanner(taskData);
            while (dataFileReader.hasNextLine()) {
                taskList.add(readTaskData(dataFileReader.nextLine()));
            }
            dataFileReader.close();
            return new TaskList(taskList);
        } catch (InvalidFileDataException e) {
            System.out.println(e.getMessage() + "\n");
            boolean fileCreationSuccessful = taskData.createNewFile();
            if (fileCreationSuccessful) {
                System.out.println(" A new data file has been created successfully");
                return new TaskList();
            } else {
                return null;
            }
        }
    }

    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task: tasks) {
            fw.write(task.getDataString());
        }
        fw.close();
    }

    // reads the task data from the data file and adds task into the task ArrayList
    private Task readTaskData(String taskData) throws InvalidFileDataException {
        try {
            String[] processedData = taskData.split(" \\|\\|\\| ");
            Task task;
            String taskDescription = processedData[2];

            switch (processedData[0]) {
            case "T":
                task = new Todo(taskDescription);
                break;
            case "D":
                task = new Deadline(taskDescription, processedData[3]);
                break;
            case "E":
                task = new Event(taskDescription, processedData[3], processedData[4]);
                break;
            default:
                throw new InvalidFileDataException(taskData);
            }

            boolean taskIsDone;
            taskIsDone = Integer.parseInt(processedData[1]) > 0;
            if (taskIsDone) {
                task.markAsDone();
            }

            return task;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidFileDataException(" The data file is not in the correct format :(\n");
        }
    }
}
