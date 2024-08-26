package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import data.TaskList;
import data.exception.BarneyException;
import data.exception.InvalidSaveFormatException;
import data.task.DeadlineTask;
import data.task.EventTask;
import data.task.Task;
import data.task.TodoTask;

public class Storage {

    private static final String SAVE_FILE_DELIMITER = "###";
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private ArrayList<Task> readFile() throws FileNotFoundException, InvalidSaveFormatException {
        ArrayList<Task> taskList = new ArrayList<Task>();
        File listFile = new File(filePath);
        Scanner fileScanner = new Scanner(listFile);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] taskData = line.split(SAVE_FILE_DELIMITER);

            Task newTask;

            // description
            String description = taskData[1];

            // taskType
            String taskType = taskData[2];
            switch (taskType) {
            case "T":
                newTask = new TodoTask(description);
                break;
            case "D":
                newTask = new DeadlineTask(description, taskData[3]);
                break;
            case "E":
                newTask = new EventTask(description, taskData[3], taskData[4]);
                break;
            default:
                throw new InvalidSaveFormatException("Invalid task type in the file: " + taskData[2]);
            }

            // isMarked
            switch (taskData[0]) {
            case "1":
                newTask.mark();
                break;
            case "0":
                newTask.unmark();
                break;
            default:
                throw new InvalidSaveFormatException("Invalid task status in the file: " + taskData[1]);
            }

            taskList.add(newTask);
        }
        fileScanner.close();
        return taskList;
    }

    public ArrayList<Task> loadData() throws BarneyException {
        try {
            return readFile();
        } catch (FileNotFoundException e) {
            throw new BarneyException("File not found: " + e.getMessage());
        } catch (Exception e) {
            throw new BarneyException("Error reading file: " + e.getMessage());
        }
    }

    private void writeFile(ArrayList<Task> taskList) throws FileNotFoundException, IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (Task task : taskList) {
            for (String data : task.toSaveArray()) {
                fileWriter.write(data + SAVE_FILE_DELIMITER);
            }
            fileWriter.write("\n");
        }
        fileWriter.close();
    }

    public void writeData(TaskList taskList) throws BarneyException {
        try {
            writeFile(taskList.getArrayList());
        } catch (FileNotFoundException e) {
            throw new BarneyException("File not found: " + e.getMessage());
        } catch (IOException e) {
            throw new BarneyException("Error writing file: " + e.getMessage());
        } catch (Exception e) {
            throw new BarneyException(e.getMessage());
        }
    }
}
