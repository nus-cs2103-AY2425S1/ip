package bimo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

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
            //safe to cast since type checking is done
            text = "D|" + description + ((Deadline) task).getDateAsText();
        } else {
            text = "E|" + description + ((Event) task).getDatesAsText();
        }
        return text;
    }


    /**
     * Converts text to its corresponding Task object.
     *
     * @param text line of code inside data/Bimo.txt.
     * @return Task object.
     */
    private Task convertTextToTask(String text) {
        String[] details = text.split("\\|");
        String type = details[0];
        Task task = null;

        boolean status = details[1].equals("0") ? false : true;
        if (type.equals("T")) {
            task = new ToDo(details[2]);
        } else if (type.equals("D")) {
            task = new Deadline(details[2], LocalDate.parse(details[3]));
        } else if (type.equals("E")) {
            String[] dates = details[3].split("/");
            task = new Event(details[2], LocalDate.parse(dates[0]), LocalDate.parse(dates[1]));
        }
        if (status) {
            task.markCompleted();
        }
        return task;
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
            System.out.println("Unable to write to file for task " + task.toString());
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
     * Loads tasks stored inside data file into TaskList.
     *
     * @return List of tasks.
     * @throws BimoException If file path is invalid.
     */
    public ArrayList<Task> loadFile() throws BimoException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File dataFolder = dataFile.getParentFile();
            if (!dataFolder.exists()) {
                dataFolder.mkdirs();
            }
            File file = this.dataFile;
            if (!dataFile.exists()) {
                file.createNewFile();
            }
            Scanner fileReader = new Scanner(this.dataFile);
            while (fileReader.hasNext()) {
                String text = fileReader.nextLine();
                Task task = convertTextToTask(text);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Unable to create new file");
        }
        return tasks;
    }
}
