package nimbus.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import nimbus.Nimbus;
import nimbus.exception.WrongDateTimeFormatException;
import nimbus.task.DeadlineTask;
import nimbus.task.EventTask;
import nimbus.task.Task;
import nimbus.task.TodoTask;

/**
 * Handles the writing and retrieving of data from text file
 */
@SuppressWarnings("checkstyle:Regexp")
public class Storage {
    private static String filepath;
    private static final Logger staticLogger = Logger.getLogger(Nimbus.class.getName());
    private final Logger logger = Logger.getLogger(Nimbus.class.getName());
    /**
     * Creates a storage object with filepath provided
     *
     * @param filepath where the file should be stored
     */
    public Storage(String filepath) {
        assert filepath != null;
        this.filepath = filepath;
    }

    /**
     * Creates the file if file is not present
     */
    public void createFile() {
        try {
            File file = new File(filepath);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException ex) {
            logger.log(Level.WARNING, "IO Exception found, no such filepath");
        }
    }

    /**
     * Loads the file from the text file
     *
     * @param taskList target tasklist where tasks are loaded into
     */
    public void loadFile(TaskList taskList) {
        try {
            File savedFile = new File(filepath);
            if (!savedFile.exists()) {
                throw new IOException("File not found");
            }

            try (BufferedReader br = new BufferedReader(new FileReader(savedFile))) {
                String line;

                while ((line = br.readLine()) != null) {
                    // split text line into its components
                    String[] parts = line.split(" \\| ");

                    if (parts.length < 3 || parts.length > 4) {
                        logger.log(Level.WARNING, "Corrupted Line: " + line);
                    }

                    String taskType = parts[0];
                    boolean isCompleted = parts[1].equals("1");
                    String description = parts[2];

                    switch (taskType) {
                    case "T":
                        if (parts.length == 3) {
                            TodoTask todoTask;
                            todoTask = new TodoTask(description, isCompleted);
                            taskList.add(todoTask);
                            // logger.log(Level.INFO,"Todo nimbus.task.Task is added");
                        } else {
                            logger.log(Level.WARNING, "Corrupted Line: " + line
                                    + " Line wrong format for nimbus.task.TodoTask");
                        }
                        break;
                    case "D":
                        if (parts.length == 4) {
                            String deadline = parts[3];

                            DeadlineTask deadlineTask = new DeadlineTask(description,
                                    isCompleted, deadline);
                            taskList.add(deadlineTask);
                            // logger.log(Level.INFO,"Deadline nimbus.task.Task is added");
                        } else {
                            logger.log(Level.WARNING, "Corrupted Line: " + line
                                    + " Line wrong format for nimbus.task.DeadlineTask");
                        }
                        break;
                    case "E":
                        if (parts.length == 4) {
                            String[] time = parts[3].split(" - ");
                            String startTime = time[0];
                            String endTime = time[1];

                            if (endTime.contains("pm") && !startTime.contains("pm")
                                    && !startTime.contains("am")) {
                                startTime += "pm";
                            } else if (endTime.contains("am") && !startTime.contains("pm")
                                    && !startTime.contains("am")) {
                                startTime += "am";
                            }

                            EventTask eventTask = new EventTask(description, isCompleted,
                                    startTime, endTime);
                            taskList.add(eventTask);
                            // logger.log(Level.INFO,"Event nimbus.task.Task is added");
                        } else {
                            logger.log(Level.WARNING, "Corrupted Line: " + line
                                    + " Line wrong format for nimbus.task.EventTask");
                        }
                        break;
                    default:
                        logger.log(Level.WARNING, "nimbus.task.Task does not start with T/D/E: " + line);
                        break;
                    }
                }
            } catch (WrongDateTimeFormatException e) {
                System.out.println(e.getMessage());
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "File not found", e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    /**
     * Updates the file when user wants to exit the chatbot
     *
     * @param taskArrayList arraylist containing tasks in tasklist object
     * @throws IOException if file is not found
     */
    public static void updateFile(ArrayList<Task> taskArrayList) throws IOException {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(filepath))) {
            for (Task task : taskArrayList) {
                printWriter.println(task.toFileFormat());
            }
        } catch (IOException e) {
            staticLogger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
