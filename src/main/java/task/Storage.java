package task;

import command.DukeException;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to parse task data from a file. <br/>
 *
 * Example of data:
 * <pre>
 * T 1 attend mission brief
 * D 0 2024-08-24+23:59 save the world
 * E 1 2020-01-01+00:00 2020-01-01+23:59 skynet meeting
 * E 0 2024-09-01+00:00 2024-09-02+23:59 terminate aliens
 * </pre>
 */
public final class Storage {

    private static final String DATA_FILE_DIR = "./data";

    private static final String DATA_FILE_PATH = "terminator.txt";

    public Storage() {

    }

    public void loadDataFromFile(TaskList taskList) throws DukeException {
        // Create directory if it does not exist
        System.out.println("Checking if directory exists...");
        File baseDir = new File(DATA_FILE_DIR);
        if (!baseDir.exists()) {
            baseDir.mkdir();
            System.out.println("Directory created successfully.");
        } else {
            System.out.println("Directory already exists.");
        }

        // Create file if it does not exist
        System.out.println("Checking if data file exists...");
        File dataFile = new File(DATA_FILE_DIR + "/" + DATA_FILE_PATH);
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                throw new DukeException("[Fatal] Failed to create data file");
            }
        } else {
            System.out.println("File already exists");
        }

        // Read file data into task list
        parseFileData(dataFile, taskList);
    }

    private void parseFileData(File f, TaskList taskList) {
        System.out.println("Parsing file data...");

        // Create temporary list to store task objects
        ArrayList<Task> tempTaskList = new ArrayList<>();
        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNext()) {
                String data = scanner.nextLine();

                // Handle empty lines
                if (data.isEmpty()) {
                    continue;
                }

                // Parse task type
                TaskType taskType =
                        switch (data.charAt(0)) {
                        case 'T' -> TaskType.TODO;
                        case 'E' -> TaskType.EVENT;
                        case 'D' -> TaskType.DEADLINE;
                        default -> TaskType.UNKNOWN;
                };

                if (taskType == TaskType.UNKNOWN) {
                    System.out.println("Invalid data format encountered. Skipping to next line...");
                    continue;
                }

                // Parse task completion status
                int completionStatus = Integer.parseInt(String.valueOf(data.charAt(2)));
                if (completionStatus != 0 && completionStatus != 1) {
                    System.out.println("Invalid data format encountered. Skipping to next line...");
                    continue;
                }

                // Parse task description
                String desc = data.substring(4);
                Task task =
                        switch(taskType) {
                        case TODO -> parseTodo(desc);
                        case EVENT -> parseEvent(desc);
                        case DEADLINE -> parseDeadline(desc);
                        // We shouldn't reach this condition since we already checked the TaskType beforehand
                        default -> null;
                };

                // Update task status
                if (completionStatus == 0) {
                    task.markAsIncomplete();
                } else {
                    task.markAsDone();
                }

                tempTaskList.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (NumberFormatException nfe) {
            System.out.println("Error in parsing completion status: " + nfe.getMessage());
        }

        // Copy temporary list to TaskList
        taskList.addAll(tempTaskList);
    }

    /**
     * Parses the description of a TodoTask and returns the Task object.
     *
     * <br/><br/>
     *
     * Example of expected description format:
     * <pre>
     *     terminate aliens
     * </pre>
     *
     * @param desc The description of the task.
     * @return An instance of TodoTask.
     */
    private Task parseTodo(String desc) {
        return new TodoTask(desc);
    }

    /**
     * Parses the description of an EventTask and returns the Task object.
     *
     * <br/><br/>
     *
     * Example of expected description format:
     * <pre>
     *     2020-01-01+00:00 2020-01-01+23:59 skynet meeting
     *     2024-09-01+00:00 2024-09-02+23:59 terminate aliens
     * </pre>
     *
     * @param desc The description of the task.
     * @return An instance of EventTask.
     */
    private Task parseEvent(String desc) {
        String startDateTimeString = desc.substring(0, 16);
        String endDateTimeString = desc.substring(17, 33);
        String eventDesc = desc.substring(34);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd+HH:mm");
        LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeString, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeString, formatter);

        // Create EventTask instance and return it
        return new EventTask(eventDesc, startDateTime, endDateTime);
    }

    /**
     * Parses the description of an DeadlineTask and returns the Task object.
     *
     * <br/><br/>
     *
     * Example of expected description format:
     * <pre>
     *      2024-08-24+00:00 save the world
     *      2024-08-24+23:59 return to the past
     * </pre>
     *
     * @param desc The description of the task.
     * @return An instance of DeadlineTask.
     */
    private Task parseDeadline(String desc) {
        String byDateString = desc.substring(0, 16);
        String deadlineDesc = desc.substring(17);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd+HH:mm");
        LocalDateTime byDate = LocalDateTime.parse(byDateString, formatter);
        return new DeadlineTask(deadlineDesc, byDate);
    }

    public void writeToDisk(ArrayList<Task> taskList) throws DukeException {
        // Overwrite previous file if it exists
        try (BufferedWriter outputStream = new BufferedWriter(
                new FileWriter(DATA_FILE_DIR + "/" + DATA_FILE_PATH, false))) {

            // Write each task item to its own line
            for (int i = 0; i < taskList.size(); i++) {
                Task t = taskList.get(i);
                String taskData = t.getDataRepresentation();
                outputStream.write(taskData, 0, taskData.length());
                outputStream.newLine();
            }

            // Flush the output stream
            outputStream.flush();
        } catch (IOException e) {
            throw new DukeException("Failed to write to disk.");
        }
    }
}
