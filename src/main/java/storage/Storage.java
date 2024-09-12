package storage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import exception.PrimoException;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;
import tasks.TaskList;
import tasks.TodoTask;

/**
 * The Storage class handles the loading and saving of tasks from/to a file.
 * It is responsible for reading tasks from a file when the application starts
 * and writing the current tasks to the file when they are updated.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file into an array of Task objects.
     *
     * @return An array of Task objects loaded from the file.
     * @throws PrimoException If the file format is incorrect or tasks cannot be loaded.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public Task[] load() throws PrimoException, IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Path filePath = Paths.get(this.filePath);
            List<String> lines = Files.readAllLines(filePath);
            for (String s : lines) {
                String[] words = s.split(" ");
                boolean isDone;
                switch (words[0].charAt(2)) {
                case 'T':
                    parseTodoTaskData(s, taskList);
                    break;
                case 'D':
                    parseDeadlineTaskData(s, taskList);
                    break;
                case 'E':
                    parseEventTaskData(s, taskList);
                    break;
                default:
                    return null; // should not reach here if exception handling is correct
                }
            }
            return taskList.toArray(new Task[0]);
        } catch (IOException e) {
            throw e;
        }
    }

    private static void parseEventTaskData(String s, ArrayList<Task> taskList) {
        int eventDescriptionIndex = s.indexOf("description: ");
        int eventTimingsIndex = s.indexOf("timings: ");
        int eventFromIndex = s.indexOf("(from: ");
        int eventToIndex = s.indexOf("to: ");
        int eventNoteIndex = s.indexOf("note: ");

        String eventDescription = s.substring(eventDescriptionIndex + 13, eventTimingsIndex).trim();
        String eventNote = s.substring(eventNoteIndex + 6).trim();
        String eventFromTime = s.substring(eventFromIndex + 7, eventToIndex).trim();
        String eventToTime = s.substring(eventToIndex + 4, s.indexOf(')')).trim();

        boolean isDone = s.charAt(3) == 'X';
        Task newEventTask = new EventTask(eventDescription, eventFromTime, eventToTime, eventNote);
        if (isDone) {
            newEventTask.markAsDone();
        }
        taskList.add(newEventTask);
    }

    private static void parseDeadlineTaskData(String s, ArrayList<Task> taskList) {
        int deadlineDescriptionIndex = s.indexOf("description: ");
        int deadlineTimingsIndex = s.indexOf("timings: ");
        int deadlineByIndex = s.indexOf("(by: ");
        int deadlineNoteIndex = s.indexOf("note: ");

        String deadlineDescription = s.substring(deadlineDescriptionIndex + 13, deadlineTimingsIndex).trim();
        String deadlineNote = s.substring(deadlineNoteIndex + 6).trim();
        String deadlineTime = s.substring(deadlineByIndex + 5, s.indexOf(')')).trim();

        boolean isDone = s.charAt(3) == 'X';
        Task newDeadlineTask = new DeadlineTask(deadlineDescription, deadlineTime, deadlineNote);
        if (isDone) {
            newDeadlineTask.markAsDone();
        }
        taskList.add(newDeadlineTask);
    }

    private static void parseTodoTaskData(String s, ArrayList<Task> taskList) {
        int todoDescriptionIndex = s.indexOf("description: ");
        int todoTimingsIndex = s.indexOf("timings: ");
        int todoNoteIndex = s.indexOf("note: ");

        String todoDescription = s.substring(todoDescriptionIndex + 13, todoTimingsIndex).trim();
        String todoNote = s.substring(todoNoteIndex + 6).trim();

        boolean isDone = s.charAt(3) == 'X';
        Task newToDoTask = new TodoTask(todoDescription, todoNote);
        if (isDone) {
            newToDoTask.markAsDone();
        }
        taskList.add(newToDoTask);
    }

    /**
     * Updates the storage file with the current list of tasks.
     *
     * @param taskList The TaskList object containing the tasks to be saved.
     */
    public void updateStorage(TaskList taskList) {
        ArrayList<Task> list = taskList.getTasks();
        int len = list.size();
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < len; i++) {
            Task task = list.get(i);
            String output = i + 1 + "."
                    + task.getSymbol() + task.getStatusIcon()
                    + " description: " + task.getDescription()
                    + " timings: " + task.getTimings()
                    + " note: " + task.getNote();
            data.append(output);
            data.append("\n");
        }
        try (FileWriter writer = new FileWriter("./data/data.txt")) {
            writer.write(data.toString());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
