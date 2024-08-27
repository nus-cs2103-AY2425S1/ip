package pikappi;

import pikappi.exception.PikappiException;
import pikappi.task.DeadlineTask;
import pikappi.task.EventTask;
import pikappi.task.Task;
import pikappi.task.TodoTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    protected String path;
    protected TaskList tasks;

    public Storage(String path) {
        this.path = path;
        this.tasks = new TaskList();
    }

    public TaskList load() throws PikappiException {
        this.loadTasks();
        return this.tasks;
    }

    public void save(TaskList tasks) {
        this.tasks = tasks;
        this.saveTasks();
    }

    public void loadTasks() throws PikappiException {
        File file = new File(this.path);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println("Error creating file!");
            }
        }

        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String currentTask = fileReader.nextLine();
                ArrayList<String> task = new ArrayList<String>(List.of(currentTask.split(" \\| ")));
                loadCurrentTask(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public void loadCurrentTask(ArrayList<String> task) throws PikappiException {
        if (task.size() < 3) {
            throw new PikappiException("Error loading task!");
        }
        String taskType = task.get(0);
        boolean isDone = task.get(1).equals("1");
        String taskDescription = task.get(2);
        String taskTime = "";
        String taskEndTime = "";
        if (task.size() > 3) {
            taskTime = task.get(3);
        }
        if (task.size() > 4) {
            taskEndTime = task.get(4);
        }
        switch (taskType) {
        case "T":
            this.tasks.add(new TodoTask(taskDescription, isDone));
            break;
        case "D":
            this.tasks.add(new DeadlineTask(taskDescription, taskTime, isDone));
            break;
        case "E":
            this.tasks.add(new EventTask(taskDescription, taskTime, taskEndTime, isDone));
            break;
        default:
            throw new PikappiException("Error loading task!");
        }
    }

    public void saveTasks() {
        try {
            FileWriter fileWriter = new FileWriter(this.path);
            for (Task task : tasks.getTasks()) {
                String isDone = task.isDone() ? "1" : "0";
                String description = task.getDescription();
                if (task instanceof DeadlineTask) {
                    String by = ((DeadlineTask) task).getBy();
                    fileWriter.write("D | " + isDone + " | " + description + " | " + by + "\n");
                } else if (task instanceof EventTask) {
                    String from = ((EventTask) task).getFrom();
                    String to = ((EventTask) task).getTo();
                    fileWriter.write("E | " + isDone + " | " + description + " | " + from + " | " + to + "\n");
                } else if (task instanceof TodoTask) {
                    fileWriter.write("T | " + isDone + " | " + description + "\n");
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing to file!");
        }

    }
}
