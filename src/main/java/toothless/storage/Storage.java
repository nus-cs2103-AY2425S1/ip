package toothless.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import toothless.exceptions.ToothlessExceptions;
import toothless.task.Deadline;
import toothless.task.Event;
import toothless.task.Task;
import toothless.task.ToDo;


/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private static final String DATA_FILE_PATH = "src/data/tasks.txt";

    /**
     * Saves the tasks to a file.
     */
    public void saveTask(ArrayList<Task> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE_PATH))) {
            for (Task task : list) {
                writer.write(getTaskData(task));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Oh no! There is an error saving quests to file.");
        }
    }

    /**
     * Returns the data of the task to save in the text file.
     *
     * @param task The task to be saved.
     * @return The data of the task to be saved in the text file.
     */
    private String getTaskData(Task task) {
        if (task instanceof Deadline) {
            return task.toFileString();
        } else if (task instanceof Event) {
            return task.toFileString();
        }
        return task.toFileString();
    }

    /**
     * Loads the tasks from a file.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> list = new ArrayList<>();
        list.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseDataToTask(line);
                if (task != null) {
                    list.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Oh no! There is an error loading quests from file.");
        }

        return list;
    }

    /**
     * Parses the data from the file to a task.
     *
     * @param taskData The data of the task.
     * @return The task parsed from the data.
     */
    private Task parseDataToTask(String taskData) {
        String[] splitData = taskData.split(" \\| ");

        String taskType = splitData[0];
        boolean isDone = splitData[1].equals("1");
        String description = splitData[2];

        switch (taskType) {
        case "T":
            return new ToDo(description, isDone);
        case "D":
            String deadline = splitData[3];
            try {
                return new Deadline(description, deadline, isDone);
            } catch (ToothlessExceptions e) {
                System.out.println(e.getMessage());
            }
            break;
        case "E":
            String eventStart = splitData[3];
            String eventEnd = splitData[4];
            try {
                return new Event(description, eventStart, eventEnd, isDone);
            } catch (ToothlessExceptions e) {
                System.out.println(e.getMessage());
            }
            break;
        default:
            return null;
        }
        return null;
    }
}
