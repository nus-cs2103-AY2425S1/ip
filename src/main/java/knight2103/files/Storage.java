package knight2103.files;

import knight2103.Pair;
import knight2103.tasks.TaskList;
import knight2103.tasks.Task;
import knight2103.tasks.TodoTask;
import knight2103.tasks.DeadlineTask;
import knight2103.tasks.EventTask;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Storage {
    private final File taskFile;

    public Storage(String filePath) {
        this.taskFile = new File(filePath);
    }


    /**
     * Appends the tasks added to the storage file. When the bot's list of tasks
     * is changed due to the addition of tasks such as Todo, Deadline and Event,
     * these changes are reflected in the storage file.
     *
     * @param taskToSave The added task to be saved to the storage file.
     */
    public void saveToFile(Task taskToSave) throws IOException {
        FileWriter tasksWriter = new FileWriter(this.taskFile, true);
        tasksWriter.write("\n" + taskToSave.toStringInFile());
        tasksWriter.close();
    }

    /**
     * Updates by overwriting the entire list of tasks in the storage file.
     * When the task in bot's list of task is modified such as mark as done,
     * unmark as done or deleted, these modifications are captured in the
     * storage file as well. The newly updated list of task in the bot will be
     * saved into the storage file.
     *
     * @param tasks The newly updated tasks to be saved into the Storage File.
     */
    public void saveToFile(TaskList tasks) throws IOException {
        FileWriter tasksWriter = new FileWriter(this.taskFile, false);
        tasksWriter.write(formatToFileList(tasks));
        tasksWriter.close();
    }

    private String formatToFileList(TaskList tasks) {
        String stringToWrite = "";
        for (int i = 0; i < tasks.getSize(); i++) { // IndexOutOfBounds possibility
            stringToWrite += tasks.getTask(i).toStringInFile() + "\n";
        }
        return stringToWrite;
    }

    /**
     * Returns ArrayList<Task>. Reads the file that stores all the tasks and
     * load them into an ArrayList<Task> to be stored in the bot's list of tasks.
     *
     * @return The ArrayList of task that can be stored in the bot's list of tasks.
     */
    public Pair<ArrayList<Task>, String> loadFileContents() throws FileNotFoundException { // Exception handling in Knight2103.java
        ArrayList<Task> tasks = new ArrayList<Task>();
        String errorMessage = "";
        int lineInFileCount = 0; // not item count, because .txt file can see line number easily
        Scanner scanner = new Scanner(this.taskFile);
        while (scanner.hasNextLine()) {
            lineInFileCount++;
            String lineInFocus = scanner.nextLine();
            if (lineInFocus.isEmpty()) {
                continue;
            }
            Pair<Optional<Task>, String> taskAndErrorMsgPair = convertLineToTask(lineInFocus);
            taskAndErrorMsgPair.getFirstItem().ifPresent(item -> tasks.add(item));
            if (!taskAndErrorMsgPair.getSecondItem().isEmpty()) {
                errorMessage += String.format("\nFile line %d - %s",
                        lineInFileCount, taskAndErrorMsgPair.getSecondItem());
            }
        }
        return new Pair<ArrayList<Task>, String>(tasks, errorMessage);
    }

    private Pair<Optional<Task>, String> convertLineToTask(String lineInFile) {
        String[] lineArray = lineInFile.split(" \\| ");
        String errorMessage = "";
        Task taskToAdd = null;

        final int TASK_TYPE_INDEX = 0;
        final int IS_TASK_MARKED_INDEX = 1;
        final int TASK_DESCRIPTION_INDEX = 2;
        final int DEADLINE_INDEX = 3;
        final int EVENT_START_TIME_INDEX = 3;
        final int EVENT_END_TIME_INDEX = 4;

        final int EXPECTED_TODO_ARRAY_LENGTH = 3;
        final int EXPECTED_DEADLINE_ARRAY_LENGTH = 4;
        final int EXPECTED_EVENT_ARRAY_LENGTH = 5;

        final String TASK_UNMARKED_STATUS = "0";
        final String TASK_MARKED_STATUS = "1";

        try {
            switch (lineArray[TASK_TYPE_INDEX]) {
            case "T":
                if (lineArray.length != EXPECTED_TODO_ARRAY_LENGTH) {
                    throw new InvalidFileContentsException(
                            "Number of columns mismatch. 3 columns for Todo task");
                }
                taskToAdd = new TodoTask(lineArray[TASK_DESCRIPTION_INDEX]);
                break;
            case "D":
                if (lineArray.length != EXPECTED_DEADLINE_ARRAY_LENGTH) {
                    throw new InvalidFileContentsException(
                            "Number of columns mismatch. 4 columns for Deadline task");
                }
                try {
                    taskToAdd = new DeadlineTask(
                            lineArray[TASK_DESCRIPTION_INDEX],
                            lineArray[DEADLINE_INDEX]);
                } catch (DateTimeParseException e) {
                    errorMessage += "\nDate format is wrong in the file contents."
                            + " For Deadline task, it should be yyyy-MM-dd format.";
                }
                break;
            case "E":
                if (lineArray.length != EXPECTED_EVENT_ARRAY_LENGTH) {
                    throw new InvalidFileContentsException(
                            "Number of columns mismatch. 5 columns for Event task");
                }
                try {
                    taskToAdd = new EventTask(
                            lineArray[TASK_DESCRIPTION_INDEX],
                            lineArray[EVENT_START_TIME_INDEX],
                            lineArray[EVENT_END_TIME_INDEX]);
                } catch (DateTimeParseException e) {
                    errorMessage += "\nDate & Time format is wrong in the file contents."
                            + " For Events task, it should be yyyy-MM-ddThh:mm format";
                }
                break;
            default:
                throw new InvalidFileContentsException("Only T, E, D accepted but others found in 1st column");
            }

            // need to assert here that taskToAdd won't be null
            if (lineArray[IS_TASK_MARKED_INDEX].equals(TASK_MARKED_STATUS)) {
                taskToAdd.markDone();
            } else if (!lineArray[IS_TASK_MARKED_INDEX].equals(TASK_UNMARKED_STATUS)) { // neither 0 nor 1
                throw new InvalidFileContentsException("the value of the 2nd column should only be 0 or 1");
            }
            return new Pair<Optional<Task>, String>(Optional.of(taskToAdd), errorMessage);
        } catch (InvalidFileContentsException e) {
            errorMessage += e.getMessage();
        }
        return new Pair<Optional<Task>, String>(Optional.empty(), "Errors found: " + errorMessage);
    }
}