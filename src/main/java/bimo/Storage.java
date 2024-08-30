package bimo;

import bimo.tasks.Deadline;
import bimo.tasks.Event;
import bimo.tasks.Task;
import bimo.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File dataFile;

    public Storage(String filePath) {
        this.dataFile = new File(filePath);
    }
    /**
     * Format the type of task, status, description and dates into
     * text format type|status|description|date1/date2
     * Split by | , if length == 2 means todo,
     * if length == 3 split by /, if length == 2 means event
     * @param task Bimo.Tasks.Task object inside tasks
     * @return Formatted text that can be written into data file
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
     * Converts line of text in Bimo.txt to its corresponding Bimo.Tasks.Task object
     * @param text line of code inside Bimo.txt
     * @return Task object
     */
    private Task convertTextToTask(String text) {
        String[] details = text.split("\\|");
        String type = details[0];
        Task task= null;

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
     * Converts description of task to text in the form
     * type|status|description|date1|date2
     * @param task Bimo.Tasks.Task inside the tasks
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
     * To re-write file when user marks, unmarks or delete a task
     * @param tasks List of tasks
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
            System.out.println("Enable to write to file");
        }
    }

    /**
     *
     * @return Returns a list of tasks
     * @throws BimoException
     */
    public ArrayList<Task> loadFile() throws BimoException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(this.dataFile);
            while (fileReader.hasNext()) {
                String text = fileReader.nextLine();
                Task task = convertTextToTask(text);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw new BimoException("File not found, tasks cannot be loaded");
        }
        return tasks;
    }
}
