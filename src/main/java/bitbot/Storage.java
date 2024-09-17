package bitbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This is the Storage class which helps to store the file created and also
 * reads and writes to the file.
 */
public class Storage {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");


    private static final String PATH_TO_FILE = "./data/Bitbot.txt";

    /**
     * Checks if the file that is being read from exists
     * else it creates a new directory and a new file.
     *
     * @throws IOException if directory is not found.
     */
    public static void ensureFileExists() throws IOException {
        File file = new File(PATH_TO_FILE);

        if (!file.exists()) {
            // creating the relevant directory
            // USAGE OF HELP FROM AI:
            /*
                Prompt given: How to create a new directory if the user's system does not have the
                directory initially?

                Answer from ChatGPT:
                "To create a new directory if it doesn't already exist on the user's system,
                you can use the mkdir() or mkdirs() methods from the File class in Java.
                Here’s how you can do it:

                Using mkdir() and mkdirs()
                mkdir(): Creates the directory if it doesn’t exist.
                         It will not create any required but non-existent parent directories.
                mkdirs(): Creates the directory and any necessary but non-existent parent directories."
             */
            file.getParentFile().mkdirs();
            // creating the new file.
            file.createNewFile();
        }
    }

    /**
     * Saves the tasks from the arrayList into the PATH_TO_FILE.
     * It is using the .toFileFormat() in the Task class to convert it into a storable format.
     *
     * @param tasks this is the arrayList of tasks that have been saved.
     * @throws IOException
     */
    public static void saveTasksToFile(ArrayList<Task> tasks, String filename) throws IOException {
        ensureFileExists();
        FileWriter fileWriter = new FileWriter(filename);
        for (Task task : tasks) {
            fileWriter.write(task.toFileFormat() + System.lineSeparator());
        }

        fileWriter.close();
    }

    /**
     * Reads all the tasks stored in the file and then converts it back into
     * user-readable format and stored in an ArrayList.
     *
     * @param filePath uses the filePath to read the correct file.
     * @return an arrayList of tasks which contains all the tasks from the storage file.
     * @throws FileNotFoundException if the file is not found
     */
    public static ArrayList<Task> readTasksFromFile(String filePath) throws FileNotFoundException {
        ArrayList<Task> listFromFile = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            // adapted from W3.4c (using Scanner) of notes.
            while (scanner.hasNextLine()) {
                String[] partsOfLineFromFile = scanner.nextLine().split("\\|");
                Task task = parseTask(partsOfLineFromFile);

                if (partsOfLineFromFile[1].trim().equals("X")) {
                    task.markAsDone();
                }
                if (partsOfLineFromFile.length >= 5
                        && !partsOfLineFromFile[partsOfLineFromFile.length - 1].trim().equals("NIL")) {
                    task.markAsTagged(partsOfLineFromFile[partsOfLineFromFile.length - 1]);
                }
                listFromFile.add(task);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e.getMessage());
        }
        return listFromFile;

    }

    /**
     * Parses the type of task from the input and creates a corresponding Task object.
     *
     * @param partsOfInput A String array which consists of the type of task and the relevant details.
     * @return the Task object corresponding to the task type.
     */
    private static Task parseTask(String[] partsOfInput) {
        Task task = null;
        String typeOfTask = partsOfInput[0].trim();

        assert typeOfTask.equals("D") || typeOfTask.equals("T") || typeOfTask.equals("E")
                : "Invalid task type: " + typeOfTask;

        switch (typeOfTask) {
        case "D":
            task = createDealineTask(partsOfInput);
            break;
        case "T":
            task = parseTodoTask(partsOfInput);
            break;
        case "E":
            task = createEventTask(partsOfInput);
            break;
        default:
            // there should not be any other task
            break;
        }
        return task;
    }

    /**
     * Creates a Deadline Task from the input given.
     *
     * @param partsOfInput A String array which consists of the type of task and the relevant details.
     * @return a Task which in this case is a Deadline task.
     */
    private static Task createDealineTask(String[] partsOfInput) {
        String descriptionOfDeadline = partsOfInput[2].trim();
        String deadlineBy = partsOfInput[3].trim();
        Task task = null;
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(deadlineBy, dateTimeFormatter);
            task = new Deadline(descriptionOfDeadline, localDateTime);
        } catch (DateTimeParseException e) {
            try {
                LocalDate localDate = LocalDate.parse(deadlineBy, dateFormatter);
                task = new Deadline(descriptionOfDeadline, localDate);
            } catch (DateTimeParseException err) {
                try {
                    LocalTime localTime = LocalTime.parse(deadlineBy, timeFormatter);
                    task = new Deadline(descriptionOfDeadline, localTime);
                } catch (DateTimeParseException error) {
                    task = new Deadline(descriptionOfDeadline, deadlineBy);
                }
            }
        }
        return task;
    }

    /**
     * Creates an Event Task from the input given.
     *
     * @param partsOfInput A String array which consists of the type of task and the relevant details.
     * @return a Task which in this case is an Event task.
     */
    private static Task createEventTask(String[] partsOfInput) {
        String descriptionOfEvent = partsOfInput[2].trim();
        String eventFrom = partsOfInput[3].trim();
        String eventTo = partsOfInput[4].trim();
        Task task = null;

        try {
            LocalDateTime localDateTime = LocalDateTime.parse(eventFrom, dateTimeFormatter);
            LocalDateTime localDateTime1 = LocalDateTime.parse(eventTo, dateTimeFormatter);
            task = new Events(descriptionOfEvent, localDateTime, localDateTime1);
        } catch (DateTimeParseException e) {
            try {
                LocalDate localDate = LocalDate.parse(eventFrom, dateFormatter);
                LocalDate localDate1 = LocalDate.parse(eventTo, dateFormatter);
                task = new Events(descriptionOfEvent, localDate, localDate1);
            } catch (DateTimeParseException err) {
                try {
                    LocalTime localTime = LocalTime.parse(eventFrom, timeFormatter);
                    LocalTime localTime1 = LocalTime.parse(eventTo, timeFormatter);
                    task = new Events(descriptionOfEvent, localTime, localTime1);
                } catch (DateTimeParseException error) {
                    task = new Events(descriptionOfEvent, eventFrom, eventTo);
                }
            }
        }

        return task;
    }

    /**
     * Creates a Todo Task from the input given.
     *
     * @param partsOfInput A String array which consists of the type of task and the relevant details.
     * @return a Task which in this case is a Todo task.
     */
    private static Task parseTodoTask(String[] partsOfInput) {
        return new Todo(partsOfInput[2].trim());
    }
}
