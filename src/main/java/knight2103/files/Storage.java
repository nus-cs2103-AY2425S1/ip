package knight2103.files;

import knight2103.tasks.TaskList;
import knight2103.tasks.Task;
import knight2103.tasks.TodoTask;
import knight2103.tasks.DeadlineTask;
import knight2103.tasks.EventTask;
import knight2103.tasks.TaskType;
import knight2103.Pair;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Enables storing of a text file which indicates a list of tasks.
 */
public class Storage {
    private final File taskFile;

    /**
     * Constructs a Storage object which contains a text file that
     * stores the list of tasks on computer hard disk.
     *
     * @param filePath the relative path of the location of the file that
     * stores the list of tasks in the computer hard disk.
     */
    public Storage(String filePath) {
        this.taskFile = new File(filePath);
    }


    /**
     * Appends the tasks added to the storage text file. When the bot's list of tasks
     * changes due to the addition of tasks such as TodoTask, DeadlineTask and EventTask objects,
     * these changes are reflected in the storage file.
     *
     * @param taskToSave The added task to be saved to the storage file.
     * @throws IOException If the FileWriter cannot be instantiated.
     */
    public void saveToFile(Task taskToSave) throws IOException {
        FileWriter tasksWriter = new FileWriter(this.taskFile, true);
        tasksWriter.write("\n" + taskToSave.toStringInFile());
        tasksWriter.close();
    }

    /**
     * Updates by overwriting the entire list of tasks in the storage file.
     * When the task in bot's list of task is modified such as being marked as done,
     * unmarked as done or deleted, these modifications are captured in the
     * storage file as well. The newly updated list of task in the bot will be
     * saved into the storage file.
     *
     * @param tasks The newly updated tasks to be saved into the storage File.
     * @throws IOException If the FileWriter cannot be instantiated.
     */
    public void saveToFile(TaskList tasks) throws IOException {
        FileWriter tasksWriter = new FileWriter(this.taskFile, false);
        tasksWriter.write(tasks.toStringInFile());
        tasksWriter.close();
    }


