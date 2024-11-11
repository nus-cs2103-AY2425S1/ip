package bimo.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import bimo.exception.BimoException;
import bimo.tasks.Deadline;
import bimo.tasks.Event;
import bimo.tasks.Task;
import bimo.tasks.ToDo;

/**
 * Deals with writing and loading files.
 */
public class Storage {

    private File dataFile;

    public Storage(String filePath) {
        this.dataFile = new File(filePath);
    }
    /**
     * Converts the type of task, status, description and dates into text.
     *
     * @param task Task object inside Tasklists.
     * @return Formatted text in the form type|1|description|yyyy-mm-dd/yyyy-mm-dd.
     */
    public String convertTaskToText(Task task) {
        String text = "";
        String description = task.getTaskText();
        if (task instanceof ToDo) {
            text = "T|" + description;
        } else if (task instanceof Deadline) {
            text = "D|" + description + ((Deadline) task).getDateAsText();
        } else if (task instanceof Event) {
            text = "E|" + description + ((Event) task).getDatesAsText();
        } else {
            throw new IllegalArgumentException("Invalid task type");
        }
        return task.convertPriorityToText() + text;
    }

    /**
     * Converts text to its corresponding Task object.
     *
     * @param text line of code inside data/Bimo.txt.
     * @return Task object.
     */
    public Task convertTextToTask(String text) {
        String[] details = getDetails(text);
        String type = details[0];

        boolean isCompleted = details[1].equals("0") ? false : true;
        Task task = this.createTask(type, details);
        if (isCompleted) {
            task.markCompleted();
        }
        this.setTaskPriority(task, text);
        return task;
    }

    /**
     * Creates tasks based on type and details stored in data file.
     *
     * @param type The type of task.
     * @param details The task information.
     * @return Task added by user previously.
     */
    public Task createTask(String type, String[] details) {
        Task task = null;
        if (type.equals("T")) {
            task = new ToDo(details[2]);
        } else if (type.equals("D")) {
            LocalDate dueDateObject = LocalDate.parse(details[3]);
            task = new Deadline(details[2], dueDateObject);
        } else if (type.equals("E")) {
            String[] dates = details[3].split("/");
            LocalDate startDateObject = LocalDate.parse(dates[0]);
            LocalDate endDateObject = LocalDate.parse(dates[1]);
            task = new Event(details[2], startDateObject, endDateObject);
        }
        assert task != null : "Task cannot be created";
        return task;
    }

    /**
     * Splits components in the text line into an array.
     *
     * @param text Textline saved in data file.
     * @return An array containing details of task.
     */
    public String[] getDetails(String text) {
        String[] arrayByPriority = text.split("~");
        boolean hasPriority = arrayByPriority.length > 1;
        String[] details = hasPriority ? arrayByPriority[1].split("\\|") : text.split("\\|");
        return details;
    }

    /**
     * Sets priority labal for tasks.
     *
     * @param task Task created from text line.
     * @param text Text line stored in data file.
     */
    public void setTaskPriority(Task task, String text) {
        String[] arrayByPriority = text.split("~");
        boolean hasPriority = arrayByPriority.length > 1;
        if (hasPriority) {
            String priority = arrayByPriority[0];
            task.setPriority(priority);
        }
    }

    /**
     * Adds text value of task into data/Bimo.txt.
     *
     * @param task Task object inside the tasks.
     */
    public void appendToFile(Task task) {
        String text = this.convertTaskToText(task) + System.lineSeparator();
        try {
            FileWriter writer = new FileWriter("data/Bimo.txt", true);
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            System.out.println("Unable to write to file for task " + task);
        }
    }

    /**
     * Re-writes file when user marks, unmarks or deletes a task.
     *
     * @param tasks List of tasks.
     */
    public void overwriteFile(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter("data/Bimo.txt");
            for (int i = 0; i < tasks.getLength(); i++) {
                String text = convertTaskToText(tasks.getTask(i));
                writer.write(text + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Unable to write to file");
        }
    }

    /**
     * Creates a new folder with a data file if folder
     * or data file does not exist yet.
     *
     * @throws BimoException If unable to create new file.
     */
    public void createFile() throws BimoException {
        try {
            File dataFolder = dataFile.getParentFile();
            if (!dataFolder.exists()) {
                dataFolder.mkdirs();
            }
            File file = this.dataFile;
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new BimoException("Unable to create new file");
        }
    }

    /**
     * Loads tasks stored inside data file into TaskList.
     *
     * @return List of tasks.
     * @throws BimoException If file path is invalid.
     */
    public ArrayList<Task> loadFile() throws BimoException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            this.createFile();
            Scanner fileReader = new Scanner(this.dataFile);
            while (fileReader.hasNext()) {
                String text = fileReader.nextLine();
                Task task = convertTextToTask(text);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new BimoException("File not found");
        }
        return tasks;
    }
}
