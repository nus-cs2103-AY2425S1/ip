package zbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import zbot.task.Deadline;
import zbot.task.Event;
import zbot.task.Task;
import zbot.task.ToDo;

/**
 * Represents the storage of tasks in a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath Path of the file to store tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates the file if it does not exist.
     * <p>
     * If the file does not exist, the folder containing the file will be created as
     * well.
     * <p>
     * If the file already exists, nothing will be done.
     */
    public void createFileIfNotExists() {
        File file = new File(filePath);

        if (file.exists()) {
            return;
        }

        String folderPath = filePath.substring(0, filePath.lastIndexOf("/"));
        File folder = new File(folderPath);
        folder.mkdirs();

        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("I/O error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Writes text to the file.
     *
     * Overwrites the file if it already exists.
     *
     * @param text Text to write to the file.
     */
    public void writeToTextFile(String text) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            System.out.println("I/O error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Saves tasks to file.
     *
     * The tasks are saved in the following format:
     * [task type],[status],[description],[task-specific details]
     *
     * @param tasks Tasks to save.
     */
    public void save(TaskList tasks) {
        StringBuilder sb = new StringBuilder();

        for (Task task : tasks) {
            char taskType = task instanceof ToDo ? 'T'
                    : task instanceof Deadline ? 'D'
                            : task instanceof Event ? 'E' : null;
            int taskStatusAsInt = task.getStatus() ? 1 : 0;
            sb.append(taskType).append(",").append(taskStatusAsInt)
                    .append(",").append(task.getDescription());

            if (taskType == 'D') {
                sb.append(",").append(Parser.formatDateTimeToInput(((Deadline) task).getDueDate()))
                        .append("\n");
            } else if (taskType == 'E') {
                sb.append(",").append(Parser.formatDateTimeToInput(((Event) task).getStartDate()))
                        .append(",").append(Parser.formatDateTimeToInput(((Event) task).getEndDate()))
                        .append("\n");
            } else if (taskType == 'T') {
                sb.append("\n");
            }

            assert taskType == 'T' || taskType == 'D' || taskType == 'E';
        }

        writeToTextFile(sb.toString());
    }

    /**
     * Loads tasks from file.
     *
     * The tasks are loaded in the following format:
     * [task type],[status],[description],[task-specific details]
     *
     * @return ArrayList of tasks loaded from file.
     * @throws ZBotException If no saved data is found.
     */
    public ArrayList<Task> load() throws ZBotException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File(filePath));

            while (sc.hasNextLine()) {
                String[] taskComponents = sc.nextLine().split(",");

                Task task = null;
                if (taskComponents[0].equals("T")) {
                    assert taskComponents.length == 3;
                    task = new ToDo(taskComponents[2]);
                } else if (taskComponents[0].equals("D")) {
                    assert taskComponents.length == 4;
                    task = new Deadline(taskComponents[2], Parser.parseDateTime(taskComponents[3]));
                } else if (taskComponents[0].equals("E")) {
                    assert taskComponents.length == 5;
                    task = new Event(taskComponents[2],
                            Parser.parseDateTime(taskComponents[3]),
                            Parser.parseDateTime(taskComponents[4]));
                }

                assert task != null;

                if (taskComponents[1].equals("1")) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new ZBotException("No saved data found.");
        } catch (DateTimeParseException e) {
            System.out.println(
                    "Please enter a valid date and time format (dd/MM/yyyy HHmm, dd/MM/yyyy)!\n");
        }
        return tasks;
    }

}
