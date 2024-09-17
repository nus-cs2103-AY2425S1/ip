package stelle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import stelle.exception.StorageWriteException;
import stelle.exception.TaskException;
import stelle.exception.TaskWithoutTypeException;
import stelle.exception.WrongDateFormatException;
import stelle.task.Deadline;
import stelle.task.Event;
import stelle.task.Task;
import stelle.task.ToDo;

/**
 * Represents a stelle.Storage object that handles writing and reading tasks from a local file.
 * @author Lee Ze Hao (A0276123J)
 */

public class Storage {
    private final String filePath;

    private static final String DATE_TIME_INPUT_FORMATTER_PATTERN = "yyyy-MM-dd HH:mm";
    private static final String DATE_TIME_OUTPUT_FORMATTER_PATTERN = "d LLLL yyyy HH:mm";
    private static final DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter
            .ofPattern(DATE_TIME_INPUT_FORMATTER_PATTERN);
    private static final DateTimeFormatter dateTimeOutputFormatter = DateTimeFormatter
            .ofPattern(DATE_TIME_OUTPUT_FORMATTER_PATTERN);

    /**
     * Creates a new stelle.Storage object.
     * @param filePath The file path data is stored to and read from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Takes a list of tasks and writes them to a text file at the filePath given during object creation.
     * @param taskList The list of tasks to be written to the file.
     */
    public void writeTasksToFile(ArrayList<Task> taskList) throws TaskException {
        File file = new File(this.filePath);
        file.getParentFile().mkdirs();

        FileWriter fw;
        try {
            file.createNewFile();
            fw = new FileWriter(this.filePath);
        } catch (IOException e) {
            throw new StorageWriteException();
        }

        String textToWrite = "";

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            Task.TaskType taskType = task.getTaskType();
            String isDoneMark = task.getIsDone() ? "1" : "0";

            switch (taskType) {
            case TODO:
                textToWrite = textToWrite + "T | " + isDoneMark + " | " + task.getTaskName() + "\n";
                break;
            case DEADLINE:
                // Note: taskType only set to DEADLINE when stelle.task.Deadline class is created,
                // and is final, so this is safe
                Deadline deadline = (Deadline) task;
                textToWrite = textToWrite + "D | " + isDoneMark + " | " + deadline.getTaskName()
                        + " | " + deadline.getByTime() + "\n";
                break;
            case EVENT:
                // Note: taskType only set to EVENT when stelle.task.Event class is created,
                // and is final, so this is safe
                Event event = (Event) task;
                textToWrite = textToWrite + "E | " + isDoneMark + " | " + event.getTaskName()
                        + " | " + event.getFromTime() + "to" + event.getToTime() + "\n";
                break;
            default:
                throw new TaskWithoutTypeException();
            }
        }

        try {
            fw.write(textToWrite);
            fw.close();
        } catch (IOException e) {
            throw new StorageWriteException();
        }
    }

    /**
     * Reads a list of tasks from a text file at the filePath given during object creation.
     * @return ArrayList[stelle.task.Task] List of tasks stored in the text file.
     */
    public ArrayList<Task> readTasksFromFile() throws FileNotFoundException, TaskWithoutTypeException {
        ArrayList<Task> taskList = new ArrayList<Task>();

        File f = new File(this.filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String taskString = s.nextLine();
            if (taskString.isEmpty()) {
                continue;
            }

            Task.TaskType taskType;
            switch (taskString.split(" \\| ", 0)[0]) {
            case "T":
                taskType = Task.TaskType.TODO;
                break;
            case "D":
                taskType = Task.TaskType.DEADLINE;
                break;
            case "E":
                taskType = Task.TaskType.EVENT;
                break;
            default:
                throw new TaskWithoutTypeException();
            }

            boolean taskIsDone = taskString.split(" \\| ", 0)[1].equals("1");

            String taskName = taskString.split(" \\| ", 0)[2];

            switch (taskType) {
            case TODO:
                ToDo toDoTask = new ToDo(taskName);
                if (taskIsDone) {
                    toDoTask.mark();
                } else {
                    toDoTask.unmark();
                }
                taskList.add(toDoTask);
                break;
            case DEADLINE:
                String byString = taskString.split(" \\| ")[3];

                // parse date
                LocalDateTime by;
                try {
                    by = LocalDateTime.parse(byString.strip(), dateTimeInputFormatter);
                } catch (Exception e) {
                    throw new WrongDateFormatException();
                }
                Deadline deadlineTask = new Deadline(taskName, by);
                if (taskIsDone) {
                    deadlineTask.mark();
                } else {
                    deadlineTask.unmark();
                }
                taskList.add(deadlineTask);
                break;
            case EVENT:
                String dates = taskString.split(" \\| ")[3];
                String fromDate = dates.split("to")[0];
                String toDate = dates.split("to")[1];
                // parse dates
                LocalDateTime from;
                LocalDateTime to;
                try {
                    from = LocalDateTime.parse(fromDate.strip(), dateTimeInputFormatter);
                    to = LocalDateTime.parse(toDate.strip(), dateTimeInputFormatter);
                } catch (Exception e) {
                    throw new WrongDateFormatException();
                }

                Event eventTask = new Event(taskName, from, to);
                if (taskIsDone) {
                    eventTask.mark();
                } else {
                    eventTask.unmark();
                }
                taskList.add(eventTask);
                break;
            default:
                throw new TaskWithoutTypeException();
            }
        }

        return taskList;
    }
}
