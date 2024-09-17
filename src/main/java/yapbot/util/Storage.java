package yapbot.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import yapbot.exceptions.TaskParseException;
import yapbot.exceptions.YapBotException;
import yapbot.tasks.Deadline;
import yapbot.tasks.Event;
import yapbot.tasks.Task;
import yapbot.tasks.ToDo;


/**
 * Handles all file interactions for YapBot.
 */
public class Storage {
    private File file;
    private String filepath;

    /**
     * Returns a Storage instance.
     * The actual file is not automatically created together with the instance.
     *
     * @param filepath Location where tasks are saved to.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
        this.file = new File(filepath);
    }

    /**
     * Parses the file to return the tasks stored on it.
     * If the file is empty, an empty ArrayList is returned.
     * If the file is corrupted or parsing fails, data on the file is dumped.
     *
     * @return An ArrayList of tasks parsed from the file.
     * @throws YapBotException If the file cannot be parsed or the file cannot be created.
     */
    public ArrayList<Task> load() throws YapBotException {
        Scanner s = null;
        try {

            // Creates the file if it does not exist
            if (!file.exists()) {
                if (file.getParentFile() != null) {
                    file.getParentFile().mkdirs();
                }

                file.createNewFile();
            }

            s = new Scanner(file);
            ArrayList<Task> result = new ArrayList<>();

            while (s.hasNext()) {
                String[] taskData = s.nextLine().split("/");
                String taskType = taskData[0];

                switch (taskType) {
                case "T": {
                    Task task = this.generateToDo(taskData);
                    result.add(task);
                    break;
                }

                case "D": {
                    Task task = this.generateDeadline(taskData);
                    result.add(task);
                    break;
                }

                case "E": {
                    Task task = this.generateEvent(taskData);
                    result.add(task);
                    break;
                }

                // Handles the case where the tasktype may not exist due to file corruption
                default: {
                    throw new TaskParseException("Corrupted File Found.");
                }

                }

            }
            s.close();
            return result;
        } catch (IOException e) {
            throw new YapBotException("Error, save file could not be created."
                    + "\nYour tasks from this session will not be saved.");
        } catch (NumberFormatException | DateTimeParseException | TaskParseException e) {
            // Covers parsing errors due to file corruption
            s.close();

            this.file.delete();

            try {
                this.file.createNewFile();
            } catch (IOException ignored) {
                // IOException would have been thrown already if the file cannot be created.
            }

            throw new YapBotException("Save data detected...load failed.\nCorrupted data found."
                    + "\nYapBot will execute without prior data.");
        }
    }

    private Task generateToDo(String[] taskData) throws TaskParseException, YapBotException, NumberFormatException {
        if (taskData.length < 3) {
            throw new TaskParseException("Corrupted File Found.");
        }

        int isDone = Integer.parseInt(taskData[1]);
        String taskDetails = taskData[2];

        if (isDone == 1) {
            return new ToDo(taskDetails, true);
        } else {
            return new ToDo(taskDetails);
        }
    }

    private Task generateDeadline(String[] taskData) throws TaskParseException, YapBotException, DateTimeParseException,
            NumberFormatException {

        if (taskData.length < 4) {
            throw new TaskParseException("Corrupted File Found.");
        }

        int isDone = Integer.parseInt(taskData[1]);
        String taskDetails = taskData[2];
        LocalDateTime deadline = LocalDateTime.parse(taskData[3]);

        if (isDone == 1) {
            return new Deadline(taskDetails, deadline, true);
        } else {
            return new Deadline(taskDetails, deadline, false);
        }
    }

    private Task generateEvent(String[] taskData) throws TaskParseException, YapBotException,
            NumberFormatException {

        if (taskData.length < 5) {
            throw new TaskParseException("Corrupted File Found.");
        }

        int isDone = Integer.parseInt(taskData[1]);
        String taskDetails = taskData[2];
        LocalDateTime from = LocalDateTime.parse(taskData[3]);
        LocalDateTime to = LocalDateTime.parse(taskData[4]);

        if (isDone == 1) {
            return new Event(taskDetails, from, to, true);
        } else {
            return new Event(taskDetails, from, to, false);
        }
    }

    /**
     * Saves tasks onto the file.
     *
     * @param saveableTasks String representation of the tasks to be saved.
     * @return true if tasks are saved successfully.
     * @throws IOException If tasks cannot be saved.
     */
    public boolean save(String saveableTasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filepath);
        fileWriter.write(saveableTasks);
        fileWriter.close();

        return true;
    }
}
