package yapper.main;

import yapper.exceptions.YapperException;
import yapper.exceptions.YapperFileFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private File referenceFile;

    public Storage(String filePath) {
        this.filePath = filePath;
        initialiseFile();
    }

    private void initialiseFile() {
        this.referenceFile = new File(this.filePath);

        File directory = this.referenceFile.getParentFile();
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }

        // Creates the data file if it doesn't exist
        try {
            if (!this.referenceFile.exists()) {
                this.referenceFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reads the files contents
        try {
            readFile();
        } catch (YapperException e) {
            Ui.errorCaught(e.getMessage());
        }
    }

    public ArrayList<Task> readFile() throws YapperException {
        ArrayList<Task> taskList = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(this.referenceFile)) {
            while (fileScanner.hasNextLine()) {
                String[] taskInfo = fileScanner.nextLine().split("\\|");
                if (taskInfo.length < 4) {
                    throw new YapperFileFormatException("File is corrupted in one way or another");
                }
                String taskType = taskInfo[1].trim();
                String taskStatus = taskInfo[2].trim();
                String taskDesc = taskInfo[3].trim();
                Task task = null;
                switch (taskType) {
                case "T":
                    task = new ToDo(taskDesc);
                    break;
                case "D":
                    task = new Deadline(taskDesc, taskInfo[4].trim());
                    break;
                case "E":
                    String[] timeRange = taskInfo[4].trim().split("-----");
                    if (timeRange.length != 2) {
                        throw new YapperFileFormatException("Invalid event range detected");
                    }
                    task = new Event(taskDesc, timeRange[0], timeRange[1]);
                    break;
                default:
                    throw new YapperFileFormatException("yapper.main.Task type not recognised: " + taskType);
                }
                if ("X".equals(taskStatus)) {
                    task.mark();
                } else if (!taskStatus.isEmpty()) {
                    throw new YapperFileFormatException("yapper.main.Task status not recognised: " + taskStatus);
                }
                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new YapperFileFormatException("File not found");
        }
        return taskList;
    }
}
