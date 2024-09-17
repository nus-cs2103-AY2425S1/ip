package bobbybot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import bobbybot.tasks.Deadline;
import bobbybot.tasks.Event;
import bobbybot.tasks.Task;
import bobbybot.tasks.ToDo;

/**
 * Represents the storage of the task list, using a text file.
 */
public class Storage {
    private final File taskListFile;

    /**
     * Creates a new Storage object.
     *
     * @param filePath Path of the file to store the task list.
     */
    public Storage(String filePath) {
        taskListFile = new File(filePath);
        try {
            taskListFile.getParentFile().mkdirs();
            taskListFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating file");
        }
    }

    /**
     * Reads the task list from the file.
     *
     * @return List of tasks read from the file.
     * @throws Exception If there is any error reading from the file.
     */
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
            case ToDo.TASK_TYPE:
                newTask = new ToDo(description);
                break;
            case Deadline.TASK_TYPE:
                newTask = new Deadline(description, taskDetails[3].trim());
                break;
            case Event.TASK_TYPE:
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

    /**
     * Saves the tasks to the file.
     *
     * @param tasks List of tasks to be saved to the file.
     * @throws BobbyBotException If there is an error writing to the file.
     */
    public void saveTasksToFile(Task[] tasks) throws BobbyBotException {
        try {
            FileWriter fileWriter = new FileWriter(taskListFile);
            for (Task task : tasks) {
                fileWriter.write(task.getFileString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new BobbyBotException("Error saving to file.");
        }
    }


}
