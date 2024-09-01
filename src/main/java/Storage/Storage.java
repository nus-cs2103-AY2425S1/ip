package Storage;

import Exceptions.BrockException;
import Tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "./src/main/java/saveFile.txt";
    private static final File SAVE_FILE = new File(FILE_PATH);

    private String removeCloseBracket(String target) {
        int length = target.length();
        // Substring from start to the second last index
        return target.substring(0, length - 1);
    }

    private String parseDate(String targetDate) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return LocalDate.parse(targetDate, dateFormatter)
                .toString();
    }

    private Task handleToDo(String taskBody) {
        return new ToDos(taskBody);
    }

    private Task handleDeadline(String taskBody) throws BrockException {
        String[] parts = taskBody.split("\\(by : ", 2);
        String description = parts[0];

        String dateTime = parts[1];
        String[] dateTimeParts = dateTime.split(", ");

        String dueDateString = dateTimeParts.length == 1
                ? parseDate(removeCloseBracket(dateTimeParts[0]))
                : parseDate(dateTimeParts[0]);
        String dueTimeString = dateTimeParts.length == 1
                ? ""
                : removeCloseBracket(dateTimeParts[1])
                .replace(":", "");

        if (dueTimeString.isEmpty()) {
            return new Deadlines(description, dueDateString);
        } else {
            return new Deadlines(description, dueDateString, dueTimeString);
        }
    }

    private Task handleEvent(String taskBody) throws BrockException {
        String[] parts = taskBody.split(" \\(from: ", 2);
        String description = parts[0];

        String dateTime = parts[1];
        String[] dateTimeParts = dateTime.split(" \\| ", 2);
        String startDateTime = dateTimeParts[0];
        String endDateTime = dateTimeParts[1];

        String[] startDateTimeParts = startDateTime.split(", ");
        String[] endDateTimeParts = endDateTime.substring(4).split(", ");

        String startDateString = parseDate(startDateTimeParts[0]);
        String startTimeString = startDateTimeParts.length == 1
                ? ""
                : startDateTimeParts[1].replace(":", "");
        String endDateString = endDateTimeParts.length == 1
                ? parseDate(removeCloseBracket(endDateTimeParts[0]))
                : parseDate(endDateTimeParts[0]);
        String endTimeString = endDateTimeParts.length == 1
                ? ""
                : removeCloseBracket(endDateTimeParts[1])
                .replace(":", "");

        if (startTimeString.isEmpty()) {
            return new Events(description, startDateString, endDateString);
        } else {
            return new Events(description, startDateString, startTimeString
                    , endDateString, endTimeString);
        }
    }

    private Task convertToTaskObject(String taskString) throws BrockException {
        // Split by ". "
        String[] taskComponents = taskString.split("\\. ", 2);

        // Remove the [<type>][<status>]
        String taskDetails = taskComponents[1];
        char taskType = taskDetails.charAt(1);
        String taskBody = taskDetails.substring(7);

        switch (taskType) {
        case 'T':
            return handleToDo(taskBody);
        case 'D':
            return handleDeadline(taskBody);
        case 'E':
            return handleEvent(taskBody);
        default:
            throw new BrockException("Unrecognized task type!");
        }
    }

    public ArrayList<Task> loadTasksFromFile() throws BrockException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Scanner s = new Scanner(SAVE_FILE);
            while (s.hasNext()) {
                String taskString = s.nextLine();
                Task task = convertToTaskObject(taskString);
                tasks.add(task);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new BrockException("Unable to find and read from save file!");
        }
    }

    public String[] createFile() throws IOException {
        StringBuilder dirResult = new StringBuilder();
        String dirStatus;
        boolean isDirectoryMissing = SAVE_FILE.getParentFile().mkdirs();
        if (!isDirectoryMissing) {
            dirStatus = "Parent directories already exists!";
        } else {
            dirStatus = "Parent directories successfully created!";
        }
        dirResult.append("Creating parent directories for save file...\n")
                .append(dirStatus);

        StringBuilder fileResult = new StringBuilder();
        String fileStatus;
        boolean isFileMissing = SAVE_FILE.createNewFile();
        if (!isFileMissing) {
            fileStatus = "Save file already exists!";
        } else {
            fileStatus = "Save file successfully created";
        }
        fileResult.append("Creating save file for tasks...\n")
                .append(fileStatus);

        return new String[] {dirResult.toString(), fileResult.toString()};
    }

    // Read from file with no exclusion
    public String readFromFile() throws BrockException {
        // Use SB as it is a faster way to append strings
        StringBuilder tasksString = new StringBuilder();
        try {
            Scanner s = new Scanner(SAVE_FILE);
            while (s.hasNext()) {
                tasksString.append(s.nextLine())
                        .append('\n');
            }
        } catch (FileNotFoundException e) {
            throw new BrockException("Unable to find and read from save file!");
        }

        if (!tasksString.isEmpty()) {
            // Remove last new line character
            // To prevent duplicates when displaying response
            tasksString.deleteCharAt(tasksString.length() - 1);
        }
        return tasksString.toString();
    }

    // Read from file with exclusion
    // Exclusion is the task number to be excluded (ie: deleted)
    public String readFromFile(int exclusion) throws BrockException {
        // Use SB as it is a faster way to append strings
        StringBuilder tasksString = new StringBuilder();
        try {
            Scanner s = new Scanner(SAVE_FILE);
            int count = 1;
            boolean hasSeenExclusion = false;
            while (s.hasNext()) {
                if (count == exclusion) {
                    s.nextLine();
                    count += 1;
                    hasSeenExclusion = true;
                    continue;
                }
                if (hasSeenExclusion) {
                    // s.next() reads: "<task number>."
                    // remove the ., modify the task number, append to string builder
                    String nextToken = s.next();
                    String taskNumber = nextToken.substring(0, nextToken.length() - 1);
                    int newTaskNumber = Integer.parseInt(taskNumber) - 1;
                    tasksString.append(newTaskNumber)
                            .append('.')
                            .append(s.nextLine());
                } else {
                    tasksString.append(s.nextLine())
                            .append('\n');
                }
                count += 1;
            }
        } catch (FileNotFoundException e) {
            throw new BrockException("Unable to find and read from save file!");
        }

        if (!tasksString.isEmpty()) {
            // Remove last new line character
            // To prevent duplicates when displaying response
            tasksString.deleteCharAt(tasksString.length() - 1);
        }
        return tasksString.toString();
    }

    public void writeToFile(String writeContent, boolean isAppendMode) throws BrockException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, isAppendMode);
            fw.write(writeContent);
            fw.close();
        } catch (IOException e) {
            throw new BrockException(e.getMessage());
        }
    }
}
