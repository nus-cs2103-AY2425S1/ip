package bobbybot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File taskListFile;

    Storage(String filePath) {
        taskListFile = new File(filePath);
        try {
            taskListFile.getParentFile().mkdirs();
            taskListFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating file");
        }
    }

    public ArrayList<Task> getTasksFromFile() throws Exception {
        Scanner fileScanner = new Scanner(taskListFile);
        ArrayList<Task> tasks = new ArrayList<>();
        while (fileScanner.hasNext()) {
            String task = fileScanner.nextLine();
            String[] taskDetails = task.split(" \\| ");
            String taskType = taskDetails[0].trim();
            boolean isDone = taskDetails[1].trim().equals("1");
            String description = taskDetails[2].trim();
            Task newTask;
            switch (taskType) {
            case "T":
                newTask = new ToDo(description);
                break;
            case "D":
                newTask = new Deadline(description, taskDetails[3].trim());
                break;
            case "E":
                newTask = new Event(description, taskDetails[3].trim(), taskDetails[4].trim());
                break;
            default:
                continue;
            }
            newTask.setDone(isDone);
            tasks.add(newTask);
        }
        return tasks;
    }

    public void saveTasksToFile(Task[] tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(taskListFile);
        for (Task task : tasks) {
            fileWriter.write(task.getFileString() + "\n");
        }
        fileWriter.close();
    }


}
