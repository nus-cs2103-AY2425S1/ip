package pikappi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pikappi.exception.PikappiException;
import pikappi.task.DeadlineTask;
import pikappi.task.EventTask;
import pikappi.task.Task;
import pikappi.task.TodoTask;

/**
 * Represents a storage that saves and loads tasks.
 */
public class Storage {
    protected String path;
    protected TaskList tasks;
    protected Ui ui;

    /**
     * Returns a Storage object that saves and loads tasks.
     *
     * @param path Filepath to save and load tasks from
     */
    public Storage(String path) {
        this.path = path;
        this.tasks = new TaskList();
    }

    /**
     * Returns a TaskList object that contains tasks.
     *
     * @return TaskList object that contains tasks
     * @throws PikappiException If there is an error loading tasks
     */
    public TaskList load() throws PikappiException {
        this.loadTasks();
        return this.tasks;
    }

    /**
     * Saves the tasks to the file.
     *
     * @param tasks TaskList object that contains tasks
     */
    public void save(TaskList tasks) throws PikappiException {
        this.tasks = tasks;
        this.saveTasks();
    }

    /**
     * Loads tasks from the file.
     *
     * @throws PikappiException If there is an error loading tasks
     */
    public void loadTasks() throws PikappiException {
        File file = new File(this.path);
        if (!file.exists() && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (Exception e) {
                ui.showErrorMessage("Error creating file!");
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
            ui.showErrorMessage("File not found!");
        }
    }

    /**
     * Loads a task to <code>tasks</code>.
     *
     * @param task Task to load
     * @throws PikappiException If there is an error loading the task
     */
    public void loadCurrentTask(ArrayList<String> task) throws PikappiException {
        if (task.size() < 3) {
            throw new PikappiException("Error loading task!");
        }
        String taskType = task.get(0);
        boolean isDone = task.get(1).equals("1");
        String taskDescription = task.get(2);
        String taskTime = "";
        String taskEndTime = "";
        switch (taskType) {
        case "T":
            this.tasks.load(new TodoTask(taskDescription, isDone));
            break;
        case "D":
            taskTime = task.get(3);
            this.tasks.load(new DeadlineTask(taskDescription, taskTime, isDone));
            break;
        case "E":
            taskTime = task.get(3);
            taskEndTime = task.get(4);
            this.tasks.load(new EventTask(taskDescription, taskTime, taskEndTime, isDone));
            break;
        default:
            throw new PikappiException("Error loading task!");
        }
    }

    /**
     * Saves tasks to the file.
     */
    public void saveTasks() throws PikappiException {
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
            throw new PikappiException("Error saving tasks!");
        }

    }
}