    /**
     * Returns a pair of values: the list of tasks the function is able to generate and any error messages
     * that came during the generation.
     * Reads the file that stores all the tasks and load them into an ArrayList<Task>. This ArrayList<Task>
     * instance is needed to be stored in the bot's list of tasks, which is a TaskList instance. If there
     * are any errors in the line formatting in the storage files, these error messages generated are
     * accumulated and stored as well. The function will not be terminated prematurely due to an error in
     * the formatting of file contents, it will fully process the file contents and stores all possible
     * Task objects so long as the task in the file is formatted correctly. Hence, if all the file contents
     * are formatted wrongly, an empty ArrayList<Task> object will be instantiated to be used in the bot's
     * TaskList instance, containing all the error messages regarding each line of file contents. Likewise,
     * if only some of the file contents are formatted wrongly, error messages regarding those and an
     * ArrayList<<Task> that is successfully generated are returned. Blank empty lines in the file storage
     * are not ignored and are not detected as formatting errors.
     *
     * @return A Pair object containing an ArrayList of valid tasks objects tobe stored in the
     * bot's list of tasks, and any error messages that came when generating the ArrayList of tasks.
     * @throws FileNotFoundException If the storage file does not exist or cannot be located based on the
     * location address given.
     */
    public Pair<ArrayList<Task>, String> loadFileContents() throws FileNotFoundException { // Exception
        // handling in Knight2103.java
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
                errorMessage += String.format("\nFile line %d - %s", lineInFileCount,
                        taskAndErrorMsgPair.getSecondItem());
            }
        }
        return new Pair<ArrayList<Task>, String>(tasks, errorMessage);
    }

    private Pair<Optional<Task>, String> convertLineToTask(String lineInFile) {
        String[] lineArray = lineInFile.split(" \\| ");
        String errorMessage = "";
        Task taskToAdd = null;

        try {
            TaskType taskType = checkTaskType(lineArray);
            checkArrayLength(taskType, lineArray.length);
            taskToAdd = addTask(taskType, lineArray);
            checkMarkStatus(lineArray, taskToAdd);
            return new Pair<Optional<Task>, String>(Optional.of(taskToAdd), "");
        } catch (InvalidFileContentsException e) {
            errorMessage += e.getMessage();
        }
        return new Pair<Optional<Task>, String>(Optional.empty(), "Errors found: " + errorMessage);
    }

    private TaskType checkTaskType(String[] lineArray) throws InvalidFileContentsException {
        final int TASK_TYPE_INDEX = 0;
        TaskType taskType;

        switch (lineArray[TASK_TYPE_INDEX]) {
        case "T" -> taskType = TaskType.TODO;
        case "D" -> taskType = TaskType.DEADLINE;
        case "E" -> taskType = TaskType.EVENT;
        default -> throw new InvalidFileContentsException("Only T, E, D accepted but others found in 1st " +
                "column");
        }
        return taskType;
    }

    private void checkArrayLength(TaskType taskType, int lineArrayLength) throws InvalidFileContentsException {
        assert (taskType == TaskType.TODO || taskType == TaskType.DEADLINE || taskType == TaskType.EVENT);

        final int EXPECTED_TODO_ARRAY_LENGTH = 3;
        final int EXPECTED_DEADLINE_ARRAY_LENGTH = 4;
        final int EXPECTED_EVENT_ARRAY_LENGTH = 5;
        final int ERROR_ARRAY_LENGTH = -1;

        int expectedArrayLength;
        String taskTypeName;

        switch (taskType) {
        case TODO:
            expectedArrayLength = EXPECTED_TODO_ARRAY_LENGTH;
            taskTypeName = "Todo";
            break;
        case DEADLINE:
            expectedArrayLength = EXPECTED_DEADLINE_ARRAY_LENGTH;
            taskTypeName = "Deadline";
            break;
        case EVENT:
            expectedArrayLength = EXPECTED_EVENT_ARRAY_LENGTH;
            taskTypeName = "Event";
            break;
        default:
            expectedArrayLength = ERROR_ARRAY_LENGTH;
            taskTypeName = "ERROR";
        }

        assert expectedArrayLength != ERROR_ARRAY_LENGTH;

        if (lineArrayLength != expectedArrayLength) {
            throw new InvalidFileContentsException(
                    String.format("Number of columns mismatch. %d columns for %s expected",
                            expectedArrayLength, taskTypeName));
        }
    }

    private Task addTask(TaskType taskType, String[] lineArray) throws InvalidFileContentsException {
        assert (taskType == TaskType.TODO || taskType == TaskType.DEADLINE || taskType == TaskType.EVENT);

        final int TASK_DESCRIPTION_INDEX = 2;
        final int DEADLINE_INDEX = 3;
        final int EVENT_START_TIME_INDEX = 3;
        final int EVENT_END_TIME_INDEX = 4;

        Task taskToAdd = null;

        try {
            switch (taskType) {
            case TODO -> taskToAdd = new TodoTask(lineArray[TASK_DESCRIPTION_INDEX]);
            case DEADLINE ->
                    taskToAdd = new DeadlineTask(lineArray[TASK_DESCRIPTION_INDEX], lineArray[DEADLINE_INDEX]);
            case EVENT ->
                    taskToAdd = new EventTask(lineArray[TASK_DESCRIPTION_INDEX],
                            lineArray[EVENT_START_TIME_INDEX], lineArray[EVENT_END_TIME_INDEX]);
            default -> {
                assert taskToAdd != null;
            }
            }
        } catch (DateTimeParseException e) {
            throw new InvalidFileContentsException("\nDate format is wrong in the file contents."
                    + " For Deadline task, it should be yyyy-MM-dd format."
                    + " For Events task, it should be yyyy-MM-ddThh:mm format");
        }
        return taskToAdd;
    }

    private void checkMarkStatus(String[] lineArray, Task taskToAdd) throws InvalidFileContentsException {
        final int IS_TASK_MARKED_INDEX = 1;

        final String TASK_UNMARKED_STATUS = "0";
        final String TASK_MARKED_STATUS = "1";

        if (lineArray[IS_TASK_MARKED_INDEX].equals(TASK_MARKED_STATUS)) {
            taskToAdd.markDone();
        } else if (!lineArray[IS_TASK_MARKED_INDEX].equals(TASK_UNMARKED_STATUS)) { // invalid status
            throw new InvalidFileContentsException(
                    String.format("the value of the 2nd column should only be %s or %s",
                            TASK_MARKED_STATUS, TASK_UNMARKED_STATUS));
        }
    }
}