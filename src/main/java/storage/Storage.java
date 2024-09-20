package storage;

import task.Event;
import task.ToDo;
import task.Task;
import task.Deadline;

import java.io.BufferedReader;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class Storage {
    private final String filePath;
    private static final DateTimeFormatter DESIRED_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        assert file.exists() : "Well... This file surprisingly, does not exist";

        // create the txt file if it does not exist
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return tasks;
       }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String tempInfo;
        while ((tempInfo = reader.readLine()) != null) {
            String[] taskInfo = tempInfo.split(" \\| ");
            String taskType = taskInfo[0];
            boolean isDone = taskInfo[1].equals("1");
            String des = taskInfo[2];

            Task task;
            switch (taskType) {
            case "T":
                task = new ToDo(des);
                break;
            case "D":
                if (taskInfo.length > 3) {
                    task = new Deadline(des, taskInfo[3]);
                } else {
                    System.out.println("Hey man, I am missing info for deadline task in storage file.");
                    continue;
                }
                break;
            case "E":
                if (taskInfo.length > 4) {
                    task = new Event(des, taskInfo[3], taskInfo[4]);
                } else {
                    System.out.println("Hey man, I am missing info for event task data in storage file.");
                    continue;
                }
                break;
            default:
                System.out.println("Well, I have never seen this task type before.");
                continue;
            }

            if (isDone) {
                task.markAsDone();
            }
            tasks.add(task);
        }
        reader.close();
        return tasks;
    }

    public void save() throws IOException {
        save(null);
    }

    public void save(List<Task> tasks) throws IOException {
        assert tasks != null : "Hey man, the task list should not be null.";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (Task task : tasks) {
            writer.write(taskToFile(task));
            writer.newLine();
        }
        writer.close();
    }

    /**
     * Writes the tasks onto the file.
     *
     * @param task which has been written
     * @return task onto the handsome.txt file
     */
    private String taskToFile(Task task) {
        StringBuilder storedTask = new StringBuilder();

        assert task != null : "Well, seems like I'm writing a null file.";

        if (task instanceof ToDo) {
            storedTask.append("T");
            storedTask.append(" | ").append(task.isDone ? "1" : "0");
            storedTask.append(" | ").append(task.description);
        } else if (task instanceof Deadline) {
            storedTask.append("D");
            storedTask.append(" | ").append(task.isDone ? "1" : "0");
            storedTask.append(" | ").append(task.description);
            storedTask.append(" | ").append(DESIRED_FORMAT.format(((Deadline) task).by));
        } else if (task instanceof Event) {
            storedTask.append("E");
            storedTask.append(" | ").append(task.isDone ? "1" : "0");
            storedTask.append(" | ").append(task.description);
            storedTask.append(" | ").append(DESIRED_FORMAT.format(((Event) task).from));
            storedTask.append(" | ").append(DESIRED_FORMAT.format(((Event) task).to));
        }
        return storedTask.toString();
    }
}
