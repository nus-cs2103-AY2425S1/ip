package garfield.storage;

import garfield.tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class represents the local storage where tasks are being saved so
 * that they persist even after the chatbot is exited. It contains methods to
 * load and save tasks from and into a  .txt file for local storage.
 */
public class Storage {
    private String saveFilePath;

    /**
     * Constructs a new storage object with the specified save file path.
     *
     * @param saveFilePath Path to the save .txt file.
     */
    public Storage(String saveFilePath) {
        this.saveFilePath = saveFilePath;
    }

    /**
     * Loads the tasks in the save .txt file into memory.
     *
     * @return ArrayList of Tasks which have been loaded from the persistent save file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();
        File savedFile = new File(saveFilePath);
        try {
            Scanner fileScanner = new Scanner(savedFile);
            String savedTask;
            while (fileScanner.hasNext()) {
                savedTask = fileScanner.nextLine();
                String[] taskDetails = savedTask.split(" \\| ");
                String taskType = taskDetails[0];

                switch (taskType) {
                    case "T":
                        Todo newTodo = new Todo(taskDetails[2]);
                        taskList.add(newTodo);
                        if (taskDetails[1].equals("1")) {
                            newTodo.markAsDone();
                        } else {
                            newTodo.markAsUndone();
                        }
                        break;
                    case "D":
                        Deadline newDeadline = new Deadline(taskDetails[2], this.parseDateTime(taskDetails[3]));
                        taskList.add(newDeadline);
                        if (taskDetails[1].equals("1")) {
                            newDeadline.markAsDone();
                        } else {
                            newDeadline.markAsUndone();
                        }
                        break;
                    case "E":
                        Event newEvent = new Event(taskDetails[2], this.parseDateTime(taskDetails[3]), this.parseDateTime(taskDetails[4]));
                        taskList.add(newEvent);
                        if (taskDetails[1].equals("1")) {
                            newEvent.markAsDone();
                        } else {
                            newEvent.markAsUndone();
                        }
                        break;
                }
            }
            return taskList;
        } catch (FileNotFoundException e) {
            // You don't have a save file todo: add some error
            return new ArrayList<Task>();
        }
    }

    /**
     * Saves the inputted taskList into the persistent save .txt file.
     *
     * @param taskList ArrayList of Tasks to be saved.
     */
    public void save(TaskList taskList) {
        ArrayList<Task> internalTaskList = taskList.getArrayList();  // Feels iffy, see how i can fix this
        try (FileWriter fw = new FileWriter(this.saveFilePath)) {
            String prefix = "";
            StringBuilder textToWrite = new StringBuilder();
            for (Task t : internalTaskList) {
                textToWrite.append(prefix);
                prefix = System.lineSeparator();
                textToWrite.append(t.toSaveRepresentation());
            }
            fw.write(textToWrite.toString());
        } catch (IOException e) {
            // Throw some Garfield Exception
        }
    }

    /**
     * Returns a LocalDateTime object after parsing a String representation of a date and time.
     *
     * @param dateInput String representing the date and time of a garfield.task.Task.
     * @return LocalDateTime object.
     * @throws DateTimeParseException Error thrown if date time format is wrong in the input.
     */
    private LocalDateTime parseDateTime(String dateInput) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateInput, formatter);
    }
}
