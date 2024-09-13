package dave.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import dave.exceptions.InvalidDateTimeFormatException;
import dave.exceptions.InvalidDescriptionException;
import dave.task.Deadline;
import dave.task.Event;
import dave.task.Task;
import dave.task.TaskList;
import dave.task.Todo;

/**
 * The Storage class handles the loading and saving of tasks to and from a file.
 */
public class Storage {
    private static String filePath;

    /**
     * Constructs a Storage object and loads tasks from the specified file into the given TaskList.
     *
     * @param file     The file path to load tasks from and save tasks to.
     * @param taskList The TaskList to populate with tasks loaded from the file.
     */
    public Storage(String file, TaskList taskList) {
<<<<<<< HEAD
        this.filePath = file;
        loadTasks(taskList);
    }

    private void loadTasks(TaskList taskList) {
=======
        assert file != null : "File path must not be null";
>>>>>>> master
        try {
            taskList.setTaskList(loadFile());
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks from the file.");
        }
    }

    /**
     * Saves the given TaskList to the file.
     *
     * @param dataList The TaskList containing tasks to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public static void saveFile(TaskList dataList) throws IOException {
<<<<<<< HEAD
        File fileObj = createFile();
        writeTasksToFile(dataList, fileObj);
    }

    private static File createFile() throws IOException {
=======
        assert filePath != null : "File path must not be null";

>>>>>>> master
        File fileObj = new File(filePath);
        fileObj.getParentFile().mkdirs();
        if (!fileObj.exists()) {
            fileObj.createNewFile();
        }
        return fileObj;
    }

    private static void writeTasksToFile(TaskList dataList, File fileObj) throws IOException {
        try (FileWriter fw = new FileWriter(fileObj)) {
            for (int i = 0; i < dataList.getSize(); i++) {
                fw.write(dataList.getTask(i).write());
            }
        }
    }

    /**
     * Clears the contents of the file.
     *
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public static void clearFile() throws IOException {
<<<<<<< HEAD
        writeToFile("");
    }

    private static void writeToFile(String content) throws IOException {
        try (FileWriter fw = new FileWriter(filePath, false)) {
            fw.write(content);
        }
=======
        assert filePath != null : "File path must not be null";

        FileWriter fw = new FileWriter(filePath, false);
        fw.write("");
        fw.close();
>>>>>>> master
    }

    /**
     * Appends a task to the file.
     *
     * @param task The Task to be appended to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public static void amendFile(Task task) throws IOException {
<<<<<<< HEAD
        File fileObj = createFile();
        try (FileWriter fw = new FileWriter(fileObj, true)) {
            fw.write(task.write());
=======
        assert filePath != null : "File path must not be null";

        File fileObj = new File(filePath);
        fileObj.getParentFile().mkdirs();
        if (!fileObj.exists()) {
            fileObj.createNewFile();
>>>>>>> master
        }
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws IOException If an I/O error occurs while reading from the file.
     */
    public ArrayList<Task> loadFile() throws IOException {
        assert filePath != null : "File path must not be null";

        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks; // Return empty list if file doesn't exist
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                tasks.add(parseTask(line));
            }
        }
        return tasks;
    }

    private Task parseTask(String line) throws IOException {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        try {
            return createTask(taskType, isDone, description, parts);
        } catch (InvalidDateTimeFormatException | InvalidDescriptionException e) {
            System.out.println("An error occurred while loading the task from the file.");
            return null;
        }
    }

    private Task createTask(String taskType, boolean isDone, String description, String[] parts)
            throws IOException, InvalidDescriptionException, InvalidDateTimeFormatException {
        switch (taskType) {
        case "T":
            return createTodoTask(description, isDone);
        case "D":
            return createDeadlineTask(description, isDone, parts[3]);
        case "E":
            return createEventTask(description, isDone, parts[3]);
        default:
            throw new IOException("Unrecognized task type: " + taskType);
        }
    }

    private Todo createTodoTask(String description, boolean isDone) {
        Todo todo = new Todo(description);
        if (isDone) {
            todo.markAsDone();
        }
        return todo;
    }

    private Deadline createDeadlineTask(String description, boolean isDone, String datePart)
            throws InvalidDateTimeFormatException, InvalidDescriptionException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        LocalDateTime dueDate = LocalDateTime.parse(datePart.trim(), formatter);
        Deadline deadline = new Deadline(description + " /by "
                + dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
        if (isDone) {
            deadline.markAsDone();
        }
        return deadline;
    }

    private Event createEventTask(String description, boolean isDone, String eventPart)
            throws InvalidDateTimeFormatException, InvalidDescriptionException {
        String[] eventParts = eventPart.split(" - ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        LocalDateTime fromDate = LocalDateTime.parse(eventParts[0], formatter);
        Event event = new Event(description + " /from "
                + fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " /to " + eventParts[1]);
        if (isDone) {
            event.markAsDone();
        }
        return event;
    }
}
