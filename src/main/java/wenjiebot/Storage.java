package wenjiebot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import wenjiebot.exceptions.NoFileException;
import wenjiebot.tasks.Deadline;
import wenjiebot.tasks.Event;
import wenjiebot.tasks.Task;
import wenjiebot.tasks.ToDo;

/**
 * The Storage class handles the reading and writing of tasks to and from a file.
 * It allows tasks to be persisted between sessions of the bot.
 */
public class Storage {

    private String filePath;
    private ArrayList<Task> tasks = new ArrayList<>(0);

    /**
     * Constructs a Storage object with the specified file path.
     * The constructor attempts to read existing tasks from the file.
     *
     * @param filePath the path of the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            readTasks();
        } catch (NoFileException e) {
            System.out.println("Bro I can't find a file to retrieve the data, \n"
                    + " can help lobang me and create a file pls");
        }
    }

    /**
     * Loads the list of tasks from storage.
     *
     * @return an ArrayList of Task objects that were read from the file.
     */
    public ArrayList<Task> load() {
        return tasks;
    }

    /**
     * Reads tasks from the file specified by filePath and populates the tasks list.
     * If the file does not exist, a NoFileException is thrown.
     *
     * @throws NoFileException if the file does not exist.
     */
    public void readTasks() throws NoFileException {
        File file = new File(filePath);

        if (!file.exists()) {
            throw new NoFileException();
        } else {
            try {
                String content = new String(Files.readAllBytes(Paths.get(filePath)));
                String[] parts = content.split("\n");

                if (!content.isBlank()) {
                    for (String stringifiedTask : parts) {
                        boolean isDone = stringifiedTask.charAt(4) == '1';
                        Task taskToAdd = convertStringToTask(stringifiedTask, isDone);
                        tasks.add(taskToAdd);
                    }
                }

            } catch (IOException e) {
                System.out.println("An error occurred while reading the file.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Converts a stringified task into a Task object based on its type (ToDo, Deadline, or Event).
     *
     * @param stringifiedTask the string representation of the task.
     * @param isDone whether the task is marked as done.
     * @return the corresponding Task object.
     */
    private static Task convertStringToTask(String stringifiedTask, boolean isDone) {
        String description = stringifiedTask.substring(8);
        Task taskToAdd = null;

        switch (stringifiedTask.charAt(0)) {

        case 'T':
            taskToAdd = new ToDo(description);
            break;

        case 'D':
            String[] deadlineSegments = description.split("/");
            int index = 0;
            for (int i = 0; i < description.length(); i++) {
                if (description.charAt(i) == '/') {
                    index = i + 4;
                    break;
                }

            }
            taskToAdd = new Deadline(deadlineSegments[0], description.substring(index));
            break;

        case 'E':
            String[] eventSegments = description.split("/");
            taskToAdd = new Event(eventSegments[0],
                    eventSegments[1].substring(6),
                    eventSegments[2].substring(4));

            break;

        default:
            System.out.println("Invalid type of task in .txt file");
        }

        if (isDone) {
            taskToAdd.setStatusIcon(true);
        } else {
            taskToAdd.setStatusIcon(false);
        }
        return taskToAdd;
    }

    /**
     * Writes the current list of tasks to the file specified by filePath.
     * If the file does not exist, a FileNotFoundException is thrown.
     */
    public void writeTasks() {
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                throw new FileNotFoundException();
            } else {
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

                    StringBuilder formattedString = new StringBuilder();

                    for (Task task : tasks) {
                        formattedString.append(task.toPrettierString());
                        formattedString.append("\n");
                    }

                    writer.write(formattedString.toString());
                    writer.flush();

                } catch (IOException e) {
                    System.out.println("An error occurred while saving the file.");
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Bro I can't find a file to store the data, \n"
                    + " can help lobang me and create a file pls");
        }
    }
}
