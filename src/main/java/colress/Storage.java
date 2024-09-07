package colress;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;

import colress.exception.FileCorruptedException;
import colress.task.Deadline;
import colress.task.Event;
import colress.task.Task;
import colress.task.ToDo;

/**
 * Represents the Storage of the Colress chatbot.
 */
public final class Storage {
    private final File taskFile;
    private FileWriter writer;

    /**
     * Constructor for the Storage class.
     *
     * @param filePath A string representing the relative filepath for the text file containing the tasks.
     */
    public Storage(String filePath) {
        this.taskFile = new File(filePath);
    }
    private LocalDate readDate(String date) throws FileCorruptedException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new FileCorruptedException();
        }
    }

    private LocalTime readTime(String time) throws FileCorruptedException {
        try {
            return LocalTime.parse(time);
        } catch (DateTimeParseException e) {
            throw new FileCorruptedException();
        }
    }

    private void repopulateTasks(TaskList taskList) throws FileCorruptedException, FileNotFoundException {
        Scanner reader = new Scanner(taskFile);
        String[] strings;
        String currLine;

        while (reader.hasNextLine()) {
            currLine = reader.nextLine();
            strings = currLine.split(" \\| ");
            boolean isChecked = isChecked(strings);
            addTaskToList(taskList, strings, isChecked, TaskType.valueOf(strings[1]));
        }
    }

    private boolean isChecked(String[] arr) throws FileCorruptedException {
        if (Objects.equals(arr[0], "[X]")) {
            return true;
        } else if (Objects.equals(arr[0], "[ ]")) {
            return false;
        } else {
            throw new FileCorruptedException();
        }
    }

    private void addTaskToList(TaskList taskList, String[] arr, boolean isChecked, TaskType taskType)
            throws FileCorruptedException {
        switch (taskType) {
        case TODO:
            taskList.addTask(new ToDo(arr[2], isChecked));
            break;
        case DEADLINE:
            LocalDate deadline = readDate(arr[3]);

            taskList.addTask(new Deadline(arr[2], deadline, isChecked));
            break;
        case EVENT:
            LocalDate eventDate = readDate(arr[3]);
            String[] times = arr[4].split(" to ");
            LocalTime from = readTime(times[0]);
            LocalTime to = readTime(times[1]);

            taskList.addTask(new Event(arr[2], eventDate, from, to, isChecked));
            break;
        default:
            throw new FileCorruptedException();
        }
    }

    /**
     * Facilitates loading the task from the file to the provided TaskList object.
     * The method throws a FileCorruptedException if there are error reading the file.
     */
    public void loadTasks(TaskList taskList) throws IOException, FileCorruptedException {
        taskFile.createNewFile();
        repopulateTasks(taskList);
    }

    private void initialiseFileWriter() throws IOException {
        writer = new FileWriter(taskFile, false);
    }

    /**
     * Facilitates writing tasks from the provided TaskList object to the text file.
     */
    public void writeToTaskFile(TaskList taskList) throws IOException {
        String result = taskList.stream().map(Task::toTextFile).reduce("", (res, next) -> res + next);
        initialiseFileWriter();
        writer.write(result);
        writer.close();
    }
}
