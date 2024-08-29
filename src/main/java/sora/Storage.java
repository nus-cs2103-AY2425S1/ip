package sora;

import sora.Tasks.Deadline;
import sora.Tasks.Event;
import sora.Tasks.Task;
import sora.Tasks.TaskList;
import sora.Tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private String saveTaskString(TaskList taskList) {
        return taskList.getTaskList().stream()
                .map(Task::getTaskDetails)
                .map(x -> String.join(" | ", x) + "\n")
                .reduce("", (x, y) -> x + y);
    }

    public void saveTaskList(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            fileWriter.write(saveTaskString(taskList));
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("\tSora is unable to write to file" + Ui.HORIZONTAL_LINE);
        }
    }

    public void loadTaskList(TaskList taskList) throws SoraException {
        File file = new File(this.filePath);
        try {
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            Scanner fileScanner = new Scanner(file);
            int lineNumber = 1;
            while (fileScanner.hasNext()) {
                try {
                    loadTaskListHelper(lineNumber, fileScanner.nextLine(), taskList);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    lineNumber++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadTaskListHelper(int lineNumber, String fileTaskDetails, TaskList taskList) throws SoraException {
        String[] parsedFileTaskDetails = fileTaskDetails.split(" \\| ", 5);
        switch (parsedFileTaskDetails[0]) {
            case "T":
                if (parsedFileTaskDetails.length != 3) {
                    throw new SoraException("\tSora is unable to read file from line " + lineNumber);
                }
                taskList.getTaskList().add(new ToDo(parsedFileTaskDetails[2]));
                break;
            case "D":
                if (parsedFileTaskDetails.length != 4) {
                    throw new SoraException("\tSora is unable to read file from line " + lineNumber);
                }
                taskList.getTaskList().add(new Deadline(parsedFileTaskDetails[2],
                        parsedFileTaskDetails[3]));
                break;
            case "E":
                if (parsedFileTaskDetails.length != 5) {
                    throw new SoraException("\tSora is unable to read file from line " + lineNumber);
                }
                taskList.getTaskList().add(new Event(parsedFileTaskDetails[2],
                        parsedFileTaskDetails[3],
                        parsedFileTaskDetails[4]));
                break;
        }
        if (parsedFileTaskDetails[1].equalsIgnoreCase("X")) {
            taskList.getTaskList().get(taskList.getSize() - 1).markAsDone();
        }
    }
}
