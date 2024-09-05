package tira;

import tira.task.Task;
import tira.task.Deadline;
import tira.task.ToDo;
import tira.task.Event;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;

/**
 * Utility class for Tira chatbot.
 * Manages reading and saving of output on text file.
 * Manages conversion of Task to String of text and vice versa.
 */
public class Storage {
    private static DateTimeFormatter OUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private final String filePath;
    private final Ui ui = new Ui();

    /**
     * Initialises the Storage class using a specified filepath name.
     *
     * @param filePath Filepath specified by the user.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /*
    Solution below inspired by https://github.com/hansneddyanto/ip/blob/master/src/main/java/hana/Storage.java
     */

    /**
     * Reads the String from the specified file.
     *
     * @return The tasks as list of tasks.
     * @throws TiraException Exception class for Tira chatbot.
     */
    public ArrayList<Task> load() throws TiraException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        File file = new File(filePath); //try creating filePath
        FileReader fileReader = null;

        try {
            if (!file.exists()) { //create new file if the file doesn't exist
                file.getParentFile().mkdirs();
                file.createNewFile();
                return tasks;
            } else {// a file exists
                fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                ArrayList<String> lines = new ArrayList<>();
                String line = bufferedReader.readLine();
                while (line != null) {
                    lines.add(line);
                    if (bufferedReader.readLine() == null) {
                        break;
                    }
                }
                return this.convertToTaskList(lines);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
        } catch (IOException a) {
            System.out.println("Error while file loading");
        }
        for (Task i : tasks) {
            System.out.println(i);
        }
        return tasks;
    }

    /**
     * Writes the list of tasks onto the file as a text.
     *
     * @param tasks The list of tasks that need to be written on the file.
     * @throws IOException
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            String string = this.convertTaskToString(tasks.get(i));
            writer.write(string);
        }
        writer.close();
    }

    //@@hansneddyanto-reused
    // Hans is my friend and he has allowed me to refer to parts of his code.

    /**
     * Converts Task objects into String.
     *
     * @param task Task that needs to be converted to a String.
     * @return The formatted Task as a String.
     */
    public String convertTaskToString(Task task) {
        String output = "";
        if (task instanceof ToDo) {
            output += "T | ";
            if (task.getIsDone()) {
                output += "1";
            } else {
                output += "0";
            }
            output += " | " + task.getDescription();
        }
        if (task instanceof Deadline) {
            output += "D |";
            Deadline deadline = (Deadline) task;
            if (deadline.getIsDone()) {
                output += "1";
            } else {
                output += "0";
            }
            output += " | " + deadline.getDescription() + " | " + deadline.getEndDate();

        }
        if (task instanceof Event) {
            output += "D |";
            Event event = (Event) task;
            if (event.getIsDone()) {
                output += "1 ";
            } else {
                output += "0 ";
            }
            output +=  " | " + event.getDescription() + " | " + event.getStartDate() +
                    " | " + event.getEndDate();
        }
        return output;
    }

    /**
     * Converts the text on the file to an ArrayList of String.
     *
     * @param taskStringList The String of text read from the file.
     * @return the ArrayList of Task objects.
     */

    public ArrayList<Task> convertToTaskList(ArrayList<String> taskStringList) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        for (String task : taskStringList) {
            String[] splitString = task.split( " \\|");
            String taskType = splitString[0];
            boolean isDone = splitString[1].equals("1");
            String description = splitString[2];

            switch (taskType) {
                case "T":
                    ToDo todo = new ToDo(description);
                    if (isDone) {
                        todo.markStatus();
                    }
                    tasks.add(todo);
                    break;
                case "D":
                    LocalDate endDate = LocalDate.parse(splitString[3], OUT_FORMATTER);
                    Deadline deadline = new Deadline(description, endDate);
                    if (isDone) {
                        deadline.markStatus();
                    }
                    tasks.add(deadline);
                    break;
                case "E":
                    LocalDate startDate = LocalDate.parse(splitString[3], OUT_FORMATTER);
                    LocalDate endDateEvent = LocalDate.parse(splitString[4], OUT_FORMATTER);
                    Event event = new Event(description, startDate, endDateEvent);
                    if (isDone) {
                        event.markStatus();
                    }
                    tasks.add(event);
                    break;
            }
        }
        return tasks;
    }
}
