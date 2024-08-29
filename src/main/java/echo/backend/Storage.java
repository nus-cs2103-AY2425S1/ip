package echo.backend;

import echo.EchoException;
import echo.task.TaskList;
import echo.task.TaskType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private final TaskList taskList;
    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskList = new TaskList();
    }
    public TaskList load() throws EchoException {
        File savedTasks  = new File(filePath);

        if (!savedTasks.exists()) {
            File parentDirectory = new File(savedTasks.getParent());
            if (!parentDirectory.exists()) {
                parentDirectory.mkdir();
            }

            try {
                savedTasks.createNewFile();
            } catch (IOException ex) {
                System.out.println("Cannot create new 'savedTasks.txt' file.");
                ex.printStackTrace();
                throw new EchoException();
            }
        }

        Scanner fileScanner;
        try {
            fileScanner = new Scanner(savedTasks);
        } catch (FileNotFoundException e) {
            System.out.println("'savedTasks.txt' file not found.");
            throw new EchoException();
        }

        String nextLine;
        String[] splitLines;

        while (fileScanner.hasNext()) {
            nextLine = fileScanner.nextLine();
            splitLines = nextLine.split("\\|");

            String taskType  = splitLines[0].trim();
            switch(taskType) {
                case "T":
                    taskList.addTask(
                            splitLines[2].trim(),
                            TaskType.TODO,
                            "");
                    break;
                case "D":
                    taskList.addDeadline(
                            splitLines[2].trim(),
                            LocalDate.parse(splitLines[3].trim()));
                    break;
                case "E":
                    taskList.addTask(
                            splitLines[2].trim(),
                            TaskType.EVENT,
                            splitLines[3].trim());
                    break;
            }

            if (Integer.valueOf(splitLines[1].trim()) == 1) {
                taskList.markTask(taskList.getNumTasks());
            }
        }
        return this.taskList;
    }
    public void saveToFile() {
        FileWriter fileWriter =  null;
        try {
            fileWriter = new FileWriter(filePath);
            fileWriter.write(taskList.getTasksToSave());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Failed to write to file");
        }
    }
}
