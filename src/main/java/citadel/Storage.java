package citadel;

import java.io.*;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import citadel.Task.Task;
import citadel.Task.TaskList;
import citadel.Task.Deadline;
import citadel.Task.Event;
import citadel.Task.ToDo;
import citadel.exception.CitadelException;
import citadel.exception.CitadelTaskNoInput;

/**
 * The {@code Storage} class is responsible for handling the reading and writing
 * of tasks to and from a file in the Citadel application. It allows tasks to be
 * saved persistently and retrieved when the application is restarted.
 */
public class Storage {

    /** The file path where the task data is stored. */
    private String filePath;

    /**
     * Constructs a new {@code Storage} object with the specified file path.
     * If the file does not exist, it is created. Otherwise, the existing file is used.
     *
     * @param filePath The file path where the task data will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        File db = new File(filePath);
        try {
            if (db.createNewFile()) {
                System.out.println("File created: " + db.getName());
            } else {
                System.out.println("File already exists: " + db.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Retrieves the list of tasks from the storage file.
     * <p>
     * This method reads the file line by line, parsing each line into a task object,
     * and adds the task to a {@link TaskList}. The task list is then returned.
     * </p>
     *
     * @return The {@link TaskList} containing all tasks from the storage file.
     */
    public TaskList getTasks() {
        TaskList tasks = new TaskList();
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String taskType = line.substring(1, 2);
                String taskString = line.substring(7);
                if (taskType.equals("T")) {
                    tasks.add(createTodo(taskString));
                } else if (taskType.equals("D")) {
                    tasks.add(createDeadline(taskString));
                } else if (taskType.equals("E")) {
                    tasks.add(createEvent(taskString));
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the list of tasks to the storage file.
     * <p>
     * This method writes each task in the task list to the storage file,
     * overwriting any existing content in the file.
     * </p>
     *
     * @param tasks The {@link TaskList} containing the tasks to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void saveData(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(this.filePath, false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (int i = 0; i < tasks.size(); i++) {
            bufferedWriter.write(tasks.get(i).toString());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        fileWriter.close();
    }

    /**
     * Creates a {@link Deadline} task from a string input.
     * <p>
     * The input string is parsed to extract the task description and the deadline.
     * </p>
     *
     * @param input The string containing the task description and deadline.
     * @return A {@link Deadline} task created from the input string.
     * @throws CitadelException If the input string is invalid or incomplete.
     */
    private static Task createDeadline(String input) throws CitadelException {
        Task t;
        String[] words = input.split(" \\(by: ");
        if (words.length < 2) {
            throw new CitadelTaskNoInput();
        }
        String task = words[0].trim();
        String deadline = words[1].substring(0, words[1].length() - 1).trim();
        if (task.isEmpty() || deadline.isEmpty()) {
            throw new CitadelTaskNoInput();
        }

        LocalDateTime deadlineFormatted = LocalDateTime.parse(deadline,
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        t = new Deadline(task, deadlineFormatted);
        return t;
    }

    /**
     * Creates an {@link Event} task from a string input.
     * <p>
     * The input string is parsed to extract the task description, start time, and end time.
     * </p>
     *
     * @param input The string containing the task description, start time, and end time.
     * @return An {@link Event} task created from the input string.
     * @throws CitadelException If the input string is invalid or incomplete.
     */
    private static Task createEvent(String input) throws CitadelException {
        Task t;
        String[] words = input.split(" \\(from: ");
        if (words.length < 2) {
            throw new CitadelTaskNoInput();
        }
        String task = words[0].trim();
        String[] timeline = words[1].split(" to: ");
        if (timeline.length < 2) {
            throw new CitadelTaskNoInput();
        }
        String from = timeline[0].trim();
        String to = timeline[1].substring(0, timeline[1].length() - 1).trim();
        if (task.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new CitadelTaskNoInput();
        }
        LocalDateTime fromFormatted = LocalDateTime.parse(from,
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        LocalDateTime toFormatted = LocalDateTime.parse(to,
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        t = new Event(task, fromFormatted, toFormatted);
        return t;
    }

    /**
     * Creates a {@link ToDo} task from a string input.
     * <p>
     * The input string is parsed to extract the task description.
     * </p>
     *
     * @param input The string containing the task description.
     * @return A {@link ToDo} task created from the input string.
     * @throws CitadelException If the input string is invalid or incomplete.
     */
    private static Task createTodo(String input) throws CitadelException {
        Task t;
        String todo = input.trim();
        if (todo.isEmpty()) {
            throw new CitadelTaskNoInput();
        }
        t = new ToDo(todo);
        return t;
    }
}
